package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.core.command.ConsoleHandler;
import cz.cvut.fel.dsv.core.data.*;
import cz.cvut.fel.dsv.core.infrastructure.Config;
import cz.cvut.fel.dsv.core.service.clients.ElectionClient;
import cz.cvut.fel.dsv.core.service.clients.RemoteClient;
import cz.cvut.fel.dsv.core.service.clients.UpdatableClient;
import cz.cvut.fel.dsv.utils.Director;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import io.grpc.Context;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_GREEN_NODE;

public class Node {
    private static final Logger logger = DsvLogger.getLogger("NODE", ANSI_GREEN_NODE, Node.class);
    @Getter @Setter private String username;
    @Getter @Setter private String currentRoom;
    @Setter private DsvPair<Boolean, Room> isLeader;
    @Getter @Setter private Address address;
    @Getter @Setter private NodeState state;
    @Getter @Setter private DsvNeighbours dsvNeighbours;
    @Getter @Setter private boolean isVoting;
    private static volatile Node INSTANCE;
    private static RemoteClient sendMessageClient;

    private Node() {
        isLeader = DsvPair.of(false, new Room.NullableRoom());
        state = NodeState.RELEASED;
    }

    public static Node getInstance() {
        if (INSTANCE != null) return INSTANCE;
        synchronized (Node.class) {
            if (INSTANCE == null) INSTANCE = new Node();
        }
        return INSTANCE;
    }


    public void joinRoomViaLeader(String roomName, Integer delay) {
        assert dsvNeighbours.getLeader() != null;
        logger.log(Level.INFO, "Joining room {0}", roomName);
        if (currentRoom == null || !(currentRoom.equals(roomName)))
            joinRoomHelper(dsvNeighbours.getLeader(), Director.buildJoinReq(roomName, delay, false));
        else
            logger.log(Level.INFO, "You already in room {0}", roomName);
    }

    private void joinRoomHelper(Address joinAddress, generated.JoinRequest req) {
        var client = new RemoteClient(address, joinAddress);
        var pairWithResponse = client.sendJoinRoom(req);

        if(pairWithResponse == null) {
            startRepairTopology(address, dsvNeighbours.getLeader());
            makeElection(address);
            joinRoomHelper(dsvNeighbours.getLeader(), req);
            return;
        } else {
            var joinResponse = pairWithResponse.getKey();
            var leaderAddressFromResponse = Utils.Mapper.remoteToAddress(joinResponse.getLeader());

            if (!joinResponse.getIsLeader()) {
                joinRoomHelper(Utils.Mapper.remoteToAddress(joinResponse.getLeader()), req);
                return;
            }

            dsvNeighbours = Utils.Mapper.neighboursToDsvNeighbours(joinResponse.getNeighbours());
            dsvNeighbours.setLeader(leaderAddressFromResponse);

            if (leaderAddressFromResponse.equals(address)) { // Node created a room. Set properties.
                handleRoomOwner(req);
            } else { // Delete data about rooms and leaders.
                logger.log(Level.INFO, "Joined room {0}", req.getRoomName());
                if(!dsvNeighbours.getNext().equals(dsvNeighbours.getLeader()))
                {
                    SharedData.clear();
                }
                isLeader = DsvPair.of(false, new Room.NullableRoom());
            }

        }


        currentRoom = req.getRoomName();
        preflight();
    }

    private void handleRoomOwner(generated.JoinRequest req) {
        logger.log(Level.INFO, "Node created the room {0}", req.getRoomName());
        isLeader = DsvPair.of(true, new Room(address, req.getRoomName()));
    }


    public void exitRoom() {
        logger.log(Level.INFO, "Exiting room {0}", currentRoom);
        try {
            new RemoteClient(address, dsvNeighbours.getLeader())
                    .sendExitRoom(Utils.Mapper.nodeToRemote());
        } catch (StatusRuntimeException e) {
            startRepairTopology(address, dsvNeighbours.getLeader());
            makeElection(address);
            exitRoom();
        }
    }

    public void startRepairTopology(Address onNode, Address missing) {
        logger.log(Level.INFO, "Starting repair topology on {0}:{1} with missing node {2}:{3}",
                new Object[]{onNode.getHostname(), onNode.getPort(), missing.getHostname(), missing.getPort()});
        new ElectionClient(address, onNode)
                .sendStartRepairTopology(missing);
    }

    public void startElectionWithDelay(int seconds) {
        Utils.tryToSleep(seconds);
        makeElection(address);
    }

    public void makeElection(Address onNode) {
        logger.log(Level.INFO, "Starting election on node {0}:{1}", new Object[]{onNode.getHostname(), onNode.getPort()});
        new ElectionClient(address, onNode)
                .sendStartElection(new Address(Config.STUB_STRING, 0, -1));
    }

