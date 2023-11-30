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
        thisNode.addRemote(new cz.cvut.fel.dsv.core.Remote(request.getNodeId()));
        RemoteResponse.Builder builder = RemoteResponse.newBuilder();
        for(int i = 0; i < thisNode.getRemotes().size(); i++){
            builder.setRemotes(i, Remote.newBuilder().setNodeId(thisNode.getRemotes().get(i).getRemoteId()).build());
        }
        responseObserver.onNext(builder.setAdded(true).build());
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
