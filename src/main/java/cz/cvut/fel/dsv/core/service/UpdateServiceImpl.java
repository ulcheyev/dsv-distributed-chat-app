package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.DsvThreadPool;
import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.DsvRemote;
import cz.cvut.fel.dsv.core.data.NodeState;
import cz.cvut.fel.dsv.core.infrastructure.Config;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.PermissionRequest;
import generated.UpdateServiceGrpc;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;
import static cz.cvut.fel.dsv.utils.Utils.tryToSleep;

public class UpdateServiceImpl extends generated.UpdateServiceGrpc.UpdateServiceImplBase {

    private final Queue<DsvRemote> dsvRemotes = new LinkedList<>(); // TODO

    private static final Logger logger = DsvLogger.getLogger("UPDATE SERVICE", ANSI_PURPLE_SERVICE, UpdateServiceImpl.class);
    private Node node;
    private AtomicInteger myClock = new AtomicInteger(0);
    private AtomicInteger maxClock = new AtomicInteger(0);
    private AtomicInteger responseCount = new AtomicInteger(0);
    private Queue<Function<List<Address>, Boolean>> completableFutureQueue = new LinkedList<>();
    private final RemoteServiceImpl remoteService;

    public UpdateServiceImpl(Node node, RemoteServiceImpl remoteService, ElectionServiceImpl electionService) {
        this.node = node;
        this.remoteService = remoteService;
        electionService.setUpdateService(this);
    }

    @Override
    public void receiveRoom(generated.RoomEntry request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Receiving room [" + request.getRoomOwner().getUsername() + " - " + request.getRoomOwner().getHostname() + ":" + request.getRoomOwner().getPort() + " - " + request.getRoomName() + "]");
        var leader = Utils.Mapper.remoteToAddress(request.getRoomOwner());
        var backupNode = Utils.Mapper.remoteToAddress(request.getRoomBackup());
        node.getRoomsAndLeaders().put(request.getRoomName(), new DsvPair<>(leader, backupNode));

        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
        unblock();
    }

    private void unblock() {
        if(!node.getState().equals(NodeState.RELEASED)){
            logger.info("Unblock node");
            node.setState(NodeState.RELEASED);
            remoteService.notifyJoinObservers();
            try {
                completableFutureQueue.remove().apply(getLeadersAddresses());
            } catch (Exception e) {}
        }
    }

    @Override
    public void receiveRooms(generated.Rooms request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Receiving rooms. Count = " + request.getRoomsCount() + ": ");
        request.getRoomsList().forEach((key) -> logger.info("\t\t[" +
                key.getRoomOwner().getHostname() + ":" + key.getRoomOwner().getPort() + " - " + key.getRoomName() + "]" +
                " [" + key.getRoomBackup().getHostname() + ":" + key.getRoomBackup().getPort() + "]"));

        for (var room : request.getRoomsList()) {
            var leader = Utils.Mapper.remoteToAddress(room.getRoomOwner());
            var backupNode = Utils.Mapper.remoteToAddress(room.getRoomBackup());
            node.getRoomsAndLeaders()
                    .put(room.getRoomName(), new DsvPair<>(leader, backupNode));
        }

        updateBackupNode(request.getIsNotVisited());

        unblock();
        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void removeRoom(generated.RoomEntry request, StreamObserver<generated.Empty> responseObserver) {
        logger.info("Delete room [" + request.getRoomName() + "]");
        node.getRoomsAndLeaders().remove(request.getRoomName());
        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }


    @Override
    public void updateRoomsTable(generated.Empty request, StreamObserver<generated.GrantMessage> responseObserver) {
        boolean value = makeUpdateRoomsTable(getLeadersAddresses());
        responseObserver.onNext(generated.GrantMessage.newBuilder().setGrant(value).build());
        responseObserver.onCompleted();
    }


    protected boolean makeUpdateRoomsTable(List<Address> leadersAddresses) {
        if(!node.getState().equals(NodeState.RELEASED)){
            logger.info("makeUpdateRoomsTable is delayed...");
            Function<List<Address>, Boolean> foo = this::makeUpdateRoomsTable;
            completableFutureQueue.add(foo);
        }else{
            logger.info("*[CS] Start CS update tables: *");
            node.setState(NodeState.REQUESTING);
            myClock.set(maxClock.incrementAndGet());
            int leaderSize = leadersAddresses.size();
            if (leaderSize > 0 ) {
                logger.info("[CS] send requests to nodes, size = [" + leaderSize + "]; maxClock: " + maxClock + ", nodeClock: " + myClock);
                boolean joinedInCSFlag = true;
                for (var key : leadersAddresses) {
                    try {
                        logger.info("\t\t-[CS] send request to node " + key);
                        if(!UpdateServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(key))
                                .receivePermissionRequest(PermissionRequest.newBuilder()
                                        .setRequestByRemote(Utils.Mapper.nodeToRemote(node))
                                        .setClock(myClock.get())
                                        .build()).getGrant())
                        {
                            joinedInCSFlag = false;
                        }
                    } catch (StatusRuntimeException exc) {
                        logger.info("\t\t-[CS] node " + key + " is not responding, removing...");
                        leadersAddresses.remove(key);
                    }
                }

                return joinedInCSFlag;
            } else {
                logger.info("Leaders size is less than 0");
                unblock();
                return true;
            }
        }
        return false;
    }

