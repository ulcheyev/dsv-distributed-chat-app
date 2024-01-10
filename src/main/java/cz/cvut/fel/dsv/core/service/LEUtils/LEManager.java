package cz.cvut.fel.dsv.core.service.LEUtils;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.Room;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvNeighbours;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.core.service.MEUtils.CSManager;
import cz.cvut.fel.dsv.core.service.UpdateServiceImpl;
import cz.cvut.fel.dsv.core.service.clients.UpdatableClient;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import io.grpc.StatusRuntimeException;
import lombok.Setter;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class LEManager {

    private static final Logger logger = DsvLogger.getLogger("LE MANAGER", ANSI_PURPLE_SERVICE, LEManager.class);
    private static  LEManager INSTANCE;
    @Setter
    private UpdateServiceImpl updateService;
    private final Node node = Node.getInstance();


    public void startChangingPrev(generated.Remote request) {
        // TODO NEED TO BE IN CS
        Address prev = Utils.Mapper.remoteToAddress(request);
        node.getDsvNeighbours().setPrev(prev);
        if (node.isLeader()) {
            SharedData.put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), prev));
            Node.getInstance().reflectOnBackup();
            updateService.updateTables();
        }
    }

    public static LEManager getInstance() {
        if (INSTANCE == null) {
            synchronized (CSManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LEManager();
                }
            }
        }
        return INSTANCE;
    }

    public void startElection(generated.Remote request) {
        var nextSkeleton = generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(node.getDsvNeighbours().getNext()));

        if (node.getAddress().getId() < request.getNodeId()) {
            node.setVoting(true);
            nextSkeleton.election(request);
        } else if ((node.getAddress().getId() > request.getNodeId()) && !node.isVoting()) {
            node.setVoting(true);
            nextSkeleton.election(Utils.Mapper.addressToRemote(node.getAddress()));
        } else if (node.getAddress().getId() == request.getNodeId())
            electedCandidate();
    }

    private void electedCandidate() {
        // TODO Need to be in CS
        logger.info("Node is elected candidate. Setting up properties...");
        node.setIsLeader(DsvPair.of(true, new Room(node.getAddress(), node.getCurrentRoom())));
        var former = node.getDsvNeighbours().getLeader();
        SharedData.put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), node.getDsvNeighbours().getPrev()));
        updateService.updateTables();
        node.updateLeaderChannelAndObserver(node.getAddress());
        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(node.getDsvNeighbours().getNext()))
                .elected(Utils.Mapper.addressToRemote(node.getAddress()));
    }

    public void startRepairing(generated.Remote request) {
        DsvNeighbours dsvNeighbours = node.getDsvNeighbours();
        if (request.getNodeId() == node.getDsvNeighbours().getNext().getId()) {
            var nextNextSkeleton = generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(dsvNeighbours.getNextNext()));

            logger.log(Level.INFO, "Setting next to {0}", dsvNeighbours.getNextNext());
            dsvNeighbours.setNext(dsvNeighbours.getNextNext());

            logger.log(Level.INFO, "Changing prev on {0} to {1} called by {2}",
                    new Object[]{dsvNeighbours.getNextNext(), node.getAddress(), node.getAddress()});
            var nextOfNextNextNode = nextNextSkeleton.changePrev(Utils.Mapper.addressToRemote(node.getAddress()));

            logger.log(Level.INFO, "Setting nextNext to {0}", Utils.Mapper.remoteToAddress(nextOfNextNextNode));
            dsvNeighbours.setNextNext(Utils.Mapper.remoteToAddress(nextOfNextNextNode));

            logger.log(Level.INFO, "Changing nextNext on {0} to {1} called by {2}",
                    new Object[]{dsvNeighbours.getPrev(), dsvNeighbours.getNext(), node.getAddress()});
            generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(dsvNeighbours.getPrev()))
                    .changeNextNext(Utils.Mapper.addressToRemote(dsvNeighbours.getNext()));
        } else {
            logger.log(Level.INFO, "Send message [repair topology] to the next node {0}", dsvNeighbours.getNext());
            try {
                generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(dsvNeighbours.getNext()))
                        .repairTopology(request);
            } catch (StatusRuntimeException runtimeException){
                logger.log(Level.WARNING, "Node is down {0}", dsvNeighbours.getNext());
            }

        }
    }

    public DsvNeighbours startChanging(generated.JoinRequest request) {
        DsvNeighbours myDsvNeighbours = node.getDsvNeighbours();
        Address initialNext = new Address(myDsvNeighbours.getNext());
        Address initialPrev = new Address(myDsvNeighbours.getPrev());
        DsvNeighbours tempDsvNeighbours = new DsvNeighbours(
                myDsvNeighbours.getNext(), myDsvNeighbours.getNextNext(), node.getAddress(), myDsvNeighbours.getLeader()
        );

        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(node.getDsvNeighbours().getNext()))
                .changePrev(request.getRemote());
        generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(initialPrev))
                .changeNextNext(request.getRemote());

        tempDsvNeighbours.setNextNext(myDsvNeighbours.getNextNext());
        myDsvNeighbours.setNextNext(initialNext);
        myDsvNeighbours.setNext(Utils.Mapper.remoteToAddress(request.getRemote()));
        return tempDsvNeighbours;
    }
}
