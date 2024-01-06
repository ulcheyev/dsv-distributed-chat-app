package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.Room;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.utils.DsvConditionLock;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.Empty;
import generated.Neighbours;
import generated.Remote;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class RemoteServiceImpl extends generated.RemoteServiceGrpc.RemoteServiceImplBase {
    private static final Logger logger = DsvLogger.getLogger("REMOTE SERVICE", ANSI_PURPLE_SERVICE, RemoteServiceImpl.class);
    private final Node node = Node.getInstance();
    private final ElectionServiceImpl electionService;
    private final UpdateServiceImpl updateService;

    private final DsvConditionLock lock;

    public RemoteServiceImpl(UpdateServiceImpl updateService, ElectionServiceImpl electionService) {
        this.electionService = electionService;
        this.updateService = updateService;
        lock = new DsvConditionLock(true);
    }

    @Override
    public void exitRoom(Remote request, StreamObserver<Empty> responseObserver) {
        // If node to disconnect == leader node in room, then disconnect all nodes from current node
        // Then node will change the room in joinRoom()

        if (request.getNodeId() == node.getAddress().getId()) {
            logger.info("Exited node was leader. Disconnect all nodes");

            Room leadingRoom = node.getLeadingRoom();
            if (!node.leadingRoomIsNotEmpty()) {
                logger.warning("Exited node was the last node in room");
            }
            leadingRoom.disconnectAllUsers();
        }
        else {
            logger.info(request.getUsername() + " exited room " + node.getCurrentRoom());
            node.getLeadingRoom().removeFromRoom(request.getNodeId());
        }

        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }


    @Override
    public void joinRoom(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
            lock.await();
            lock.lock();
            logger.info("[request by Node " + request.getRemote().getUsername() + "] request to join is processing");
            // If node is leader
            if (node.isLeader()) {
                updateService.requestCS(request.getDelay());
                updateService.awaitPermitToEnterCS();
                // Leader of requested room
                if (Objects.equals(node.getLeadingRoom().getRoomName(), request.getRoomName())) {
                    logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node to connect is leader in requested room");
                    Neighbours value = changeNeighbours(request);
                    responseObserver.onNext(generated.JoinResponse.newBuilder()
                            .setIsLeader(true)
                            .setLeader(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getLeader()))
                            .setNeighbours(value)
                            .build());
                    responseObserver.onCompleted();
                }
                // Not a leader of requested room
                else {
                    logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node to connect is leader, but not leader in requested room");

                    try {
                        var leader = SharedData.get(request.getRoomName()).getKey();
                        var neighbours = generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(leader)).changeNeighbours(request);
                        responseObserver.onNext(generated.JoinResponse.newBuilder()
                                .setIsLeader(true)
                                .setLeader(Utils.Mapper.addressToRemote(leader))
                                .setNeighbours(neighbours)
                                .build());
                        responseObserver.onCompleted();
                        logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room is exists. Find in leaders table");
                    }
                    // If not -> create the room and leader will be the sender
                    catch (NullPointerException e) {
                        logger.info("[request by Node " + request.getRemote().getUsername() + "] requested room does not exist. Update tables");
                        Address toPut = Utils.Mapper.remoteToAddress(request.getRemote());
                        SharedData.put(request.getRoomName(), DsvPair.of(toPut));
                        updateService.updateTables();
                        responseObserver.onNext(generated.JoinResponse
                                .newBuilder()
                                .setIsLeader(true)
                                .setLeader(request.getRemote())
                                .setNeighbours(Utils.Mapper.remoteToNeighbours(request.getRemote()))
                                .build());
                        responseObserver.onCompleted();
                    }
                }
                updateService.releaseCS();
            }
            // Not a leader at all, send a leader address
            else {
                logger.info("[request by Node " + request.getRemote().getUsername() + "] requested node is not a leader");
                responseObserver.onNext(generated.JoinResponse.newBuilder()
                        .setIsLeader(false)
                        .setLeader(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getLeader()))
                        .build());
                responseObserver.onCompleted();
            }
            lock.signal();
        }

    @Override
    public void beat(Empty request, StreamObserver<generated.Health> responseObserver) {
        responseObserver.onNext(generated.Health.newBuilder().setIsAlive(true).build());
        responseObserver.onCompleted();
    }

    @Override
    public void receiveGetRoomListRequest(Empty request, StreamObserver<generated.StringPayload> responseObserver) {
        String sb = "[ROOMS]" + SharedData.stringify();
        responseObserver.onNext(generated.StringPayload.newBuilder()
                .setMsg(sb)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void receiveGetNodeListInCurrentRoomRequest(Empty request, StreamObserver<generated.StringPayload> responseObserver) {
        responseObserver.onNext(generated.StringPayload.newBuilder()
                .setMsg(node.getLeadingRoom().toString())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void receiveMessage(generated.ChatMessage request, StreamObserver<Empty> responseObserver) {
        node.getLeadingRoom().sendMessageToRoom(request);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void preflight(Remote request, StreamObserver<generated.ChatMessage> responseObserver) {
        logger.info("Preflight received. Added [" + request.getUsername() + "] to room " + "[" + node.getCurrentRoom() + "]");
        node.getLeadingRoom().addToRoom(DsvPair.of(Utils.Mapper.remoteToDsvRemote(request), responseObserver));
    }

    private generated.Neighbours changeNeighbours(generated.JoinRequest request) {
        return electionService.changeNeighbours(request);
    }
}
