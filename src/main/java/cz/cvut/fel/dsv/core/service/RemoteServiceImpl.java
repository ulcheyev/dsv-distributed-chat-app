package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.NodeState;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.Empty;
import generated.Message;
import generated.Neighbours;
import generated.Remote;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class RemoteServiceImpl extends generated.RemoteServiceGrpc.RemoteServiceImplBase {
    private static final Logger logger = DsvLogger.getLogger("REMOTE SERVICE", ANSI_PURPLE_SERVICE, RemoteServiceImpl.class);
    private final Node node = Node.getInstance();
    private final ElectionServiceImpl electionService;
    private final Queue<DsvPair<generated.JoinRequest, StreamObserver<generated.JoinResponse>>> joinQueue = new LinkedList<>();

    public RemoteServiceImpl(ElectionServiceImpl electionService) {
        this.electionService = electionService;
    }

    @Override
    public void exitRoom(Remote request, StreamObserver<Empty> responseObserver) {
        logger.info(request.getUsername() + " exited room " + node.getCurrentRoom());
        // If node to disconnect == leader node in room, then disconnect all nodes from current node
        // Then node will change the room in joinRoom()

        if (request.getNodeId() == node.getAddress().getId()) {

            node.getIsLeader().getValue().disconnectAllUsers();
            node.getRoomsAndLeaders().remove(node.getCurrentRoom());

            if (node.getIsLeader().getValue().getSize() == 1) {
                for (var key : node.getRoomsAndLeaders().keySet()) {
                    try {
                        generated.UpdateServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(node.getRoomsAndLeaders().get(key).getKey()))
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
    }

    @Override
    public void joinRoom(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
        if(node.getState() == NodeState.HOLDING) {
            logger.info("[request by Node " + request.getRemote().getUsername() + "] request to join is delayed");
            joinQueue.add(DsvPair.of(request, responseObserver));
        }else{
            logger.info("[request by Node " + request.getRemote().getUsername() + "] request to join is processing");
            // If node is leader
            if (node.getIsLeader().getKey()) {
                // Leader of requested room
                if (Objects.equals(node.getIsLeader().getValue().getRoomName(), request.getRoomName())) {
                    logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node to connect is leader in requested room");
                    Neighbours value = changeNeighbours(request);
                    responseObserver.onNext(generated.JoinResponse.newBuilder()
                            .setIsLeader(true)
                            .setLeader(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getLeader()))
                            .setNeighbours(value)
                            .build());
                }
                // Not a leader of requested room
                else {
                    logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node to connect is leader, but not leader in requested room");

                    try {
                        var leader = node.getRoomsAndLeaders().get(request.getRoomName()).getKey();
                        var neighbours = generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildChannel(leader)).changeNeighbours(request);
                        responseObserver.onNext(generated.JoinResponse.newBuilder()
                                .setIsLeader(true)
                                .setLeader(Utils.Mapper.addressToRemote(leader))
                                .setNeighbours(neighbours)
                                .build());
                        logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room is exists. Find in leaders table");
                    }
                    // If not -> create the room and leader will be the sender
                    catch (NullPointerException e) {

                        logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room does not exist. Try to enter CS");
                        // Send to leaders new rooms table
                        // CS - send requests
                        var initial = Utils.Mapper.remoteToAddress(request.getRemote());
                        node.getRoomsAndLeaders().put(request.getRoomName(), DsvPair.of(initial, initial));
                        responseObserver.onNext(generated.JoinResponse.newBuilder()
                                .setNeighbours(Utils.Mapper.remoteToNeighbours(request.getRemote()))
                                .setIsLeader(true)
                                .setLeader(request.getRemote())
                                .build());
                    }
                }
            }

            // Not a leader at all, send a leader address
            else {
                logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node is not a leader");
                responseObserver.onNext(generated.JoinResponse.newBuilder()
                        .setIsLeader(false)
                        .setLeader(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getLeader()))
                        .build());
            }
            responseObserver.onCompleted();
        }
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
    public void receiveMessage(generated.ChatMessage request, StreamObserver<Empty> responseObserver) {
        node.getIsLeader()
                .getValue()
                .sendMessageToRoom(request);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void preflight(Remote request, StreamObserver<generated.ChatMessage> responseObserver) {
        logger.info("Preflight received. Added [" + request.getUsername() + "] to room " + "[" + node.getCurrentRoom() + "]");
        node.getIsLeader()
                .getValue()
                .addToRoom(DsvPair.of(Utils.Mapper.remoteToDsvRemote(request), responseObserver));
    }

    public void notifyJoinObservers() {
        try {
            var suspect = joinQueue.remove();
            joinRoom(suspect.getKey(), suspect.getValue());
        } catch (NoSuchElementException ignored) {
        }
    }

    private generated.Neighbours changeNeighbours(generated.JoinRequest request) {
        return electionService.changeNeighbours(request);
    }

    private List<Address> getLeadersAddresses() {
        return node.getRoomsAndLeaders().values().stream()
                .map(DsvPair::getKey)
                .toList();
    }
}
