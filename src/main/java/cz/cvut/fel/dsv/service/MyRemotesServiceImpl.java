package cz.cvut.fel.dsv.service;

import cz.cvut.fel.dsv.core.Node;
import generated.Remote;
import generated.RemoteResponse;
import io.grpc.stub.StreamObserver;

public class MyRemotesServiceImpl extends generated.MyRemotesServiceGrpc.MyRemotesServiceImplBase{

    private Node thisNode;

    public MyRemotesServiceImpl(Node node) {
        this.thisNode = node;
    }

    @Override
    public void addRemote(Remote request, StreamObserver<RemoteResponse> responseObserver) {
        super.addRemote(request, responseObserver);

    }

    @Override
    public void removeRemote(Remote request, StreamObserver<RemoteResponse> responseObserver) {
        super.removeRemote(request, responseObserver);
    }

    @Override
    public StreamObserver<Remote> updateRemotes(StreamObserver<RemoteResponse> responseObserver) {
        return super.updateRemotes(responseObserver);
    }
}
