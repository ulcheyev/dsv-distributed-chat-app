package cz.cvut.fel.dsv.service;

import com.google.rpc.Help;
import generated.ChatMessageRequest;
import generated.ChatServiceOuterClass;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChatServiceImpl extends generated.ChatServiceGrpc.ChatServiceImplBase {

    private static final Logger logger = Logger.getLogger(ChatServiceImpl.class.getName());

    // todo co se tam deje uvnitr
    private static List<StreamObserver<generated.ChatMessageResponse>> observers = new ArrayList<>();

    @Override
    public StreamObserver<generated.ChatMessageRequest> chat(
            StreamObserver<generated.ChatMessageResponse> responseObserver)
    {

        observers.add(responseObserver);
        return new StreamObserver<generated.ChatMessageRequest>() {
            @Override
            public void onNext(ChatMessageRequest chatMessageRequest) {
                logger.info("Received message: " + chatMessageRequest.getMessage());
                logger.info("Sending message to all observers. Actual observers : " + observers.size());
                for(var observer: observers) {
                    observer.onNext(generated.ChatMessageResponse.newBuilder()
                            .setMessage(chatMessageRequest.getMessage()).build());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                logger.severe("Error on service side. " + throwable.getMessage());
                observers.remove(responseObserver);
                logger.info("Observers on service side updated.");
            }

            @Override
            public void onCompleted() {
                logger.info("Completed on service side.");
                responseObserver.onCompleted();
                observers.remove(responseObserver);
                logger.info("Observers on service side updated.");
            }
        };
    }

}
