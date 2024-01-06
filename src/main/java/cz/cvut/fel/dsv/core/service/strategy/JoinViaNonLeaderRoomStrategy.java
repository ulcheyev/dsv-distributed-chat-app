package cz.cvut.fel.dsv.core.service.strategy;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.utils.Director;
import cz.cvut.fel.dsv.utils.DsvLogger;
import generated.JoinRequest;
import generated.JoinResponse;
import io.grpc.stub.StreamObserver;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class JoinViaNonLeaderRoomStrategy extends BaseJoinRoomStrategy {
    private final Logger logger = DsvLogger.getLogger("VIA NON LEADER", ANSI_PURPLE_SERVICE, JoinViaNonLeaderRoomStrategy.class);
    private final Node node = Node.getInstance();

    @Override
    protected void execute(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
        logger.log(Level.INFO, "[request by Node {0}] requested node is not a leader", request.getRemote().getUsername());
        responseObserver.onNext(Director.buildJoinRes(false, node.getDsvNeighbours().getLeader(), null));
        responseObserver.onCompleted();
    }
}
