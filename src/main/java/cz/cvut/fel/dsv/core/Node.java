package cz.cvut.fel.dsv.core;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import cz.cvut.fel.dsv.core.command.ConsoleHandler;
import cz.cvut.fel.dsv.core.data.*;
import cz.cvut.fel.dsv.core.infrastructure.Config;
import cz.cvut.fel.dsv.utils.Director;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_GREEN_NODE;

public class Node {
    private static final Logger logger = DsvLogger.getLogger("NODE", ANSI_GREEN_NODE, Node.class);
    @Getter @Setter private String username;
    @Getter @Setter private String currentRoom;
    // Flag, which represents the knowledge:
    //      key == true => node is leader and in value is room which that node leads.
    //      key == false => node is not leader in value is null.
    @Setter private volatile DsvPair<Boolean, Room> isLeader;
    @Getter @Setter private Address address;
    @Getter @Setter private NodeState state;
    @Getter @Setter private DsvNeighbours dsvNeighbours;
    @Getter @Setter private boolean isVoting;
    private ManagedChannel managedChannelToLeader;
    private StreamObserver<generated.ChatMessage> receiveMessagesObserver;
    private static volatile Node INSTANCE;

    private Node() {
        isLeader = DsvPair.of(false, new Room.NullableRoom());
        state = NodeState.RELEASED;
        receiveMessagesObserver = new StreamObserverImpl();
    }

    public static Node getInstance() {
        if (INSTANCE != null) return INSTANCE;
        synchronized (Node.class) {
            if (INSTANCE == null) INSTANCE = new Node();
        }
        return INSTANCE;
    }

    private void updateChannelToLeader() {
        managedChannelToLeader = ManagedChannelBuilder
                .forAddress(dsvNeighbours.getLeader().getHostname(), dsvNeighbours.getLeader().getPort())
                .usePlaintext()
                .build();
    }

    private void joinRoom(Address joinAddress, generated.JoinRequest req) {
        var stub = generated.RemoteServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(joinAddress));
        var joinResponse = stub.joinRoom(req);
        var leaderAddressFromResponse = Utils.Mapper.remoteToAddress(joinResponse.getLeader());

        if (!joinResponse.getIsLeader()) {
            joinRoom(Utils.Mapper.remoteToAddress(joinResponse.getLeader()), req);
            return;
        }

        dsvNeighbours = Utils.Mapper.neighboursToDsvNeighbours(joinResponse.getNeighbours());
        dsvNeighbours.setLeader(leaderAddressFromResponse);

        if (leaderAddressFromResponse.equals(address)) { // Node created a room. Set properties.
            logger.log(Level.INFO, "Node created the room {0}", req.getRoomName());
            isLeader = DsvPair.of(true, new Room(address, req.getRoomName()));
        } else if (isLeader()) { // Delete data about rooms and leaders.
            logger.log(Level.INFO, "Joined room {0}", req.getRoomName());
            isLeader = DsvPair.of(false, new Room.NullableRoom());
            SharedData.clear();
        }

