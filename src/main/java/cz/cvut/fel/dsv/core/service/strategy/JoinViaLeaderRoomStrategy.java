package cz.cvut.fel.dsv.core.service.strategy;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.core.service.ElectionServiceImpl;
import cz.cvut.fel.dsv.core.service.UpdateServiceImpl;
import cz.cvut.fel.dsv.utils.Director;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.JoinRequest;
import generated.JoinResponse;
import io.grpc.stub.StreamObserver;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class JoinViaLeaderRoomStrategy extends BaseJoinRoomStrategy {
    private final Logger logger = DsvLogger.getLogger("VIA LEADER", ANSI_PURPLE_SERVICE, JoinViaLeaderRoomStrategy.class);
    private final Node node = Node.getInstance();
    private final UpdateServiceImpl updateService;
    private final ElectionServiceImpl electionService;

    public JoinViaLeaderRoomStrategy(UpdateServiceImpl updateService, ElectionServiceImpl electionService) {
        this.updateService = updateService;
        this.electionService = electionService;
    }

    @Override
    protected void execute(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
        updateService.requestCS(request.getDelay());
        updateService.awaitPermitToEnterCS();
        if (Objects.equals(node.getLeadingRoom().getRoomName(), request.getRoomName()))
            handleLeaderOfReqRoom(request, responseObserver);
        else
            handleNotLeaderOfReqRoom(request, responseObserver);
    }

    private void handleLeaderOfReqRoom(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
        logger.log(Level.INFO, "[request by Node {0}] requested node to connect is leader in requested room", request.getRemote().getUsername());
        generated.Neighbours value = electionService.changeNeighbours(request);
        responseObserver.onNext(Director.buildJoinRes(true, node.getDsvNeighbours().getLeader(), value));
        responseObserver.onCompleted();
    }

    private void handleNotLeaderOfReqRoom(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
        logger.log(Level.INFO, "[request by Node {0}] requested node to connect is leader, " +
                "but not leader in requested room", request.getRemote().getUsername());

        try {
            var leader = SharedData.get(request.getRoomName()).getKey();
            var neighbours = generated.ElectionServiceGrpc
                    .newBlockingStub(Utils.Skeleton.buildChannel(leader)).changeNeighbours(request);
            responseObserver.onNext(Director.buildJoinRes(true, leader, neighbours));
            responseObserver.onCompleted();
            logger.log(Level.INFO, "[request by Node {0}] requested room is exists. Find in leaders table",
                    request.getRemote().getUsername());
        } catch (NullPointerException e) {
            handleCreateRoom(request, responseObserver);
        }
    }

    private void handleCreateRoom(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
        logger.log(Level.INFO, "[request by Node {0}] requested room does not exist. Update tables",
                request.getRemote().getUsername());
        Address toPut = Utils.Mapper.remoteToAddress(request.getRemote());
        SharedData.put(request.getRoomName(), DsvPair.of(toPut));
        updateService.updateTables();
        responseObserver.onNext(Director.buildJoinRes(true, request.getRemote(),
                Utils.Mapper.remoteToNeighbours(request.getRemote())));
        responseObserver.onCompleted();
    }
}
