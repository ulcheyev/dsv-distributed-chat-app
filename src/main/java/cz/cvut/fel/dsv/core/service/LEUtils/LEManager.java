package cz.cvut.fel.dsv.core.service.LEUtils;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.Room;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvNeighbours;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.core.service.MEUtils.CSManager;
import cz.cvut.fel.dsv.core.service.UpdateServiceImpl;
import cz.cvut.fel.dsv.core.service.clients.ElectionClient;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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
        assert Node.getInstance().isInCS() : "Node is not in CS";
        Address prev = Utils.Mapper.remoteToAddress(request);
        node.getDsvNeighbours().setPrev(prev);
        if (node.isLeader()) {
            SharedData.put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), prev));
            updateService.updateTables(SharedData.getNodeAddressesWithoutCurrent(), SharedData.getData());
            Node.getInstance().reflectOnBackup();
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
        var electionClientNext = new ElectionClient(node.getAddress(), node.getDsvNeighbours().getNext());

        if (node.getAddress().getId() < request.getNodeId()) {
            node.setVoting(true);
            electionClientNext.sendStartElection(Utils.Mapper.remoteToAddress(request));
        } else if ((node.getAddress().getId() > request.getNodeId()) && !node.isVoting()) {
            node.setVoting(true);
            electionClientNext.sendStartElection(node.getAddress());
        } else if (node.getAddress().getId() == request.getNodeId())
            electedCandidate();
    }

    private void electedCandidate() {
        assert Node.getInstance().isInCS() : "Node is not in CS";
        logger.info("Node is elected candidate. Setting up properties...");
        var former = node.getDsvNeighbours().getLeader();
        node.setIsLeader(DsvPair.of(true, new Room(node.getAddress(), node.getCurrentRoom())));
        SharedData.put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), node.getDsvNeighbours().getPrev()));


        List<Address> newNodeAddresses = new ArrayList<>(SharedData.getNodeAddressesWithoutCurrent());
        newNodeAddresses.add(former);
        updateService.updateTables(newNodeAddresses, SharedData.getData());

        node.reflectOnBackup();
        node.updateConnectionPropertiesToLeader(node.getAddress());
        new ElectionClient(node.getAddress(), node.getDsvNeighbours().getNext())
                .sendElected(node.getAddress());
    }

    public void startRepairing(generated.Remote request) {
        DsvNeighbours dsvNeighbours = node.getDsvNeighbours();
        if (request.getNodeId() == node.getDsvNeighbours().getNext().getId()) {

            logger.log(Level.INFO, "Setting next to {0}", dsvNeighbours.getNextNext());
            dsvNeighbours.setNext(dsvNeighbours.getNextNext());

            logger.log(Level.INFO, "Changing prev on {0} to {1} called by {2}",
                    new Object[]{dsvNeighbours.getNextNext(), node.getAddress(), node.getAddress()});
            var pairOfResAndClientNextNext = new ElectionClient(node.getAddress(), dsvNeighbours.getNextNext())
                    .sendChangePrev(node.getAddress());
            var nextOfNextNextNode = pairOfResAndClientNextNext.getKey();
            pairOfResAndClientNextNext.getValue();

            logger.log(Level.INFO, "Setting nextNext to {0}", Utils.Mapper.remoteToAddress(nextOfNextNextNode));
            dsvNeighbours.setNextNext(Utils.Mapper.remoteToAddress(nextOfNextNextNode));

            logger.log(Level.INFO, "Changing nextNext on {0} to {1} called by {2}",
                    new Object[]{dsvNeighbours.getPrev(), dsvNeighbours.getNext(), node.getAddress()});
            new ElectionClient(node.getAddress(), dsvNeighbours.getPrev())
                    .sendChangeNextNext(dsvNeighbours.getNext());

        } else {
            if(!dsvNeighbours.getNext().equals(Node.getInstance().getAddress()))
            {
                logger.log(Level.INFO, "Send message [repair topology] to the next node {0}", dsvNeighbours.getNext());
                new ElectionClient(node.getAddress(), dsvNeighbours.getNext())
                        .sendStartRepairTopology(Utils.Mapper.remoteToAddress(request));
            }
            else {
                logger.log(Level.WARNING, "Next is similar to current node. Stop repair...");
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

        new ElectionClient(node.getAddress(), myDsvNeighbours.getNext())
                .sendChangePrev(Utils.Mapper.remoteToAddress(request.getRemote()))
                .getValue();

        new ElectionClient(node.getAddress(), initialPrev)
                .sendChangeNextNext(Utils.Mapper.remoteToAddress(request.getRemote()));

        tempDsvNeighbours.setNextNext(myDsvNeighbours.getNextNext());
        myDsvNeighbours.setNextNext(initialNext);
        myDsvNeighbours.setNext(Utils.Mapper.remoteToAddress(request.getRemote()));
        return tempDsvNeighbours;
    }
}
