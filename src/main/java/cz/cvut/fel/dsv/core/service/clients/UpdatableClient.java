package cz.cvut.fel.dsv.core.service.clients;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.utils.Director;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.UpdateServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;
import static cz.cvut.fel.dsv.core.infrastructure.Config.TIMEOUT_SECONDS_IN_CS;

public class UpdatableClient {
    private static final Logger logger = DsvLogger.getLogger("UPDATABLE CLIENT", ANSI_PURPLE_SERVICE, UpdatableClient.class);
    private final generated.UpdateServiceGrpc.UpdateServiceBlockingStub blockingStub;
    private final UpdateServiceGrpc.UpdateServiceFutureStub futureStub;
    private final Address currentNodeAddress;
    private final Address targetNodeAddress;
    private ManagedChannel managedChannel;

    public UpdatableClient(Address currentNodeAddress, Address targetNodeAddress) {
        this.currentNodeAddress = currentNodeAddress;
        Utils.Skeleton.buildChannel(targetNodeAddress);
        this.targetNodeAddress = targetNodeAddress;
        managedChannel = ManagedChannelBuilder
                .forAddress(targetNodeAddress.getHostname(), targetNodeAddress.getPort())
                .usePlaintext()
//                .intercept(new DsvClientInterceptor())
                .build();
        blockingStub = generated.UpdateServiceGrpc.newBlockingStub(managedChannel);
        futureStub = generated.UpdateServiceGrpc.newFutureStub(managedChannel);
    }

    public UpdatableClient sendRequestCriticalSection(int timestamp, int delay, Long requestId) {
        try {
            generated.PermissionRequest request = Director.buildPermReq(timestamp, delay, requestId, currentNodeAddress);
            blockingStub.withDeadlineAfter(TIMEOUT_SECONDS_IN_CS, TimeUnit.SECONDS).receivePermissionRequest(request);
        } finally {
            clear();
        }

        return this;
    }


    public UpdatableClient sendPermitCriticalSection(Long requestId) {
        try {
            generated.PermissionResponse response = Director.buildPermRes(currentNodeAddress, requestId);
            blockingStub.receivePermissionReply(response);
        } finally {
            clear();
        }

        return this;
    }

    public UpdatableClient sendAllData(ConcurrentMap<String, DsvPair<Address, Address>> map) {
        try {
            generated.Rooms updateMessage = Utils.Mapper.leaderRoomsToRemoteRooms(map);
            blockingStub.receiveRooms(updateMessage);
        }
        finally {
            clear();
        }
        return this;
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

    public UpdatableClient sendData(String roomName, DsvPair<Address, Address> val) {
        try {
            blockingStub.receiveRoom(Utils.Mapper.pairToRoomEntry(roomName, val));
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Node {0} is down", targetNodeAddress);
        } finally {
            clear();
        }
        return this;
    }

    public UpdatableClient sendRemoveData(String roomName) {
        blockingStub.removeRoom(Utils.Mapper.pairToRoomEntry(roomName, DsvPair.of(Node.getInstance().getAddress())));
        return this;
    }
}
