package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.NodeState;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import org.checkerframework.checker.units.qual.N;

import java.util.LinkedList;
import java.util.Queue;

public class DsvRequestsQueue {

    private Queue<DsvPair<ServerCallHandler, DsvPair<ServerCall, Metadata>>> requests = new LinkedList();

    public ServerCall.Listener handleRequest(ServerCallHandler serverCallHandler, ServerCall serverCall, Metadata metadata) {
        if(!Node.getInstance().getState().equals(NodeState.RELEASED)){
            requests.add(DsvPair.of(serverCallHandler, DsvPair.of(serverCall, metadata)));
        }
        return serverCallHandler.startCall(serverCall, metadata);
    }



}
