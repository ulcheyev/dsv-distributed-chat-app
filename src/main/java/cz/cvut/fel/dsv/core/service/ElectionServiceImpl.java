package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.Room;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvNeighbours;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.ElectionServiceGrpc;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.Setter;

import java.util.List;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class ElectionServiceImpl extends ElectionServiceGrpc.ElectionServiceImplBase {
    private static final Logger logger = DsvLogger.getLogger("ELECTION SERVICE", ANSI_PURPLE_SERVICE, ElectionServiceImpl.class);
    private final Node node = Node.getInstance();
    @Setter private UpdateServiceImpl updateService;

    @Override
    public void changeNextNext(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Method changeNextNext is called...");
        logger.info("Changing nextNext to " + Utils.Mapper.remoteToAddress(request));
        node.getDsvNeighbours().setNextNext(Utils.Mapper.remoteToAddress(request));
        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void changePrev(generated.Remote request, StreamObserver<generated.Remote> responseObserver) {
        Address prev = Utils.Mapper.remoteToAddress(request);
        logger.info("Method changePrev is called...");
        logger.info("Changing prev to " + prev);

        node.getDsvNeighbours().setPrev(prev);
        if (node.getIsLeader().getKey()) {
            updateService.updateBackupNode(true);
            updateService.makeUpdateRoomsTable(updateService.getLeadersAddresses());
        }

        logger.info("Returning next node " + node.getDsvNeighbours().getNext());
        responseObserver.onNext(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getNext()));
        responseObserver.onCompleted();
    }

    @Override
    public void election(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Election is called with ID: " + request.getNodeId());
        var nextSkeleton = generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getNext()));

        if (node.getAddress().getId() < request.getNodeId()) {
            node.setVoting(true);
            nextSkeleton.election(request);
        } else if ((node.getAddress().getId() > request.getNodeId()) && !node.isVoting()) {
            node.setVoting(true);
            nextSkeleton.election(Utils.Mapper.addressToRemote(node.getAddress()));
        } else if (node.getAddress().getId() == request.getNodeId())
            electedCandidate();

        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    private void electedCandidate() {
        logger.info("Node is elected candidate. Setting up properties...");
        node.setIsLeader(DsvPair.of(true, new Room(node.getAddress(), node.getCurrentRoom())));
        var former = node.getDsvNeighbours().getLeader();
        node.getRoomsAndLeaders().put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), node.getDsvNeighbours().getPrev()));
        node.getRoomsAndLeaders().forEach((key, value) -> logger.info(value.toString()));
        updateService.updateBackupNode(true);
        updateService.makeUpdateRoomsTable(List.of(node.getAddress(), former));
//
//        try {
//            generated.UpdateServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getLeader()))
//                    .receiveRoom(generated.RoomEntry.newBuilder()
//                            .setRoomName(node.getCurrentRoom())
//                            .setRoomOwner(Utils.Mapper.nodeToRemote(node))
//                            .build());
//        } catch (StatusRuntimeException ignored) {
//            // todo
//        }

        node.updateLeaderChannelAndObserver(node.getAddress());
        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getNext()))
                .elected(Utils.Mapper.addressToRemote(node.getAddress()));
    }

    @Override
    public void elected(generated.Remote request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Elected is called with ID: " + request.getNodeId());
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
        DsvNeighbours dsvNeighbours = node.getDsvNeighbours();
        if (request.getNodeId() == node.getDsvNeighbours().getNext().getId()) {
            logger.info("Setting next to " + dsvNeighbours.getNextNext());
            dsvNeighbours.setNext(dsvNeighbours.getNextNext());

            var nextNextSkeleton = generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getNextNext()));

            logger.info("Changing prev on " + dsvNeighbours.getNextNext() + " to " + node.getAddress() + " called by " + node.getAddress());
            var nextOfNextNextNode = nextNextSkeleton.changePrev(Utils.Mapper.addressToRemote(node.getAddress()));

            logger.info("Setting nextNext to " + Utils.Mapper.remoteToAddress(nextOfNextNextNode));
            dsvNeighbours.setNextNext(Utils.Mapper.remoteToAddress(nextOfNextNextNode));

            logger.info("Changing nextNext on " + dsvNeighbours.getPrev() + " to " + dsvNeighbours.getNext() + "called by " + node.getAddress());
            generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getPrev()))
                    .changeNextNext(Utils.Mapper.addressToRemote(dsvNeighbours.getNext()));

        } else {
            logger.info("Send message [repair topology] to the next node " + dsvNeighbours.getNext());
            generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(dsvNeighbours.getNext()))
                    .repairTopology(request);
        }

        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void changeNeighbours(generated.JoinRequest request, StreamObserver<generated.Neighbours> responseObserver) {
        responseObserver.onNext(changeNeighbours(request));
        responseObserver.onCompleted();
    }

    protected generated.Neighbours changeNeighbours(generated.JoinRequest request) {
        logger.info("Changing neighbours is started...");
        DsvNeighbours myDsvNeighbours = node.getDsvNeighbours();
        Address initialNext = new Address(myDsvNeighbours.getNext());
        Address initialPrev = new Address(myDsvNeighbours.getPrev());
        DsvNeighbours tempDsvNeighbours = new DsvNeighbours(
                myDsvNeighbours.getNext(),
                myDsvNeighbours.getNextNext(),
                node.getAddress(),
                myDsvNeighbours.getLeader()
        );

        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getNext()))
                .changePrev(request.getRemote());
        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(initialPrev))
                .changeNextNext(request.getRemote());

        tempDsvNeighbours.setNextNext(myDsvNeighbours.getNextNext());
        myDsvNeighbours.setNextNext(initialNext);
        myDsvNeighbours.setNext(Utils.Mapper.remoteToAddress(request.getRemote()));

        return Utils.Mapper.dsvNeighboursToNeighbours(tempDsvNeighbours);
    }
}
