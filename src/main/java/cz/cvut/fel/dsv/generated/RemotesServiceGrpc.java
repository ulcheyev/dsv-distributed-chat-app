package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: ChatService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RemotesServiceGrpc {

  private RemotesServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "generated.RemotesService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.JoinRequest,
      generated.JoinResponse> getJoinRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinRoom",
      requestType = generated.JoinRequest.class,
      responseType = generated.JoinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.JoinRequest,
      generated.JoinResponse> getJoinRoomMethod() {
    io.grpc.MethodDescriptor<generated.JoinRequest, generated.JoinResponse> getJoinRoomMethod;
    if ((getJoinRoomMethod = RemotesServiceGrpc.getJoinRoomMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getJoinRoomMethod = RemotesServiceGrpc.getJoinRoomMethod) == null) {
          RemotesServiceGrpc.getJoinRoomMethod = getJoinRoomMethod =
              io.grpc.MethodDescriptor.<generated.JoinRequest, generated.JoinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "joinRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.JoinRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.JoinResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("joinRoom"))
              .build();
        }
      }
    }
    return getJoinRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getExitRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "exitRoom",
      requestType = generated.Remote.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getExitRoomMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.Empty> getExitRoomMethod;
    if ((getExitRoomMethod = RemotesServiceGrpc.getExitRoomMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getExitRoomMethod = RemotesServiceGrpc.getExitRoomMethod) == null) {
          RemotesServiceGrpc.getExitRoomMethod = getExitRoomMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "exitRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("exitRoom"))
              .build();
        }
      }
    }
    return getExitRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.Message> getPreflightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "preflight",
      requestType = generated.Remote.class,
      responseType = generated.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.Message> getPreflightMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.Message> getPreflightMethod;
    if ((getPreflightMethod = RemotesServiceGrpc.getPreflightMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getPreflightMethod = RemotesServiceGrpc.getPreflightMethod) == null) {
          RemotesServiceGrpc.getPreflightMethod = getPreflightMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "preflight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Message.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("preflight"))
              .build();
        }
      }
    }
    return getPreflightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Rooms,
      generated.Empty> getReceiveRoomsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveRooms",
      requestType = generated.Rooms.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Rooms,
      generated.Empty> getReceiveRoomsMethod() {
    io.grpc.MethodDescriptor<generated.Rooms, generated.Empty> getReceiveRoomsMethod;
    if ((getReceiveRoomsMethod = RemotesServiceGrpc.getReceiveRoomsMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getReceiveRoomsMethod = RemotesServiceGrpc.getReceiveRoomsMethod) == null) {
          RemotesServiceGrpc.getReceiveRoomsMethod = getReceiveRoomsMethod =
              io.grpc.MethodDescriptor.<generated.Rooms, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveRooms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Rooms.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("receiveRooms"))
              .build();
        }
      }
    }
    return getReceiveRoomsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.RoomEntry,
      generated.Empty> getReceiveRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveRoom",
      requestType = generated.RoomEntry.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.RoomEntry,
      generated.Empty> getReceiveRoomMethod() {
    io.grpc.MethodDescriptor<generated.RoomEntry, generated.Empty> getReceiveRoomMethod;
    if ((getReceiveRoomMethod = RemotesServiceGrpc.getReceiveRoomMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getReceiveRoomMethod = RemotesServiceGrpc.getReceiveRoomMethod) == null) {
          RemotesServiceGrpc.getReceiveRoomMethod = getReceiveRoomMethod =
              io.grpc.MethodDescriptor.<generated.RoomEntry, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomEntry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("receiveRoom"))
              .build();
        }
      }
    }
    return getReceiveRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.RoomEntry,
      generated.Empty> getRemoveRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeRoom",
      requestType = generated.RoomEntry.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.RoomEntry,
      generated.Empty> getRemoveRoomMethod() {
    io.grpc.MethodDescriptor<generated.RoomEntry, generated.Empty> getRemoveRoomMethod;
    if ((getRemoveRoomMethod = RemotesServiceGrpc.getRemoveRoomMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getRemoveRoomMethod = RemotesServiceGrpc.getRemoveRoomMethod) == null) {
          RemotesServiceGrpc.getRemoveRoomMethod = getRemoveRoomMethod =
              io.grpc.MethodDescriptor.<generated.RoomEntry, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomEntry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("removeRoom"))
              .build();
        }
      }
    }
    return getRemoveRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.PermissionRequest,
      generated.Empty> getReceivePermissionRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receivePermissionRequest",
      requestType = generated.PermissionRequest.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.PermissionRequest,
      generated.Empty> getReceivePermissionRequestMethod() {
    io.grpc.MethodDescriptor<generated.PermissionRequest, generated.Empty> getReceivePermissionRequestMethod;
    if ((getReceivePermissionRequestMethod = RemotesServiceGrpc.getReceivePermissionRequestMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getReceivePermissionRequestMethod = RemotesServiceGrpc.getReceivePermissionRequestMethod) == null) {
          RemotesServiceGrpc.getReceivePermissionRequestMethod = getReceivePermissionRequestMethod =
              io.grpc.MethodDescriptor.<generated.PermissionRequest, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receivePermissionRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.PermissionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("receivePermissionRequest"))
              .build();
        }
      }
    }
    return getReceivePermissionRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.PermissionResponse,
      generated.Empty> getReceivePermissionResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receivePermissionResponse",
      requestType = generated.PermissionResponse.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.PermissionResponse,
      generated.Empty> getReceivePermissionResponseMethod() {
    io.grpc.MethodDescriptor<generated.PermissionResponse, generated.Empty> getReceivePermissionResponseMethod;
    if ((getReceivePermissionResponseMethod = RemotesServiceGrpc.getReceivePermissionResponseMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getReceivePermissionResponseMethod = RemotesServiceGrpc.getReceivePermissionResponseMethod) == null) {
          RemotesServiceGrpc.getReceivePermissionResponseMethod = getReceivePermissionResponseMethod =
              io.grpc.MethodDescriptor.<generated.PermissionResponse, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receivePermissionResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.PermissionResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("receivePermissionResponse"))
              .build();
        }
      }
    }
    return getReceivePermissionResponseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Empty,
      generated.Empty> getUpdateRoomsTableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateRoomsTable",
      requestType = generated.Empty.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Empty,
      generated.Empty> getUpdateRoomsTableMethod() {
    io.grpc.MethodDescriptor<generated.Empty, generated.Empty> getUpdateRoomsTableMethod;
    if ((getUpdateRoomsTableMethod = RemotesServiceGrpc.getUpdateRoomsTableMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getUpdateRoomsTableMethod = RemotesServiceGrpc.getUpdateRoomsTableMethod) == null) {
          RemotesServiceGrpc.getUpdateRoomsTableMethod = getUpdateRoomsTableMethod =
              io.grpc.MethodDescriptor.<generated.Empty, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateRoomsTable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("updateRoomsTable"))
              .build();
        }
      }
    }
    return getUpdateRoomsTableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Message,
      generated.Empty> getReceiveMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveMessage",
      requestType = generated.Message.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Message,
      generated.Empty> getReceiveMessageMethod() {
    io.grpc.MethodDescriptor<generated.Message, generated.Empty> getReceiveMessageMethod;
    if ((getReceiveMessageMethod = RemotesServiceGrpc.getReceiveMessageMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getReceiveMessageMethod = RemotesServiceGrpc.getReceiveMessageMethod) == null) {
          RemotesServiceGrpc.getReceiveMessageMethod = getReceiveMessageMethod =
              io.grpc.MethodDescriptor.<generated.Message, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("receiveMessage"))
              .build();
        }
      }
    }
    return getReceiveMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Empty,
      generated.StringPayload> getReceiveGetRoomListRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveGetRoomListRequest",
      requestType = generated.Empty.class,
      responseType = generated.StringPayload.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Empty,
      generated.StringPayload> getReceiveGetRoomListRequestMethod() {
    io.grpc.MethodDescriptor<generated.Empty, generated.StringPayload> getReceiveGetRoomListRequestMethod;
    if ((getReceiveGetRoomListRequestMethod = RemotesServiceGrpc.getReceiveGetRoomListRequestMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getReceiveGetRoomListRequestMethod = RemotesServiceGrpc.getReceiveGetRoomListRequestMethod) == null) {
          RemotesServiceGrpc.getReceiveGetRoomListRequestMethod = getReceiveGetRoomListRequestMethod =
              io.grpc.MethodDescriptor.<generated.Empty, generated.StringPayload>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveGetRoomListRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.StringPayload.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("receiveGetRoomListRequest"))
              .build();
        }
      }
    }
    return getReceiveGetRoomListRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Empty,
      generated.StringPayload> getReceiveGetNodeListInCurrentRoomRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveGetNodeListInCurrentRoomRequest",
      requestType = generated.Empty.class,
      responseType = generated.StringPayload.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Empty,
      generated.StringPayload> getReceiveGetNodeListInCurrentRoomRequestMethod() {
    io.grpc.MethodDescriptor<generated.Empty, generated.StringPayload> getReceiveGetNodeListInCurrentRoomRequestMethod;
    if ((getReceiveGetNodeListInCurrentRoomRequestMethod = RemotesServiceGrpc.getReceiveGetNodeListInCurrentRoomRequestMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getReceiveGetNodeListInCurrentRoomRequestMethod = RemotesServiceGrpc.getReceiveGetNodeListInCurrentRoomRequestMethod) == null) {
          RemotesServiceGrpc.getReceiveGetNodeListInCurrentRoomRequestMethod = getReceiveGetNodeListInCurrentRoomRequestMethod =
              io.grpc.MethodDescriptor.<generated.Empty, generated.StringPayload>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveGetNodeListInCurrentRoomRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.StringPayload.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("receiveGetNodeListInCurrentRoomRequest"))
              .build();
        }
      }
    }
    return getReceiveGetNodeListInCurrentRoomRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getChangeNextNextMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changeNextNext",
      requestType = generated.Remote.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getChangeNextNextMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.Empty> getChangeNextNextMethod;
    if ((getChangeNextNextMethod = RemotesServiceGrpc.getChangeNextNextMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getChangeNextNextMethod = RemotesServiceGrpc.getChangeNextNextMethod) == null) {
          RemotesServiceGrpc.getChangeNextNextMethod = getChangeNextNextMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changeNextNext"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("changeNextNext"))
              .build();
        }
      }
    }
    return getChangeNextNextMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.Remote> getChangePrevMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changePrev",
      requestType = generated.Remote.class,
      responseType = generated.Remote.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.Remote> getChangePrevMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.Remote> getChangePrevMethod;
    if ((getChangePrevMethod = RemotesServiceGrpc.getChangePrevMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getChangePrevMethod = RemotesServiceGrpc.getChangePrevMethod) == null) {
          RemotesServiceGrpc.getChangePrevMethod = getChangePrevMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Remote>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changePrev"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("changePrev"))
              .build();
        }
      }
    }
    return getChangePrevMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getElectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "election",
      requestType = generated.Remote.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getElectionMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.Empty> getElectionMethod;
    if ((getElectionMethod = RemotesServiceGrpc.getElectionMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getElectionMethod = RemotesServiceGrpc.getElectionMethod) == null) {
          RemotesServiceGrpc.getElectionMethod = getElectionMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "election"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("election"))
              .build();
        }
      }
    }
    return getElectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getElectedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "elected",
      requestType = generated.Remote.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getElectedMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.Empty> getElectedMethod;
    if ((getElectedMethod = RemotesServiceGrpc.getElectedMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getElectedMethod = RemotesServiceGrpc.getElectedMethod) == null) {
          RemotesServiceGrpc.getElectedMethod = getElectedMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "elected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("elected"))
              .build();
        }
      }
    }
    return getElectedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getRepairTopologyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "repairTopology",
      requestType = generated.Remote.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.Empty> getRepairTopologyMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.Empty> getRepairTopologyMethod;
    if ((getRepairTopologyMethod = RemotesServiceGrpc.getRepairTopologyMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getRepairTopologyMethod = RemotesServiceGrpc.getRepairTopologyMethod) == null) {
          RemotesServiceGrpc.getRepairTopologyMethod = getRepairTopologyMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "repairTopology"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("repairTopology"))
              .build();
        }
      }
    }
    return getRepairTopologyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.JoinRequest,
      generated.Neighbours> getChangeNeighboursMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changeNeighbours",
      requestType = generated.JoinRequest.class,
      responseType = generated.Neighbours.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.JoinRequest,
      generated.Neighbours> getChangeNeighboursMethod() {
    io.grpc.MethodDescriptor<generated.JoinRequest, generated.Neighbours> getChangeNeighboursMethod;
    if ((getChangeNeighboursMethod = RemotesServiceGrpc.getChangeNeighboursMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getChangeNeighboursMethod = RemotesServiceGrpc.getChangeNeighboursMethod) == null) {
          RemotesServiceGrpc.getChangeNeighboursMethod = getChangeNeighboursMethod =
              io.grpc.MethodDescriptor.<generated.JoinRequest, generated.Neighbours>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changeNeighbours"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.JoinRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Neighbours.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("changeNeighbours"))
              .build();
        }
      }
    }
    return getChangeNeighboursMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Empty,
      generated.Health> getBeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "beat",
      requestType = generated.Empty.class,
      responseType = generated.Health.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Empty,
      generated.Health> getBeatMethod() {
    io.grpc.MethodDescriptor<generated.Empty, generated.Health> getBeatMethod;
    if ((getBeatMethod = RemotesServiceGrpc.getBeatMethod) == null) {
      synchronized (RemotesServiceGrpc.class) {
        if ((getBeatMethod = RemotesServiceGrpc.getBeatMethod) == null) {
          RemotesServiceGrpc.getBeatMethod = getBeatMethod =
              io.grpc.MethodDescriptor.<generated.Empty, generated.Health>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "beat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Health.getDefaultInstance()))
              .setSchemaDescriptor(new RemotesServiceMethodDescriptorSupplier("beat"))
              .build();
        }
      }
    }
    return getBeatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RemotesServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemotesServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemotesServiceStub>() {
        @java.lang.Override
        public RemotesServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemotesServiceStub(channel, callOptions);
        }
      };
    return RemotesServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RemotesServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemotesServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemotesServiceBlockingStub>() {
        @java.lang.Override
        public RemotesServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemotesServiceBlockingStub(channel, callOptions);
        }
      };
    return RemotesServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RemotesServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemotesServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemotesServiceFutureStub>() {
        @java.lang.Override
        public RemotesServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemotesServiceFutureStub(channel, callOptions);
        }
      };
    return RemotesServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Connecting methods
     * </pre>
     */
    default void joinRoom(generated.JoinRequest request,
        io.grpc.stub.StreamObserver<generated.JoinResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getJoinRoomMethod(), responseObserver);
    }

    /**
     */
    default void exitRoom(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExitRoomMethod(), responseObserver);
    }

    /**
     * <pre>
     * To get message streamobserver
     * </pre>
     */
    default void preflight(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Message> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPreflightMethod(), responseObserver);
    }

    /**
     */
    default void receiveRooms(generated.Rooms request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveRoomsMethod(), responseObserver);
    }

    /**
     */
    default void receiveRoom(generated.RoomEntry request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveRoomMethod(), responseObserver);
    }

    /**
     */
    default void removeRoom(generated.RoomEntry request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveRoomMethod(), responseObserver);
    }

    /**
     * <pre>
     * Methods to control CS when non existing rooms are creating
     * </pre>
     */
    default void receivePermissionRequest(generated.PermissionRequest request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceivePermissionRequestMethod(), responseObserver);
    }

    /**
     */
    default void receivePermissionResponse(generated.PermissionResponse request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceivePermissionResponseMethod(), responseObserver);
    }

    /**
     */
    default void updateRoomsTable(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateRoomsTableMethod(), responseObserver);
    }

    /**
     * <pre>
     * Chat methods
     * </pre>
     */
    default void receiveMessage(generated.Message request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveMessageMethod(), responseObserver);
    }

    /**
     */
    default void receiveGetRoomListRequest(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.StringPayload> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveGetRoomListRequestMethod(), responseObserver);
    }

    /**
     */
    default void receiveGetNodeListInCurrentRoomRequest(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.StringPayload> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveGetNodeListInCurrentRoomRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     *Leader election
     * </pre>
     */
    default void changeNextNext(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChangeNextNextMethod(), responseObserver);
    }

    /**
     */
    default void changePrev(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Remote> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChangePrevMethod(), responseObserver);
    }

    /**
     */
    default void election(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getElectionMethod(), responseObserver);
    }

    /**
     */
    default void elected(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getElectedMethod(), responseObserver);
    }

    /**
     */
    default void repairTopology(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRepairTopologyMethod(), responseObserver);
    }

    /**
     */
    default void changeNeighbours(generated.JoinRequest request,
        io.grpc.stub.StreamObserver<generated.Neighbours> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChangeNeighboursMethod(), responseObserver);
    }

    /**
     */
    default void beat(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.Health> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBeatMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RemotesService.
   */
  public static abstract class RemotesServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RemotesServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RemotesService.
   */
  public static final class RemotesServiceStub
      extends io.grpc.stub.AbstractAsyncStub<RemotesServiceStub> {
    private RemotesServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemotesServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemotesServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Connecting methods
     * </pre>
     */
    public void joinRoom(generated.JoinRequest request,
        io.grpc.stub.StreamObserver<generated.JoinResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getJoinRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exitRoom(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExitRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * To get message streamobserver
     * </pre>
     */
    public void preflight(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Message> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getPreflightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveRooms(generated.Rooms request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveRoomsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveRoom(generated.RoomEntry request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeRoom(generated.RoomEntry request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Methods to control CS when non existing rooms are creating
     * </pre>
     */
    public void receivePermissionRequest(generated.PermissionRequest request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceivePermissionRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receivePermissionResponse(generated.PermissionResponse request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceivePermissionResponseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateRoomsTable(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateRoomsTableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Chat methods
     * </pre>
     */
    public void receiveMessage(generated.Message request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveGetRoomListRequest(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.StringPayload> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveGetRoomListRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveGetNodeListInCurrentRoomRequest(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.StringPayload> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveGetNodeListInCurrentRoomRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Leader election
     * </pre>
     */
    public void changeNextNext(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChangeNextNextMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changePrev(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Remote> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChangePrevMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void election(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void elected(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void repairTopology(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRepairTopologyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeNeighbours(generated.JoinRequest request,
        io.grpc.stub.StreamObserver<generated.Neighbours> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChangeNeighboursMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void beat(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.Health> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBeatMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RemotesService.
   */
  public static final class RemotesServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RemotesServiceBlockingStub> {
    private RemotesServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemotesServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemotesServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Connecting methods
     * </pre>
     */
    public generated.JoinResponse joinRoom(generated.JoinRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getJoinRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty exitRoom(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExitRoomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * To get message streamobserver
     * </pre>
     */
    public java.util.Iterator<generated.Message> preflight(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getPreflightMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty receiveRooms(generated.Rooms request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveRoomsMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty receiveRoom(generated.RoomEntry request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty removeRoom(generated.RoomEntry request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveRoomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Methods to control CS when non existing rooms are creating
     * </pre>
     */
    public generated.Empty receivePermissionRequest(generated.PermissionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceivePermissionRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty receivePermissionResponse(generated.PermissionResponse request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceivePermissionResponseMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty updateRoomsTable(generated.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateRoomsTableMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Chat methods
     * </pre>
     */
    public generated.Empty receiveMessage(generated.Message request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.StringPayload receiveGetRoomListRequest(generated.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveGetRoomListRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.StringPayload receiveGetNodeListInCurrentRoomRequest(generated.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveGetNodeListInCurrentRoomRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Leader election
     * </pre>
     */
    public generated.Empty changeNextNext(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChangeNextNextMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Remote changePrev(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChangePrevMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty election(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getElectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty elected(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getElectedMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty repairTopology(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRepairTopologyMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Neighbours changeNeighbours(generated.JoinRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChangeNeighboursMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Health beat(generated.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBeatMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RemotesService.
   */
  public static final class RemotesServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<RemotesServiceFutureStub> {
    private RemotesServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemotesServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemotesServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Connecting methods
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.JoinResponse> joinRoom(
        generated.JoinRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getJoinRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> exitRoom(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExitRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> receiveRooms(
        generated.Rooms request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveRoomsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> receiveRoom(
        generated.RoomEntry request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> removeRoom(
        generated.RoomEntry request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveRoomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Methods to control CS when non existing rooms are creating
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> receivePermissionRequest(
        generated.PermissionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceivePermissionRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> receivePermissionResponse(
        generated.PermissionResponse request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceivePermissionResponseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> updateRoomsTable(
        generated.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateRoomsTableMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Chat methods
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> receiveMessage(
        generated.Message request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.StringPayload> receiveGetRoomListRequest(
        generated.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveGetRoomListRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.StringPayload> receiveGetNodeListInCurrentRoomRequest(
        generated.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveGetNodeListInCurrentRoomRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Leader election
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> changeNextNext(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChangeNextNextMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Remote> changePrev(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChangePrevMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> election(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> elected(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> repairTopology(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRepairTopologyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Neighbours> changeNeighbours(
        generated.JoinRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChangeNeighboursMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Health> beat(
        generated.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBeatMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN_ROOM = 0;
  private static final int METHODID_EXIT_ROOM = 1;
  private static final int METHODID_PREFLIGHT = 2;
  private static final int METHODID_RECEIVE_ROOMS = 3;
  private static final int METHODID_RECEIVE_ROOM = 4;
  private static final int METHODID_REMOVE_ROOM = 5;
  private static final int METHODID_RECEIVE_PERMISSION_REQUEST = 6;
  private static final int METHODID_RECEIVE_PERMISSION_RESPONSE = 7;
  private static final int METHODID_UPDATE_ROOMS_TABLE = 8;
  private static final int METHODID_RECEIVE_MESSAGE = 9;
  private static final int METHODID_RECEIVE_GET_ROOM_LIST_REQUEST = 10;
  private static final int METHODID_RECEIVE_GET_NODE_LIST_IN_CURRENT_ROOM_REQUEST = 11;
  private static final int METHODID_CHANGE_NEXT_NEXT = 12;
  private static final int METHODID_CHANGE_PREV = 13;
  private static final int METHODID_ELECTION = 14;
  private static final int METHODID_ELECTED = 15;
  private static final int METHODID_REPAIR_TOPOLOGY = 16;
  private static final int METHODID_CHANGE_NEIGHBOURS = 17;
  private static final int METHODID_BEAT = 18;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_JOIN_ROOM:
          serviceImpl.joinRoom((generated.JoinRequest) request,
              (io.grpc.stub.StreamObserver<generated.JoinResponse>) responseObserver);
          break;
        case METHODID_EXIT_ROOM:
          serviceImpl.exitRoom((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_PREFLIGHT:
          serviceImpl.preflight((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.Message>) responseObserver);
          break;
        case METHODID_RECEIVE_ROOMS:
          serviceImpl.receiveRooms((generated.Rooms) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_ROOM:
          serviceImpl.receiveRoom((generated.RoomEntry) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_REMOVE_ROOM:
          serviceImpl.removeRoom((generated.RoomEntry) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_PERMISSION_REQUEST:
          serviceImpl.receivePermissionRequest((generated.PermissionRequest) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_PERMISSION_RESPONSE:
          serviceImpl.receivePermissionResponse((generated.PermissionResponse) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_ROOMS_TABLE:
          serviceImpl.updateRoomsTable((generated.Empty) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_MESSAGE:
          serviceImpl.receiveMessage((generated.Message) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_GET_ROOM_LIST_REQUEST:
          serviceImpl.receiveGetRoomListRequest((generated.Empty) request,
              (io.grpc.stub.StreamObserver<generated.StringPayload>) responseObserver);
          break;
        case METHODID_RECEIVE_GET_NODE_LIST_IN_CURRENT_ROOM_REQUEST:
          serviceImpl.receiveGetNodeListInCurrentRoomRequest((generated.Empty) request,
              (io.grpc.stub.StreamObserver<generated.StringPayload>) responseObserver);
          break;
        case METHODID_CHANGE_NEXT_NEXT:
          serviceImpl.changeNextNext((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_CHANGE_PREV:
          serviceImpl.changePrev((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.Remote>) responseObserver);
          break;
        case METHODID_ELECTION:
          serviceImpl.election((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_ELECTED:
          serviceImpl.elected((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_REPAIR_TOPOLOGY:
          serviceImpl.repairTopology((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_CHANGE_NEIGHBOURS:
          serviceImpl.changeNeighbours((generated.JoinRequest) request,
              (io.grpc.stub.StreamObserver<generated.Neighbours>) responseObserver);
          break;
        case METHODID_BEAT:
          serviceImpl.beat((generated.Empty) request,
              (io.grpc.stub.StreamObserver<generated.Health>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getJoinRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.JoinRequest,
              generated.JoinResponse>(
                service, METHODID_JOIN_ROOM)))
        .addMethod(
          getExitRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.Empty>(
                service, METHODID_EXIT_ROOM)))
        .addMethod(
          getPreflightMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              generated.Remote,
              generated.Message>(
                service, METHODID_PREFLIGHT)))
        .addMethod(
          getReceiveRoomsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Rooms,
              generated.Empty>(
                service, METHODID_RECEIVE_ROOMS)))
        .addMethod(
          getReceiveRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.RoomEntry,
              generated.Empty>(
                service, METHODID_RECEIVE_ROOM)))
        .addMethod(
          getRemoveRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.RoomEntry,
              generated.Empty>(
                service, METHODID_REMOVE_ROOM)))
        .addMethod(
          getReceivePermissionRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.PermissionRequest,
              generated.Empty>(
                service, METHODID_RECEIVE_PERMISSION_REQUEST)))
        .addMethod(
          getReceivePermissionResponseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.PermissionResponse,
              generated.Empty>(
                service, METHODID_RECEIVE_PERMISSION_RESPONSE)))
        .addMethod(
          getUpdateRoomsTableMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Empty,
              generated.Empty>(
                service, METHODID_UPDATE_ROOMS_TABLE)))
        .addMethod(
          getReceiveMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Message,
              generated.Empty>(
                service, METHODID_RECEIVE_MESSAGE)))
        .addMethod(
          getReceiveGetRoomListRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Empty,
              generated.StringPayload>(
                service, METHODID_RECEIVE_GET_ROOM_LIST_REQUEST)))
        .addMethod(
          getReceiveGetNodeListInCurrentRoomRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Empty,
              generated.StringPayload>(
                service, METHODID_RECEIVE_GET_NODE_LIST_IN_CURRENT_ROOM_REQUEST)))
        .addMethod(
          getChangeNextNextMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.Empty>(
                service, METHODID_CHANGE_NEXT_NEXT)))
        .addMethod(
          getChangePrevMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.Remote>(
                service, METHODID_CHANGE_PREV)))
        .addMethod(
          getElectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.Empty>(
                service, METHODID_ELECTION)))
        .addMethod(
          getElectedMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.Empty>(
                service, METHODID_ELECTED)))
        .addMethod(
          getRepairTopologyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.Empty>(
                service, METHODID_REPAIR_TOPOLOGY)))
        .addMethod(
          getChangeNeighboursMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.JoinRequest,
              generated.Neighbours>(
                service, METHODID_CHANGE_NEIGHBOURS)))
        .addMethod(
          getBeatMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Empty,
              generated.Health>(
                service, METHODID_BEAT)))
        .build();
  }

  private static abstract class RemotesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RemotesServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.ChatService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RemotesService");
    }
  }

  private static final class RemotesServiceFileDescriptorSupplier
      extends RemotesServiceBaseDescriptorSupplier {
    RemotesServiceFileDescriptorSupplier() {}
  }

  private static final class RemotesServiceMethodDescriptorSupplier
      extends RemotesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    RemotesServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RemotesServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RemotesServiceFileDescriptorSupplier())
              .addMethod(getJoinRoomMethod())
              .addMethod(getExitRoomMethod())
              .addMethod(getPreflightMethod())
              .addMethod(getReceiveRoomsMethod())
              .addMethod(getReceiveRoomMethod())
              .addMethod(getRemoveRoomMethod())
              .addMethod(getReceivePermissionRequestMethod())
              .addMethod(getReceivePermissionResponseMethod())
              .addMethod(getUpdateRoomsTableMethod())
              .addMethod(getReceiveMessageMethod())
              .addMethod(getReceiveGetRoomListRequestMethod())
              .addMethod(getReceiveGetNodeListInCurrentRoomRequestMethod())
              .addMethod(getChangeNextNextMethod())
              .addMethod(getChangePrevMethod())
              .addMethod(getElectionMethod())
              .addMethod(getElectedMethod())
              .addMethod(getRepairTopologyMethod())
              .addMethod(getChangeNeighboursMethod())
              .addMethod(getBeatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
