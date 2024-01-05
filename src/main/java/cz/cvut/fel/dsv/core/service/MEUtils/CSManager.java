package cz.cvut.fel.dsv.core.service.MEUtils;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.NodeState;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class CSManager {
    private static final Logger logger = DsvLogger.getLogger("CS MANAGER", ANSI_PURPLE_SERVICE, CSManager.class);
    private final TimesTamp maxClock;
    private final TimesTamp nodeClock;
    private int replyCount;
    private final Queue<DelayedRequest> delayedRequests = new LinkedList<>();
    private CountDownLatch replyLatch;


    public CSManager() {
        maxClock = new TimesTamp();
        nodeClock = new TimesTamp();
    }

    public synchronized void requestCriticalSection(Integer delay) {
        replyCount = 0;
        maxClock.update();
        nodeClock.setClock(maxClock);
        int currentIntValueOfClock = nodeClock.getClock();
        int necessaryReplyCount = SharedData.getSizeNecessaryForUpdate();
        replyLatch = new CountDownLatch(necessaryReplyCount);
        Node.getInstance().setState(NodeState.REQUESTING);
        logger.log(Level.INFO, "[CS] Requesting nodes to enter CS. Clock: {0}. Need {1} replies", new Object[]{currentIntValueOfClock, necessaryReplyCount});
        for (var remoteNodeAddr: SharedData.getNodeAddressesWithoutCurrent()) {
            Utils.tryToSleep(delay);
            new UpdatableClient(Node.getInstance().getAddress(), remoteNodeAddr).sendRequestCriticalSection(currentIntValueOfClock);
            logger.log(Level.INFO, "[- CS] Request sent to {0}", remoteNodeAddr);
        }
    }

    public synchronized void receiveRequest(Address requestingNodeAddress, int timestamp) {
        boolean delayed = isDelay(timestamp, requestingNodeAddress.getId());
        maxClock.receiveMsg(timestamp);
        if (!delayed) {
            new UpdatableClient(Node.getInstance().getAddress(), requestingNodeAddress).sendPermitCriticalSection();
            logger.log(Level.INFO, "[CS] request by {0} is permitted; Request clock: {1}; Node clock: {2}; State: {3}",
                    new Object[]{requestingNodeAddress, timestamp, nodeClock.getClock(), Node.getInstance().getState()});
        }
        else {
            logger.log(Level.INFO, "[CS] request by {0} is delayed; Request clock: {1}; Node clock: {2}; State: {3}",
                    new Object[]{requestingNodeAddress, timestamp, nodeClock.getClock(), Node.getInstance().getState()});
            delayedRequests.add(new DelayedRequest(requestingNodeAddress, timestamp));
        }
    }

    public synchronized void receivePermit() {
        int necessarySize = SharedData.getSizeNecessaryForUpdate();

        replyCount++;
        int awaitingReplies = necessarySize - replyCount;
        logger.log(Level.INFO, "[CS] Awaiting {0}", new Object[]{awaitingReplies});
        if(replyCount == necessarySize){
            enterCriticalSection();
        }
        replyLatch.countDown();
    }

    private synchronized void processDelayedRequests() {
        while (!delayedRequests.isEmpty()) {
            DelayedRequest delayedRequest = delayedRequests.poll();
            logger.log(Level.INFO, "[CS] Processing delayed request {0}", delayedRequest);
            new UpdatableClient(Node.getInstance().getAddress(), delayedRequest.requestingNodeAddress()).sendPermitCriticalSection();
        }
    }

    public synchronized void receiveRelease() {
        Node.getInstance().setState(NodeState.RELEASED);
        processDelayedRequests();
    }

    private synchronized void enterCriticalSection() {
        Node.getInstance().setState(NodeState.HOLDING);
        logger.log(Level.INFO, "[CS] Node {0} entered critical section", Node.getInstance().getAddress());
    }

    public synchronized void broadcastDataUpdate() {
        logger.log(Level.INFO, "[CS] broadcast update on leaders. Size = {0}", SharedData.getSizeNecessaryForUpdate());
        for (var addr : SharedData.getNodeAddressesWithoutCurrent()) {
            new UpdatableClient(Node.getInstance().getAddress(), addr).sendAllData(SharedData.getData());
            logger.log(Level.INFO, "[- CS] sent new data to {0}", addr);
        }
    }

    public void awaitReplies() {
        logger.log(Level.INFO, "[CS] awaiting {0} replies; Node clock: {1}", new Object[]{SharedData.getSizeNecessaryForUpdate(), maxClock});
        try {
            replyLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.severe("InterruptedException while waiting for replies");
        }
    }

    private synchronized boolean isDelay(int timestamp, long requestedNodeId) {
        if(Node.getInstance().getState().equals(NodeState.HOLDING)){
            return true;
        }
        if(Node.getInstance().getState().equals(NodeState.REQUESTING) &&
                ((nodeClock.isLessThan(timestamp))
                || (nodeClock.isEqual(timestamp) && requestedNodeId > Node.getInstance().getAddress().getId())))
        {
            return true;
        }
        return false;
    }


        private record DelayedRequest(Address requestingNodeAddress, int timestamp) {

        @Override
            public String toString() {
                return requestingNodeAddress.toString();
            }
        }
}