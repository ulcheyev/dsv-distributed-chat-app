package cz.cvut.fel.dsv.core.service.MEUtils;

import cz.cvut.fel.dsv.core.DsvThreadPool;
import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.NodeState;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.core.service.LEUtils.LEManager;
import cz.cvut.fel.dsv.core.service.clients.ElectionClient;
import cz.cvut.fel.dsv.core.service.clients.RemoteClient;
import cz.cvut.fel.dsv.core.service.clients.UpdatableClient;
import cz.cvut.fel.dsv.utils.DsvConditionLock;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.DynamicCountDownLatch;
import cz.cvut.fel.dsv.utils.Utils;
import io.grpc.StatusRuntimeException;
import org.checkerframework.checker.units.qual.A;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class CSManager {
    private static final Logger logger = DsvLogger.getLogger("CS MANAGER", ANSI_PURPLE_SERVICE, CSManager.class);
    private static CSManager INSTANCE;
    private final TimesTamp maxClock;
    private final TimesTamp nodeClock;
    private int replyCount;
    private final Set<DelayedRequest> permitDelayedRequests = new LinkedHashSet<>();
    private DynamicCountDownLatch awaitingResponsesLock;
    private final Lock csLock = new ReentrantLock();
    private final Condition csCondition = csLock.newCondition();
    private boolean inCriticalSection = false;
    private final AtomicLong requestIdCounter;
    private Integer delayOfCurrentCs;


    private CSManager() {
        maxClock = new TimesTamp();
        nodeClock = new TimesTamp();
        requestIdCounter = new AtomicLong(0);
        awaitingResponsesLock = new DynamicCountDownLatch(0);
    }


    public static CSManager getInstance() {
        if (INSTANCE == null) {
            synchronized (CSManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CSManager();
                }
            }
        }
        return INSTANCE;
    }

    public synchronized void requestCriticalSection(Integer delay) {
        replyCount = 0;
        maxClock.update();
        int necessaryReplyCount = SharedData.getSizeNecessaryForUpdate();
        awaitingResponsesLock.setCount(necessaryReplyCount);
        delayOfCurrentCs = delay;
        nodeClock.setClock(maxClock);
        int currentIntValueOfClock = nodeClock.getClock();
        Node.getInstance().setState(NodeState.REQUESTING);
        Long requestId = requestIdCounter.incrementAndGet();
        logger.log(Level.INFO, "[CS] Requesting nodes to enter CS. Clock: {0}. Need {1} replies", new Object[]{currentIntValueOfClock, necessaryReplyCount});
        for (var remoteNodeAddr : SharedData.getNodeAddressesWithoutCurrent()) {
            logger.log(Level.INFO, "[- CS] Request sending to {0}", remoteNodeAddr);
            sendPermissionRequest(remoteNodeAddr, currentIntValueOfClock, delay, requestId);
        }
    }

    private void sendPermissionRequest(Address remoteNodeAddr, int currentIntValueOfClock, Integer delay, Long requestId) {
        CompletableFuture.runAsync(() -> {
            try {
                new UpdatableClient(Node.getInstance().getAddress(), remoteNodeAddr)
                        .sendRequestCriticalSection(currentIntValueOfClock, delay, requestId)
                        .clear();
            } catch (Exception e) {
                handleTimeout(remoteNodeAddr, delay, requestId);
            }
        });
    }

    private void handleTimeout(Address remoteNodeAddr, Integer delay, Long requestId) {
        logger.log(Level.WARNING, "[- CS] Timeout for {0}, Beat checking...", remoteNodeAddr);
        try {
            new RemoteClient(Node.getInstance().getAddress(), remoteNodeAddr).tryToLocateWithBeat();
            logger.log(Level.WARNING, "[- CS] Timeout for {0}, Beat OK; Sending request again...", remoteNodeAddr);
            int decrementedDelay = Math.max(0, delay - 1);
            sendPermissionRequest(remoteNodeAddr, nodeClock.getClock(), decrementedDelay, requestId);
        } catch (StatusRuntimeException e) {
            handleBeatFailure(remoteNodeAddr);
        }
    }

    private  void handleBeatFailure(Address remoteNodeAddr) {
        logger.log(Level.WARNING, "[- CS] Failed beat checking for {0};...", remoteNodeAddr);
        // check back up
        var byLeaderAddress = SharedData.getByLeaderAddress(remoteNodeAddr);
        Address backup = byLeaderAddress.getValue().getValue();
        String room = byLeaderAddress.getKey();
        SharedData.removeByLeaderAddress(remoteNodeAddr);
        if(!backup.equals(remoteNodeAddr)) {
            logger.log(Level.WARNING, "[- CS] start election on {0};...", backup);
            ElectionClient electionClient = new ElectionClient(Node.getInstance().getAddress(), backup);
            electionClient.sendStartRepairTopology(remoteNodeAddr);
            electionClient.sendStartElection(backup);
            electionClient.clear();
            replyCount++;
        }
        checkForPermit();
    }

    public synchronized void receiveRequest(Address requestingNodeAddress, int timestamp, int delay, Long requestId) {
        awaitCs();
        logger.log(Level.INFO, "[CS] request by {0} is received; Delay: {1}", new Object[]{requestingNodeAddress, delay});
        Utils.tryToSleep(delay);
        boolean isWillDelay = isDelay(timestamp, requestingNodeAddress.getId());
        maxClock.receiveMsg(timestamp);
        if (!isWillDelay) {
            try {
                new UpdatableClient(Node.getInstance().getAddress(), requestingNodeAddress)
                        .sendPermitCriticalSection(requestId)
                        .clear();
                logger.log(Level.INFO, "[CS] request by {0} is permitted; Request clock: {1}; Node clock: {2}; State: {3}",
                        new Object[]{requestingNodeAddress, timestamp, nodeClock.getClock(), Node.getInstance().getState()});
            } catch (StatusRuntimeException e) {
                logger.log(Level.INFO, "[CS] request by {0} is cancelled; {1}",
                        new Object[]{requestingNodeAddress, e.getMessage()});
            }
        } else {
            DelayedRequest delayedReq = new DelayedRequest(requestingNodeAddress, timestamp, requestId);
            synchronized (permitDelayedRequests) {
                if (permitDelayedRequests.add(delayedReq)) {
                    logger.log(Level.INFO, "[CS] request by {0} is delayed; Request clock: {1}; Node clock: {2}; State: {3}",
                            new Object[]{requestingNodeAddress, timestamp, nodeClock.getClock(), Node.getInstance().getState()});
                } else {
                    logger.log(Level.INFO, "[CS] Duplicate request by {0} is ignored; Request clock: {1}; Node clock: {2}; State: {3}",
                            new Object[]{requestingNodeAddress, timestamp, nodeClock.getClock(), Node.getInstance().getState()});
                }
            }
        }
    }

    public void receivePermit(Long requestId) {
        if (requestIdCounter.get() == requestId) {
            replyCount++;
            checkForPermit();
        } else {
            logger.log(Level.WARNING, "Permit was ignored; expected id: {0}, was: {1}", new Object[]{requestIdCounter.get(), requestId});
        }
    }

    private synchronized void checkForPermit() {
        awaitingResponsesLock.countDown();
        if (Node.getInstance().getState().equals(NodeState.HOLDING)) {
            logger.log(Level.WARNING, "Already IN CS");
            return;
        }
        int necessarySize = SharedData.getSizeNecessaryForUpdate();
//        int awaitingReplies = necessarySize - replyCount; // TODO: meow meow =3
//        logger.log(Level.INFO, "[CS] Awaiting {0}, Current {1}", new Object[]{awaitingReplies, replyCount});
        if (replyCount == necessarySize) {
            logger.log(Level.WARNING, "[CS] Necessary {0}, Current {1}", new Object[]{necessarySize, replyCount});
            enterCriticalSection();
        }
    }

    public void awaitCs() {
        csLock.lock();
        try {
            while (inCriticalSection) {
                csCondition.await();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.severe("InterruptedException while waiting for critical section");
        } finally {
            csLock.unlock();
        }
    }

    private synchronized void processDeferredRequests() {
        synchronized (permitDelayedRequests) {
            Iterator<DelayedRequest> iterator = permitDelayedRequests.iterator();
            while (iterator.hasNext()) {
                DelayedRequest delayedRequest = iterator.next();
                iterator.remove();
                logger.log(Level.INFO, "[CS] Processing delayed request {0}", delayedRequest);
                try {
                    new UpdatableClient(Node.getInstance().getAddress(), delayedRequest.requestingNodeAddress())
                            .sendPermitCriticalSection(delayedRequest.id)
                            .clear();
                } catch (StatusRuntimeException e) {
                    logger.log(Level.SEVERE, "Error while process deferred request: {0}", e.getMessage());
                }
            }
        }
    }

    public void receiveRelease() {
        inCriticalSection = false;
        Node.getInstance().setState(NodeState.RELEASED);
        processDeferredRequests();
    }

    private void enterCriticalSection() {
        replyCount = 0;
        Node.getInstance().setState(NodeState.HOLDING);
        logger.log(Level.INFO, "[CS] Node {0} entered critical section", Node.getInstance().getAddress());
    }

    public synchronized void broadcastDataUpdate() {
        logger.log(Level.INFO, "[CS] broadcast update on leaders. Size = {0}", SharedData.getSizeNecessaryForUpdate());
        for (var addr : SharedData.getNodeAddressesWithoutCurrent()) {
            logger.log(Level.INFO, "[- CS] sending new data to {0}", addr);
            try {
                new UpdatableClient(Node.getInstance().getAddress(), addr)
                        .sendAllData(SharedData.getData())
                        .clear();
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "Node {0} is down while broadcast", addr);
            }
        }
    }

    public void awaitReplies() {
        logger.log(Level.INFO, "[CS] awaiting {0} replies; Node clock: {1}", new Object[]{SharedData.getSizeNecessaryForUpdate(), maxClock});
        try {
            awaitingResponsesLock.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.severe("InterruptedException while waiting for replies");
        }
    }

    private synchronized boolean isDelay(int timestamp, long requestedNodeId) {
        if (Node.getInstance().getState().equals(NodeState.HOLDING)) {
            return true;
        }
        return Node.getInstance().getState().equals(NodeState.REQUESTING)
                && ((nodeClock.isLessThan(timestamp))
                || (nodeClock.isEqual(timestamp) && requestedNodeId > Node.getInstance().getAddress().getId()));
    }

    public void dataReceived() {
        if (Node.getInstance().getState().equals(NodeState.REQUESTING)) {
            requestCriticalSection(delayOfCurrentCs);
        }
    }

    private record DelayedRequest(Address requestingNodeAddress, int timestamp, Long id) {

        @Override
        public String toString() {
            return requestingNodeAddress.toString();
        }
    }
}