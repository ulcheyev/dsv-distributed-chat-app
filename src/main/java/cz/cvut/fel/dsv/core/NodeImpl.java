package cz.cvut.fel.dsv.core;

import generated.Message;
import generated.RemotesServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class NodeImpl {
    private static final Logger logger = Logger.getLogger(NodeImpl.class.getName());
    @Getter @Setter private String username;
    @Getter @Setter private String currentRoom;
    @Getter @Setter private DsvPair<Boolean, String> isLeader = new DsvPair<>(false, "");
    @Getter @Setter private Room roomLeader;
    @Getter @Setter private Address address;
    @Getter @Setter private Address leaderAddress;
    @Getter @Setter private Map<String, Address> roomsAndLeaders;
    @Getter @Setter private NodeState state = NodeState.RELEASED;
    private final ConsoleHandler consoleHandler;
    private final ServerWrapper server;
    private ManagedChannel managedChannelToLeader;
    private StreamObserver<generated.Message> receiveMessagesObserver;

    public NodeImpl() {
        server = new ServerWrapper(this);
        consoleHandler = new ConsoleHandler(this);
        roomsAndLeaders = new HashMap<>();
    }

    private void init() {
        managedChannelToLeader = ManagedChannelBuilder
                .forAddress(leaderAddress.getHostname(), leaderAddress.getPort())
                .usePlaintext()
                .build();
        receiveMessagesObserver = new StreamObserver<Message>() {
            @Override
            public void onNext(Message message) {
                System.out.println("\r["+currentRoom+"] "+ message.getUsername() + ": " + message.getMsg());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

    public void joinRoom(Address address, String roomName) {

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(address.getHostname(), address.getPort())
                .usePlaintext()
                .build();

        RemotesServiceGrpc.RemotesServiceBlockingStub stub =
                RemotesServiceGrpc.newBlockingStub(managedChannel);

        generated.JoinRequest req = generated.JoinRequest.newBuilder()
                .setRoomName(roomName)
                .setRemote(Mapper.nodeToRemote(this))
                .build();

        generated.JoinResponse joinResponse = stub.joinRoom(req);

        if(!joinResponse.getIsLeader()) {
            managedChannel = ManagedChannelBuilder
                    .forAddress(joinResponse.getLeader().getHostname(), joinResponse.getLeader().getPort())
                    .usePlaintext()
                    .build();
            stub = RemotesServiceGrpc.newBlockingStub(managedChannel);
            joinResponse = stub.joinRoom(req);
        }
        leaderAddress = new Address(joinResponse.getLeader().getHostname(), joinResponse.getLeader().getPort());

        // Node created a room. Set properties
        // TODO CS
        if (leaderAddress.equals(this.address)) {
            isLeader = new DsvPair<>(true, roomName);
            roomLeader = new Room(roomName);
        }

        currentRoom = roomName;
        init();
        preflight();
    }

    public void joinRoomViaLeader(String roomName) {
        if(!Objects.equals(roomName, currentRoom))
                exitRoom();
        joinRoom(leaderAddress, roomName);
    }

    private void exitRoom() {
        Utils.getSyncSkeleton(leaderAddress.getHostname(), leaderAddress.getPort())
                .exitRoom(Mapper.nodeToRemote(this));
    }

    public void sendMessage(String msg) {
        RemotesServiceGrpc.RemotesServiceBlockingStub leaderStub =
                RemotesServiceGrpc.newBlockingStub(managedChannelToLeader);
        leaderStub.receiveMessage(Mapper.stringToMessage(username, msg));
    }

    private void listen(){
        consoleHandler.run();
    }

    private void handleArgs(String[] args){
        try {
            if(args.length <= 4){

                this.username = args[0];
                this.address = new Address(InetAddress.getLocalHost().getHostName(), Integer.parseInt(args[1]));
                this.address.generateId();
                this.server.startServer(address.getPort());

                // The first node in topology. Creates the global room and is its leader.
                if(args.length == 2){
                    isLeader = new DsvPair<>(true, "global"); // 1. true/false = leader/not leader, 2. if leader->roomName
                    currentRoom = "global";
                    roomsAndLeaders.put("global", address);
                    leaderAddress = address;
                    roomLeader = new Room("global");
                    init();
                }
                // The "user" node which wants to connect with some other node.
                else if (args.length == 4) {
                    joinRoom(new Address(args[2], Integer.parseInt(args[3])), "global");
                }

            }else{
                System.err.println("Error while parsing args. Max number of args is 4.");
                System.exit(1);
            }

        } catch (Exception e){
            logger.severe("Error while handling input args. Max. quantity of parameters is 4, Min. is 2.");
            logger.severe(e.getMessage());
        }
    }

    public static void main(String[] args) {
        final NodeImpl node = new NodeImpl();
        node.handleArgs(args);
        node.listen();
    }

    private void preflight() {
        RemotesServiceGrpc.RemotesServiceStub leaderStub =
                RemotesServiceGrpc.newStub(managedChannelToLeader);
        leaderStub.preflight(Mapper.nodeToRemote(this), receiveMessagesObserver);
    }

    // Todo
    @Override
    public String toString() {
        return new StringBuilder().append("").toString();
    }
}
