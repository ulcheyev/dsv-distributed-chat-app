package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.infrastructure.Config;
import cz.cvut.fel.dsv.utils.DsvLogger;
import io.grpc.*;

import java.util.concurrent.Executor;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_INTERCEPTOR;

public class DsvClientInterceptor implements ClientInterceptor {


    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        CallOptions newCallOptions = callOptions.withCallCredentials(new CallCredentials() {
            @Override
            public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
                Metadata metadata = new Metadata();
                metadata.put(Metadata.Key.of("Client-IP", Metadata.ASCII_STRING_MARSHALLER), Node.getInstance().getAddress().getStringAddress());
                metadataApplier.apply(metadata);
            }
        });
        return channel.newCall(methodDescriptor, newCallOptions);
    }

}
