package cz.cvut.fel.dsv.core;

import generated.Rooms;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {


    public static generated.Remote addressToRemote(Address address) {
        return generated.Remote.newBuilder()
                .setHostname(address.getHostname())
                .setNodeId(address.getId())
                .setPort(address.getPort())
                .build();
    }

    public static Address remoteToAddress(generated.Remote remote) {
        return new Address(remote.getHostname(), remote.getPort());
    }

    public static generated.Message stringToMessage(String senderName, String message) {
        return generated.Message.newBuilder()
                .setMsg(message)
                .setUsername(senderName)
                .build();
    }

    public static generated.Remote nodeToRemote(NodeImpl node) {
        return generated.Remote.newBuilder()
                .setHostname(node.getAddress().getHostname())
                .setNodeId(node.getAddress().getId())
                .setPort(node.getAddress().getPort())
                .setUsername(node.getUsername())
                .build();
    }

    public static generated.Rooms leaderRoomsToRemoteRooms(Map<String, Address> map) {
        Rooms.Builder builder = Rooms.newBuilder();
        for(var lead: map.entrySet()) {
            builder.addRooms(generated.RoomEntry
                    .newBuilder()
                    .setRoomName(lead.getKey())
                    .setRoomOwner(Mapper.addressToRemote(lead.getValue()))
                    .build());
        }
        return builder.build();
    }




}
