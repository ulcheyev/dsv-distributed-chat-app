package cz.cvut.fel.dsv.core.service.strategy;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.service.RemoteServiceImpl;
import cz.cvut.fel.dsv.core.service.UpdateServiceImpl;
import io.grpc.stub.StreamObserver;

public abstract class BaseJoinRoomStrategy {
    private RemoteServiceImpl remoteService;
    private UpdateServiceImpl updateService;

    public void executeJoin(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
        // Add logic exit -> (if leader exited) election -> send new leader -> update
        //                |_________________________________________________|
        //        updateService.awaitCs(); or lock self there
        updateService.requestCS(request.getDelay());
        updateService.awaitPermitToEnterCS();
        if (!request.getIsInitial()) remoteService.executeExit(request.getRemote());
        // repair
        // elect esli nado
        // update kto electnulsya (i esli mozhno sdelat kto udalilsa)

        execute(request, responseObserver);
//        updateService.releaseCS(); or self unlock there
    }

    public BaseJoinRoomStrategy setServices(RemoteServiceImpl remoteService, UpdateServiceImpl updateService) {
        this.updateService = updateService;
        this.remoteService = remoteService;
        return this;
    }

    protected abstract void execute(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver);
}
