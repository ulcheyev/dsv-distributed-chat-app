package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.core.service.ElectionServiceImpl;
import cz.cvut.fel.dsv.core.service.RemoteServiceImpl;
import cz.cvut.fel.dsv.core.service.UpdateServiceImpl;
import cz.cvut.fel.dsv.utils.DsvLogger;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_GREEN_NODE;

public class ServerWrapper implements Runnable {
    private static final Logger logger = DsvLogger.getLogger("NODE SERVER", ANSI_GREEN_NODE, ServerWrapper.class);
    private Server server;
    private int port;

    private Runnable callback;

    @Override
    public void run() {
        this.startServer(port);
    }

    public ServerWrapper(int port, Runnable runnable) {
        this.port = port;
        this.callback = runnable;
    }

    private ServerWrapper() {
    }

    private void startServer(int port) {
        ElectionServiceImpl electionService = new ElectionServiceImpl();
        UpdateServiceImpl updateService = new UpdateServiceImpl(electionService);
        RemoteServiceImpl remoteService = new RemoteServiceImpl(updateService, electionService);
        this.server = ServerBuilder
                .forPort(port)
//                .intercept(interceptor)
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
            logger.log(Level.INFO, "Server started on port {0}", server.getPort());
            callback.run();
            server.awaitTermination();

        } catch (Exception e) {
            logger.severe("Error while starting the server.");
            logger.severe(e.getMessage());
        }
    }

    private void stopServer() {
        server.shutdown();
    }
}