        currentRoom = req.getRoomName();
        updateChannelToLeader();
        preflight();
    }

    public void joinRoomViaLeader(String roomName, Integer delay) {
        assert dsvNeighbours.getLeader() != null;
        if (!Objects.equals(roomName, currentRoom))
            executeExit();

        logger.log(Level.INFO, "Joining room {0}", roomName);
        if (currentRoom == null || !(currentRoom.equals(roomName)))
            joinRoom(dsvNeighbours.getLeader(), Director.buildJoinReq(roomName, delay));
        else
            logger.log(Level.INFO, "You already in room {0}", roomName);
    }

    private void exitRoom() {
        logger.log(Level.INFO, "Exiting room {0}", currentRoom);
        try {
            generated.RemoteServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getLeader()))
                    .exitRoom(Utils.Mapper.nodeToRemote());
        } catch (StatusRuntimeException e) {
            startRepairTopology(address, dsvNeighbours.getLeader());
            makeElection(address);
            exitRoom();
        }
    }

    private void startRepairTopology(Address onNode, Address missing) {
        logger.log(Level.INFO, "Starting repair topology on {0}:{1} with missing node {2}:{3}",
                new Object[]{onNode.getHostname(), onNode.getPort(), missing.getHostname(), missing.getPort()});
        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(onNode))
                .repairTopology(Utils.Mapper.addressToRemote(missing));
    }

    public void startElectionWithDelay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        makeElection(address);
    }

    private void makeElection(Address onNode) {
        logger.log(Level.INFO, "Starting election on node {0}:{1}", new Object[]{onNode.getHostname(), onNode.getPort()});
        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(onNode))
                .election(Utils.Mapper.addressToRemote(new Address(Config.STUB_STRING, 0, -1)));
    }

    public void updateLeaderChannelAndObserver(Address address) {
        setVoting(false);
        dsvNeighbours.setLeader(address);
        updateChannelToLeader();
        receiveMessagesObserver = new StreamObserverImpl();
        preflight();
    }

    public void sendMessage(String msg) {
        var stub = generated.RemoteServiceGrpc.newFutureStub(managedChannelToLeader);
        ListenableFuture<generated.Empty> sendMessageResponse = stub.receiveMessage(Utils.Mapper.stringToMessage(Utils.Mapper.nodeToRemote(), msg));
        Futures.addCallback(sendMessageResponse, new FutureCallback<generated.Empty>() {
            @Override
            public void onSuccess(generated.Empty result) {
                // Do nothing
            }

            @Override
            public void onFailure(Throwable t) {
                startRepairTopology(address, dsvNeighbours.getLeader());
                makeElection(address);
            }
        }, DsvThreadPool.getInstance().getPool());
    }

    private void handleArgs(String[] args) {
        try {
            if (args.length <= 4) {
                this.username = args[0];
                this.address = new Address(InetAddress.getLocalHost().getHostAddress(), Integer.parseInt(args[1]));
                this.address.generateId();
                this.dsvNeighbours = new DsvNeighbours(this.address);
                ServerWrapper server = new ServerWrapper(address.getPort());
                DsvThreadPool.getInstance().execute(server);

                if (args.length == 2)
                    firstInTopology();
                else if (args.length == 4)
                    joinRoom(new Address(args[2], Integer.parseInt(args[3])), Director.buildJoinReq(Config.INITIAL_ROOM_NAME, 0));
            } else {
                System.err.println("Error while parsing args. Max number of args is 4.");
                System.exit(1);
            }

        } catch (Exception e) {
            logger.severe("Error while handling input args. Max. quantity of parameters is 4, Min. is 2.");
            logger.severe(e.getMessage());
        }
    }

    private void firstInTopology() {
        isLeader = DsvPair.of(true, new Room(address, Config.INITIAL_ROOM_NAME));
        currentRoom = Config.INITIAL_ROOM_NAME;
        SharedData.put(Config.INITIAL_ROOM_NAME, DsvPair.of(address, address));
        dsvNeighbours.setLeader(address);
        updateChannelToLeader();
        preflight();
    }

    public static void main(String[] args) {
        final Node node = getInstance();
        node.handleArgs(args);
        node.listen();
    }

    private void preflight() {
            logger.log(Level.INFO, "Sending preflight to {0}", dsvNeighbours.getLeader());
            generated.RemoteServiceGrpc.newStub(Utils.Skeleton.buildChannel(dsvNeighbours.getLeader()))
                    .preflight(Utils.Mapper.nodeToRemote(), receiveMessagesObserver);
    }

    public String getNodeListInCurrentRoom() {
        return Utils.handleAndReturn(empty ->
                        generated.RemoteServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getLeader()))
                                .receiveGetNodeListInCurrentRoomRequest(generated.Empty.getDefaultInstance())
                                .getMsg(),
                emergencyRepairAndElection()
        ).orElse("");
    }

    public String getRoomListInNetwork() {
        return Utils.handleAndReturn(empty ->
                        generated.RemoteServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getLeader()))
                                .receiveGetRoomListRequest(generated.Empty.getDefaultInstance())
                                .getMsg(),
                emergencyRepairAndElection()
        ).orElse("");
    }

    private UnaryOperator<Void> emergencyRepairAndElection() {
        // Use this method for handle leader's death
        return v -> {
            startRepairTopology(address, dsvNeighbours.getLeader());
            makeElection(address);
            return null;
        };
    }

    private void listen() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        DsvThreadPool.getInstance().execute(consoleHandler);
    }


    private boolean nodeWasLeader(Address address) {
        return dsvNeighbours.getLeader().equals(address);
    }

    public void executeExit() {
        exitRoom();
//        startRepairTopology(dsvNeighbours.getPrev(), address);
        if (nodeWasLeader(address)) {
//            logger.info("Was a leader. Remove this node from leaders tables");
            if (leadingRoomIsNotEmpty()) {
//                logger.info("Was a leader. Exited room is not empty. Start election");
//                makeElection(dsvNeighbours.getNext());
            }
        }
    }

    public boolean isLeader() {
        return isLeader.getKey();
    }

    public boolean leadingRoomIsNotEmpty() {
        return isLeader.getValue().getSize() != 1;
    }

    public Room getLeadingRoom() {
        return isLeader.getValue();
    }

    @Override
    public String toString() {
        return "[" + username + "]:" +
                "\n\t" + address.toString() +
                "\n\t[Leader]: " + dsvNeighbours.getLeader().toString() +
                "\n\t[Current room]: " + currentRoom +
                "\n\t[State]: " + state +
                "\n\t" + dsvNeighbours.toString() +
                "\n\t[Is Leader]: " + isLeader.getKey() + ":" + isLeader.getValue().getRoomName() +
                "\n\t[Rooms and leaders table]: Room name : Leader address" + SharedData.stringify();
    }

    public class StreamObserverImpl implements StreamObserver<generated.ChatMessage> {

        @Override
        public void onNext(generated.ChatMessage message) {
            System.out.println("\r[" + currentRoom + "] " + message.getRemote().getUsername() + ": " + message.getMsg());
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onCompleted() {

        }
    }
}