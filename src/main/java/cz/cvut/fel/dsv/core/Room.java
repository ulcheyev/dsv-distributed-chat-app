package cz.cvut.fel.dsv.core;

import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.N;

import java.util.*;

public class Room {
    @Getter @Setter private String name;
    private List<Map.Entry<String, StreamObserver<generated.RoomChatMessage>>> users;
    public Room(String name){
        this.name = name;
        this.users = new ArrayList<>();
    }
    public void addUser(String username, StreamObserver<generated.RoomChatMessage> streamObserver) {
        users.add(new AbstractMap.SimpleEntry<String, StreamObserver<generated.RoomChatMessage>>(username, streamObserver));
    }

    public void deleteUser(String username) {
        for(var user: users) {
            if(Objects.equals(user.getKey(), username))
            {
                user.getValue().onCompleted();
                users.remove(user);
                // todo will room be deleted when users size is 0?
                break;
            }
        }
    }

    public void sendMessage(String msg, String username) {
        for(var user: users) {
            if(!Objects.equals(user.getKey(), username)){
                user.getValue().onNext(generated.RoomChatMessage
                        .newBuilder()
                        .setRoom(name)
                        .setMsg(generated.ChatMessage
                                .newBuilder()
                                .setMessage(msg)
                                .setSenderUsername(username)
                                .build())
                        .build());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Room ").append(name).append("]");
        for(var user: users) {
            sb.append(user.getKey());
        }
        return sb.toString();
    }
}
