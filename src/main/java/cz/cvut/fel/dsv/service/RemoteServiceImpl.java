package cz.cvut.fel.dsv.service;

import cz.cvut.fel.dsv.core.*;
import generated.*;
import generated.Empty;
import generated.Message;
import generated.PermissionRequest;
import generated.Remote;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RemoteServiceImpl extends generated.RemotesServiceGrpc.RemotesServiceImplBase {

    private static final Logger logger = Logger.getLogger(RemoteServiceImpl.class.getName());
    private Integer responseCount = 0;
    private List<DsvRemote> dsvRemotes = new ArrayList<>(); // TODO
    private final Queue<DsvPair<generated.JoinRequest, StreamObserver<generated.JoinResponse>>> joinQueue = new LinkedList<>(); // TODO
    private int myClock = 0;
    private int maxClock = 0;
    private volatile Node node;

    public RemoteServiceImpl(Node node) {
        this.node = node;
    }

    private RemoteServiceImpl() {
    }

    @Override
    public void receiveGetNodeListInCurrentRoomRequest(Empty request, StreamObserver<generated.StringPayload> responseObserver) {
        responseObserver.onNext(generated.StringPayload.newBuilder()
                .setMsg(node.getIsLeader().getValue().toString())
                .build());
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
    public void receiveRooms(generated.Rooms request, StreamObserver<Empty> responseObserver) {
        DsvThreadPool.execute(() -> {
            for (var room : request.getRoomsList()) {
                node.getRoomsAndLeaders()
                        .putIfAbsent(room.getRoomName(), Utils.Mapper.remoteToAddress(room.getRoomOwner()));
            }
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        });

    }


    // TODO delete room from table when nobody in there...
    @Override
    public void exitRoom(Remote request, StreamObserver<Empty> responseObserver) {
        DsvThreadPool.execute(() -> {
            logger.info(request.getUsername() + " exited room " + node.getCurrentRoom());
            node.getIsLeader()
                    .getValue()
                    .removeFromRoom(request.getNodeId());
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        });

    }

    @Override
    public void joinRoom(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
        DsvThreadPool.execute(() -> {
            logger.info("[request by Node " + request.getRemote().getUsername() + "] requested to join in " + request.getRoomName());
            if (!node.getState().equals(NodeState.RELEASED)) {
                logger.info("[request by Node " + request.getRemote().getUsername() + "] node is in CS now, request will be processed later");
                joinQueue.add(new DsvPair<>(request, responseObserver));
            } else {
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

                        // Try to find specified room in leader. // TODO: Wrong topology when node is joining from [global]
                        try {
                            responseObserver.onNext(generated.JoinResponse.newBuilder()
                                    .setLeader(Utils.Mapper.addressToRemote(  // ORDER OF THIS SETTERS IS IMPORTANT!!!
                                            node
                                                    .getRoomsAndLeaders()
                                                    .get(request.getRoomName())))
                                    .setIsLeader(true)
                                    .setNeighbours(changeNeighbours(request))
                                    .build());
                            logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room is exists. Find in leaders table.");

                        }
                        // If not -> create the room and leader will be the sender
                        catch (NullPointerException e) {

                            logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room does not exists. Requested node will be the leader.");

                            responseObserver.onNext(generated.JoinResponse.newBuilder()
                                    .setNeighbours(Utils.Mapper.remoteToNeighbours(request.getRemote()))
                                    .setIsLeader(true)
                                    .setLeader(request.getRemote())
                                    .build());

                            // Send to leaders new room table
                            // CS - send requests
                            var refToTable = node.getRoomsAndLeaders();
                            refToTable.put(request.getRoomName(), Utils.Mapper.remoteToAddress(request.getRemote()));
                            node.setState(NodeState.REQUESTING);
                            myClock = maxClock + 1;
                            logger.info("[CS] send requests to leaders, size = [" + refToTable.size() + "]; maxClock: " + maxClock + ", nodeClock: " + myClock);
                            for (var leader : refToTable.values()) {
                                Utils.Skeleton.getSyncSkeleton(leader)
                                        .receivePermissionRequest(PermissionRequest.newBuilder()
                                                .setRemote(Utils.Mapper.nodeToRemote(node))
                                                .setClock(myClock)
                                                .build());
                            }
                            Utils.Skeleton.shutdown();
                        }

                    }

                }

                // Not a leader at all, send a leader address
                else {
                    logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node is not a leader.");
                    responseObserver.onNext(generated.JoinResponse.newBuilder()
                            .setIsLeader(false)
                            .setLeader(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getLeader()))
                            .build());
                }

                responseObserver.onCompleted();
            }
        });
    }

    @Override
    public void receiveMessage(Message request, StreamObserver<Empty> responseObserver) {
        DsvThreadPool.execute(() -> {
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
            node.getIsLeader()
                    .getValue()
                    .sendMessageToRoom(request);
        });

    }

    @Override
    public void preflight(Remote request, StreamObserver<Message> responseObserver) {
        DsvThreadPool.execute(() -> {
            node.getIsLeader()
                    .getValue()
                    .addToRoom(new DsvPair<>(Utils.Mapper.remoteToDsvRemote(request), responseObserver));
        });

    }

    @Override
    public void receivePermissionRequest(generated.PermissionRequest request, StreamObserver<generated.Empty> responseObserver) {
        DsvThreadPool.execute(() -> {
            synchronized (this) {
                maxClock = Math.max(maxClock, request.getClock());
                maxClock++;
                if (isDelay(request)) {
                    logger.info("[CS] request is delayed, maxClock: " + maxClock + " nodeClock: " + myClock);
                    dsvRemotes.add(DsvRemote.builder()
                            .address(Utils.Mapper.remoteToAddress(request.getRemote()))
                            .username(request.getRemote().getUsername())
                            .isRequesting(true)
                            .build());
                } else {
                    logger.info("[CS] request is granted, maxClock: " + maxClock + " nodeClock: " + myClock);
                    Utils.Skeleton.getSyncSkeleton(Utils.Mapper.remoteToAddress(request.getRemote()))
                            .receivePermissionResponse(generated.PermissionResponse.newBuilder()
                                    .setGranted(true)
                                    .build());
                }
                responseObserver.onNext(Empty.getDefaultInstance());
                responseObserver.onCompleted();
            }

        });

    }

    @Override
    public void receivePermissionResponse(generated.PermissionResponse request, StreamObserver<generated.Empty> responseObserver) {
        DsvThreadPool.execute(() -> {
            synchronized (responseCount) {
                responseCount++;
                int necessarySize = node.getRoomsAndLeaders().size();
                if (responseCount == necessarySize) { // self -> -1
                    logger.info("[CS] responseCount = [" + responseCount + "] is similar to leaders size = [" + necessarySize + "], join CS. maxClock: " + maxClock + " nodeClock: " + myClock);
                    block();
                    for (var leader : node.getRoomsAndLeaders().values()) {
                        Utils.Skeleton.getSyncSkeleton(leader)
                                .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(node.getRoomsAndLeaders()));
                    }
                    unblock();
                    responseCount = 0;
                    for (var rem : dsvRemotes) {
                        logger.info("[CS] send permission to requesting nodes. maxClock: " + maxClock + " nodeClock: " + myClock);
                        if (rem.getIsRequesting()) {
                            Address address = node.getRoomsAndLeaders().get(rem.getUsername());
                            Utils.Skeleton.getSyncSkeleton(address).receivePermissionResponse(
                                    generated.PermissionResponse.newBuilder().setGranted(true).build()
                            );
                            rem.setIsRequesting(false);
                        }
                    }
                    // Process delayed requests
                    for (var joinReq : joinQueue) {
                        joinRoom(joinReq.getKey(), joinReq.getValue());
                    }
                    joinQueue.clear();
                }

            }
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        });

    }

    @Override
    public void changeNextNext(Remote request, StreamObserver<Empty> responseObserver) {
        logger.info("Method changeNextNext is called...");
        logger.info("Changing nextNext on " + node.getAddress() + " to " + Utils.Mapper.remoteToAddress(request));
        node.getDsvNeighbours().setNextNext(Utils.Mapper.remoteToAddress(request));
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void changePrev(Remote request, StreamObserver<Remote> responseObserver) {
        logger.info("Method changePrev is called...");
        logger.info("Changing prev on " + node.getAddress() + " to " + Utils.Mapper.remoteToAddress(request));
        node.getDsvNeighbours().setPrev(Utils.Mapper.remoteToAddress(request));

        logger.info("Returning next node " + node.getDsvNeighbours().getNext());
        responseObserver.onNext(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getNext()));
        responseObserver.onCompleted();
    }

    @Override
    public void election(Remote request, StreamObserver<Empty> responseObserver) {
        super.election(request, responseObserver);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void elected(Remote request, StreamObserver<Empty> responseObserver) {
        super.elected(request, responseObserver);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void repairTopology(Remote request, StreamObserver<Empty> responseObserver) {
        DsvThreadPool.execute(() -> {
            logger.info("-------------------------Changing topology is started... ");
            DsvNeighbours dsvNeighbours = node.getDsvNeighbours();
            if (request.getNodeId() == node.getDsvNeighbours().getNext().getId()) {
                logger.info("Setting next to " + dsvNeighbours.getNextNext());
                dsvNeighbours.setNext(dsvNeighbours.getNextNext());

                var nextNextSkeleton = Utils.Skeleton.getSyncSkeleton(dsvNeighbours.getNextNext());

                logger.info("Changing prev on " + dsvNeighbours.getNextNext() + " to " + node.getAddress() + " called by " + node.getAddress());
                var nextOfNextNextNode = nextNextSkeleton.changePrev(Utils.Mapper.addressToRemote(node.getAddress()));

                logger.info("Setting nextNext to " + Utils.Mapper.remoteToAddress(nextOfNextNextNode));
                dsvNeighbours.setNextNext(Utils.Mapper.remoteToAddress(nextOfNextNextNode));

                logger.info("Changing nextNext on " + dsvNeighbours.getPrev() + " to " +dsvNeighbours.getNext() + "called by " + node.getAddress());
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

    private boolean isDelay(generated.PermissionRequest request) {
        return (node.getState() != NodeState.RELEASED)
                &&
                ((request.getClock() > myClock)
                        || (request.getClock() == myClock && request.getRemote().getNodeId() > node.getAddress().getId()));
    }

    private generated.Neighbours changeNeighbours(generated.JoinRequest request) {
        logger.info("-------------------------Changing neighbours is started... " + request.getRemote().getPort());
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

    private void block() {
        node.setState(NodeState.HOLDING);
        try {
            logger.info("Joining CS..." + Thread.currentThread().getName());
            Thread.sleep(TimeUnit.SECONDS.toMillis(Config.SLEEP_TIME_IN_CS_S));
        } catch (InterruptedException e) {
            logger.severe("Error while sleeping in " + Thread.currentThread().getName());
        }
    }

    private void unblock() {
        logger.info("Exiting CS..." + Thread.currentThread().getName());
        node.setState(NodeState.RELEASED);
    }
}
