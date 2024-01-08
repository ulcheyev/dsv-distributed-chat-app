package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.service.LEUtils.LEManager;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.ElectionServiceGrpc;
import generated.Neighbours;
import io.grpc.stub.StreamObserver;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class ElectionServiceImpl extends ElectionServiceGrpc.ElectionServiceImplBase {
    private static final Logger logger = DsvLogger.getLogger("ELECTION SERVICE", ANSI_PURPLE_SERVICE, ElectionServiceImpl.class);
    private final Node node = Node.getInstance();
    private final LEManager leManager = new LEManager();

    @Override
    public void changeNextNext(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Method changeNextNext is called...");
        logger.log(Level.INFO, "Changing nextNext to {0}", Utils.Mapper.remoteToAddress(request));
        node.getDsvNeighbours().setNextNext(Utils.Mapper.remoteToAddress(request));
        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void changePrev(generated.Remote request, StreamObserver<generated.Remote> responseObserver) {
        logger.info("Method changePrev is called...");
        logger.log(Level.INFO, "Changing prev to {0}", Utils.Mapper.remoteToAddress(request));
        leManager.startChangingPrev(request);

        logger.log(Level.INFO, "Returning next node {0}", node.getDsvNeighbours().getNext());
        responseObserver.onNext(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getNext()));
        responseObserver.onCompleted();
    }

    @Override
    public void election(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.log(Level.INFO, "Election is called with ID: {0}", request.getNodeId());
        leManager.startElection(request);
        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void elected(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.log(Level.INFO, "Elected is called with ID: {0}", request.getNodeId());
        if (node.getAddress().getId() != request.getNodeId()) {
            node.updateLeaderChannelAndObserver(Utils.Mapper.remoteToAddress(request));
            generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(node.getDsvNeighbours().getNext()))
                    .elected(request);
        }

        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void repairTopology(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Changing topology is started...");
        leManager.startRepairing(request);
        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void changeNeighbours(generated.JoinRequest request, StreamObserver<generated.Neighbours> responseObserver) {
        Neighbours toSend = changeNeighbours(request);
        responseObserver.onNext(toSend);
        responseObserver.onCompleted();
    }

    public generated.Neighbours changeNeighbours(generated.JoinRequest request) {
        logger.info("Changing neighbours is started...");
        return Utils.Mapper.dsvNeighboursToNeighbours(leManager.startChanging(request));
    }

    public void setUpdateServiceToManager(UpdateServiceImpl updateService) {
        leManager.setUpdateService(updateService);
    }
}