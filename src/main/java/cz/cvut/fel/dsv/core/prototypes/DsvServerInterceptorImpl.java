package cz.cvut.fel.dsv.core.prototypes;

import cz.cvut.fel.dsv.core.DsvRequestsQueue;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.Getter;
import java.util.concurrent.ExecutionException;

public class DsvServerInterceptorImpl implements ServerInterceptor {
    @Getter private static String clientIP;
    private static final DsvRequestsQueue requestsQueue = new DsvRequestsQueue();
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next)
    {
        clientIP = headers.get(Metadata.Key.of("Client-IP", Metadata.ASCII_STRING_MARSHALLER));
        // Invoke the next handler in the chain with the updated context
        try {
            ServerCall.Listener listener = requestsQueue.handle(next, call, headers).get();
            return listener;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
