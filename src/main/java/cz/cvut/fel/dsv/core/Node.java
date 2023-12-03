package cz.cvut.fel.dsv.core;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import generated.JoinResponse;
import generated.Message;
import generated.RemotesServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class Node {
    private static final Logger logger = Logger.getLogger(Node.class.getName());
    @Getter @Setter private String username;
    @Getter @Setter private String currentRoom;
    // Flag, which represents the knowledge:
    //      key == true => node is leader and in value is room which that node leads.
    //      key == false => node is not leader in value is null.
    @Getter @Setter private volatile DsvPair<Boolean, Room> isLeader;
    @Getter @Setter private volatile ConcurrentMap<String, Address> roomsAndLeaders;

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
        roomsAndLeaders = new ConcurrentHashMap<>();
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

    public void joinRoom(Address joinAddress, String roomName) {

        var stub = Utils.Skeleton.getFutureStub(joinAddress.getHostname(), joinAddress.getPort());

        generated.JoinRequest req = generated.JoinRequest.newBuilder()
                .setRoomName(roomName)
                .setRemote(Utils.Mapper.nodeToRemote(this))
                .build();

        ListenableFuture<JoinResponse> joinResponse = stub.joinRoom(req);
        Futures.addCallback(joinResponse, new FutureCallback<JoinResponse>() {
            @Override
            public void onSuccess(JoinResponse response) {
                ListenableFuture<JoinResponse> joinResponse;
                if(!response.getIsLeader()) {
                    Utils.Skeleton.shutdown();
                    var stub = Utils.Skeleton.getFutureStub(response.getLeader().getHostname(), response.getLeader().getPort());
                    var callBack = this;
                    joinResponse = stub.joinRoom(req);
                    Futures.addCallback(joinResponse, callBack, DsvThreadPool.getPool());
                    return;
                }

                leaderAddress = new Address(response.getLeader().getHostname(),
                        response.getLeader().getPort(),
                        response.getLeader().getNodeId());

                // Node created a room. Set properties
                if (leaderAddress.equals(address)) {
                    isLeader = new DsvPair<>(true, new Room(roomName));
                }
                currentRoom = roomName;
                updateChannelToLeader();
                preflight();

            }

            @Override
            public void onFailure(Throwable t) {
               logger.severe("Error while handling response in future in node. " +t.getMessage());
            }
        },
       DsvThreadPool.getPool());
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
        DsvThreadPool.execute(consoleHandler);
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
                    isLeader = new DsvPair<>(true, new Room(Config.INITIAL_ROOM_NAME));
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
                .append("\n\t[Rooms and leaders table]: Room name : Leader address");

        for(var room: roomsAndLeaders.entrySet()){
            sb.append("\n\t\t\t\t\t\t\t\t")
            .append(room.getKey())
                    .append(" : ")
                    .append(room.getValue());
        }
        return sb.toString();
    }

}
