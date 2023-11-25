package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.service.ChatServiceImpl;
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
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Node{

    private static final Logger logger = Logger.getLogger(Node.class.getName());

    @Getter private Address currentNodeAddress;
    private ConsoleHandler consoleHandler; // todo change impl
    private Server server;



    // Todo implement client logic
    @Getter private String username;
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
        streamObserver = asyncStub.chat(new StreamObserver<>() {
            @Override
            public void onNext(generated.ChatMessage chatMessage) {
//                logger.info("Client: Message received from: " + chatMessage.getSenderUsername() +
//                        "\n\t\tBody: " + chatMessage.getMessage());
                // todo problem when node is typed smth -> it will be deleted
                System.out.print('\r');
                System.out.println("[" + chatMessage.getSenderUsername()+"] " + chatMessage.getMessage());
                System.out.print(username+"> "); //todo how to write this when something changed in console

            }
            @Override
            public void onError(Throwable throwable) {
                logger.severe("Client: Error. " + throwable.getMessage());
            }
            @Override
            public void onCompleted() {
                logger.info("Client: Completed.");
            }
        });

        consoleHandler = new ConsoleHandler(this);
        new Thread(consoleHandler).start();
    }

    private void startServer(int port)  {
        this.server = ServerBuilder
                .forPort(currentNodeAddress.getPort())
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

    // todo will be global room!!!
    public void sendMessage(String msg) {
        if(!Objects.equals(msg, "")) {
            CountDownLatch finishLatch = new CountDownLatch(1);
            streamObserver.onNext(generated.ChatMessage.newBuilder()
                    .setMessage(msg)
                    .setSenderUsername(this.username)
                    .build());
            // waiting Todo change
            try {
                if (!finishLatch.await(1, TimeUnit.MINUTES)) {
                    logger.warning("Cannot finish the request during 1 minute.");
                }
            } catch (InterruptedException e) {
                logger.severe("Error while waiting on finish request.");
                logger.severe(e.getMessage());
            }
        }
    }

    private void stopServer() {
        server.shutdown();
    }

    private void handleArgs(String[] args){
        try {
            this.username = args[0];
            this.currentNodeAddress = new Address(InetAddress.getLocalHost().getHostName(), Integer.parseInt(args[1]));
            if(args.length == 2){ // todo for test it will be a server
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
                            logger.info("[Room] " + roomChatMessage.getRoom() + ", [user] " + roomChatMessage.getMsg().getSenderUsername() + ", [msg] "
                                    + roomChatMessage.getMsg().getMessage());
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
        try {
            th.join();
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public void sendMessageToRoom(String room, String msg){
        ChatServiceGrpc.ChatServiceBlockingStub asyncStub = ChatServiceGrpc.newBlockingStub(managedChannel);
       asyncStub.sendMessageToRoom(RoomChatMessage.newBuilder()
               .setMsg(generated.ChatMessage.newBuilder().setMessage(msg).setSenderUsername(username).build())
               .setRoom(room)
               .build());
    }

    public static void main(String[] args) {
        final Node node = new Node();
        node.handleArgs(args);
    }

}
