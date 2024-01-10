package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.DsvRemote;
import cz.cvut.fel.dsv.utils.Utils;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    @Getter private final String roomName;
    private final List<DsvPair<DsvRemote, StreamObserver<generated.ChatMessage>>> users;
    private Address leader;

    public Room(Address leader, String name) {
        this.roomName = name;
        this.leader = leader;
        users = new ArrayList<>();
    }

    public Room(String name) {
        this.roomName = name;
        users = new ArrayList<>();
    }

    public void addToRoom(DsvPair<DsvRemote, StreamObserver<generated.ChatMessage>> user) {
        users.add(user);
    }

    public void removeFromRoom(Long id) {
        // removing
        for (var user : users) {
            if (Objects.equals(user.getKey().getAddress().getId(), id)) {
                user.getValue().onCompleted();
                users.remove(user);
                break;
            }
        }
    }

    public void sendMessageToRoom(generated.ChatMessage msg) {
        for (var user : users) {
            if (user.getKey().getAddress().getId() != msg.getRemote().getNodeId())
                try {
                    user.getValue().onNext(msg);
                } catch (StatusRuntimeException e) {
                    System.out.println(user.getKey().getAddress().getId());
                    System.out.println(e.getMessage());
                    generated.ElectionServiceGrpc.newBlockingStub(Utils.Skeleton.buildManagedChannel(leader))
                            .repairTopology(Utils.Mapper.addressToRemote(user.getKey().getAddress()));
                    users.remove(user);
                }
        }
    }

    public void disconnectAllUsers() {
        for (var user : users) {
            user.getValue().onCompleted();
        }
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public int getSize() {
        return users.size();
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
