package cz.cvut.fel.dsv.utils;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvNeighbours;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.DsvRemote;
import cz.cvut.fel.dsv.core.service.DsvClientInterceptor;
import generated.ChatMessage;
import generated.Remote;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Utils {

    private Utils() {
    }

    public static <R> Optional<R> handleAndReturn(Function<Object, R> funcToHandle, UnaryOperator<Void> handler) {
        Optional<R> toRet = Optional.empty();
        try {
            toRet = Optional.ofNullable(funcToHandle.apply(generated.Empty.getDefaultInstance()));
        } catch (Exception e) {
            handler.apply(null);
        }
        return toRet;
    }

    public static void tryToSleep(Integer sec) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMachineIp(String interfaCe){
        String addr = "localhost";
        try {
            NetworkInterface networkInterface = NetworkInterface.getByName(interfaCe);
            if (networkInterface == null) {
                return addr;
            }

            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                addr = inetAddress.getHostAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
            addr =  "localhost";
        }
        return addr;
    }

    public static class Skeleton {


        private Skeleton() {
        }

        public static ManagedChannel buildManagedChannel(Address address) {
            ManagedChannel build = ManagedChannelBuilder
                    .forAddress(address.getHostname(), address.getPort())
                    .usePlaintext()
                    .intercept(new DsvClientInterceptor())
                    .build();
            build.notifyWhenStateChanged(ConnectivityState.IDLE, build::shutdown);
            return build;
        }

        public static ManagedChannel buildManagedChannel(generated.Remote remote) {
            return buildManagedChannel(Utils.Mapper.remoteToAddress(remote));
        }

        public static ManagedChannel buildChannel(Address address){
            return ManagedChannelBuilder
                    .forAddress(address.getHostname(), address.getPort())
                    .usePlaintext()
                    .intercept(new DsvClientInterceptor())
                    .build();
        }

        public static ManagedChannel buildChannel(generated.Remote remote) {
            return buildChannel(Utils.Mapper.remoteToAddress(remote));
        }

    }

    public static class Mapper {
        private static final Node node = Node.getInstance();

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

        public static ChatMessage stringToMessage(Remote remote, String message) {
            return generated.ChatMessage.newBuilder()
                    .setMsg(message)
                    .setRemote(remote)
                    .build();
        }

        public static generated.Remote nodeToRemote() {
            return generated.Remote.newBuilder()
                    .setHostname(node.getAddress().getHostname())
                    .setNodeId(node.getAddress().getId())
                    .setPort(node.getAddress().getPort())
                    .setUsername(node.getUsername())
                    .build();
        }

        public static generated.Rooms leaderRoomsToRemoteRooms(Map<String, DsvPair<Address, Address>> map) {
            generated.Rooms.Builder builder = generated.Rooms.newBuilder();
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

        public static generated.RoomEntry nodeToRoomEntry() {
            return generated.RoomEntry.newBuilder()
                    .setRoomName(node.getCurrentRoom())
                    .setRoomOwner(Utils.Mapper.addressToRemote(node.getAddress()))
                    .setRoomBackup(Utils.Mapper.addressToRemote(node.getDsvNeighbours().getPrev()))
                    .build();
        }

        public static generated.RoomEntry pairToRoomEntry(String roomName, DsvPair<Address, Address> pair) {
            return generated.RoomEntry.newBuilder()
                    .setRoomName(roomName)
                    .setRoomOwner(addressToRemote(pair.getKey()))
                    .setRoomBackup(addressToRemote(pair.getValue()))
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
