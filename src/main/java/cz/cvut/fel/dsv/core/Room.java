package cz.cvut.fel.dsv.core;

import io.grpc.stub.StreamObserver;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {

    @Getter private final String roomName;
    private List<DsvPair<DsvRemote, StreamObserver<generated.Message>>> users;

    public Room(String name) {
        this.roomName = name;
        users = new ArrayList<>();
    }

    public void addToRoom(DsvPair<DsvRemote,StreamObserver<generated.Message>> user) {
        users.add(user);
    }

    public void removeFromRoom(Long id) {
        for(var user: users) {
            if (Objects.equals(user.getKey().getAddress().getId(), id)) {
                user.getValue().onCompleted();
                users.remove(user);
                break;
            }
        }
    }

    public void sendMessageToRoom(generated.Message msg) {
        for(var user: users) {
            if(!Objects.equals(user.getKey().getAddress().getId(), msg.getRemote().getNodeId()))
                user.getValue().onNext(msg);
        }
    }

    public static class NullableRoom extends Room {

        public NullableRoom() {
            super("NULLABLE ROOM IS NOT AN ACTUAL ROOM");
        }
    }

}
