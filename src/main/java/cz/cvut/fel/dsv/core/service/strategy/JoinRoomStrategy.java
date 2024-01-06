package cz.cvut.fel.dsv.core.service.strategy;

import io.grpc.stub.StreamObserver;

public interface JoinRoomStrategy {
    void execute(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver);
}
