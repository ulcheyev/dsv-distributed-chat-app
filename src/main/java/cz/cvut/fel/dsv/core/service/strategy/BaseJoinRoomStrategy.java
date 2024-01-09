package cz.cvut.fel.dsv.core.service.strategy;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.service.ElectionServiceImpl;
import cz.cvut.fel.dsv.core.service.RemoteServiceImpl;
import cz.cvut.fel.dsv.core.service.UpdateServiceImpl;
import cz.cvut.fel.dsv.utils.DsvConditionLock;
import cz.cvut.fel.dsv.utils.DsvLogger;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public abstract class BaseJoinRoomStrategy {
    protected static final Logger logger = DsvLogger.getLogger("BASE JOIN", ANSI_PURPLE_SERVICE, BaseJoinRoomStrategy.class);
    protected RemoteServiceImpl remoteService;
    protected UpdateServiceImpl updateService;
    protected ElectionServiceImpl electionService;
    protected final Node node = Node.getInstance();

    private final DsvConditionLock lock = new DsvConditionLock(true);

    public void executeJoin(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
        lock.await();
        lock.lock();
        updateService.requestCS(request.getDelay());
        updateService.awaitPermitToEnterCS();
        if(Node.getInstance().isLeader()){
            if (!request.getIsInitial()) {
                remoteService.executeExit(request.getRemote());
                updateService.updateTables();
            }
        }
        execute(request, responseObserver);
        updateService.releaseCS();
        lock.signal();
    }

    public BaseJoinRoomStrategy setServices(RemoteServiceImpl remoteService, UpdateServiceImpl updateService, ElectionServiceImpl electionService) {
        this.updateService = updateService;
        this.remoteService = remoteService;
        this.electionService = electionService;
        return this;
    }

    protected abstract void execute(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver);
}
