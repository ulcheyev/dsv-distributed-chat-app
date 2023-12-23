package cz.cvut.fel.dsv.core.service;

import io.grpc.*;


public class DsvServerInterceptorImpl implements ServerInterceptor {
    private static final Context.Key<String> CLIENT_IP_KEY = Context.key("Client-IP");
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {
        String clientIP = headers.get(Metadata.Key.of("Client-IP", Metadata.ASCII_STRING_MARSHALLER));
        // Create a new context with the client's IP address
        Context context = Context.current().withValue(CLIENT_IP_KEY, clientIP);
        // Invoke the next handler in the chain with the updated context
        return Contexts.interceptCall(context, call, headers, next);
    }

    public static String getClientIP() {
        return CLIENT_IP_KEY.get();
    }

}
