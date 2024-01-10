package cz.cvut.fel.dsv.core.service.joinStrategy;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.utils.Director;
import cz.cvut.fel.dsv.utils.Utils;
import generated.JoinRequest;
import generated.JoinResponse;
import io.grpc.stub.StreamObserver;

import java.util.Objects;
import java.util.logging.Level;

public class JoinViaLeaderRoomStrategy extends BaseJoinRoomStrategy {

    @Override
    protected void execute(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
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
                    .newBlockingStub(Utils.Skeleton.buildManagedChannel(leader)).changeNeighbours(request);
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
        Node.getInstance().reflectOnBackup();
        updateService.updateTables();
        responseObserver.onNext(Director.buildJoinRes(true, request.getRemote(),
                Utils.Mapper.remoteToNeighbours(request.getRemote())));
        responseObserver.onCompleted();
    }
}
