package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.service.ChatServiceImpl;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.Getter;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
    private Address nodeAddressToConnect = new Address("localhost", 8080);
    private Address leaderAddress = new Address("localhost", 8080);
    private ManagedChannel managedChannel;
    private StreamObserver<generated.ChatMessage> streamObserver;

    public Node(){}

    public Node(String username,
                Address address,
                Server server,
                Address nodeAddressToConnect) {
        assert server != null;
        this.username = username;
        this.currentNodeAddress = address;
        this.server = server;
        this.nodeAddressToConnect = nodeAddressToConnect;
    }

    public Node(String username,
                Address address,
                Server server) {
        this.username = username;
        this.currentNodeAddress = address;
        assert server != null;
        this.server = server;
    }

    private void init() {
        managedChannel = ManagedChannelBuilder
                .forAddress(leaderAddress.getHostname(), leaderAddress.getPort())
                .usePlaintext()
                .build();

        generated.ChatServiceGrpc.ChatServiceStub asyncStub = generated.ChatServiceGrpc.newStub(managedChannel);
        streamObserver = asyncStub.chat(new StreamObserver<>() {
            @Override
            public void onNext(generated.ChatMessage chatMessage) {
                logger.info("Client: Message received from: " + chatMessage.getSenderUsername() +
                        "\n\t\tBody: " + chatMessage.getMessage());
            }
            @Override
            public void onError(Throwable throwable) {
                logger.severe("Client: Error." + throwable.getMessage());
            }
            @Override
            public void onCompleted() {
                logger.info("Client: Completed.");
            }
        });

        consoleHandler = new ConsoleHandler(this);
        new Thread(consoleHandler).start();
    }

    private void startServer()  {

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

    public void sendMessage(String msg) {
        CountDownLatch finishLatch = new CountDownLatch(1);
        streamObserver.onNext(generated.ChatMessage.newBuilder()
                .setMessage(msg)
                .setSenderUsername(this.username)
                .build());
        // waiting Todo change
        try {
            if(!finishLatch.await(1, TimeUnit.MINUTES)) {
                logger.warning("Cannot finish the request during 1 minute.");
            }
        }catch (InterruptedException e) {
            logger.severe("Error while waiting on finish request.");
            logger.severe(e.getMessage());
        }

    }

    private void stopServer() {
        server.shutdown();
    }

    private void handleArgs(String[] args){
        try {
            this.username = args[0];
            if(args.length == 2){ // server
                this.currentNodeAddress = new Address(InetAddress.getLocalHost().getHostName(), Integer.parseInt(args[1]));
                this.server = ServerBuilder
                        .forPort(currentNodeAddress.getPort())
                        .addService(new ChatServiceImpl())
                        .build();
                this.startServer();
            }
            else if (args.length == 3) { // client
                this.nodeAddressToConnect = new Address(args[1], Integer.parseInt(args[2]));
            }
            init();
        } catch (UnknownHostException e){
            logger.severe("Error while reading local host address");
            logger.info(e.getMessage());
        }
    }

    public static void main(String[] args) {
        final Node node = new Node();
        node.handleArgs(args);
    }

}
