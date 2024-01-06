package cz.cvut.fel.dsv.core.service.strategy;

import io.grpc.stub.StreamObserver;

public class JoinRoomContext {
    private JoinRoomStrategy strategy;

    public void setStrategy(JoinRoomStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeJoin(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
        strategy.execute(request, responseObserver);
    }
}
