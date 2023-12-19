package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.core.service.ElectionServiceImpl;
import cz.cvut.fel.dsv.core.service.RemoteServiceImpl;
import cz.cvut.fel.dsv.core.service.UpdateServiceImpl;
import cz.cvut.fel.dsv.utils.DsvLogger;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_GREEN_NODE;

public class ServerWrapper {

    private static final Logger logger = DsvLogger.getLogger("NODE SERVER", ANSI_GREEN_NODE, ServerWrapper.class);
    private Server server;
    private final Node node;

    public void startServer(int port) {
        DsvThreadPool.execute(() -> {
            ElectionServiceImpl electionService = new ElectionServiceImpl(node);
            RemoteServiceImpl remoteService = new RemoteServiceImpl(node, electionService);
            UpdateServiceImpl updateService = new UpdateServiceImpl(node, remoteService, electionService);

            this.server = ServerBuilder
                    .forPort(port)
                    .addService(remoteService)
                    .addService(electionService)
                    .addService(updateService)
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
        });
    }

    private void stopServer() {
        server.shutdown();
    }

    public ServerWrapper(Node node) {
        this.node = node;
    }
}
