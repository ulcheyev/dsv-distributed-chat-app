package cz.cvut.fel.dsv.utils;

import cz.cvut.fel.dsv.core.data.Address;
import generated.JoinResponse;

public class Director {

    private Director() {
    }

    public static generated.JoinResponse buildJoinRes(boolean isLeader, Object leader, generated.Neighbours neighbours) {
        JoinResponse.Builder builder = generated.JoinResponse.newBuilder();
        if (leader instanceof Address address)
            builder.setLeader(Utils.Mapper.addressToRemote(address));
        else if (leader instanceof generated.Remote remote)
            builder.setLeader(remote);

        if (neighbours != null) builder.setNeighbours(neighbours);
        return builder.setIsLeader(isLeader).build();
    }

    public static generated.StringPayload buildStrPayload(String message) {
        return generated.StringPayload.newBuilder()
                .setMsg(message)
                .build();
    }

    public static generated.JoinRequest buildJoinReq(String roomName, Integer delay) {
        return generated.JoinRequest.newBuilder()
                .setRoomName(roomName)
                .setRemote(Utils.Mapper.nodeToRemote())
                .setDelay(delay)
                .build();
    }

    public static generated.PermissionRequest buildPermReq(int timestamp, Address current) {
        return generated.PermissionRequest.newBuilder()
                .setClock(timestamp)
                .setRequestByRemote(Utils.Mapper.addressToRemote(current))
                .build();
    }

    public static generated.PermissionResponse buildPermRes(Address current) {
        return generated.PermissionResponse.newBuilder()
                .setResponseByRemote(Utils.Mapper.addressToRemote(current))
                .build();
    }
}
