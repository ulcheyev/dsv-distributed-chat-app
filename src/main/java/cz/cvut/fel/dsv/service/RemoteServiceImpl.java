package cz.cvut.fel.dsv.service;

import cz.cvut.fel.dsv.core.*;
import cz.cvut.fel.dsv.core.data.*;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.Empty;
import generated.Message;
import generated.PermissionRequest;
import generated.Remote;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RemoteServiceImpl extends generated.RemotesServiceGrpc.RemotesServiceImplBase {
    private static final Logger logger = DsvLogger.getLogger(RemoteServiceImpl.class);
    private Integer responseCount = 0;
    private final List<DsvRemote> dsvRemotes = new ArrayList<>(); // TODO
    private final Queue<DsvPair<generated.JoinRequest, StreamObserver<generated.JoinResponse>>> joinQueue = new LinkedList<>(); // TODO
    private int myClock = 0;
    private int maxClock = 0;
    private volatile Node node;
    private DsvThreadPool dsvThreadPool;

    public RemoteServiceImpl(Node node) {
        dsvThreadPool = DsvThreadPool.getInstance();
        this.node = node;
        node.setRemotesServiceImpl(this);
    }

    private RemoteServiceImpl() {
    }

    @Override
    public void receiveRoom(generated.RoomEntry request, StreamObserver<Empty> responseObserver) {
        String threadName = "[RECEIVE ROOM]";
        dsvThreadPool.blockingExecute(new Thread(() -> {
            logger.info("Receiving room [" + request.getRoomName() + " : " + request.getRoomOwner().getUsername() + "]");
            node.getRoomsAndLeaders().put(request.getRoomName(), Utils.Mapper.remoteToAddress(request.getRoomOwner()));

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
            node.setState(NodeState.RELEASED);
            dsvThreadPool.notifyExecutors();
        }, threadName));
    }

    @Override
    public void receiveRooms(generated.Rooms request, StreamObserver<Empty> responseObserver) {
        String threadName = "[RECEIVE ROOMS]";
        dsvThreadPool.blockingExecute(new Thread(() -> {
            logger.info("Receiving rooms. Count = " + request.getRoomsCount());
            for (var room : request.getRoomsList())
                node.getRoomsAndLeaders()
                        .put(room.getRoomName(), Utils.Mapper.remoteToAddress(room.getRoomOwner()));
            updateBackupNode();

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
            node.setState(NodeState.RELEASED);
            dsvThreadPool.notifyExecutors();
        }, threadName));
    }

    @Override
    public void removeRoom(generated.RoomEntry request, StreamObserver<Empty> responseObserver) {
        String threadName = "[REMOVE ROOM]";
        dsvThreadPool.blockingExecute(new Thread(() -> {
            logger.info("Delete room [" + request.getRoomName() + "]");
            node.getRoomsAndLeaders().remove(request.getRoomName());
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
            node.setState(NodeState.RELEASED);
            dsvThreadPool.notifyExecutors();
        }, threadName));
    }

    @Override
    public void updateRoomsTable(Empty request, StreamObserver<Empty> responseObserver) {
        makeUpdateRoomsTable();
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    private void makeUpdateRoomsTable() {
        String threadName = "[UPDATE ROOMS ON LEADERS]";
        dsvThreadPool.execute(new Thread(() -> {
            var leadersAddresses = getLeadersAddresses();
            node.setState(NodeState.REQUESTING);
            myClock = maxClock + 1;
            logger.info("[CS] send requests to leaders, size = [" + leadersAddresses.size() + "]; maxClock: " + maxClock + ", nodeClock: " + myClock);
            for (var key : leadersAddresses) {
                try {
                    Utils.Skeleton.getFutureStub(key)
                            .receivePermissionRequest(PermissionRequest.newBuilder()
                                    .setRequestByRemote(Utils.Mapper.nodeToRemote(node))
                                    .setClock(myClock)
                                    .build());
                } catch (StatusRuntimeException exc) {
                    leadersAddresses.remove(key);
                }
            }
            Utils.Skeleton.shutdown();
        }, threadName));
    }

    @Override
    public void exitRoom(Remote request, StreamObserver<Empty> responseObserver) {
        String threadName = "[EXIT ROOM]";
        dsvThreadPool.blockingExecute(new Thread(() -> {
            logger.info(request.getUsername() + " exited room " + node.getCurrentRoom());

            // If node to disconnect == leader node in room, then disconnect all nodes from current node
            // Then node will change the room in joinRoom()


            if (request.getNodeId() == node.getAddress().getId()) {

                node.getIsLeader().getValue().disconnectAllUsers();
                node.getRoomsAndLeaders().remove(node.getCurrentRoom());

                if (node.getIsLeader().getValue().getSize() == 1) {
                    for (var key : node.getRoomsAndLeaders().keySet()) {
                        try {
                            Utils.Skeleton.getSyncSkeleton(node.getRoomsAndLeaders().get(key))
                                    .removeRoom(generated.RoomEntry.newBuilder().setRoomName(node.getCurrentRoom()).build());
                        } catch (StatusRuntimeException exc) {
                            // todo
                        }

                    }
                }
            } else {
                node.getIsLeader()
                        .getValue()
                        .removeFromRoom(request.getNodeId());
            }

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
            node.setState(NodeState.RELEASED);
            dsvThreadPool.notifyExecutors();

        }, threadName));
    }

    @Override
    public void joinRoom(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
        String threadName = "[JOIN]";
        dsvThreadPool.blockingExecute(new Thread(() -> {
            logger.info("[request by Node " + request.getRemote().getUsername() + "] request to join is processing");
            // If node is leader
            if (node.getIsLeader().getKey()) {
                // Leader of requested room
                if (Objects.equals(node.getIsLeader().getValue().getRoomName(), request.getRoomName())) {
                    logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node to connect is leader");
                    responseObserver.onNext(generated.JoinResponse.newBuilder()
                            .setIsLeader(true)
                            .setLeader(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getLeader()))
                            .setNeighbours(changeNeighbours(request))
                            .build());
                }
                // Not a leader of requested room
                else {
                    logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node to connect is leader, but not leader in requested room");

                    try {
                        var leader = node.getRoomsAndLeaders().get(request.getRoomName());
                        var neighbours = Utils.Skeleton.getSyncSkeleton(leader).changeNeighbours(request);
                        responseObserver.onNext(generated.JoinResponse.newBuilder()
                                .setIsLeader(true)
                                .setLeader(Utils.Mapper.addressToRemote(leader))
                                .setNeighbours(neighbours)
                                .build());

                        logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room is exists. Find in leaders table");
                    }
                    // If not -> create the room and leader will be the sender
                    catch (NullPointerException e) {

                        logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room does not exists. Requesting node will be the leader");

                        responseObserver.onNext(generated.JoinResponse.newBuilder()
                                .setNeighbours(Utils.Mapper.remoteToNeighbours(request.getRemote()))
                                .setIsLeader(true)
                                .setLeader(request.getRemote())
                                .build());

                        // Send to leaders new room table
                        // CS - send requests
                        node.getRoomsAndLeaders().put(request.getRoomName(), Utils.Mapper.remoteToAddress(request.getRemote()));
                        makeUpdateRoomsTable();
                    }
                }
            }

            // Not a leader at all, send a leader address
            else {
                logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node is not a leader");
                try {
                    Utils.Skeleton.getSyncSkeleton(node.getDsvNeighbours().getLeader()).beat(Empty.getDefaultInstance());
                } catch (StatusRuntimeException e) {
                    node.repairAndElect(node.getAddress(), node.getDsvNeighbours().getLeader());
                }

                responseObserver.onNext(generated.JoinResponse.newBuilder()
                        .setIsLeader(false)
                        .setLeader(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getLeader()))
                        .build());
            }
            responseObserver.onCompleted();
            node.setState(NodeState.RELEASED);
            dsvThreadPool.notifyExecutors();
        }, threadName));
    }

    @Override
    public void beat(Empty request, StreamObserver<generated.Health> responseObserver) {
        responseObserver.onNext(generated.Health.newBuilder().setIsAlive(true).build());
        responseObserver.onCompleted();
    }


    @Override
    public void receiveGetRoomListRequest(Empty request, StreamObserver<generated.StringPayload> responseObserver) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ROOMS]");
        for (var room : node.getRoomsAndLeaders().keySet()) {
            sb.append("\n\t--").append(room);
        }
        responseObserver.onNext(generated.StringPayload.newBuilder()
                .setMsg(sb.toString())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void receiveGetNodeListInCurrentRoomRequest(Empty request, StreamObserver<generated.StringPayload> responseObserver) {
        responseObserver.onNext(generated.StringPayload.newBuilder()
                .setMsg(node.getIsLeader().getValue().toString())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void receiveMessage(Message request, StreamObserver<Empty> responseObserver) {
        dsvThreadPool.execute(() ->
                node.getIsLeader()
                        .getValue()
                        .sendMessageToRoom(request)
        );

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void preflight(Remote request, StreamObserver<Message> responseObserver) {
        dsvThreadPool.execute(() -> {
            logger.info("Added [" + request.getUsername() + "] to room " + "[" + node.getCurrentRoom() + "]");
            node.getIsLeader()
                    .getValue()
                    .addToRoom(new DsvPair<>(Utils.Mapper.remoteToDsvRemote(request), responseObserver));
        });
    }

    @Override
    public void receivePermissionRequest(generated.PermissionRequest request, StreamObserver<generated.Empty> responseObserver) {
        dsvThreadPool.execute(() -> {
            synchronized (RemoteServiceImpl.this) {
                maxClock = Math.max(maxClock, request.getClock());
                maxClock++;
                if (isDelay(request)) {
                    logger.info("[CS] request by " + request.getRequestByRemote().getUsername() + " is delayed, maxClock: " + maxClock + " nodeClock: " + myClock);
                    dsvRemotes.add(DsvRemote.builder()
                            .address(Utils.Mapper.remoteToAddress(request.getRequestByRemote()))
                            .username(request.getRequestByRemote().getUsername())
                            .isRequesting(true)
                            .build());
                } else {
                    logger.info("[CS] request is granted to " + request.getRequestByRemote().getUsername() + ", maxClock: " + maxClock + " nodeClock: " + myClock);
                    Utils.Skeleton.getFutureStub(Utils.Mapper.remoteToAddress(request.getRequestByRemote()))
                            .receivePermissionResponse(generated.PermissionResponse.newBuilder()
                                    .setGranted(true)
                                    .setResponseByRemote(Utils.Mapper.nodeToRemote(node))
                                    .build());
                }
                responseObserver.onNext(Empty.getDefaultInstance());
                responseObserver.onCompleted();

            }
        });
    }

    @Override
    public void receivePermissionResponse(generated.PermissionResponse request, StreamObserver<generated.Empty> responseObserver) {
        dsvThreadPool.execute(() -> {
            synchronized (this) {
                logger.info("[CS] received permission response by " + request.getResponseByRemote().getUsername());
                responseCount++;
                int necessarySize = node.getRoomsAndLeaders().size();
                var copyOfTable = new ConcurrentHashMap<>(node.getRoomsAndLeaders());
                if (responseCount == necessarySize) { // self -> -1
                    logger.info("[CS] responseCount = [" + responseCount + "] is similar to leaders size = [" + necessarySize + "], join CS. maxClock: " + maxClock + " nodeClock: " + myClock);
                    block();
                    for (var leader : node.getRoomsAndLeaders().values()) {
                        try {
                            Thread.sleep(TimeUnit.SECONDS.toMillis(Config.SLEEP_TIME_IN_CS_S));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        Utils.Skeleton.getFutureStub(leader)
                                .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(copyOfTable));
                    }
                    unblock();
                    responseCount = 0;
                    for (var rem : dsvRemotes) {
                        logger.info("[CS] send permission to requesting nodes. maxClock: " + maxClock + " nodeClock: " + myClock);
                        if (rem.getIsRequesting()) {
                            Address address = node.getRoomsAndLeaders().get(rem.getUsername());
                            Utils.Skeleton.getFutureStub(address).receivePermissionResponse(
                                    generated.PermissionResponse.newBuilder().setGranted(true).build()
                            );
                            rem.setIsRequesting(false);
                        }
                    }
                }
            }

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
            node.setState(NodeState.RELEASED);
            dsvThreadPool.notifyExecutors();

        });
    }

    private boolean isDelay(generated.PermissionRequest request) {
        return (node.getState() == NodeState.HOLDING || node.getState() == NodeState.REQUESTING)
                &&
                ((request.getClock() > myClock)
                        || (request.getClock() == myClock && request.getRequestByRemote().getNodeId() > node.getAddress().getId()));
    }

    private void block() {
        node.setState(NodeState.HOLDING);
        logger.info("-------Joining CS-------");
    }

    private void unblock() {
        logger.info("-------Exiting CS-------");
        node.setState(NodeState.RELEASED);
    }

    @Override
    public void changeNextNext(Remote request, StreamObserver<Empty> responseObserver) {
        logger.info("Method changeNextNext is called...");
        logger.info("Changing nextNext to " + Utils.Mapper.remoteToAddress(request));
        node.getDsvNeighbours().setNextNext(Utils.Mapper.remoteToAddress(request));
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void changePrev(Remote request, StreamObserver<Remote> responseObserver) {
        Address prev = Utils.Mapper.remoteToAddress(request);
        logger.info("Method changePrev is called...");
        logger.info("Changing prev to " + prev);
        node.getDsvNeighbours().setPrev(prev);
        updateBackupNode();

        logger.info("Returning next node " + node.getDsvNeighbours().getNext());
        responseObserver.onNext(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getNext()));
        responseObserver.onCompleted();
    }

    @Override
    public void election(Remote request, StreamObserver<Empty> responseObserver) {
        dsvThreadPool.execute(() -> {
            logger.info("Election is called with ID: " + request.getNodeId());
            var nextSkeleton = Utils.Skeleton.getSyncSkeleton(node.getDsvNeighbours().getNext());

            if (node.getAddress().getId() < request.getNodeId()) {
                node.setVoting(true);
                nextSkeleton.election(request);
            } else if ((node.getAddress().getId() > request.getNodeId()) && !node.isVoting()) {
                node.setVoting(true);
                nextSkeleton.election(Utils.Mapper.addressToRemote(node.getAddress()));
            } else if (node.getAddress().getId() == request.getNodeId())
                electedCandidate();

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        });
    }

    private void electedCandidate() {
        node.setIsLeader(new DsvPair<>(true, new Room(node.getAddress(), node.getCurrentRoom())));
        node.getRoomsAndLeaders().put(node.getCurrentRoom(), node.getAddress());

        try {
            Utils.Skeleton.getSyncSkeleton(node.getDsvNeighbours().getLeader())
                    .receiveRoom(generated.RoomEntry.newBuilder()
                            .setRoomName(node.getCurrentRoom())
                            .setRoomOwner(Utils.Mapper.nodeToRemote(node))
                            .build());
        } catch (StatusRuntimeException ignored) {}

        node.updateLeaderChannelAndObserver(node.getAddress());
        updateBackupNode();
        Utils.Skeleton.getFutureStub(node.getDsvNeighbours().getNext())
                .elected(Utils.Mapper.addressToRemote(node.getAddress()));
    }

    @Override
    public void elected(Remote request, StreamObserver<Empty> responseObserver) {
        dsvThreadPool.execute(() -> {
            logger.info("Elected is called with ID: " + request.getNodeId());

            if (node.getAddress().getId() != request.getNodeId()) {
                node.updateLeaderChannelAndObserver(Utils.Mapper.remoteToAddress(request));
                Utils.Skeleton.getSyncSkeleton(node.getDsvNeighbours().getNext())
                        .elected(request);
            }

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        });
    }

    @Override
    public void repairTopology(Remote request, StreamObserver<Empty> responseObserver) {
        dsvThreadPool.execute(() -> {
            logger.info("-------Changing topology is started-------");
            DsvNeighbours dsvNeighbours = node.getDsvNeighbours();
            if (request.getNodeId() == node.getDsvNeighbours().getNext().getId()) {
                logger.info("Setting next to " + dsvNeighbours.getNextNext());
                dsvNeighbours.setNext(dsvNeighbours.getNextNext());

                var nextNextSkeleton = Utils.Skeleton.getSyncSkeleton(dsvNeighbours.getNextNext());

                logger.info("Changing prev on " + dsvNeighbours.getNextNext() + " to " + node.getAddress() + " called by " + node.getAddress());
                var nextOfNextNextNode = nextNextSkeleton.changePrev(Utils.Mapper.addressToRemote(node.getAddress()));

                logger.info("Setting nextNext to " + Utils.Mapper.remoteToAddress(nextOfNextNextNode));
                dsvNeighbours.setNextNext(Utils.Mapper.remoteToAddress(nextOfNextNextNode));

                logger.info("Changing nextNext on " + dsvNeighbours.getPrev() + " to " + dsvNeighbours.getNext() + "called by " + node.getAddress());
                Utils.Skeleton
                        .getSyncSkeleton(dsvNeighbours.getPrev())
                        .changeNextNext(Utils.Mapper.addressToRemote(dsvNeighbours.getNext()));

            } else {
                logger.info("Send message [repair topology] to the next node " + dsvNeighbours.getNext());
                Utils.Skeleton
                        .getSyncSkeleton(dsvNeighbours.getNext())
                        .repairTopology(request);
            }

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        });
    }

    private void updateBackupNode() {
        dsvThreadPool.execute(() -> {
            if (node.getIsLeader().getKey() && !node.getDsvNeighbours().getPrev().equals(node.getAddress())) {
                logger.info("Updating backup node");
                Utils.Skeleton.getFutureStub(node.getDsvNeighbours().getPrev())
                        .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(node.getRoomsAndLeaders()));
            }
        });
    }

    @Override
    public void changeNeighbours(generated.JoinRequest request, StreamObserver<generated.Neighbours> responseObserver) {
        dsvThreadPool.execute(() -> {
            responseObserver.onNext(changeNeighbours(request));
            responseObserver.onCompleted();
        });
    }

    private generated.Neighbours changeNeighbours(generated.JoinRequest request) {
        logger.info("-------Changing neighbours is started-------");
        DsvNeighbours myDsvNeighbours = node.getDsvNeighbours();
        Address initialNext = new Address(myDsvNeighbours.getNext());
        Address initialPrev = new Address(myDsvNeighbours.getPrev());
        DsvNeighbours tempDsvNeighbours = new DsvNeighbours(
                myDsvNeighbours.getNext(),
                myDsvNeighbours.getNextNext(),
                node.getAddress(),
                myDsvNeighbours.getLeader()
        );

        Utils.Skeleton.getSyncSkeleton(node.getDsvNeighbours().getNext())
                .changePrev(request.getRemote());
        Utils.Skeleton.getSyncSkeleton(initialPrev)
                .changeNextNext(request.getRemote());

        tempDsvNeighbours.setNextNext(myDsvNeighbours.getNextNext());
        myDsvNeighbours.setNextNext(initialNext);
        myDsvNeighbours.setNext(Utils.Mapper.remoteToAddress(request.getRemote()));

        return Utils.Mapper.dsvNeighboursToNeighbours(tempDsvNeighbours);
    }

    private List<Address> getLeadersAddresses() {
        return node.getRoomsAndLeaders().values().stream().toList();
    }
}
