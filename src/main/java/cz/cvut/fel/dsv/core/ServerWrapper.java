package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.service.RemoteServiceImpl;
import cz.cvut.fel.dsv.service.ServerInterceptorImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.Builder;

import java.util.logging.Logger;

public class ServerWrapper {

    private static final Logger logger = Logger.getLogger(ServerWrapper.class.getName());
    private Server server;
    private NodeImpl node;

    public void startServer(int port)  {
        new Thread(() -> {
            this.server = ServerBuilder
                    .forPort(port)
                    .intercept(new ServerInterceptorImpl())
                    .addService(new RemoteServiceImpl(node))
                    .build();
            try {
                server.start();
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    ServerWrapper.this.stopServer();
                    System.err.println("Server shut down");
                }));
                logger.info("Server started: " + server);
                server.awaitTermination();

            } catch (Exception e) {
                System.err.println("Error while starting the server.");
                logger.severe(e.getMessage());
            }
        }).start();
    }

    private void stopServer() {
        server.shutdown();
    }

    public ServerWrapper(NodeImpl node) {
        this.node = node;
    }
}
