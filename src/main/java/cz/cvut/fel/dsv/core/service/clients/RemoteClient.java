package cz.cvut.fel.dsv.core.service.clients;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.utils.Utils;
import generated.RemoteServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class RemoteClient {
    private final  RemoteServiceGrpc.RemoteServiceBlockingStub blockingStub;
    private final Address currentNodeAddress;

    private final Address targetNodeAddress;

    private ManagedChannel managedChannel;


    public RemoteClient(Address currentNodeAddress, Address targetNodeAddress) {
        this.currentNodeAddress = currentNodeAddress;
        this.targetNodeAddress = targetNodeAddress;
        managedChannel = Utils.Skeleton.buildManagedChannel(targetNodeAddress);
        blockingStub = generated.RemoteServiceGrpc.newBlockingStub(managedChannel);
    }

    public RemoteClient sendTryLocateWithBeat() {
        try {
            blockingStub.beat(generated.Empty.getDefaultInstance());
        } finally {
            clear();
        }
        return this;
    }

    public DsvPair<generated.JoinResponse, RemoteClient> sendJoinRoom(generated.JoinRequest req) {
        try {
            generated.JoinResponse joinResponse = blockingStub.joinRoom(req);
            return DsvPair.of(joinResponse, this);
        } catch (StatusRuntimeException e){
            handleStatusRuntime();
        } finally {
            clear();
        }
        return null;
    }

    public RemoteClient sendExitRoom(generated.Remote req) {
        try {
            blockingStub.exitRoom(req);
        } catch (StatusRuntimeException e) {
            handleStatusRuntime();
        } finally {
            clear();
        }
        return this;
    }

    public RemoteClient sendMsg(String msg) {
        try{
            blockingStub.receiveMessage(Utils.Mapper.stringToMessage(Utils.Mapper.nodeToRemote(), msg));
        } catch (StatusRuntimeException e){
            handleStatusRuntime();
        } finally {
            clear();
        }
        return this;
    }

    public RemoteClient sendPreflight() {
        Node.StreamObserverImpl responseObserver = Node.getInstance().getNewMessageStreamObserver(managedChannel);
        RemoteServiceGrpc.newStub(managedChannel)
                        .preflight(Utils.Mapper.nodeToRemote(), responseObserver);
        return this;
    }


    public DsvPair<String, RemoteClient> sendGetNodeListInCurrentRoomRequest() {
        var res = blockingStub.receiveGetNodeListInCurrentRoomRequest(generated.Empty.getDefaultInstance())
                .getMsg();
        return DsvPair.of(res, this);
    }

    public DsvPair<String, RemoteClient> sendGetRoomListRequest() {
        var res = blockingStub.receiveGetRoomListRequest(generated.Empty.getDefaultInstance())
                .getMsg();
        return DsvPair.of(res, this);
    }

    public void clear() {
        try {
            managedChannel.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            if(!managedChannel.isShutdown()) {
                managedChannel.shutdownNow();
            }
        } catch (InterruptedException e) {
            managedChannel.shutdownNow();
        }
    }

    private void handleStatusRuntime() {
        Node.getInstance().startRepairTopology(currentNodeAddress, targetNodeAddress);
        Node.getInstance().makeElection(currentNodeAddress);
    }
}
