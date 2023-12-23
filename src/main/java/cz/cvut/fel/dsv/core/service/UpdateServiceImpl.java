package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.DsvRemote;
import cz.cvut.fel.dsv.core.data.NodeState;
import cz.cvut.fel.dsv.core.infrastructure.Config;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.Message;
import generated.PermissionRequest;
import generated.UpdateServiceGrpc;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;
import static cz.cvut.fel.dsv.utils.Utils.tryToSleep;

public class UpdateServiceImpl extends generated.UpdateServiceGrpc.UpdateServiceImplBase {

    private static final Logger logger = DsvLogger.getLogger("UPDATE SERVICE", ANSI_PURPLE_SERVICE, UpdateServiceImpl.class);
    private final Node node = Node.getInstance();
    private final Queue<DsvRemote> dsvRemotes = new LinkedList<>();
    private AtomicInteger myClock = new AtomicInteger(0);
    private AtomicInteger maxClock = new AtomicInteger(0);
    private AtomicInteger responseCount = new AtomicInteger(0);
    private final RemoteServiceImpl remoteService;
    private Map<String, DsvPair<Address, Address>> tempMap;
    private List<Address> tempNodeAddresses;


    public UpdateServiceImpl(RemoteServiceImpl remoteService, ElectionServiceImpl electionService) {
        this.remoteService = remoteService;
        electionService.setUpdateService(this);
    }

    @Override
    public void receiveRoom(generated.RoomEntry request, StreamObserver<generated.Message> responseObserver) {
        logger.info("Receiving room [" + request.getRoomOwner().getUsername() + " - " + request.getRoomOwner().getHostname() + ":" + request.getRoomOwner().getPort() + " - " + request.getRoomName() + "]");
        var leader = Utils.Mapper.remoteToAddress(request.getRoomOwner());
        var backupNode = Utils.Mapper.remoteToAddress(request.getRoomBackup());
        node.getRoomsAndLeaders().put(request.getRoomName(), DsvPair.of(leader, backupNode));

        responseObserver.onNext(generated.Message.newBuilder().setMsg("Response from " +node.getAddress().getHostname()+":"+node.getAddress().getPort()+ " - OK: Room "+request.getRoomName()+" received").build());
        responseObserver.onCompleted();
        unblock();
    }

    private void unblock() {
        if (!node.getState().equals(NodeState.RELEASED)) {
            logger.info("Unblock node");
            node.setState(NodeState.RELEASED);
        }
    }

    @Override
    public void receiveRooms(generated.Rooms request, StreamObserver<generated.Message> responseObserver) {

        logger.info("Receiving rooms. Count = " + request.getRoomsCount() + ": ");
        request.getRoomsList().forEach((key) -> logger.info("\t\t[" +
                key.getRoomOwner().getHostname() + ":" + key.getRoomOwner().getPort() + " - " + key.getRoomName() + "]" +
                " [" + key.getRoomBackup().getHostname() + ":" + key.getRoomBackup().getPort() + "]"));

        for (var room : request.getRoomsList()) {
            var leader = Utils.Mapper.remoteToAddress(room.getRoomOwner());
            var backupNode = Utils.Mapper.remoteToAddress(room.getRoomBackup());
            node.getRoomsAndLeaders().put(room.getRoomName(), DsvPair.of(leader, backupNode));
        }

        updateBackupNode(request.getIsNotVisited());
        unblock();

        responseObserver.onNext(generated.Message.newBuilder().setMsg("Response from " +node.getAddress().getHostname()+":"+node.getAddress().getPort()+ " - OK: Rooms received").build());
        responseObserver.onCompleted();
    }

    @Override
    public void removeRoom(generated.RoomEntry request, StreamObserver<generated.Message> responseObserver) {
        logger.info("Remove room [" + request.getRoomName() + "]");
        node.getRoomsAndLeaders().remove(request.getRoomName());
        responseObserver.onNext(generated.Message.newBuilder().setMsg("Response from " +node.getAddress().getHostname()+":"+node.getAddress().getPort()+ " - OK: Room "+request.getRoomName()+" removed").build());
        responseObserver.onCompleted();
    }


    @Override
    public void updateRoomsTable(generated.Empty request, StreamObserver<generated.GrantMessage> responseObserver) {
        boolean value = makeUpdateRoomsTable(node.getRoomsAndLeaders(), getCurrentLeadersAddresses());
        responseObserver.onNext(generated.GrantMessage.newBuilder().setGrant(value).build());
        responseObserver.onCompleted();
    }


