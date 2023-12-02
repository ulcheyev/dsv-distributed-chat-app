package cz.cvut.fel.dsv.core;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {

    private String roomName;
    private List<DsvPair<String,StreamObserver<generated.Message>>> users;

    public Room(String name) {
        this.roomName = name;
        users = new ArrayList<>();
    }

    public void addToRoom(DsvPair<String,StreamObserver<generated.Message>> user) {
        users.add(user);
    }

    public void removeFromRoom(String username) {
        for(var user: users) {
            if (Objects.equals(user.getKey(), username)) {
                user.getValue().onCompleted();
                users.remove(user);
                break;
            }
        }
    }

    public void sendMessageToRoom(String senderName, String msg) {
        for(var user: users) {
            if(!Objects.equals(user.getKey(), senderName))
                user.getValue().onNext(Mapper.stringToMessage(senderName, msg));
        }
    }

}
