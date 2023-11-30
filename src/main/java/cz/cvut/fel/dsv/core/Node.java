package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.service.ChatServiceImpl;
import generated.ChatServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Getter
public class Node implements Runnable {
    private long nodeId;
    @Setter private Neighbour myNeighbour;
    @Setter private boolean voting;
    private static final Logger logger = Logger.getLogger(Node.class.getName());

    private Address currentNodeAddress;
    private ConsoleHandler consoleHandler; // todo change impl
    private Server server;

    public Node() {
    }

    // Todo implement client logic
    private String username;
    // Todo change implementation of leader access
    private Address nodeAddressToConnect;// standard
    private Address leaderAddress; // standard leader
    private ManagedChannel managedChannel;
    private StreamObserver<generated.ChatMessage> streamObserver;


    private void startServer(int port)  {
        this.server = ServerBuilder
                .forPort(port)
                .addService(new ChatServiceImpl(this))
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
//            server.awaitTermination();

        } catch (Exception e) {
            System.err.println("Error while starting the server.");
            logger.severe(e.getMessage());
        }
    }

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
            if (args.length == 2) {
                this.username = args[0];
                this.currentNodeAddress = new Address(InetAddress.getLocalHost().getHostAddress(), Integer.parseInt(args[1]));
                this.nodeId = generateId(this.currentNodeAddress);
                this.myNeighbour = new Neighbour(this.currentNodeAddress);
                this.nodeAddressToConnect = this.currentNodeAddress;
            } else {
                this.username = args[0];
                this.currentNodeAddress = new Address(InetAddress.getLocalHost().getHostAddress(), Integer.parseInt(args[1]));
                this.nodeId = generateId(this.currentNodeAddress);
                this.myNeighbour = new Neighbour(this.currentNodeAddress);
                this.nodeAddressToConnect = new Address(args[2], Integer.parseInt(args[3]));
            }
        } catch (Exception e) {
            logger.severe("Error.");
            logger.info("popka zhopka");
        }
    }

    private long generateId(Address myAddress) {
        String[] parsedArray = myAddress.getHostname().split("\\.");
        long temp;
        long generatedId = 0;
        for (var s : parsedArray) {
            temp = Long.parseLong(s);
            generatedId *= 1000;
            generatedId += temp + LocalDateTime.now().getNano();
        }
        return generatedId + myAddress.getPort() * 10_000L;
    }

    public void getStatus() {
        System.out.println("Node status: " + this);
    }

    @Override
    public String toString() {
        return "\n\tID: " + this.nodeId + ";\n" +
                "\tADDRESS: " + this.currentNodeAddress + ";\n" +
                "\tNEIGHBOUR: " + this.myNeighbour + ";\n" +
                "\tCONNECT TO: " + this.nodeAddressToConnect + ";\n";
    }

    public static void main(String[] args) {
        final Node node = new Node();
        node.handleArgs(args);
        node.run();
    }

    @Override
    public void run() {
        startServer(this.currentNodeAddress.getPort());
        // todo for test leader will be 8080
        leaderAddress = new Address("localhost", 8080);

        managedChannel = ManagedChannelBuilder
                .forAddress(leaderAddress.getHostname(), leaderAddress.getPort())
                .usePlaintext()
                .build();

        generated.ChatServiceGrpc.ChatServiceStub asyncStub = generated.ChatServiceGrpc.newStub(managedChannel);
        ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(managedChannel);
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

        generated.Address address = Mapper.addrToGenAddr(this.nodeAddressToConnect);
        Neighbour neighbour = Mapper.genNeiToNei(stub.join(address));
        setMyNeighbour(neighbour);
        getStatus();
        consoleHandler = new ConsoleHandler(this);
        new Thread(consoleHandler).start();
    }
}
