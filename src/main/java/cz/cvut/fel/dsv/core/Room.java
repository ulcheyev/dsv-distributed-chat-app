package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.DsvRemote;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Room {
    private static final Logger logger = DsvLogger.getLogger(Room.class);
    @Getter private final String roomName;
    private final List<DsvPair<DsvRemote, StreamObserver<generated.Message>>> users;
    private Node leader;

    public Room(Node leader, String name) {
        this.roomName = name;
        users = new ArrayList<>();
        this.leader = leader;
    }

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
            if (!Objects.equals(user.getKey().getAddress().getId(), msg.getRemote().getNodeId()))
                try {
                    user.getValue().onNext(msg);    // TODO: status error when node is missing
                } catch (StatusRuntimeException e) {
                    Utils.Skeleton.getSyncSkeleton(Utils.Mapper.remoteToAddress(msg.getRemote()))
                            .repairTopology(Utils.Mapper.addressToRemote(user.getKey().getAddress()));
                }
        }
    }

    public void disconnectAllUsers() {
        for(var user: users){
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
