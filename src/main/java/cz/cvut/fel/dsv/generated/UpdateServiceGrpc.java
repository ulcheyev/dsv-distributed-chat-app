package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: ChatService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UpdateServiceGrpc {

  private UpdateServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "generated.UpdateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.PermissionRequest,
      generated.GrantMessage> getReceivePermissionRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receivePermissionRequest",
      requestType = generated.PermissionRequest.class,
      responseType = generated.GrantMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.PermissionRequest,
      generated.GrantMessage> getReceivePermissionRequestMethod() {
    io.grpc.MethodDescriptor<generated.PermissionRequest, generated.GrantMessage> getReceivePermissionRequestMethod;
    if ((getReceivePermissionRequestMethod = UpdateServiceGrpc.getReceivePermissionRequestMethod) == null) {
      synchronized (UpdateServiceGrpc.class) {
        if ((getReceivePermissionRequestMethod = UpdateServiceGrpc.getReceivePermissionRequestMethod) == null) {
          UpdateServiceGrpc.getReceivePermissionRequestMethod = getReceivePermissionRequestMethod =
              io.grpc.MethodDescriptor.<generated.PermissionRequest, generated.GrantMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receivePermissionRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.PermissionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.GrantMessage.getDefaultInstance()))
              .setSchemaDescriptor(new UpdateServiceMethodDescriptorSupplier("receivePermissionRequest"))
              .build();
        }
      }
    }
    return getReceivePermissionRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.PermissionResponse,
      generated.Empty> getReceivePermissionReplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receivePermissionReply",
      requestType = generated.PermissionResponse.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.PermissionResponse,
      generated.Empty> getReceivePermissionReplyMethod() {
    io.grpc.MethodDescriptor<generated.PermissionResponse, generated.Empty> getReceivePermissionReplyMethod;
    if ((getReceivePermissionReplyMethod = UpdateServiceGrpc.getReceivePermissionReplyMethod) == null) {
      synchronized (UpdateServiceGrpc.class) {
        if ((getReceivePermissionReplyMethod = UpdateServiceGrpc.getReceivePermissionReplyMethod) == null) {
          UpdateServiceGrpc.getReceivePermissionReplyMethod = getReceivePermissionReplyMethod =
              io.grpc.MethodDescriptor.<generated.PermissionResponse, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receivePermissionReply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.PermissionResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new UpdateServiceMethodDescriptorSupplier("receivePermissionReply"))
              .build();
        }
      }
    }
    return getReceivePermissionReplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Rooms,
      generated.Message> getReceiveRoomsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveRooms",
      requestType = generated.Rooms.class,
      responseType = generated.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Rooms,
      generated.Message> getReceiveRoomsMethod() {
    io.grpc.MethodDescriptor<generated.Rooms, generated.Message> getReceiveRoomsMethod;
    if ((getReceiveRoomsMethod = UpdateServiceGrpc.getReceiveRoomsMethod) == null) {
      synchronized (UpdateServiceGrpc.class) {
        if ((getReceiveRoomsMethod = UpdateServiceGrpc.getReceiveRoomsMethod) == null) {
          UpdateServiceGrpc.getReceiveRoomsMethod = getReceiveRoomsMethod =
              io.grpc.MethodDescriptor.<generated.Rooms, generated.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveRooms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Rooms.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Message.getDefaultInstance()))
              .setSchemaDescriptor(new UpdateServiceMethodDescriptorSupplier("receiveRooms"))
              .build();
        }
      }
    }
    return getReceiveRoomsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.RoomEntry,
      generated.Message> getReceiveRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveRoom",
      requestType = generated.RoomEntry.class,
      responseType = generated.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.RoomEntry,
      generated.Message> getReceiveRoomMethod() {
    io.grpc.MethodDescriptor<generated.RoomEntry, generated.Message> getReceiveRoomMethod;
    if ((getReceiveRoomMethod = UpdateServiceGrpc.getReceiveRoomMethod) == null) {
      synchronized (UpdateServiceGrpc.class) {
        if ((getReceiveRoomMethod = UpdateServiceGrpc.getReceiveRoomMethod) == null) {
          UpdateServiceGrpc.getReceiveRoomMethod = getReceiveRoomMethod =
              io.grpc.MethodDescriptor.<generated.RoomEntry, generated.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomEntry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Message.getDefaultInstance()))
              .setSchemaDescriptor(new UpdateServiceMethodDescriptorSupplier("receiveRoom"))
              .build();
        }
      }
    }
    return getReceiveRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.RoomEntry,
      generated.Message> getRemoveRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeRoom",
      requestType = generated.RoomEntry.class,
      responseType = generated.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.RoomEntry,
      generated.Message> getRemoveRoomMethod() {
    io.grpc.MethodDescriptor<generated.RoomEntry, generated.Message> getRemoveRoomMethod;
    if ((getRemoveRoomMethod = UpdateServiceGrpc.getRemoveRoomMethod) == null) {
      synchronized (UpdateServiceGrpc.class) {
        if ((getRemoveRoomMethod = UpdateServiceGrpc.getRemoveRoomMethod) == null) {
          UpdateServiceGrpc.getRemoveRoomMethod = getRemoveRoomMethod =
              io.grpc.MethodDescriptor.<generated.RoomEntry, generated.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomEntry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Message.getDefaultInstance()))
              .setSchemaDescriptor(new UpdateServiceMethodDescriptorSupplier("removeRoom"))
              .build();
        }
      }
    }
    return getRemoveRoomMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UpdateServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UpdateServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UpdateServiceStub>() {
        @java.lang.Override
        public UpdateServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UpdateServiceStub(channel, callOptions);
        }
      };
    return UpdateServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UpdateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UpdateServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UpdateServiceBlockingStub>() {
        @java.lang.Override
        public UpdateServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UpdateServiceBlockingStub(channel, callOptions);
        }
      };
    return UpdateServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UpdateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UpdateServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UpdateServiceFutureStub>() {
        @java.lang.Override
        public UpdateServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UpdateServiceFutureStub(channel, callOptions);
        }
      };
    return UpdateServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void receivePermissionRequest(generated.PermissionRequest request,
        io.grpc.stub.StreamObserver<generated.GrantMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceivePermissionRequestMethod(), responseObserver);
    }

    /**
     */
    default void receivePermissionReply(generated.PermissionResponse request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceivePermissionReplyMethod(), responseObserver);
    }

    /**
     */
    default void receiveRooms(generated.Rooms request,
        io.grpc.stub.StreamObserver<generated.Message> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveRoomsMethod(), responseObserver);
    }

    /**
     */
    default void receiveRoom(generated.RoomEntry request,
        io.grpc.stub.StreamObserver<generated.Message> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveRoomMethod(), responseObserver);
    }

    /**
     */
    default void removeRoom(generated.RoomEntry request,
        io.grpc.stub.StreamObserver<generated.Message> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveRoomMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UpdateService.
   */
  public static abstract class UpdateServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UpdateServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UpdateService.
   */
  public static final class UpdateServiceStub
      extends io.grpc.stub.AbstractAsyncStub<UpdateServiceStub> {
    private UpdateServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UpdateServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UpdateServiceStub(channel, callOptions);
    }

    /**
     */
    public void receivePermissionRequest(generated.PermissionRequest request,
        io.grpc.stub.StreamObserver<generated.GrantMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceivePermissionRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receivePermissionReply(generated.PermissionResponse request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceivePermissionReplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveRooms(generated.Rooms request,
        io.grpc.stub.StreamObserver<generated.Message> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveRoomsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveRoom(generated.RoomEntry request,
        io.grpc.stub.StreamObserver<generated.Message> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeRoom(generated.RoomEntry request,
        io.grpc.stub.StreamObserver<generated.Message> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveRoomMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UpdateService.
   */
  public static final class UpdateServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UpdateServiceBlockingStub> {
    private UpdateServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UpdateServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UpdateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.GrantMessage receivePermissionRequest(generated.PermissionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceivePermissionRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty receivePermissionReply(generated.PermissionResponse request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceivePermissionReplyMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Message receiveRooms(generated.Rooms request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveRoomsMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Message receiveRoom(generated.RoomEntry request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Message removeRoom(generated.RoomEntry request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveRoomMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UpdateService.
   */
  public static final class UpdateServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<UpdateServiceFutureStub> {
    private UpdateServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UpdateServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UpdateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.GrantMessage> receivePermissionRequest(
        generated.PermissionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceivePermissionRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> receivePermissionReply(
        generated.PermissionResponse request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceivePermissionReplyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Message> receiveRooms(
        generated.Rooms request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveRoomsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Message> receiveRoom(
        generated.RoomEntry request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Message> removeRoom(
        generated.RoomEntry request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveRoomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_PERMISSION_REQUEST = 0;
  private static final int METHODID_RECEIVE_PERMISSION_REPLY = 1;
  private static final int METHODID_RECEIVE_ROOMS = 2;
  private static final int METHODID_RECEIVE_ROOM = 3;
  private static final int METHODID_REMOVE_ROOM = 4;

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
        case METHODID_RECEIVE_PERMISSION_REQUEST:
          serviceImpl.receivePermissionRequest((generated.PermissionRequest) request,
              (io.grpc.stub.StreamObserver<generated.GrantMessage>) responseObserver);
          break;
        case METHODID_RECEIVE_PERMISSION_REPLY:
          serviceImpl.receivePermissionReply((generated.PermissionResponse) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_ROOMS:
          serviceImpl.receiveRooms((generated.Rooms) request,
              (io.grpc.stub.StreamObserver<generated.Message>) responseObserver);
          break;
        case METHODID_RECEIVE_ROOM:
          serviceImpl.receiveRoom((generated.RoomEntry) request,
              (io.grpc.stub.StreamObserver<generated.Message>) responseObserver);
          break;
        case METHODID_REMOVE_ROOM:
          serviceImpl.removeRoom((generated.RoomEntry) request,
              (io.grpc.stub.StreamObserver<generated.Message>) responseObserver);
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
          getReceivePermissionRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.PermissionRequest,
              generated.GrantMessage>(
                service, METHODID_RECEIVE_PERMISSION_REQUEST)))
        .addMethod(
          getReceivePermissionReplyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.PermissionResponse,
              generated.Empty>(
                service, METHODID_RECEIVE_PERMISSION_REPLY)))
        .addMethod(
          getReceiveRoomsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Rooms,
              generated.Message>(
                service, METHODID_RECEIVE_ROOMS)))
        .addMethod(
          getReceiveRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.RoomEntry,
              generated.Message>(
                service, METHODID_RECEIVE_ROOM)))
        .addMethod(
          getRemoveRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.RoomEntry,
              generated.Message>(
                service, METHODID_REMOVE_ROOM)))
        .build();
  }

  private static abstract class UpdateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UpdateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.ChatService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UpdateService");
    }
  }

  private static final class UpdateServiceFileDescriptorSupplier
      extends UpdateServiceBaseDescriptorSupplier {
    UpdateServiceFileDescriptorSupplier() {}
  }

  private static final class UpdateServiceMethodDescriptorSupplier
      extends UpdateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    UpdateServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (UpdateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UpdateServiceFileDescriptorSupplier())
              .addMethod(getReceivePermissionRequestMethod())
              .addMethod(getReceivePermissionReplyMethod())
              .addMethod(getReceiveRoomsMethod())
              .addMethod(getReceiveRoomMethod())
              .addMethod(getRemoveRoomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
