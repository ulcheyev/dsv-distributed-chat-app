package cz.cvut.fel.dsv.core.service.MEUtils;

import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.utils.Director;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.UpdateServiceGrpc;
import io.grpc.ManagedChannel;

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
        generated.PermissionRequest request = Director.buildPermReq(timestamp, currentNodeAddress);
        futureStub.receivePermissionRequest(request);
    }

    void sendPermitCriticalSection() {
        generated.PermissionResponse response = Director.buildPermRes(currentNodeAddress);
        blockingStub.receivePermissionReply(response);
    }

    void sendAllData(ConcurrentMap<String, DsvPair<Address, Address>> map) {
        generated.Rooms updateMessage = Utils.Mapper.leaderRoomsToRemoteRooms(map);
        blockingStub.receiveRooms(updateMessage);
    }

    void SendData(DsvPair<Address, Address> val) {
//        generated.RoomEntry = Utils.Mapper.
    }
}
