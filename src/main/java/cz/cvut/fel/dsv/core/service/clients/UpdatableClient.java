package cz.cvut.fel.dsv.core.service.clients;

import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.service.DsvClientInterceptor;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.UpdateServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import static cz.cvut.fel.dsv.core.infrastructure.Config.TIMEOUT_SECONDS_IN_CS;

public class UpdatableClient {

    private final generated.UpdateServiceGrpc.UpdateServiceBlockingStub blockingStub;
    private final UpdateServiceGrpc.UpdateServiceFutureStub futureStub;
    private final Address currentNodeAddress;
    private final Address targetNodeAddress;

    ManagedChannel managedChannel;
    public UpdatableClient(Address currentNodeAddress, Address targetNodeAddress) {
        this.currentNodeAddress = currentNodeAddress;
        Utils.Skeleton.buildChannel(targetNodeAddress);
        this.targetNodeAddress = targetNodeAddress;
         managedChannel = ManagedChannelBuilder
                .forAddress(targetNodeAddress.getHostname(), targetNodeAddress.getPort())
                .usePlaintext()
                .intercept(new DsvClientInterceptor())
                .build();
        blockingStub = generated.UpdateServiceGrpc.newBlockingStub(managedChannel);
        futureStub = generated.UpdateServiceGrpc.newFutureStub(managedChannel);
    }

    public UpdatableClient sendRequestCriticalSection(int timestamp, int delay, Long requestId) {
        generated.PermissionRequest request = generated.PermissionRequest.newBuilder()
                .setClock(timestamp)
                .setDelay(delay)
                .setId(requestId)
                .setRequestByRemote(Utils.Mapper.addressToRemote(currentNodeAddress))
                .build();
            blockingStub.withDeadlineAfter(TIMEOUT_SECONDS_IN_CS, TimeUnit.SECONDS).receivePermissionRequest(request);
            return this;
    }

    public UpdatableClient sendPermitCriticalSection(Long requestId) {
        generated.PermissionResponse response = generated.PermissionResponse.newBuilder()
                        .setResponseByRemote(Utils.Mapper.addressToRemote(currentNodeAddress))
                        .setId(requestId)
                        .build();
        blockingStub.receivePermissionReply(response);
        return this;
    }

    public UpdatableClient sendAllData(ConcurrentMap<String, DsvPair<Address, Address>> map) {
        generated.Rooms updateMessage = Utils.Mapper.leaderRoomsToRemoteRooms(map);
        blockingStub.receiveRooms(updateMessage);
        return this;
    }

    public UpdatableClient clear(){
        managedChannel.shutdown();
        return this;
    }

    public void SendData(DsvPair<Address, Address> val){
//        generated.RoomEntry = Utils.Mapper.
    }


}
