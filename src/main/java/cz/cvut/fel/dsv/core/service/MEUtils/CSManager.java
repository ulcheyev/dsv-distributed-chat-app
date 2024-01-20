package cz.cvut.fel.dsv.core.service.MEUtils;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.NodeState;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.core.service.clients.RemoteClient;
import cz.cvut.fel.dsv.core.service.clients.UpdatableClient;
import cz.cvut.fel.dsv.utils.DsvConditionLock;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.DynamicCountDownLatch;
import cz.cvut.fel.dsv.utils.Utils;
import io.grpc.Context;
import io.grpc.StatusRuntimeException;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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
    private static final Set<DelayedRequest> permitDelayedRequests = new LinkedHashSet<>();
    private static final DynamicCountDownLatch awaitingResponsesLock = new DynamicCountDownLatch(0);
    private static final Lock csLock = new ReentrantLock();
    private static final Condition csCondition = csLock.newCondition();
    private static final AtomicBoolean inCriticalSection = new AtomicBoolean(false);
    private static final AtomicLong requestIdCounter = new AtomicLong(0);
    private static final AtomicInteger delayOfCurrentCs = new AtomicInteger(0);
    private static final DsvConditionLock lock = new DsvConditionLock(true);
    private static final TimesTamp maxClock = new TimesTamp();
    private static final TimesTamp nodeClock = new TimesTamp();
    private static final AtomicInteger replyCount = new AtomicInteger(0);
    private static final AtomicInteger necessaryCount = new AtomicInteger(0);
    private static final List<Address> receivedPermissionsBy = new ArrayList<>();
    private static final long CHECK_INTERVAL_MS = 5000;

    private CSManager() {}


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
        lock.await();
        lock.lock();
        executeSendingRequests(delay);
    }

    private void executeSendingRequests(Integer delay) {
        receivedPermissionsBy.clear();
        replyCount.set(0);
        maxClock.update();
        var listWithLeaders = SharedData.getNodeAddressesWithoutCurrent();
        int necessCount = listWithLeaders.size();

        necessaryCount.set(necessCount);
        awaitingResponsesLock.setCount(necessCount);
        delayOfCurrentCs.set(delay);
        nodeClock.setClock(maxClock);

        Node.getInstance().setState(NodeState.REQUESTING);
        int currentIntValueOfClock = nodeClock.getClock();
        Long requestId = requestIdCounter.incrementAndGet();

        logger.log(Level.INFO, "[CS] Requesting nodes to enter CS. Clock: {0}. Need {1} replies", new Object[]{currentIntValueOfClock, necessCount});
        for (var remoteNodeAddr : SharedData.getNodeAddressesWithoutCurrent()) {
            logger.log(Level.INFO, "[- CS] Request sending to {0}", remoteNodeAddr);
            sendPermissionRequest(remoteNodeAddr, currentIntValueOfClock, delay, requestId);
        }
    }

    private void sendPermissionRequest(Address remoteNodeAddr, int currentIntValueOfClock, Integer delay, Long requestId) {
        try {
            new UpdatableClient(Node.getInstance().getAddress(), remoteNodeAddr)
                    .sendRequestCriticalSection(currentIntValueOfClock, delay, requestId);
        } catch (Exception e) {
            handleTimeout(remoteNodeAddr);
        }
    }


    private void startNodeCheckingTask(Address remoteNodeAddr) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            int numberOfRepetitions = 10;
            @Override
            public void run() {
                try {
                    if(receivedPermissionsBy.contains(remoteNodeAddr)){
                        logger.log(Level.INFO, "[CS] Stop waiting for node {0} response.", remoteNodeAddr);
                        executorService.shutdown();
                        return;
                    }

                    new RemoteClient(Node.getInstance().getAddress(), remoteNodeAddr)
                            .sendTryLocateWithBeat();

                    logger.log(Level.WARNING, "[- CS] Timeout for {0}, Beat OK; ", remoteNodeAddr);
                    numberOfRepetitions--;
                    if (numberOfRepetitions == 0) {
                        logger.log(Level.WARNING, "[CS] Node is node responding until {} requests. Handling as failure...", numberOfRepetitions);
                        handleBeatFailure(remoteNodeAddr);
                        executorService.shutdown();
                    }
                } catch (StatusRuntimeException e) {
                    handleBeatFailure(remoteNodeAddr);
                }
            }
        }, 0, CHECK_INTERVAL_MS, TimeUnit.MILLISECONDS);
    }

    private void handleTimeout(Address remoteNodeAddr) {
        logger.log(Level.WARNING, "[- CS] Timeout for {0}, Beat checking...", remoteNodeAddr);
        try {
            new RemoteClient(Node.getInstance().getAddress(), remoteNodeAddr)
                    .sendTryLocateWithBeat();
            logger.log(Level.WARNING, "[- CS] Timeout for {0}, Beat OK; ", remoteNodeAddr);
            startNodeCheckingTask(remoteNodeAddr);
        } catch (StatusRuntimeException e) {
            handleBeatFailure(remoteNodeAddr);
        }
    }

    private void handleBeatFailure(Address remoteNodeAddr) {
        logger.log(Level.WARNING, "[- CS] Failed beat checking for {0};...", remoteNodeAddr);
        var byLeaderAddress = SharedData.getByLeaderAddress(remoteNodeAddr);
        Address backup = byLeaderAddress.getValue().getValue();
        SharedData.removeByLeaderAddress(remoteNodeAddr);
        if(!backup.equals(remoteNodeAddr)) {
            SharedData.put(byLeaderAddress.getKey(), DsvPair.of(backup, backup));
        }
        checkForPermit();
    }

    public synchronized void receiveRequest(Address requestingNodeAddress, int timestamp, int delay, Long requestId) {
        logger.log(Level.INFO, "[CS] request by {0} is received; Delay: {1}", new Object[]{requestingNodeAddress, delay});
        Utils.tryToSleep(delay);
        boolean isWillDelay = isDelay(timestamp, requestingNodeAddress.getId());
        maxClock.receiveMsg(timestamp);
        if (!isWillDelay) {
            try {
                Context.current().fork().run(() -> {
                    new UpdatableClient(Node.getInstance().getAddress(), requestingNodeAddress)
                            .sendPermitCriticalSection(requestId);
                });
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

    public void receivePermit(Address byAddress, Long requestId) {
        receivedPermissionsBy.add(byAddress);
        if (requestIdCounter.get() == requestId) {
            checkForPermit();
        } else {
            logger.log(Level.WARNING, "Permit was ignored; expected id: {0}, was: {1}", new Object[]{requestIdCounter.get(), requestId});
        }
    }

    private void checkForPermit() {
        replyCount.incrementAndGet();
        awaitingResponsesLock.countDown();
        int necessCount = necessaryCount.get();
        if (replyCount.get() == necessCount) {
            logger.log(Level.WARNING, "[CS] Necessary {0}, Current {1}", new Object[]{necessCount, replyCount});
            enterCriticalSection();
        }
    }

    public void awaitCs() {
        csLock.lock();
        try {
            while (inCriticalSection.get()) {
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
                            .sendPermitCriticalSection(delayedRequest.id);
                } catch (StatusRuntimeException e) {
                    logger.log(Level.SEVERE, "Error while process deferred request: {0}", e.getMessage());
                }
            }
        }
    }

    public void receiveRelease() {
        logger.log(Level.INFO, "[CS] Release Resources");
        inCriticalSection.set(false);
        Node.getInstance().setState(NodeState.RELEASED);
        processDeferredRequests();
        lock.signal();
    }

    private void enterCriticalSection() {
        replyCount.set(0);
        inCriticalSection.set(true);
        Node.getInstance().setState(NodeState.HOLDING);
        logger.log(Level.INFO, "[CS] Node {0} entered critical section", Node.getInstance().getAddress());
    }

    public synchronized void broadcastDataUpdate(List<Address> willBeUpdatedOn, ConcurrentMap<String, DsvPair<Address, Address>> toSend) {
        logger.log(Level.INFO, "[CS] broadcast update on leaders. Size = {0}", willBeUpdatedOn.size());
        for (var addr : willBeUpdatedOn) {
            logger.log(Level.INFO, "[- CS] sending new data to {0}", addr);
            try {
                new UpdatableClient(Node.getInstance().getAddress(), addr)
                        .sendAllData(toSend);
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "Node {0} is down while broadcast", addr);
                var byAddr = Utils.Mapper.getPairByAddress(addr, toSend);
                if(byAddr.isPresent()) {
                    var map = new ConcurrentHashMap<>(toSend);
                    map.remove(byAddr.get().getKey());
                    broadcastDataUpdate(willBeUpdatedOn, map);
                }
                else {
                    logger.log(Level.WARNING, "Node {0} is not present in map to update", addr);
                }
            }
        }
    }

    public void awaitReplies() {
        logger.log(Level.INFO, "[CS] awaiting", new Object[]{SharedData.getSizeNecessaryForUpdate(), maxClock});
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
            logger.log(Level.INFO, "Table updating in REQUESTING State. Need to send permission requests again...");
            executeSendingRequests(delayOfCurrentCs.get());
        }
    }

    private record DelayedRequest(Address requestingNodeAddress, int timestamp, Long id) {

        @Override
        public String toString() {
            return requestingNodeAddress.toString();
        }
    }
}