    public void updateConnectionPropertiesToLeader(Address address) {
        setVoting(false);
        dsvNeighbours.setLeader(address);
        preflight();
    }

    public void sendMessage(String msg) {
        new RemoteClient(address, dsvNeighbours.getLeader())
                .sendMsg(msg);
    }

    public void reflectOnBackup() {
        if(isLeader() && !address.equals(dsvNeighbours.getPrev()))
        {
            logger.log(Level.INFO, "Reflected rooms update on {0}", Node.getInstance().getDsvNeighbours().getPrev());
            new UpdatableClient(address, dsvNeighbours.getPrev())
                    .sendAllData(SharedData.getData());
        }
    }

    private void handleArgs(String[] args) {
        try {
            if (args.length <= 5) {
                this.username = args[0];
                this.address = new Address(args[2], Integer.parseInt(args[1]));
                this.address.generateId();
                this.dsvNeighbours = new DsvNeighbours(this.address);
                DsvThreadPool.getInstance().execute(new ServerWrapper(address.getPort(), () -> {
                    if (args.length == 3)
                        firstInTopology();
                    else if (args.length == 5)
                        joinRoomHelper(new Address(args[3], Integer.parseInt(args[4])), Director.buildJoinReq(Config.INITIAL_ROOM_NAME, 0, true));
                }));
            } else {
                System.err.println("Error while parsing args. Max number of args is 5.");
                System.exit(1);
            }
        } catch (Exception e) {
            logger.severe("Error while handling input args. Max. quantity of parameters is 5, Min. is 3.");
            logger.severe(e.getMessage());
        }
    }

    private void firstInTopology() {
        isLeader = DsvPair.of(true, new Room(address, Config.INITIAL_ROOM_NAME));
        currentRoom = Config.INITIAL_ROOM_NAME;
        SharedData.put(Config.INITIAL_ROOM_NAME, DsvPair.of(address, address));
        dsvNeighbours.setLeader(address);
        preflight();
    }

    public static void main(String[] args) {
        final Node node = getInstance();
        node.handleArgs(args);
        node.listen();
    }

    private void preflight() {
        Context ctx = Context.current().fork();
        ctx.run(() -> {
            if(sendMessageClient != null){
                sendMessageClient.clear();
            }
            logger.log(Level.INFO, "Sending preflight to {0}", dsvNeighbours.getLeader());
            sendMessageClient = new RemoteClient(address, dsvNeighbours.getLeader())
                    .sendPreflight();
        });
    }

    public String getNodeListInCurrentRoom() {
        RemoteClient remoteClient = new RemoteClient(address, dsvNeighbours.getLeader());
        return remoteClient
                .sendGetNodeListInCurrentRoomRequest()
                .getKey();
    }

    public String getRoomListInNetwork() {
        RemoteClient remoteClient = new RemoteClient(address, dsvNeighbours.getLeader());
        return remoteClient
                .sendGetRoomListRequest()
                .getKey();
    }


    private void listen() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        DsvThreadPool.getInstance().execute(consoleHandler);
    }


    public boolean isLeader() {
        return isLeader.getKey();
    }

    public boolean isInCS() {
        return state == NodeState.HOLDING;
    }

    public boolean leadingRoomIsNotEmpty() {
        return isLeader.getValue().getSize() != 1;
    }

    public Room getLeadingRoom() {
        return isLeader.getValue();
    }

    public StreamObserverImpl getNewMessageStreamObserver(ManagedChannel managedChannel) {
        return new StreamObserverImpl(managedChannel);
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

        private final ManagedChannel managedChannel;

        private StreamObserverImpl(ManagedChannel managedChannel) {
            this.managedChannel = managedChannel;
        }

        @Override
        public void onNext(generated.ChatMessage message) {
            System.out.println("\r[" + currentRoom + "] " + message.getRemote().getUsername() + ": " + message.getMsg());
        }

        @Override
        public void onError(Throwable throwable) {
            logger.log(Level.WARNING, "Error while receiving message. {0}", throwable.getMessage());
            if(dsvNeighbours.getNext().equals(dsvNeighbours.getLeader())){
                logger.log(Level.WARNING, "Node is backup. Start repair...", throwable.getMessage());
                shutdownMyChannel();
                startRepairTopology(address, dsvNeighbours.getLeader());
                makeElection(address);
            }
        }

        @Override
        public void onCompleted() {
            logger.log(Level.WARNING, "Chat message streamObserver onCompleted");
            shutdownMyChannel();
        }


        private void shutdownMyChannel() {
            logger.log(Level.WARNING, "Chat message streamObserver shutdown...");
            managedChannel.shutdown();
            try {
                managedChannel.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                managedChannel.shutdownNow();
            }
        }
    }
}