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

public class Node {
    private static final Logger logger = Logger.getLogger(Node.class.getName());
    @Getter @Setter private String username;
    @Getter @Setter private String currentRoom;
    // Flag, which represents the knowledge:
    //      key == true => node is leader and in value is room which that node leads.
    //      key == false => node is not leader in value is null.
    @Getter @Setter private DsvPair<Boolean, Room> isLeader;
    @Getter @Setter private Map<String, Address> roomsAndLeaders;

    @Getter @Setter private Address address;
    @Getter @Setter private Address leaderAddress;
    @Getter @Setter private NodeState state;
    private final ConsoleHandler consoleHandler;
    private final ServerWrapper server;
    private ManagedChannel managedChannelToLeader;
    private StreamObserver<generated.Message> receiveMessagesObserver;

    public Node() {
        server = new ServerWrapper(this);
        consoleHandler = new ConsoleHandler(this);
        roomsAndLeaders = new HashMap<>();
        isLeader = new DsvPair<>(false, new Room.NullableRoom());
        state = NodeState.RELEASED;
        init();
    }

    private void init() {
        receiveMessagesObserver = new StreamObserver<Message>() {
            @Override
            public void onNext(Message message) {
                System.out.println("\r["+currentRoom+"] "+ message.getRemote().getUsername() + ": " + message.getMsg());
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
                .forAddress(leaderAddress.getHostname(), leaderAddress.getPort())
                .usePlaintext()
                .build();
    }

    public void joinRoom(Address address, String roomName) {

        var stub = Utils.Skeleton.getSyncSkeleton(address.getHostname(), address.getPort());

        generated.JoinRequest req = generated.JoinRequest.newBuilder()
                .setRoomName(roomName)
                .setRemote(Utils.Mapper.nodeToRemote(this))
                .build();

        generated.JoinResponse joinResponse = stub.joinRoom(req);

        if(!joinResponse.getIsLeader()) {
            Utils.Skeleton.shutdown();
            stub = Utils.Skeleton.getSyncSkeleton(joinResponse.getLeader().getHostname(), joinResponse.getLeader().getPort());
            joinResponse = stub.joinRoom(req);
        }

        leaderAddress = new Address(joinResponse.getLeader().getHostname(),
                joinResponse.getLeader().getPort(),
                joinResponse.getLeader().getNodeId());

        // Node created a room. Set properties
        if (leaderAddress.equals(this.address)) {
            isLeader = new DsvPair<>(true, new Room(roomName));
        }

        currentRoom = roomName;
        updateChannelToLeader();
        preflight();
    }

    public void joinRoomViaLeader(String roomName) {
        assert leaderAddress != null;
        if(!Objects.equals(roomName, currentRoom))
                exitRoom();
        joinRoom(leaderAddress, roomName);
    }

    private void exitRoom() {
        Utils.Skeleton.getSyncSkeleton(leaderAddress.getHostname(), leaderAddress.getPort())
                .exitRoom(Utils.Mapper.nodeToRemote(this));
    }

    public void sendMessage(String msg) {
        RemotesServiceGrpc.RemotesServiceBlockingStub leaderStub =
                RemotesServiceGrpc.newBlockingStub(managedChannelToLeader);
        leaderStub.receiveMessage(Utils.Mapper.stringToMessage(Utils.Mapper.nodeToRemote(this), msg));
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
                    isLeader = new DsvPair<>(true, new Room("global"));
                    currentRoom = "global";
                    roomsAndLeaders.put("global", address);
                    leaderAddress = address;
                    updateChannelToLeader();
                    preflight();
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
        final Node node = new Node();
        node.handleArgs(args);
        node.listen();
    }

    private void preflight() {
        Utils.Skeleton.getAsyncSkeleton(leaderAddress.getHostname(), leaderAddress.getPort())
                .preflight(Utils.Mapper.nodeToRemote(this), receiveMessagesObserver);
    }

    // Todo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("[")
                .append(username)
                .append("]\n\t")
                .append(address.toString())
                .append("\n\t[Leader] ")
                .append(leaderAddress.toString())
                .append("\n\t[Current room] ")
                .append(currentRoom)
                .append("\n\t[State] ")
                .append(state)
                .append("\n\t[Is Leader] ")
                .append(isLeader.getKey())
                .append(":")
                .append(isLeader.getValue().getRoomName())
                .append("\n\t[Room and leaders table]: RoomName : Leader address");

        for(var room: roomsAndLeaders.entrySet()){
            sb.append("\n\t\t\t\t\t\t\t\t")
            .append(room.getKey())
                    .append(" : ")
                    .append(room.getValue());
        }
        return sb.toString();
    }

}
