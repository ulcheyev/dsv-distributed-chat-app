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

//        Address prev = Utils.Mapper.remoteToAddress(request);
//        node.getDsvNeighbours().setPrev(prev);
//        if (node.isLeader()) {
//           SharedData.put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), prev));
////            List<Address> nodeAddresses = updateService.getSpecifiedAddressesToRequest(node.getRoomsAndLeaders());
////            updateService.makeUpdateRoomsTable(node.getRoomsAndLeaders(), nodeAddresses);
//        }
        leManager.startChangingPrev(request);

        logger.log(Level.INFO, "Returning next node {0}", node.getDsvNeighbours().getNext());
        responseObserver.onNext(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getNext()));
        responseObserver.onCompleted();
    }

    @Override
    public void election(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.log(Level.INFO, "Election is called with ID: {0}", request.getNodeId());
//        var nextSkeleton = generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getNext()));
//
//        if (node.getAddress().getId() < request.getNodeId()) {
//            node.setVoting(true);
//            nextSkeleton.election(request);
//        } else if ((node.getAddress().getId() > request.getNodeId()) && !node.isVoting()) {
//            node.setVoting(true);
//            nextSkeleton.election(Utils.Mapper.addressToRemote(node.getAddress()));
//        } else if (node.getAddress().getId() == request.getNodeId())
//            electedCandidate();
        leManager.startElection(request);
        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
//
//    private void electedCandidate() {
//        logger.info("Node is elected candidate. Setting up properties...");
//        node.setIsLeader(DsvPair.of(true, new Room(node.getAddress(), node.getCurrentRoom())));
//        var former = node.getDsvNeighbours().getLeader();
//        Address prevToUpdate = node.getDsvNeighbours().getPrev();
//        SharedData.put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), node.getDsvNeighbours().getPrev()));
////        updateService.makeUpdateRoomsTable(node.getRoomsAndLeaders(), List.of(former));
//
//        node.updateLeaderChannelAndObserver(node.getAddress());
//        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getNext()))
//                .elected(Utils.Mapper.addressToRemote(node.getAddress()));
//    }

    @Override
    public void elected(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.log(Level.INFO, "Elected is called with ID: {0}", request.getNodeId());
        if (node.getAddress().getId() != request.getNodeId()) {
            node.updateLeaderChannelAndObserver(Utils.Mapper.remoteToAddress(request));
            generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getNext()))
                    .elected(request);
        }

        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void repairTopology(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Changing topology is started...");
//        DsvNeighbours dsvNeighbours = node.getDsvNeighbours();
//        if (request.getNodeId() == node.getDsvNeighbours().getNext().getId()) {
//            logger.log(Level.INFO, "Setting next to {0}", dsvNeighbours.getNextNext());
//            dsvNeighbours.setNext(dsvNeighbours.getNextNext());
//
//            var nextNextSkeleton = generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getNextNext()));
//
//            logger.log(Level.INFO, "Changing prev on {0} to {1} called by {2}",
//                    new Object[]{dsvNeighbours.getNextNext(), node.getAddress(), node.getAddress()});
//            var nextOfNextNextNode = nextNextSkeleton.changePrev(Utils.Mapper.addressToRemote(node.getAddress()));
//
//            logger.log(Level.INFO, "Setting nextNext to {0}", Utils.Mapper.remoteToAddress(nextOfNextNextNode));
//            dsvNeighbours.setNextNext(Utils.Mapper.remoteToAddress(nextOfNextNextNode));
//
//            logger.log(Level.INFO, "Changing nextNext on {0} to {1} called by {2}",
//                    new Object[]{dsvNeighbours.getPrev(), dsvNeighbours.getNext(), node.getAddress()});
//            generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getPrev()))
//                    .changeNextNext(Utils.Mapper.addressToRemote(dsvNeighbours.getNext()));
//
//        } else {
//            logger.log(Level.INFO, "Send message [repair topology] to the next node {0}", dsvNeighbours.getNext());
//            generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getNext()))
//                    .repairTopology(request);
//        }
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
//        DsvNeighbours myDsvNeighbours = node.getDsvNeighbours();
//        Address initialNext = new Address(myDsvNeighbours.getNext());
//        Address initialPrev = new Address(myDsvNeighbours.getPrev());
//        DsvNeighbours tempDsvNeighbours = new DsvNeighbours(
//                myDsvNeighbours.getNext(),
//                myDsvNeighbours.getNextNext(),
//                node.getAddress(),
//                myDsvNeighbours.getLeader()
//        );
//
//        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getNext()))
//                .changePrev(request.getRemote());
//        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(initialPrev))
//                .changeNextNext(request.getRemote());
//
//        tempDsvNeighbours.setNextNext(myDsvNeighbours.getNextNext());
//        myDsvNeighbours.setNextNext(initialNext);
//        myDsvNeighbours.setNext(Utils.Mapper.remoteToAddress(request.getRemote()));

        return Utils.Mapper.dsvNeighboursToNeighbours(leManager.startChanging(request));
    }

    public void setUpdateServiceToManager(UpdateServiceImpl updateService) {
        leManager.setUpdateService(updateService);
    }
}
