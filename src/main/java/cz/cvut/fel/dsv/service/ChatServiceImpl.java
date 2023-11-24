package cz.cvut.fel.dsv.service;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChatServiceImpl extends generated.ChatServiceGrpc.ChatServiceImplBase {

    private static final Logger logger = Logger.getLogger(ChatServiceImpl.class.getName());

    // todo co se tam deje uvnitr
    private static List<StreamObserver<generated.ChatMessage>> observers = new ArrayList<>();

    @Override
    public StreamObserver<generated.ChatMessage> chat(
            StreamObserver<generated.ChatMessage> responseObserver)
    {

        observers.add(responseObserver);
        return new StreamObserver<generated.ChatMessage>() {
            @Override
            public void onNext(generated.ChatMessage chatMessage) {
                logger.info("Service: Received message from: " + chatMessage.getSenderUsername());
                logger.info("\tBody: " + chatMessage.getMessage());
                logger.info("Service: Sending message to all observers. Actual observers: " + observers.size());
                for(var observer: observers) {
                    if(!observer.equals(responseObserver))
                        observer.onNext(generated.ChatMessage.newBuilder()
                                .setMessage(chatMessage.getMessage())
                                .setSenderUsername(chatMessage.getSenderUsername())
                                .build());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                logger.severe("Service: Error on service: " + throwable.getMessage());
                observers.remove(responseObserver);
                logger.info("Service: Observers updated.");
            }

            @Override
            public void onCompleted() {
                logger.info("Service: Completed.");
                responseObserver.onCompleted();
                observers.remove(responseObserver);
                logger.info("Service: Observers updated.");
            }
        };
    }

}
