package cz.cvut.fel.dsv.core.service.clients;

import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.service.DsvClientInterceptor;
import cz.cvut.fel.dsv.utils.Utils;
import generated.ElectionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class ElectionClient {
    private final ElectionServiceGrpc.ElectionServiceBlockingStub blockingStub;
    private final generated.ElectionServiceGrpc.ElectionServiceFutureStub futureStub;
    private final Address currentNodeAddress;
    private final Address targetNodeAddress;
    ManagedChannel managedChannel;

    public ElectionClient(Address currentNodeAddress, Address targetNodeAddress) {
        this.currentNodeAddress = currentNodeAddress;
        Utils.Skeleton.buildChannel(targetNodeAddress);
        this.targetNodeAddress = targetNodeAddress;
        managedChannel = ManagedChannelBuilder
                .forAddress(targetNodeAddress.getHostname(), targetNodeAddress.getPort())
                .usePlaintext()
                .intercept(new DsvClientInterceptor())
                .build();
        blockingStub = generated.ElectionServiceGrpc.newBlockingStub(managedChannel);
        futureStub = ElectionServiceGrpc.newFutureStub(managedChannel);
    }

    public ElectionClient sendStartElection(Address onNode) {
        blockingStub.election(Utils.Mapper.addressToRemote(onNode));
        return this;
    }

    public ElectionClient sendStartRepairTopology(Address missing) {
        blockingStub.repairTopology(Utils.Mapper.addressToRemote(missing));
        return this;
    }

    public void clear() {
        managedChannel.shutdown();
        try {
            while (!managedChannel.awaitTermination(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            // ignored
            System.err.println("Error while clearing channel " + e.getMessage());
        }
    }
}
