package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: ChatService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RemoteServiceGrpc {

  private RemoteServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "generated.RemoteService";

  // Static method descriptors that strictly reflect the proto.
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
    if ((getBeatMethod = RemoteServiceGrpc.getBeatMethod) == null) {
      synchronized (RemoteServiceGrpc.class) {
        if ((getBeatMethod = RemoteServiceGrpc.getBeatMethod) == null) {
          RemoteServiceGrpc.getBeatMethod = getBeatMethod =
              io.grpc.MethodDescriptor.<generated.Empty, generated.Health>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "beat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Health.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteServiceMethodDescriptorSupplier("beat"))
              .build();
        }
      }
    }
    return getBeatMethod;
  }

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
    if ((getJoinRoomMethod = RemoteServiceGrpc.getJoinRoomMethod) == null) {
      synchronized (RemoteServiceGrpc.class) {
        if ((getJoinRoomMethod = RemoteServiceGrpc.getJoinRoomMethod) == null) {
          RemoteServiceGrpc.getJoinRoomMethod = getJoinRoomMethod =
              io.grpc.MethodDescriptor.<generated.JoinRequest, generated.JoinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "joinRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.JoinRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.JoinResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteServiceMethodDescriptorSupplier("joinRoom"))
              .build();
        }
      }
    }
    return getJoinRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.ChatMessage> getPreflightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "preflight",
      requestType = generated.Remote.class,
      responseType = generated.ChatMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.ChatMessage> getPreflightMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.ChatMessage> getPreflightMethod;
    if ((getPreflightMethod = RemoteServiceGrpc.getPreflightMethod) == null) {
      synchronized (RemoteServiceGrpc.class) {
        if ((getPreflightMethod = RemoteServiceGrpc.getPreflightMethod) == null) {
          RemoteServiceGrpc.getPreflightMethod = getPreflightMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.ChatMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "preflight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.ChatMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteServiceMethodDescriptorSupplier("preflight"))
              .build();
        }
      }
    }
    return getPreflightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.ChatMessage,
      generated.Empty> getReceiveMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveMessage",
      requestType = generated.ChatMessage.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.ChatMessage,
      generated.Empty> getReceiveMessageMethod() {
    io.grpc.MethodDescriptor<generated.ChatMessage, generated.Empty> getReceiveMessageMethod;
    if ((getReceiveMessageMethod = RemoteServiceGrpc.getReceiveMessageMethod) == null) {
      synchronized (RemoteServiceGrpc.class) {
        if ((getReceiveMessageMethod = RemoteServiceGrpc.getReceiveMessageMethod) == null) {
          RemoteServiceGrpc.getReceiveMessageMethod = getReceiveMessageMethod =
              io.grpc.MethodDescriptor.<generated.ChatMessage, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteServiceMethodDescriptorSupplier("receiveMessage"))
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
    if ((getReceiveGetRoomListRequestMethod = RemoteServiceGrpc.getReceiveGetRoomListRequestMethod) == null) {
      synchronized (RemoteServiceGrpc.class) {
        if ((getReceiveGetRoomListRequestMethod = RemoteServiceGrpc.getReceiveGetRoomListRequestMethod) == null) {
          RemoteServiceGrpc.getReceiveGetRoomListRequestMethod = getReceiveGetRoomListRequestMethod =
              io.grpc.MethodDescriptor.<generated.Empty, generated.StringPayload>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveGetRoomListRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.StringPayload.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteServiceMethodDescriptorSupplier("receiveGetRoomListRequest"))
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
    if ((getReceiveGetNodeListInCurrentRoomRequestMethod = RemoteServiceGrpc.getReceiveGetNodeListInCurrentRoomRequestMethod) == null) {
      synchronized (RemoteServiceGrpc.class) {
        if ((getReceiveGetNodeListInCurrentRoomRequestMethod = RemoteServiceGrpc.getReceiveGetNodeListInCurrentRoomRequestMethod) == null) {
          RemoteServiceGrpc.getReceiveGetNodeListInCurrentRoomRequestMethod = getReceiveGetNodeListInCurrentRoomRequestMethod =
              io.grpc.MethodDescriptor.<generated.Empty, generated.StringPayload>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveGetNodeListInCurrentRoomRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.StringPayload.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteServiceMethodDescriptorSupplier("receiveGetNodeListInCurrentRoomRequest"))
              .build();
        }
      }
    }
    return getReceiveGetNodeListInCurrentRoomRequestMethod;
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
    if ((getExitRoomMethod = RemoteServiceGrpc.getExitRoomMethod) == null) {
      synchronized (RemoteServiceGrpc.class) {
        if ((getExitRoomMethod = RemoteServiceGrpc.getExitRoomMethod) == null) {
          RemoteServiceGrpc.getExitRoomMethod = getExitRoomMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "exitRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteServiceMethodDescriptorSupplier("exitRoom"))
              .build();
        }
      }
    }
    return getExitRoomMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RemoteServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteServiceStub>() {
        @java.lang.Override
        public RemoteServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteServiceStub(channel, callOptions);
        }
      };
    return RemoteServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RemoteServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteServiceBlockingStub>() {
        @java.lang.Override
        public RemoteServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteServiceBlockingStub(channel, callOptions);
        }
      };
    return RemoteServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RemoteServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteServiceFutureStub>() {
        @java.lang.Override
        public RemoteServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteServiceFutureStub(channel, callOptions);
        }
      };
    return RemoteServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void beat(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.Health> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBeatMethod(), responseObserver);
    }

    /**
     */
    default void joinRoom(generated.JoinRequest request,
        io.grpc.stub.StreamObserver<generated.JoinResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getJoinRoomMethod(), responseObserver);
    }

    /**
     */
    default void preflight(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.ChatMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPreflightMethod(), responseObserver);
    }

    /**
     */
    default void receiveMessage(generated.ChatMessage request,
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
     */
    default void exitRoom(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExitRoomMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RemoteService.
   */
  public static abstract class RemoteServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RemoteServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RemoteService.
   */
  public static final class RemoteServiceStub
      extends io.grpc.stub.AbstractAsyncStub<RemoteServiceStub> {
    private RemoteServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteServiceStub(channel, callOptions);
    }

    /**
     */
    public void beat(generated.Empty request,
        io.grpc.stub.StreamObserver<generated.Health> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBeatMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinRoom(generated.JoinRequest request,
        io.grpc.stub.StreamObserver<generated.JoinResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getJoinRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void preflight(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.ChatMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getPreflightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveMessage(generated.ChatMessage request,
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
     */
    public void exitRoom(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExitRoomMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RemoteService.
   */
  public static final class RemoteServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RemoteServiceBlockingStub> {
    private RemoteServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.Health beat(generated.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBeatMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.JoinResponse joinRoom(generated.JoinRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getJoinRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<generated.ChatMessage> preflight(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getPreflightMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty receiveMessage(generated.ChatMessage request) {
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
     */
    public generated.Empty exitRoom(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExitRoomMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RemoteService.
   */
  public static final class RemoteServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<RemoteServiceFutureStub> {
    private RemoteServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Health> beat(
        generated.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBeatMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.JoinResponse> joinRoom(
        generated.JoinRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getJoinRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> receiveMessage(
        generated.ChatMessage request) {
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
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> exitRoom(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExitRoomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BEAT = 0;
  private static final int METHODID_JOIN_ROOM = 1;
  private static final int METHODID_PREFLIGHT = 2;
  private static final int METHODID_RECEIVE_MESSAGE = 3;
  private static final int METHODID_RECEIVE_GET_ROOM_LIST_REQUEST = 4;
  private static final int METHODID_RECEIVE_GET_NODE_LIST_IN_CURRENT_ROOM_REQUEST = 5;
  private static final int METHODID_EXIT_ROOM = 6;

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
        case METHODID_BEAT:
          serviceImpl.beat((generated.Empty) request,
              (io.grpc.stub.StreamObserver<generated.Health>) responseObserver);
          break;
        case METHODID_JOIN_ROOM:
          serviceImpl.joinRoom((generated.JoinRequest) request,
              (io.grpc.stub.StreamObserver<generated.JoinResponse>) responseObserver);
          break;
        case METHODID_PREFLIGHT:
          serviceImpl.preflight((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.ChatMessage>) responseObserver);
          break;
        case METHODID_RECEIVE_MESSAGE:
          serviceImpl.receiveMessage((generated.ChatMessage) request,
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
        case METHODID_EXIT_ROOM:
          serviceImpl.exitRoom((generated.Remote) request,
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
          getBeatMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Empty,
              generated.Health>(
                service, METHODID_BEAT)))
        .addMethod(
          getJoinRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.JoinRequest,
              generated.JoinResponse>(
                service, METHODID_JOIN_ROOM)))
        .addMethod(
          getPreflightMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              generated.Remote,
              generated.ChatMessage>(
                service, METHODID_PREFLIGHT)))
        .addMethod(
          getReceiveMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.ChatMessage,
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
          getExitRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.Empty>(
                service, METHODID_EXIT_ROOM)))
        .build();
  }

  private static abstract class RemoteServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RemoteServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.ChatService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RemoteService");
    }
  }

  private static final class RemoteServiceFileDescriptorSupplier
      extends RemoteServiceBaseDescriptorSupplier {
    RemoteServiceFileDescriptorSupplier() {}
  }

  private static final class RemoteServiceMethodDescriptorSupplier
      extends RemoteServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    RemoteServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (RemoteServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RemoteServiceFileDescriptorSupplier())
              .addMethod(getBeatMethod())
              .addMethod(getJoinRoomMethod())
              .addMethod(getPreflightMethod())
              .addMethod(getReceiveMessageMethod())
              .addMethod(getReceiveGetRoomListRequestMethod())
              .addMethod(getReceiveGetNodeListInCurrentRoomRequestMethod())
              .addMethod(getExitRoomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
