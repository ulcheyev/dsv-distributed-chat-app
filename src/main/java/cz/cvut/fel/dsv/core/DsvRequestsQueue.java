package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.NodeState;
import cz.cvut.fel.dsv.utils.DsvLogger;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_YELLOW;

public class DsvRequestsQueue{
    private static final Logger logger = DsvLogger.getLogger("REQUEST QUEUE", ANSI_YELLOW, DsvRequestsQueue.class);
    private static final LinkedBlockingDeque<DsvPair<CompletableFuture<ServerCall.Listener>, DsvPair<ServerCallHandler, DsvPair<ServerCall, Metadata>>>> requests = new LinkedBlockingDeque<>();
    private static final Node node = Node.getInstance();
    private static final ExecutorService ex = Executors.newSingleThreadExecutor();
    private static volatile Boolean working = false;

    public synchronized <ReqT, RespT> CompletableFuture<ServerCall.Listener> handle(ServerCallHandler<ReqT, RespT> serverCallHandler, ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
        CompletableFuture<ServerCall.Listener> completableFuture = new CompletableFuture<>();
        if(isNodeInCS() || working) {
            if(canHandleNow(serverCall.getMethodDescriptor())){
                processRequestInThread(DsvPair.of(serverCallHandler, DsvPair.of(serverCall, metadata)), completableFuture);
            } else {
                logger.info("Request " +serverCall.getMethodDescriptor().getBareMethodName()+" is delayed");
                requests.add(DsvPair.of(completableFuture, DsvPair.of(serverCallHandler, DsvPair.of(serverCall, metadata))));
            }
        } else {
            processRequestInThread(DsvPair.of(serverCallHandler, DsvPair.of(serverCall, metadata)), completableFuture);
        }
        return completableFuture;
    }

    private boolean isNodeInCS() {
        return !node.getState().equals(NodeState.RELEASED);
    }

    private synchronized <RespT, ReqT> boolean canHandleNow(MethodDescriptor<ReqT,RespT> methodDescriptor) {
        return methodDescriptor.equals(generated.UpdateServiceGrpc.getReceiveRoomsMethod()) ||
                methodDescriptor.equals(generated.UpdateServiceGrpc.getReceivePermissionRequestMethod());
    }

    private synchronized static void processRequestInThread(DsvPair<ServerCallHandler, DsvPair<ServerCall, Metadata>> req, CompletableFuture<ServerCall.Listener> future) {
        ex.submit(() -> {
            try {
                logger.info("Processing request: " + req.getValue().getKey().getMethodDescriptor().getBareMethodName());
                working = true;
                var result = req.getKey().startCall(req.getValue().getKey(), req.getValue().getValue());
                future.complete(result);
                working = false;
            } finally {
                processNextRequest();
            }
        });
    }

    public synchronized static void processNextRequest() {
        if(!requests.isEmpty()) {
            var peeked = requests.peek();
            if (!working) {
                processRequestInThread(peeked.getValue(), peeked.getKey());
                requests.remove();
            }
            else {
                logger.info("Request " +peeked.getValue().getValue().getKey().getMethodDescriptor().getBareMethodName()+" is delayed");
            }
        }
    }

}
