package cz.cvut.fel.dsv.core;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static ManagedChannel managedChannel;

    public static generated.RemotesServiceGrpc.RemotesServiceStub getAsyncSkeleton(String host, int port) {
        managedChannel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        return generated.RemotesServiceGrpc.newStub(managedChannel);
    }

    public static generated.RemotesServiceGrpc.RemotesServiceBlockingStub getSyncSkeleton(String host, int port) {
        managedChannel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        return generated.RemotesServiceGrpc.newBlockingStub(managedChannel);
    }

    public static void shutdown() {
        managedChannel.shutdown();
    }
}
