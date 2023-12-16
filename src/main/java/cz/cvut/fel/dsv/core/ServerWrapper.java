package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.service.RemoteServiceImpl;
import cz.cvut.fel.dsv.service.ServerInterceptorImpl;
import cz.cvut.fel.dsv.utils.DsvLogger;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.Config.ANSI_GREEN_NODE;

public class ServerWrapper {

    private static final Logger logger = DsvLogger.getLogger("NODE", ANSI_GREEN_NODE, ServerWrapper.class);
    private Server server;
    private final Node node;

    public void startServer(int port) {
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
                logger.info("Server started on port " + server.getPort());
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

    public ServerWrapper(Node node) {
        this.node = node;
    }
}
