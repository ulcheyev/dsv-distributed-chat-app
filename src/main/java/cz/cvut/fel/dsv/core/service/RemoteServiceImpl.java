package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.Room;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.core.service.strategy.BaseJoinRoomStrategy;
import cz.cvut.fel.dsv.core.service.strategy.JoinViaLeaderRoomStrategy;
import cz.cvut.fel.dsv.core.service.strategy.JoinViaNonLeaderRoomStrategy;
import cz.cvut.fel.dsv.utils.Director;
import cz.cvut.fel.dsv.utils.DsvConditionLock;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.Empty;
import generated.Remote;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class RemoteServiceImpl extends generated.RemoteServiceGrpc.RemoteServiceImplBase {
    private static final Logger logger = DsvLogger.getLogger("REMOTE SERVICE", ANSI_PURPLE_SERVICE, RemoteServiceImpl.class);
    private final Node node = Node.getInstance();
    private final ElectionServiceImpl electionService;
    private final UpdateServiceImpl updateService;

    public RemoteServiceImpl(UpdateServiceImpl updateService, ElectionServiceImpl electionService) {
        this.electionService = electionService;
        this.updateService = updateService;
        // final DsvConditionLock lock = new DsvConditionLock(true);
        // lock.await()
        // lock.lock()
        // --- lock.signal()
    }

    @Override
    public void exitRoom(Remote request, StreamObserver<Empty> responseObserver) {
        // todo enter cs
        executeExit(request);
        // todo leave cs
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    public void executeExit(Remote request) {
        // If node to disconnect == leader node in room, then disconnect all nodes from current node
        // Then node will change the room in joinRoom()
        node.startRepairTopology(node.getDsvNeighbours().getPrev(), node.getAddress());
        if (request.getNodeId() == node.getAddress().getId()) { // checking leader and only leader!!!
            logger.info("Exited node was leader. Disconnect all nodes");
            Room leadingRoom = node.getLeadingRoom();
            if (!node.leadingRoomIsNotEmpty()) {
                logger.warning("Exited node was the last node in room");
                // todo delete this room on all leaders
            } else node.makeElection(node.getDsvNeighbours().getNext());
            leadingRoom.disconnectAllUsers();
        } else {
            logger.log(Level.INFO, "{0} exited room {1}", new Object[]{request.getUsername(), node.getCurrentRoom()});
            node.getLeadingRoom().removeFromRoom(request.getNodeId());
        }
    }

    @Override
    public synchronized void joinRoom(generated.JoinRequest request, StreamObserver<generated.JoinResponse> responseObserver) {
        logger.log(Level.INFO, "[request by Node {0}] request to join is processing", request.getRemote().getUsername());
        BaseJoinRoomStrategy baseJoinRoomStrategy = (node.isLeader()
                ? new JoinViaLeaderRoomStrategy(updateService, electionService)
                : new JoinViaNonLeaderRoomStrategy())
                .setServices(this, updateService);
        baseJoinRoomStrategy.executeJoin(request, responseObserver);

        updateService.releaseCS();
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
        responseObserver.onNext(Director.buildStrPayload(node.getLeadingRoom().toString()));
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
        logger.log(Level.INFO, "Preflight received. Added [{0}] to room [{1}]",
                new Object[]{request.getUsername(), node.getCurrentRoom()});
        node.getLeadingRoom().addToRoom(DsvPair.of(Utils.Mapper.remoteToDsvRemote(request), responseObserver));
    }
}