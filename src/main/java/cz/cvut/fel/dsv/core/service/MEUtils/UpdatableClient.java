package cz.cvut.fel.dsv.core.service.MEUtils;

import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.UpdateServiceGrpc;
import io.grpc.ManagedChannel;
import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.ConcurrentMap;

public class UpdatableClient {

    private final generated.UpdateServiceGrpc.UpdateServiceBlockingStub blockingStub;
    private final UpdateServiceGrpc.UpdateServiceFutureStub futureStub;
    private final Address currentNodeAddress;

    UpdatableClient(Address currentNodeAddress, Address targetNodeAddress) {
        this.currentNodeAddress = currentNodeAddress;
        ManagedChannel managedChannel = Utils.Skeleton.buildChannel(targetNodeAddress);
        blockingStub = generated.UpdateServiceGrpc.newBlockingStub(managedChannel);
        futureStub = generated.UpdateServiceGrpc.newFutureStub(managedChannel);
    }

    void sendRequestCriticalSection(int timestamp) {
        generated.PermissionRequest request = generated.PermissionRequest.newBuilder()
                .setClock(timestamp)
                .setRequestByRemote(Utils.Mapper.addressToRemote(currentNodeAddress))
                .build();
        futureStub.receivePermissionRequest(request);
    }

    void sendPermitCriticalSection() {
        generated.PermissionResponse response = generated.PermissionResponse.newBuilder()
                        .setResponseByRemote(Utils.Mapper.addressToRemote(currentNodeAddress))
                        .build();
        blockingStub.receivePermissionReply(response);
    }

    void sendAllData(ConcurrentMap<String, DsvPair<Address, Address>> map) {
        generated.Rooms updateMessage = Utils.Mapper.leaderRoomsToRemoteRooms(map);
        blockingStub.receiveRooms(updateMessage);
    }

    void SendData(DsvPair<Address, Address> val){
//        generated.RoomEntry = Utils.Mapper.
    }

}
