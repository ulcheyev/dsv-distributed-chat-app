package cz.cvut.fel.dsv.core;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import cz.cvut.fel.dsv.core.command.ConsoleHandler;
import cz.cvut.fel.dsv.core.data.*;
import cz.cvut.fel.dsv.core.infrastructure.Config;
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
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
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
    private ConsoleHandler consoleHandler;
    private ServerWrapper server;
    private ManagedChannel managedChannelToLeader;
    private StreamObserver<generated.ChatMessage> receiveMessagesObserver;
    private static volatile Node INSTANCE;

    public Node() {
        isLeader = DsvPair.of(false, new Room.NullableRoom());
        state = NodeState.RELEASED;
        init();
    }

    public static Node getInstance() {
        if (INSTANCE != null) return INSTANCE;
        synchronized (Node.class) {
            if (INSTANCE == null) INSTANCE = new Node();
        }
        return INSTANCE;
    }

    private void init() {
        receiveMessagesObserver = new StreamObserver<generated.ChatMessage>() {
            @Override
            public void onNext(generated.ChatMessage message) {
                System.out.println("\r[" + currentRoom + "] " + message.getRemote().getUsername() + ": " + message.getMsg());
            }

            @Override
            public void onError(Throwable throwable) {
                // todo
            }

            @Override
            public void onCompleted() {
                // todo
            }
        };
    }

    private void updateChannelToLeader() {
        managedChannelToLeader = ManagedChannelBuilder
                .forAddress(dsvNeighbours.getLeader().getHostname(), dsvNeighbours.getLeader().getPort())
                .usePlaintext()
                .build();
    }

    private void joinRoom(Address joinAddress, String roomName, Integer delay) {
        logger.info("Joining room " + roomName);
        if (currentRoom == null || !(currentRoom.equals(roomName))) {
            generated.JoinRequest req = generated.JoinRequest.newBuilder()
                    .setRoomName(roomName)
                    .setRemote(Utils.Mapper.nodeToRemote())
                    .setDelay(delay)
                    .build();
            executeJoining(joinAddress, req);
        } else {
            logger.info("You already in room " + roomName);
        }
    }

    private void executeJoining(Address joinAddress, generated.JoinRequest req) {
        var stub = generated.RemoteServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(joinAddress));
        var joinResponse = stub.joinRoom(req);

        if (!joinResponse.getIsLeader()) {
            executeJoining(Utils.Mapper.remoteToAddress(joinResponse.getLeader()), req);
            return;
        }

        var leaderAddressFromResponse = Utils.Mapper.remoteToAddress(joinResponse.getLeader());
        dsvNeighbours = Utils.Mapper.neighboursToDsvNeighbours(joinResponse.getNeighbours());
        dsvNeighbours.setLeader(leaderAddressFromResponse);

        // Node created a room. Set properties.
        if (leaderAddressFromResponse.equals(address)) {
            logger.info("Node created the room " + req.getRoomName());
            isLeader = DsvPair.of(true, new Room(address, req.getRoomName()));
        }
        // Delete data about rooms and leaders.
        else {
            logger.info("Joined room " + req.getRoomName());
            if(isLeader()){
                isLeader = DsvPair.of(false, new Room.NullableRoom());
                SharedData.clear();
            }
        }

        currentRoom = req.getRoomName();
        updateChannelToLeader();
        preflight();
    }

    public void joinRoomViaLeader(String roomName, Integer delay) {
        assert dsvNeighbours.getLeader() != null;
        if (!Objects.equals(roomName, currentRoom))
            executeExit();
        joinRoom(dsvNeighbours.getLeader(), roomName, delay);
    }

    private void exitRoom() {
//        logger.info("Exiting room " + currentRoom);
        try {
            generated.RemoteServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getLeader()))
                    .exitRoom(Utils.Mapper.nodeToRemote());
        } catch (StatusRuntimeException e) {
            emergencyRepairAndElection();
            exitRoom();
        }
    }

    private void startRepairTopology(Address onNode, Address missing) {
        logger.info("Starting repair topology on " + onNode.getHostname() + ":" + onNode.getPort() + " with missing node " +
                missing.getHostname() + ":" + missing.getPort());
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
        logger.info("Starting election on node"
                + onNode.getHostname() + ":" + onNode.getPort());
        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(onNode))
                .election(Utils.Mapper.addressToRemote(new Address(Config.STUB_STRING, 0, -1)));
    }

    public void updateLeaderChannelAndObserver(Address address) {
        setVoting(false);
        dsvNeighbours.setLeader(address);
        updateChannelToLeader();
        init();
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
                emergencyRepairAndElection().apply(null);
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
                this.server = new ServerWrapper(address.getPort());
                DsvThreadPool.getInstance().execute(this.server);

                // The first node in topology. Creates the global room and is its leader.
                if (args.length == 2) {
                    isLeader = DsvPair.of(true, new Room(address, Config.INITIAL_ROOM_NAME));
                    currentRoom = Config.INITIAL_ROOM_NAME;
                    SharedData.put(Config.INITIAL_ROOM_NAME, DsvPair.of(address, address));
                    dsvNeighbours.setLeader(address);
                    updateChannelToLeader();
                    preflight();
                }
                // The "user" node which wants to connect with some other node.
                else if (args.length == 4) {
                    joinRoom(new Address(args[2], Integer.parseInt(args[3])), Config.INITIAL_ROOM_NAME, 0);
                }

            } else {
                System.err.println("Error while parsing args. Max number of args is 4.");
                System.exit(1);
            }

        } catch (Exception e) {
            logger.severe("Error while handling input args. Max. quantity of parameters is 4, Min. is 2.");
            logger.severe(e.getMessage());
        }
    }

    public static void main(String[] args) {
        final Node node = getInstance();
        node.handleArgs(args);
        node.listen();
    }

    private void preflight() {
            logger.info("Sending preflight to " + dsvNeighbours.getLeader());
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
        consoleHandler = new ConsoleHandler();
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
        StringBuilder sb = new StringBuilder()
                .append("[")
                .append(username)
                .append("]:\n\t")
                .append(address.toString())
                .append("\n\t[Leader]: ")
                .append(dsvNeighbours.getLeader().toString())
                .append("\n\t[Current room]: ")
                .append(currentRoom)
                .append("\n\t[State]: ")
                .append(state)
                .append("\n\t").append(dsvNeighbours.toString())
                .append("\n\t[Is Leader]: ")
                .append(isLeader.getKey())
                .append(":")
                .append(isLeader.getValue().getRoomName())
                .append("\n\t[Rooms and leaders table]: Room name : Leader address")
                .append(SharedData.stringify());
        return sb.toString();
    }
}
