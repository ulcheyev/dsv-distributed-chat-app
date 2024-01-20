package cz.cvut.fel.dsv.core.service.clients;

import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.ElectionServiceGrpc;
import generated.Neighbours;
import generated.Remote;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class ElectionClient {

    private static final Logger logger = DsvLogger.getLogger("ELECTION CLIENT", ANSI_PURPLE_SERVICE, ElectionClient.class);

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
//                .intercept(new DsvClientInterceptor())
                .build();
        blockingStub = generated.ElectionServiceGrpc.newBlockingStub(managedChannel);
        futureStub = ElectionServiceGrpc.newFutureStub(managedChannel);
    }

    public ElectionClient sendStartElection(Address onNode) {
        try {
            blockingStub.election(Utils.Mapper.addressToRemote(onNode));
        } catch (StatusRuntimeException e){
            logger.log(Level.WARNING, "Node {0} is down", targetNodeAddress);
        } finally {
            clear();
        }
        return this;
    }

    public ElectionClient sendElected(Address node) {
        try {
            blockingStub.elected(Utils.Mapper.addressToRemote(node));
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Node {0} is down", targetNodeAddress);
        } finally {
            clear();
        }
        return this;
    }


    public ElectionClient sendStartRepairTopology(Address missing) {
        try {
            blockingStub.repairTopology(Utils.Mapper.addressToRemote(missing));
        } catch (StatusRuntimeException runtimeException){
            logger.log(Level.WARNING, "Node is down {0}", targetNodeAddress);
        } finally {
            clear();
        }
        return this;
    }

    public DsvPair<generated.Remote, ElectionClient> sendChangePrev(Address toChange) {
        Remote remote = Remote.getDefaultInstance();
        try {
            remote = blockingStub.changePrev(Utils.Mapper.addressToRemote(toChange));
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Node is down {0}", targetNodeAddress);
        } finally {
            clear();
        }
        return DsvPair.of(remote, this);
    }

    public ElectionClient sendChangeNextNext(Address toChange) {
        try {
            blockingStub.changeNextNext(Utils.Mapper.addressToRemote(toChange));

        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Node is down {0}", targetNodeAddress);
        } finally {
            clear();
        }
        return this;
    }

    public DsvPair<Neighbours, ElectionClient> sendChangeNeighbours(generated.JoinRequest joinRequest) {
        Neighbours neighbours = null;
        try {
           neighbours = blockingStub.changeNeighbours(joinRequest);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Node is down {0}", targetNodeAddress);
        } finally {
            clear();
        }
        return DsvPair.of(neighbours, this);
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


}
