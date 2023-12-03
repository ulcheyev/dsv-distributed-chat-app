package cz.cvut.fel.dsv.service;

import cz.cvut.fel.dsv.core.*;
import generated.*;
import generated.*;
import generated.*;
import generated.Empty;
import generated.Message;
import generated.PermissionRequest;
import generated.Remote;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RemoteServiceImpl extends generated.RemotesServiceGrpc.RemotesServiceImplBase{

    private static final Logger logger = Logger.getLogger(RemoteServiceImpl.class.getName());
    private Integer responseCount = 0;
    private List<DsvRemote> dsvRemotes = new ArrayList<>(); // TODO
    private final Queue<DsvPair<generated.JoinRequest,StreamObserver<generated.JoinResponse>>> joinQueue = new LinkedList<>(); // TODO
    private int myClock = 0;
    private int maxClock = 0;
    private volatile Node node;

    public RemoteServiceImpl (Node node) {
        this.node = node;
    }
    private RemoteServiceImpl(){}

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
        for(var room: node.getRoomsAndLeaders().keySet()) {
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
            for(var room: request.getRoomsList()) {
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
            logger.info("[request by Node "+request.getRemote().getUsername()+"] requested to join in " + request.getRoomName());
            if(!node.getState().equals(NodeState.RELEASED)){
                logger.info("[request by Node "+request.getRemote().getUsername()+"] node is in CS now, request will be processed later");
                joinQueue.add(new DsvPair<>(request,responseObserver));
            }
            else{
                logger.info("[request by Node " + request.getRemote().getUsername() + "] request to join is processing");
                // If node is leader
                if (node.getIsLeader().getKey()) {
                    // Leader of requested room
                    if (Objects.equals(node.getIsLeader().getValue().getRoomName(), request.getRoomName())) {
                        logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node to connect is leader");
                        responseObserver.onNext(generated.JoinResponse.newBuilder()
                                .setIsLeader(true)
                                .setLeader(Utils.Mapper.nodeToRemote(node))
                                .build());
                    }
                    // Not a leader of requested room
                    else {
                        logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node to connect is leader, but not leader in requested room");

                        // Try to find specified room in leader
                        try {
                            responseObserver.onNext(generated.JoinResponse.newBuilder()
                                    .setIsLeader(true)
                                    .setLeader(Utils.Mapper.addressToRemote(
                                            node
                                                    .getRoomsAndLeaders()
                                                    .get(request.getRoomName())))
                                    .build());
                            logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room is exists. Find in leaders table.");

                        }
                        // If not -> create the room and leader will be the sender
                        catch (NullPointerException e) {

                            logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room does not exists. Requested node will be the leader.");

                            responseObserver.onNext(generated.JoinResponse.newBuilder()
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
                                Utils.Skeleton.getSyncSkeleton(leader.getHostname(), leader.getPort())
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
                            .setLeader(Utils.Mapper.addressToRemote(node.getLeaderAddress()))
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
                    Utils.Skeleton.getSyncSkeleton(request.getRemote().getHostname(), request.getRemote().getPort())
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
            synchronized (responseCount){
                responseCount++;
                int necessarySize = node.getRoomsAndLeaders().size();
                if(responseCount == necessarySize) { // self -> -1
                    logger.info("[CS] responseCount = ["+responseCount+"] is similar to leaders size = ["+necessarySize+"], join CS. maxClock: " + maxClock + " nodeClock: " + myClock);
                    block();
                    for(var leader: node.getRoomsAndLeaders().values()) {
                        Utils.Skeleton.getSyncSkeleton(leader.getHostname(), leader.getPort())
                                .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(node.getRoomsAndLeaders()));
                    }
                    unblock();
                    responseCount = 0;
                    for(var rem: dsvRemotes) {
                        logger.info("[CS] send permission to requesting nodes. maxClock: " + maxClock + " nodeClock: " + myClock);
                        if(rem.getIsRequesting()) {
                            Address address = node.getRoomsAndLeaders().get(rem.getUsername());
                            Utils.Skeleton.getSyncSkeleton(address.getHostname(), address.getPort()).receivePermissionResponse(
                                    generated.PermissionResponse.newBuilder().setGranted(true).build()
                            );
                            rem.setIsRequesting(false);
                        }
                    }
                    // Process delayed requests
                    for(var joinReq: joinQueue) {
                        joinRoom(joinReq.getKey(), joinReq.getValue());
                    }
                    joinQueue.clear();
                }

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
