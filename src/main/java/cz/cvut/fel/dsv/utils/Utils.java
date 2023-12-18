package cz.cvut.fel.dsv.utils;

import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvNeighbours;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.DsvRemote;
import cz.cvut.fel.dsv.core.Node;
import generated.RemotesServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Utils {

    private Utils() {
    }

    public static class Skeleton {
        private static ManagedChannel managedChannel;

        private Skeleton() {
        }

        public static generated.RemotesServiceGrpc.RemotesServiceStub getAsyncSkeleton(Address address) {
            managedChannel = ManagedChannelBuilder
                    .forAddress(address.getHostname(), address.getPort())
                    .usePlaintext()
                    .build();
            return generated.RemotesServiceGrpc.newStub(managedChannel);
        }

        public static generated.RemotesServiceGrpc.RemotesServiceBlockingStub getSyncSkeleton(Address address) {
            managedChannel = ManagedChannelBuilder
                    .forAddress(address.getHostname(), address.getPort())
                    .usePlaintext()
                    .build();
            return generated.RemotesServiceGrpc.newBlockingStub(managedChannel);
        }

        public static RemotesServiceGrpc.RemotesServiceFutureStub getFutureSkeleton(Address address) {
            managedChannel = ManagedChannelBuilder
                    .forAddress(address.getHostname(), address.getPort())
                    .usePlaintext()
                    .build();
            return generated.RemotesServiceGrpc.newFutureStub(managedChannel);
        }

        public static void shutdown() {
            managedChannel.shutdown();
        }

    }

    public static class Mapper {

        private Mapper() {
        }

        public static generated.Remote addressToRemote(Address address) {
            return generated.Remote.newBuilder()
                    .setHostname(address.getHostname())
                    .setNodeId(address.getId())
                    .setPort(address.getPort())
                    .build();
        }

        public static Address remoteToAddress(generated.Remote remote) {
            return new Address(remote.getHostname(), remote.getPort(), remote.getNodeId());
        }

        public static generated.Message stringToMessage(generated.Remote remote, String message) {
            return generated.Message.newBuilder()
                    .setMsg(message)
                    .setRemote(remote)
                    .build();
        }

        public static generated.Remote nodeToRemote(Node node) {
            return generated.Remote.newBuilder()
                    .setHostname(node.getAddress().getHostname())
                    .setNodeId(node.getAddress().getId())
                    .setPort(node.getAddress().getPort())
                    .setUsername(node.getUsername())
                    .build();
        }

        public static generated.Rooms leaderRoomsToRemoteRooms(Map<String, DsvPair<Address, Address>> map, boolean isNotVisited) {
            generated.Rooms.Builder builder = generated.Rooms.newBuilder();
            builder.setIsNotVisited(isNotVisited);
            for (var entry : map.entrySet()) {
                builder.addRooms(generated.RoomEntry
                        .newBuilder()
                        .setRoomName(entry.getKey())
                        .setRoomOwner(Mapper.addressToRemote(entry.getValue().getKey()))
                        .setRoomBackup(Mapper.addressToRemote(entry.getValue().getValue()))
                        .build());
            }
            return builder.build();
        }

        public static generated.RoomEntry nodeToRoomEntry(Node node) {
            return generated.RoomEntry.newBuilder()
                    .setRoomName(node.getCurrentRoom())
                    .setRoomOwner(Utils.Mapper.addressToRemote(node.getAddress()))
                    .setRoomBackup(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getPrev()))
                    .build();
        }

        public static ConcurrentMap<String, Address> remoteRoomsToLeaderRooms(generated.Rooms rooms) {
            var map = new ConcurrentHashMap<String, Address>();
            for (var room : rooms.getRoomsList()) {
                map.put(room.getRoomName(), remoteToAddress(room.getRoomOwner()));
            }
            return map;
        }

        public static DsvRemote remoteToDsvRemote(generated.Remote remote) {
            return DsvRemote.builder()
                    .address(remoteToAddress(remote))
                    .username(remote.getUsername())
                    .build();
        }

        public static generated.Remote dsvRemoteToRemote(DsvRemote dsvRemote) {
            return generated.Remote.newBuilder().setUsername(dsvRemote.getUsername())
                    .setHostname(dsvRemote.getAddress().getHostname())
                    .setPort(dsvRemote.getAddress().getPort())
                    .setNodeId(dsvRemote.getAddress().getId())
                    .build();
        }

        public static generated.Neighbours remoteToNeighbours(generated.Remote remote) {
            return generated.Neighbours.newBuilder()
                    .setNext(remote)
                    .setNextNext(remote)
                    .setPrev(remote)
                    .setLeader(remote)
                    .build();
        }

        public static DsvNeighbours neighboursToDsvNeighbours(generated.Neighbours neighbours) {
            return new DsvNeighbours(
                    remoteToAddress(neighbours.getNext()),
                    remoteToAddress(neighbours.getNextNext()),
                    remoteToAddress(neighbours.getPrev()),
                    remoteToAddress(neighbours.getLeader())
            );
        }

        public static generated.Neighbours dsvNeighboursToNeighbours(DsvNeighbours dsvNeighbours) {
            return generated.Neighbours.newBuilder()
                    .setNext(addressToRemote(dsvNeighbours.getNext()))
                    .setNextNext(addressToRemote(dsvNeighbours.getNextNext()))
                    .setPrev(addressToRemote(dsvNeighbours.getPrev()))
                    .setLeader(addressToRemote(dsvNeighbours.getLeader()))
                    .build();
        }
    }
}
