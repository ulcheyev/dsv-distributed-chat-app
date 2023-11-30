package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.service.ChatServiceImpl;
import cz.cvut.fel.dsv.service.ServerInterceptorImpl;
import generated.ChatServiceGrpc;
import generated.RoomChatMessage;
import generated.RoomRequestMessage;
import generated.RoomResponseMessage;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

import java.io.Reader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Node{ //todo handle error: unique name, unique room name, i td... handle on complete

    private NodeState nodeState = NodeState.RELEASED;

    private static final Logger logger = Logger.getLogger(Node.class.getName());

    @Getter private Address currentNodeAddress;
    private ConsoleHandler consoleHandler; // todo change impl
    private Server server;

    private List<Remote> remotes = new ArrayList<>(); // todo change to class


    // Todo implement client logic
    @Getter private String username;
    @Getter private String currentRoomName = "global"; // todo standard
    // Todo change implementation of leader access
    private Address nodeAddressToConnect = new Address("localhost", 8080); // standard
    private Address leaderAddress; // standard leader
    private ManagedChannel managedChannel;
    private StreamObserver<generated.ChatMessage> streamObserver;

    // todo test
    private StreamObserver<generated.RoomChatMessage> roomMessageObserver;


    private void initRemoteMethodProperties() {

        // todo for test leader will be 8080
        leaderAddress = new Address("localhost", 8080);

        managedChannel = ManagedChannelBuilder
                .forAddress(leaderAddress.getHostname(), leaderAddress.getPort())
                .usePlaintext()
                .build();

        generated.ChatServiceGrpc.ChatServiceStub asyncStub = generated.ChatServiceGrpc.newStub(managedChannel);

        joinRoom(currentRoomName); // todo initially in global room

        consoleHandler = new ConsoleHandler(this);
        new Thread(consoleHandler).start();
    }

    private void startServer(int port)  {
        this.server = ServerBuilder
                .forPort(currentNodeAddress.getPort())
                .intercept(new ServerInterceptorImpl())
                .addService(new ChatServiceImpl())
                .build();
        try {
            server.start();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    cz.cvut.fel.dsv.core.Node.this.stopServer();
                    System.err.println("Server shut down");
                }
            });
            logger.info("Server started: " + server);
            server.awaitTermination();

        } catch (Exception e) {
            System.err.println("Error while starting the server.");
            logger.severe(e.getMessage());
        }
    }

    private void stopServer() {
        server.shutdown();
    }

    private void handleArgs(String[] args){
        try {
            this.username = args[0];
            this.currentNodeAddress = new Address(InetAddress.getLocalHost().getHostName(), Integer.parseInt(args[1]));
            if(args.length == 2){ // todo for test it will be a server -- make an output

                this.startServer(currentNodeAddress.getPort());
            }
            else if (args.length == 4) { // todo for test it will be a client
                this.nodeAddressToConnect = new Address(args[2], Integer.parseInt(args[3]));
                initRemoteMethodProperties();
            }
            else {
                System.err.println("Error while handling input args. Max. quantity pf parameters is 4, Min. is 2.");
            }
        } catch (Exception e){
            logger.severe("Error while handling input args. Max. quantity pf parameters is 4, Min. is 2.");
            logger.info(e.getMessage());
        }
    }


    public void createRoom(String roomName) {
        var th = new Thread( () -> {ChatServiceGrpc.ChatServiceBlockingStub chatServiceBlockingStub = ChatServiceGrpc.newBlockingStub(managedChannel);
            RoomResponseMessage room = chatServiceBlockingStub.createRoom(RoomRequestMessage
                    .newBuilder()
                    .setRoomName(roomName)
                    .setSenderUsername(username)
                    .build());});
        th.start();
        try {
            th.join();
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        joinRoom(roomName);
    }

    public void joinRoom(String roomName) {
        exitCurrentRoom();
        var th = new Thread( () ->  {
            ChatServiceGrpc.ChatServiceStub chatServiceBlockingStub = ChatServiceGrpc.newStub(managedChannel);

            chatServiceBlockingStub.joinToRoom(RoomRequestMessage
                            .newBuilder()
                            .setRoomName(roomName)
                            .setSenderUsername(username)
                            .build(),
                    new StreamObserver<RoomChatMessage>() {
                        @Override
                        public void onNext(RoomChatMessage roomChatMessage) {
                            System.out.print('\r');
                            System.out.println("[" +currentRoomName+":msg] "+ roomChatMessage.getMsg().getSenderUsername()+" -> " + roomChatMessage.getMsg().getMessage());
                            System.out.print("["+currentRoomName+"] "+username+"> "); //todo how to write this when something changed in console
                        }

                        @Override
                        public void onError(Throwable throwable) {

                        }

                        @Override
                        public void onCompleted() {
                        }
                    });
        });
        th.start();
        currentRoomName  = roomName;
        try {
            th.join();
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public void exitCurrentRoom() {
        var th = new Thread( () -> {
            ChatServiceGrpc.ChatServiceBlockingStub chatServiceBlockingStub = ChatServiceGrpc.newBlockingStub(managedChannel);
            chatServiceBlockingStub.exitRoom(RoomRequestMessage.newBuilder().setRoomName(currentRoomName).setSenderUsername(username).build());
        });
        th.start();
        try {
            th.join();
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessageToRoom(String room, String msg){
        var th = new Thread( () -> {
            if (!msg.isEmpty()) {
                ChatServiceGrpc.ChatServiceBlockingStub asyncStub = ChatServiceGrpc.newBlockingStub(managedChannel);
                asyncStub.sendMessageToRoom(RoomChatMessage.newBuilder()
                        .setMsg(generated.ChatMessage.newBuilder().setMessage(msg).setSenderUsername(username).build())
                        .setRoom(room)
                        .build());

            }
        });
        th.start();
    }

    public static void main(String[] args) {
        final Node node = new Node();
        node.handleArgs(args);
    }

}
