package cz.cvut.fel.dsv.core;

import io.grpc.stub.StreamObserver;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Room {
    private static final Logger logger = Logger.getLogger(Room.class.getName());
    @Getter private final String roomName;
    private final List<DsvPair<DsvRemote, StreamObserver<generated.Message>>> users;

    public Room(String name) {
        this.roomName = name;
        users = new ArrayList<>();
    }

    public void addToRoom(DsvPair<DsvRemote, StreamObserver<generated.Message>> user) {
        users.add(user);
    }

    public void removeFromRoom(Long id) {
        for (var user : users) {
            if (Objects.equals(user.getKey().getAddress().getId(), id)) {
                user.getValue().onCompleted();
                users.remove(user);
                break;
            }
        }
    }

    public void sendMessageToRoom(generated.Message msg) {
        for (var user : users) {
            logger.info("Send message to " + user.getKey());
            if (!Objects.equals(user.getKey().getAddress().getId(), msg.getRemote().getNodeId()))
                user.getValue().onNext(msg);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ROOM ").append(roomName).append(" -- nodes]");
        for (var user : users) {
            sb.append("\n\t--").append(user.getKey());
        }
        return sb.toString();
    }

    public static class NullableRoom extends Room {

        public NullableRoom() {
            super("NULLABLE ROOM IS NOT AN ACTUAL ROOM");
        }

        @Override
        public String toString() {
            return "NULLABLE ROOM IS NOT AN ACTUAL ROOM";
        }
    }

}
