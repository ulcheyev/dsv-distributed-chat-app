package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.service.ChatServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestClientNode {

    private static final Logger logger = Logger.getLogger(TestClientNode.class.getName());

    public static void main(String[] args) {

        // Wait until request is not finish
        CountDownLatch finishLatch = new CountDownLatch(1);

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        generated.ChatServiceGrpc.ChatServiceStub asyncStub = generated.ChatServiceGrpc.newStub(managedChannel);

        StreamObserver<generated.ChatMessageRequest> streamObserver = asyncStub.chat(new StreamObserver<>() {
            @Override
            public void onNext(generated.ChatMessageResponse chatMessageResponse) {
                logger.info("Client: Message received: " + chatMessageResponse.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                logger.severe(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                logger.info("Completed on client side.");
            }
        });

        streamObserver.onNext(generated.ChatMessageRequest.newBuilder().setMessage("Test").build());

        try {
            if(!finishLatch.await(1, TimeUnit.MINUTES)) {
                logger.warning("Cannot finish the request during 1 minute.");
            }
        }catch (InterruptedException e) {
            logger.severe("Error while waiting on finish request.");
            logger.severe(e.getMessage());
        }

    }
}
