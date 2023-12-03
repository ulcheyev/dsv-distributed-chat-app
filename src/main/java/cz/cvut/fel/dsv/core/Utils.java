package cz.cvut.fel.dsv.core;

import generated.RemotesServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Map;

public class Utils {







    public static class Skeleton {
        private static ManagedChannel managedChannel;

        public static generated.RemotesServiceGrpc.RemotesServiceStub getAsyncSkeleton(String host, int port) {
            managedChannel = ManagedChannelBuilder
                    .forAddress(host, port)
                    .usePlaintext()
                    .build();
            return generated.RemotesServiceGrpc.newStub(managedChannel);
        }

        public static generated.RemotesServiceGrpc.RemotesServiceBlockingStub getSyncSkeleton(String host, int port) {
            managedChannel = ManagedChannelBuilder
                    .forAddress(host, port)
                    .usePlaintext()
                    .build();
            return generated.RemotesServiceGrpc.newBlockingStub(managedChannel);
        }

        public static RemotesServiceGrpc.RemotesServiceFutureStub getFutureStub(String host, int port) {
            managedChannel = ManagedChannelBuilder
                    .forAddress(host, port)
                    .usePlaintext()
                    .build();
            return generated.RemotesServiceGrpc.newFutureStub(managedChannel);
        }

        public static void shutdown() {
            managedChannel.shutdown();
        }

    }



    public static class Mapper {

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

        public static generated.Rooms leaderRoomsToRemoteRooms(Map<String, Address> map) {
            generated.Rooms.Builder builder = generated.Rooms.newBuilder();
            for(var lead: map.entrySet()) {
                builder.addRooms(generated.RoomEntry
                        .newBuilder()
                        .setRoomName(lead.getKey())
                        .setRoomOwner(Mapper.addressToRemote(lead.getValue()))
                        .build());
            }
            return builder.build();
        }

        public static DsvRemote remoteToDsvRemote(generated.Remote remote) {
            return DsvRemote.builder()
                    .address(remoteToAddress(remote))
                    .username(remote.getUsername())
                    .build();
        }

    }
}
