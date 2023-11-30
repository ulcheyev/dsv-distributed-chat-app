package cz.cvut.fel.dsv.service;

import cz.cvut.fel.dsv.core.Room;
import generated.RoomChatMessage;
import generated.RoomRequestMessage;
import generated.RoomResponseMessage;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ChatServiceImpl extends generated.ChatServiceGrpc.ChatServiceImplBase {

    private static final Logger logger = Logger.getLogger(ChatServiceImpl.class.getName());
    private static Map<String, Room> roomsMap = new HashMap();

    public ChatServiceImpl() {
        roomsMap.put("global", new Room("global"));
    }


    @Override
    public void createRoom(RoomRequestMessage request, StreamObserver<RoomResponseMessage> responseObserver) {
        Room room = new Room(request.getRoomName());
        roomsMap.put(room.getName(), room);
        responseObserver.onNext(RoomResponseMessage.newBuilder().setResponse("Room has been created").build());
        responseObserver.onCompleted();
        logger.info("Room with " + room.getName() + " has been created");
    }

    @Override
    public void joinToRoom(RoomRequestMessage request, StreamObserver<RoomChatMessage> responseObserver) {
        Room room = roomsMap.get(request.getRoomName());
        room.addUser(request.getSenderUsername(), responseObserver);
        logger.info("User with username " + request.getSenderUsername() + " has joined in room " + room.getName());
    }

    @Override
    public void sendMessageToRoom(RoomChatMessage request, StreamObserver<RoomResponseMessage> responseObserver) {
        logger.info("Sending in " + request.getRoom()+ " by " + request.getMsg().getSenderUsername());
        roomsMap.get(request.getRoom()).sendMessage(request.getMsg().getMessage(), request.getMsg().getSenderUsername());
        responseObserver.onNext(RoomResponseMessage.newBuilder().setResponse("Sended").build());
        responseObserver.onCompleted();
    }

    @Override
    public void exitRoom(RoomRequestMessage request, StreamObserver<RoomResponseMessage> responseObserver) {
        roomsMap.get(request.getRoomName()).deleteUser(request.getSenderUsername());
        logger.info("Delete user " + request.getSenderUsername() + " in " + request.getRoomName());
        responseObserver.onNext(RoomResponseMessage.newBuilder().setResponse("Exited").build());
        responseObserver.onCompleted();
    }
}
