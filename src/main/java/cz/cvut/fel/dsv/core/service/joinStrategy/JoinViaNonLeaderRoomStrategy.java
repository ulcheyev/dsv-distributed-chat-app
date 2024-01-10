package cz.cvut.fel.dsv.core.service.joinStrategy;

import cz.cvut.fel.dsv.utils.Director;
import generated.JoinRequest;
import generated.JoinResponse;
import io.grpc.stub.StreamObserver;

import java.util.logging.Level;

public class JoinViaNonLeaderRoomStrategy extends BaseJoinRoomStrategy {

    @Override
    protected void execute(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
        logger.log(Level.INFO, "[request by Node {0}] requested node is not a leader", request.getRemote().getUsername());
        responseObserver.onNext(Director.buildJoinRes(false, node.getDsvNeighbours().getLeader(), null));
        responseObserver.onCompleted();
    }
}