    protected void updateBackupNode(boolean isNotVisited) {
            // Updating backup node via leader
            if (node.getIsLeader().getKey()) {
                if (!node.getDsvNeighbours().getPrev().equals(node.getAddress())) {
                    logger.info("Updating backup node [" + node.getDsvNeighbours().getPrev() + "]");
                    node.getRoomsAndLeaders().put(node.getCurrentRoom(), new DsvPair<>(node.getAddress(), node.getDsvNeighbours().getPrev()));
                    DsvThreadPool.execute(() -> generated.UpdateServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getPrev()))
                            .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(node.getRoomsAndLeaders(), false)));
                } else { // When only a leader in a room. Prev on the leader is leader
                    node.getRoomsAndLeaders().put(node.getCurrentRoom(), new DsvPair<>(node.getAddress(), node.getDsvNeighbours().getPrev()));
                }
            }
            remoteService.notifyJoinObservers();
    }


    @Override
    public void receivePermissionRequest(generated.PermissionRequest request, StreamObserver<generated.GrantMessage> responseObserver) {
            synchronized (this) {
                boolean grant = receivePermissionRequestHelper(request);
                logger.info("Sending " + grant);
                responseObserver.onNext(generated.GrantMessage.newBuilder().setGrant(grant).build());
                responseObserver.onCompleted();
            }
    }

    private boolean receivePermissionRequestHelper(PermissionRequest request) {
        maxClock = new AtomicInteger(Math.max(maxClock.get(), request.getClock()));
        maxClock.incrementAndGet();

        if (isDelay(request))
        {
            logger.info("[CS] request by [" + request.getRequestByRemote().getUsername() +
                    " - " + request.getRequestByRemote().getHostname() + ":" + request.getRequestByRemote().getPort() + "] is delayed; maxClock: " + maxClock + ", nodeClock: " + myClock);
            dsvRemotes.add(DsvRemote.builder()
                    .address(Utils.Mapper.remoteToAddress(request.getRequestByRemote()))
                    .username(request.getRequestByRemote().getUsername())
                    .isRequesting(true)
                    .build());
            return false;
        } else
        {
            logger.info("[CS] request is granted to [" + request.getRequestByRemote().getUsername() +
                    " - " + request.getRequestByRemote().getHostname() + ":" + request.getRequestByRemote().getPort() + "], maxClock: " + maxClock + ", nodeClock: " + myClock);
            DsvThreadPool.execute(() -> UpdateServiceGrpc.newFutureStub(Utils.Skeleton.buildChannel(request.getRequestByRemote()))
                    .receivePermissionResponse(generated.PermissionResponse.newBuilder()
                            .setGranted(true)
                            .setResponseByRemote(Utils.Mapper.nodeToRemote(node))
                            .build()));
            node.setState(NodeState.HOLDING);
            return true;
        }
    }


    @Override
    public void receivePermissionResponse(generated.PermissionResponse request, StreamObserver<generated.Empty> responseObserver) {
            synchronized (this) {
                logger.info("[CS] received permission response by [" + request.getResponseByRemote().getUsername() +
                        " - " + request.getResponseByRemote().getHostname() + ":" + request.getResponseByRemote().getPort() + "]");
                responseCount.incrementAndGet();

                var copyOfTable = new ConcurrentHashMap<>(node.getRoomsAndLeaders());
                int necessarySize = copyOfTable.size();

                logger.info("Response count to necessary size [ " + responseCount.get() + " : " + necessarySize + " ]");
                if (responseCount.get() == necessarySize) {
                    logger.info("[CS] responseCount = [" + responseCount + "] is equal to requested nodes size = [" + necessarySize + "]; Sending rooms; maxClock: " + maxClock + " nodeClock: " + myClock);
                    node.setState(NodeState.HOLDING);
                    responseCount.set(0);
                    DsvThreadPool.execute(() -> {

                        for (var n : copyOfTable.keySet()) {
                            tryToSleep(Config.SLEEP_TIME_IN_CS_S);
                            try {
                                generated.UpdateServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(copyOfTable.get(n).getKey()))
                                        .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(copyOfTable, true));
                            }catch (StatusRuntimeException e) {
                                node.getRoomsAndLeaders().remove(n);
                                makeUpdateRoomsTable(getLeadersAddresses());
                                // todo update
                            }
                        }

                        logger.info("*[CS] End CS update tables*");

                        while (!dsvRemotes.isEmpty()){
                            var cur = dsvRemotes.remove();
                            if (cur.getIsRequesting()) {
                                logger.info("[CS] send permission to requesting node: " + cur.getAddress() + "; maxClock: " + maxClock + ", nodeClock: " + myClock);
                                receivePermissionRequestHelper(generated.PermissionRequest.newBuilder().setRequestByRemote(Utils.Mapper.dsvRemoteToRemote(cur)).build());
                                break;
                            }
                        }
                    });
                }
            }
            responseObserver.onNext(generated.Empty.getDefaultInstance());
            responseObserver.onCompleted();
    }



    private boolean isDelay(generated.PermissionRequest request) {
        return (node.getState() != NodeState.RELEASED)
                &&
                ((request.getClock() > myClock.get())
                        || (request.getClock() == myClock.get() && request.getRequestByRemote().getNodeId()
                        > node.getAddress().getId()));
    }


    public List<Address> getLeadersAddresses() {
        return node.getRoomsAndLeaders().values().stream().map(DsvPair::getKey).toList();
    }

    private UpdateServiceImpl(RemoteServiceImpl remoteService) {
        this.remoteService = remoteService;
    }
}
