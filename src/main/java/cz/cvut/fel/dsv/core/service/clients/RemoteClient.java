package cz.cvut.fel.dsv.core.service.clients;

import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.utils.Utils;
import generated.RemoteServiceGrpc;
import io.grpc.ManagedChannel;

public class RemoteClient {


    private final  RemoteServiceGrpc.RemoteServiceBlockingStub blockingStub;

    private final generated.RemoteServiceGrpc.RemoteServiceFutureStub futureStub;
    private final Address currentNodeAddress;

    public RemoteClient(Address currentNodeAddress, Address targetNodeAddress) {
        this.currentNodeAddress = currentNodeAddress;
        ManagedChannel managedChannel = Utils.Skeleton.buildManagedChannel(targetNodeAddress);
        blockingStub = generated.RemoteServiceGrpc.newBlockingStub(managedChannel);
        futureStub = generated.RemoteServiceGrpc.newFutureStub(managedChannel);
    }

    public void tryToLocateWithBeat() {
        blockingStub.beat(generated.Empty.getDefaultInstance());
    }
}
