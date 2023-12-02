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
     * <pre>
     * Chat methods
     * </pre>
     */
    default void receiveMessage(generated.Message request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveMessageMethod(), responseObserver);
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
     * <pre>
     * Chat methods
     * </pre>
     */
    public void receiveMessage(generated.Message request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request, responseObserver);
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
     * <pre>
     * Chat methods
     * </pre>
     */
    public generated.Empty receiveMessage(generated.Message request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveMessageMethod(), getCallOptions(), request);
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
     * <pre>
     * Chat methods
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> receiveMessage(
        generated.Message request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN_ROOM = 0;
  private static final int METHODID_EXIT_ROOM = 1;
  private static final int METHODID_PREFLIGHT = 2;
  private static final int METHODID_RECEIVE_ROOMS = 3;
  private static final int METHODID_RECEIVE_PERMISSION_REQUEST = 4;
  private static final int METHODID_RECEIVE_PERMISSION_RESPONSE = 5;
  private static final int METHODID_RECEIVE_MESSAGE = 6;

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
        case METHODID_RECEIVE_PERMISSION_REQUEST:
          serviceImpl.receivePermissionRequest((generated.PermissionRequest) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_PERMISSION_RESPONSE:
          serviceImpl.receivePermissionResponse((generated.PermissionResponse) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_MESSAGE:
          serviceImpl.receiveMessage((generated.Message) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
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
          getReceiveMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Message,
              generated.Empty>(
                service, METHODID_RECEIVE_MESSAGE)))
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
              .addMethod(getReceivePermissionRequestMethod())
              .addMethod(getReceivePermissionResponseMethod())
              .addMethod(getReceiveMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
