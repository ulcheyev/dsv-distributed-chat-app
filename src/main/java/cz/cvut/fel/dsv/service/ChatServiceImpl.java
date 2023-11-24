package cz.cvut.fel.dsv.service;

import cz.cvut.fel.dsv.core.Node;
import generated.Address;
import generated.Candidate;
import generated.CandidateId;
import generated.Empty;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChatServiceImpl extends generated.ChatServiceGrpc.ChatServiceImplBase {

    private static final Logger logger = Logger.getLogger(ChatServiceImpl.class.getName());

    // todo co se tam deje uvnitr
    private static List<StreamObserver<generated.ChatMessage>> observers = new ArrayList<>();
//    private static List<Rooms> rooms = new ArrayList<>();

    private Node currentNode;

    public ChatServiceImpl(Node currentNode) {
        this.currentNode = currentNode;
    }

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

    @Override
    public void election(CandidateId request, StreamObserver<Empty> responseObserver) {
        logger.info("Method election was called with id " + request.getId());
        long candidateId = request.getId();
        if (this.currentNode.getNodeId() < candidateId) {
            this.currentNode.setVoting(true);
            // TODO: send candidateId to the next node
        } else if ((this.currentNode.getNodeId() > candidateId) && !this.currentNode.isVoting()) {
            this.currentNode.setVoting(true);
            // TODO: send currentNode id to the next node
        } else if (this.currentNode.getNodeId() == candidateId) {
            // TODO: invoke elected method. elected(candidateId, this.currentNode.getAddress)
        }

        responseObserver.onNext(generated.Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void elected(Candidate request, StreamObserver<Empty> responseObserver) {
        logger.info("Method elected was called with id " + request.getId());
        cz.cvut.fel.dsv.core.Address leader = new cz.cvut.fel.dsv.core.Address(
                request.getAddress().getHostname(),
                request.getAddress().getPort()
        );
        this.currentNode.getMyNeighbour().setLeader(leader);
        if (this.currentNode.getNodeId() != request.getId()) {
            // TODO: invoke elected method on the next node
        }

        responseObserver.onNext(generated.Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void changeNextNext(Address request, StreamObserver<Empty> responseObserver) {
        logger.info("Method changeNextNext was called");
        cz.cvut.fel.dsv.core.Address nextNextAddress = new cz.cvut.fel.dsv.core.Address(
                request.getHostname(),
                request.getPort()
        );
        this.currentNode.getMyNeighbour().setNextNext(nextNextAddress);

        responseObserver.onNext(generated.Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void changePrev(Address request, StreamObserver<Address> responseObserver) {
        logger.info("Method changePrev was called");
        cz.cvut.fel.dsv.core.Address prevAddress = new cz.cvut.fel.dsv.core.Address(
                request.getHostname(),
                request.getPort()
        );
        this.currentNode.getMyNeighbour().setPrev(prevAddress);

        Address nextAddress = generated.Address.newBuilder()
                .setHostname(this.currentNode.getMyNeighbour().getNext().getHostname())
                .setPort(this.currentNode.getMyNeighbour().getNext().getPort())
                .build();

        responseObserver.onNext(nextAddress);
        responseObserver.onCompleted();
    }

    @Override
    public void join(Address request, StreamObserver<generated.Neighbour> responseObserver) {
        super.join(request, responseObserver);
    }
}
