package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.service.ChatServiceImpl;
import generated.ChatMessageResponse;
import generated.ChatServiceGrpc;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class Node {

    private static final Logger logger = Logger.getLogger(Node.class.getName());

    private Server server;


    // Todo implement client logic
    private ManagedChannel managedChannel;
    private StreamObserver<generated.ChatMessageRequest> streamObserver;


    public Node(Server server) {
        assert server != null;
        this.server = server;
    }
    public Node() {};


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

    private void stopServer() {
        server.shutdown();
    }


    public static void main(String[] args) {
        final Node node = new Node(ServerBuilder
                                    .forPort(8080)
                                    .addService(new ChatServiceImpl())
                                    .build());
        node.startServer();

    }
}
