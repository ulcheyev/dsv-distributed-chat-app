package cz.cvut.fel.dsv.service;

import cz.cvut.fel.dsv.core.Room;
import generated.ChatMessage;
import generated.RoomChatMessage;
import generated.RoomRequestMessage;
import generated.RoomResponseMessage;
import io.grpc.stub.StreamObserver;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ChatServiceImpl extends generated.ChatServiceGrpc.ChatServiceImplBase {

    private static final Logger logger = Logger.getLogger(ChatServiceImpl.class.getName());
    private static Map<String, Room> roomMap = new HashMap();

    // todo co se tam deje uvnitr
    private static List<StreamObserver<generated.ChatMessage>> observers = new ArrayList<>();
//    private static List<Rooms> rooms = new ArrayList<>();

    // todo will be global room
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
    public void createRoom(RoomRequestMessage request, StreamObserver<RoomResponseMessage> responseObserver) {
        Room room = new Room(request.getRoomName());
        roomMap.put(room.getName(), room);
        responseObserver.onNext(RoomResponseMessage.newBuilder().setResponse("Room has been created").build());
        responseObserver.onCompleted();
        logger.info("Room with " + room.getName() + " has been created");
    }

    @Override
    public void joinToRoom(RoomRequestMessage request, StreamObserver<RoomChatMessage> responseObserver) {
        Room room = roomMap.get(request.getRoomName());
        room.addUser(request.getSenderUsername(), responseObserver);
        logger.info("User with username " + request.getSenderUsername() + " has joined in room " + room.getName());
    }

    @Override
    public void sendMessageToRoom(RoomChatMessage request, StreamObserver<RoomResponseMessage> responseObserver) {
        logger.info("Sending in " + request.getRoom()+ " by " + request.getMsg().getSenderUsername());
        for(var room: roomMap.entrySet()) {
            room.getValue().sendMessage(request.getMsg().getSenderUsername(), request.getMsg().getMessage());
        }
    }


}
