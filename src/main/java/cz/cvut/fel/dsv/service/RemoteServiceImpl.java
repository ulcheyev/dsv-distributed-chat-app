package cz.cvut.fel.dsv.service;

import cz.cvut.fel.dsv.core.*;
import generated.Empty;
import generated.Message;
import generated.PermissionRequest;
import generated.Remote;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class RemoteServiceImpl extends generated.RemotesServiceGrpc.RemotesServiceImplBase{

    private static final Logger logger = Logger.getLogger(RemoteServiceImpl.class.getName());
    private Integer responseCount = 0;
    private List<DsvRemote> dsvRemotes = new ArrayList<>(); // TODO
    private int myClock = 0;
    private int maxClock = 0;
    private Node node;

    public RemoteServiceImpl (Node node) {
        this.node = node;
    }
    private RemoteServiceImpl(){}

    @Override
    public void receiveRooms(generated.Rooms request, StreamObserver<Empty> responseObserver) {
        for(var room: request.getRoomsList()) {
            node.getRoomsAndLeaders()
                    .putIfAbsent(room.getRoomName(), Utils.Mapper.remoteToAddress(room.getRoomOwner()));
        }
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void exitRoom(Remote request, StreamObserver<Empty> responseObserver) {
        logger.info(request.getUsername() + " exited room " + node.getCurrentRoom());
        node.getIsLeader()
                .getValue()
                .removeFromRoom(request.getNodeId());
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void joinRoom(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
        logger.info("[request by Node "+request.getRemote().getUsername()+"] requested to join in " + request.getRoomName());
        // If node is leader
        if(node.getIsLeader().getKey())
        {
            // Leader of requested room
            if(Objects.equals(node.getIsLeader().getValue().getRoomName(), request.getRoomName())){
                logger.info("[request by Node "+request.getRemote().getUsername()+"] requested node to connect is leader");
                responseObserver.onNext(generated.JoinResponse.newBuilder()
                        .setIsLeader(true)
                        .setLeader(Utils.Mapper.nodeToRemote(node))
                        .build());
            }
            // Not a leader of requested room
            else{
                logger.info("[request by Node "+request.getRemote().getUsername()+"] requested node to connect is leader, but not leader in requested room");

                // Try to find specified room in leader
                try {
                    responseObserver.onNext(generated.JoinResponse.newBuilder()
                            .setIsLeader(true)
                            .setLeader(Utils.Mapper.addressToRemote(
                                    node
                                    .getRoomsAndLeaders()
                                    .get(request.getRoomName())))
                            .build());
                    logger.info("[request by Node "+request.getRemote().getUsername()+"] requested room is exists. Find in leaders table.");

                }
                // If not -> create the room and leader will be the sender
                catch (NullPointerException e){

                    logger.info("[request by Node "+request.getRemote().getUsername()+"] requested room does not exists. Requested node will be the leader.");

                    responseObserver.onNext(generated.JoinResponse.newBuilder()
                            .setIsLeader(true)
                            .setLeader(request.getRemote())
                            .build());

                    // Send to leaders new room table
                    // TODO CS
                    node.getRoomsAndLeaders().put(request.getRoomName(), Utils.Mapper.remoteToAddress(request.getRemote()));
                    node.setState(NodeState.REQUESTING);
                    myClock = maxClock + 1;
                    logger.info("CS -> send requests to leaders, maxClock: " + maxClock + " nodeClock: " + myClock);
                    for(var leader: node.getRoomsAndLeaders().values()) {
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
        else
        {
            logger.info("Node "+request.getRemote().getUsername()+" -> not a leader");
            responseObserver.onNext(generated.JoinResponse.newBuilder()
                    .setIsLeader(false)
                    .setLeader(Utils.Mapper.addressToRemote(node.getLeaderAddress()))
                    .build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void receiveMessage(Message request, StreamObserver<Empty> responseObserver) {
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
        node.getIsLeader()
                .getValue()
                .sendMessageToRoom(request);
    }

    @Override
    public void preflight(Remote request, StreamObserver<Message> responseObserver) {
        node.getIsLeader()
                .getValue()
                .addToRoom(new DsvPair<>(Utils.Mapper.remoteToDsvRemote(request), responseObserver));
    }

    @Override
    public void receivePermissionRequest(generated.PermissionRequest request, StreamObserver<generated.Empty> responseObserver) {
        maxClock = Math.max(maxClock, request.getClock());
        maxClock++;
        if (isDelay(request)) {
            logger.info("CS -> request is delayed, maxClock: " + maxClock + " nodeClock: " + myClock);
            dsvRemotes.add(DsvRemote.builder()
                    .address(Utils.Mapper.remoteToAddress(request.getRemote()))
                    .username(request.getRemote().getUsername())
                    .isRequesting(true)
                    .build());
        } else {
            logger.info("CS -> request is granted, maxClock: " + maxClock + " nodeClock: " + myClock);
            Utils.Skeleton.getSyncSkeleton(request.getRemote().getHostname(), request.getRemote().getPort())
                    .receivePermissionResponse(generated.PermissionResponse.newBuilder()
                            .setGranted(true)
                            .build());
        }
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void receivePermissionResponse(generated.PermissionResponse request, StreamObserver<generated.Empty> responseObserver) {
        synchronized (this){
            responseCount++;
            if(responseCount == node.getRoomsAndLeaders().size()-1) { // self -> -1
                logger.info("CS -> response count is similar to leaders size, enter to CS. maxClock: " + maxClock + " nodeClock: " + myClock);
                 block();
                for(var leader: node.getRoomsAndLeaders().values()) {
                    Utils.Skeleton.getSyncSkeleton(leader.getHostname(), leader.getPort())
                            .receiveRooms(Utils.Mapper.leaderRoomsToRemoteRooms(node.getRoomsAndLeaders()));
                }
                 unblock();
                responseCount = 0;
                for(var rem: dsvRemotes) {
                    logger.info("CS -> permit requesting nodes. maxClock: " + maxClock + " nodeClock: " + myClock);
                    if(rem.getIsRequesting()) {
                        Address address = node.getRoomsAndLeaders().get(rem.getUsername());
                        Utils.Skeleton.getSyncSkeleton(address.getHostname(), address.getPort()).receivePermissionResponse(
                                generated.PermissionResponse.newBuilder().setGranted(true).build()
                        );
                        rem.setIsRequesting(false);
                    }
                }
            }

        }
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();

    }

    private boolean isDelay(generated.PermissionRequest request) {
        return (node.getState() != NodeState.RELEASED)
                &&
                ((request.getClock() > myClock)
                        || (request.getClock() == myClock && request.getRemote().getNodeId() > node.getAddress().getId()));
    }

    private void block() {
        node.setState(NodeState.HOLDING);
    }

    private void unblock() {
        node.setState(NodeState.RELEASED);
    }
}