    protected boolean makeUpdateRoomsTable(Map<String, DsvPair<Address, Address>> toSend, List<Address> nodeAddresses) {
        int leaderSize = nodeAddresses.size();
        if (leaderSize > 0) {
            node.setState(NodeState.REQUESTING);
            tempMap = toSend;
            tempNodeAddresses = nodeAddresses;
            myClock.set(maxClock.incrementAndGet());
            logger.info("*[CS] Start CS update tables*");
            logger.info("[CS] send requests to nodes, size = [" + leaderSize + "]; maxClock: " + maxClock + ", nodeClock: " + myClock);
            boolean joinedInCSFlag = true;
            for (var node : nodeAddresses) {
                if(!node.equals(this.node.getAddress())) {
                    try {
                        logger.info("\t\t-[CS] send request to node " + node);
                        if (!UpdateServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node))
                                .receivePermissionRequest(PermissionRequest.newBuilder()
                                        .setRequestByRemote(Utils.Mapper.nodeToRemote())
                                        .setClock(myClock.get())
                                        .build()).getGrant()) {
                            joinedInCSFlag = false;
                        }
                    } catch (StatusRuntimeException exc) {
                        logger.info("\t\t-[CS] node " + node + " is not responding, removing from table...");
                        for (var val : tempMap.entrySet()) {
                            if (tempMap.get(val.getKey()).getKey().equals(node)) {
                                tempMap.remove(val.getKey());
                            }
                        }
                        return joinedInCSFlag;
                    }
                }
            }
        }
        else{
            logger.warning("Update tables is aborted, size of list with nodes addresses is 0");
        }

        return false;
    }

    protected void updateBackupNode(boolean isNotVisited) {
            // Updating backup node via leader
            if (node.getIsLeader().getKey()) {
                if (!node.getDsvNeighbours().getPrev().equals(node.getAddress())) {
                    logger.info("Updating backup node [" + node.getDsvNeighbours().getPrev() + "]");
                    node.getRoomsAndLeaders().put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), node.getDsvNeighbours().getPrev()));
                    Message resp = UpdateServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getDsvNeighbours().getPrev()))
                            .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(node.getRoomsAndLeaders(), false));
                    logger.info(resp.getMsg());
                } else { // When only a leader in a room. Prev on the leader is leader
                    node.getRoomsAndLeaders().put(node.getCurrentRoom(), DsvPair.of(node.getAddress(), node.getDsvNeighbours().getPrev()));
                }
            }
            remoteService.notifyJoinObservers();
    }

    @Override
    public void receivePermissionRequest(generated.PermissionRequest request, StreamObserver<generated.GrantMessage> responseObserver) {
            synchronized (this) {
                boolean grant = receivePermissionRequestHelper(request);
                responseObserver.onNext(generated.GrantMessage.newBuilder().setGrant(grant).build());
                responseObserver.onCompleted();
            }
    }

    private boolean receivePermissionRequestHelper(PermissionRequest request) {
        maxClock = new AtomicInteger(Math.max(maxClock.get(), request.getClock()));
        maxClock.incrementAndGet();

        if (isDelay(request)) {
            logger.info("[CS] request by [" + request.getRequestByRemote().getUsername() +
                    " - " + request.getRequestByRemote().getHostname() + ":" + request.getRequestByRemote().getPort() + "] is delayed; maxClock: " + maxClock + ", nodeClock: " + myClock);
            dsvRemotes.add(DsvRemote.builder()
                    .address(Utils.Mapper.remoteToAddress(request.getRequestByRemote()))
                    .username(request.getRequestByRemote().getUsername())
                    .isRequesting(true)
                    .build());
            return false;
        } else {
            logger.info("[CS] request is granted to [" + request.getRequestByRemote().getUsername() +
                    " - " + request.getRequestByRemote().getHostname() + ":" + request.getRequestByRemote().getPort() + "], maxClock: " + maxClock + ", nodeClock: " + myClock);
            node.setState(NodeState.HOLDING);
            UpdateServiceGrpc.newFutureStub(Utils.Skeleton.buildChannel(request.getRequestByRemote()))
                    .receivePermissionResponse(generated.PermissionResponse.newBuilder()
                            .setGranted(true)
                            .setResponseByRemote(Utils.Mapper.nodeToRemote())
                            .build());
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

                int necessarySize = tempNodeAddresses.size();

                logger.info("Response count to necessary size [ " + responseCount.get() + " : " + necessarySize + " ]");
                if (responseCount.get() == necessarySize) {
                    logger.info("[CS] responseCount = [" + responseCount + "] is equal to requested nodes size = [" + necessarySize + "]; Sending rooms; maxClock: " + maxClock + " nodeClock: " + myClock);
                    node.setState(NodeState.HOLDING);
                    responseCount.set(0);

                        for (var n : tempMap.keySet()) {
                            tryToSleep(Config.SLEEP_TIME_IN_CS_S);
                            try {
                                generated.UpdateServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(tempMap.get(n).getKey()))
                                        .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(tempMap, true));
                            }catch (StatusRuntimeException e) {
                                node.getRoomsAndLeaders().remove(n);
                                makeUpdateRoomsTable(tempMap, getCurrentLeadersAddresses());
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

    public List<Address> getCurrentLeadersAddresses() {
        return node.getRoomsAndLeaders().values().stream().filter(n -> !n.getKey().equals(node.getAddress())).map(DsvPair::getKey).toList();
    }

    private UpdateServiceImpl(RemoteServiceImpl remoteService) {
        this.remoteService = remoteService;
    }
}
