package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: ChatService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ChatServiceGrpc {

  private ChatServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "generated.ChatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.RoomRequestMessage,
      generated.RoomResponseMessage> getCreateRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createRoom",
      requestType = generated.RoomRequestMessage.class,
      responseType = generated.RoomResponseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.RoomRequestMessage,
      generated.RoomResponseMessage> getCreateRoomMethod() {
    io.grpc.MethodDescriptor<generated.RoomRequestMessage, generated.RoomResponseMessage> getCreateRoomMethod;
    if ((getCreateRoomMethod = ChatServiceGrpc.getCreateRoomMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getCreateRoomMethod = ChatServiceGrpc.getCreateRoomMethod) == null) {
          ChatServiceGrpc.getCreateRoomMethod = getCreateRoomMethod =
              io.grpc.MethodDescriptor.<generated.RoomRequestMessage, generated.RoomResponseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomResponseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("createRoom"))
              .build();
        }
      }
    }
    return getCreateRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.RoomRequestMessage,
      generated.RoomChatMessage> getJoinToRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinToRoom",
      requestType = generated.RoomRequestMessage.class,
      responseType = generated.RoomChatMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.RoomRequestMessage,
      generated.RoomChatMessage> getJoinToRoomMethod() {
    io.grpc.MethodDescriptor<generated.RoomRequestMessage, generated.RoomChatMessage> getJoinToRoomMethod;
    if ((getJoinToRoomMethod = ChatServiceGrpc.getJoinToRoomMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getJoinToRoomMethod = ChatServiceGrpc.getJoinToRoomMethod) == null) {
          ChatServiceGrpc.getJoinToRoomMethod = getJoinToRoomMethod =
              io.grpc.MethodDescriptor.<generated.RoomRequestMessage, generated.RoomChatMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "joinToRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomChatMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("joinToRoom"))
              .build();
        }
      }
    }
    return getJoinToRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.RoomRequestMessage,
      generated.RoomResponseMessage> getExitRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "exitRoom",
      requestType = generated.RoomRequestMessage.class,
      responseType = generated.RoomResponseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.RoomRequestMessage,
      generated.RoomResponseMessage> getExitRoomMethod() {
    io.grpc.MethodDescriptor<generated.RoomRequestMessage, generated.RoomResponseMessage> getExitRoomMethod;
    if ((getExitRoomMethod = ChatServiceGrpc.getExitRoomMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getExitRoomMethod = ChatServiceGrpc.getExitRoomMethod) == null) {
          ChatServiceGrpc.getExitRoomMethod = getExitRoomMethod =
              io.grpc.MethodDescriptor.<generated.RoomRequestMessage, generated.RoomResponseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "exitRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomResponseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("exitRoom"))
              .build();
        }
      }
    }
    return getExitRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.RoomChatMessage,
      generated.RoomResponseMessage> getSendMessageToRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessageToRoom",
      requestType = generated.RoomChatMessage.class,
      responseType = generated.RoomResponseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.RoomChatMessage,
      generated.RoomResponseMessage> getSendMessageToRoomMethod() {
    io.grpc.MethodDescriptor<generated.RoomChatMessage, generated.RoomResponseMessage> getSendMessageToRoomMethod;
    if ((getSendMessageToRoomMethod = ChatServiceGrpc.getSendMessageToRoomMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSendMessageToRoomMethod = ChatServiceGrpc.getSendMessageToRoomMethod) == null) {
          ChatServiceGrpc.getSendMessageToRoomMethod = getSendMessageToRoomMethod =
              io.grpc.MethodDescriptor.<generated.RoomChatMessage, generated.RoomResponseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendMessageToRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RoomResponseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("sendMessageToRoom"))
              .build();
        }
      }
    }
    return getSendMessageToRoomMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServiceStub>() {
        @java.lang.Override
        public ChatServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServiceStub(channel, callOptions);
        }
      };
    return ChatServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServiceBlockingStub>() {
        @java.lang.Override
        public ChatServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServiceBlockingStub(channel, callOptions);
        }
      };
    return ChatServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServiceFutureStub>() {
        @java.lang.Override
        public ChatServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServiceFutureStub(channel, callOptions);
        }
      };
    return ChatServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createRoom(generated.RoomRequestMessage request,
        io.grpc.stub.StreamObserver<generated.RoomResponseMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateRoomMethod(), responseObserver);
    }

    /**
     */
    default void joinToRoom(generated.RoomRequestMessage request,
        io.grpc.stub.StreamObserver<generated.RoomChatMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getJoinToRoomMethod(), responseObserver);
    }

    /**
     */
    default void exitRoom(generated.RoomRequestMessage request,
        io.grpc.stub.StreamObserver<generated.RoomResponseMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExitRoomMethod(), responseObserver);
    }

    /**
     */
    default void sendMessageToRoom(generated.RoomChatMessage request,
        io.grpc.stub.StreamObserver<generated.RoomResponseMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendMessageToRoomMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ChatService.
   */
  public static abstract class ChatServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ChatServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ChatService.
   */
  public static final class ChatServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ChatServiceStub> {
    private ChatServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    /**
     */
    public void createRoom(generated.RoomRequestMessage request,
        io.grpc.stub.StreamObserver<generated.RoomResponseMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinToRoom(generated.RoomRequestMessage request,
        io.grpc.stub.StreamObserver<generated.RoomChatMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getJoinToRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exitRoom(generated.RoomRequestMessage request,
        io.grpc.stub.StreamObserver<generated.RoomResponseMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExitRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessageToRoom(generated.RoomChatMessage request,
        io.grpc.stub.StreamObserver<generated.RoomResponseMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendMessageToRoomMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ChatService.
   */
  public static final class ChatServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ChatServiceBlockingStub> {
    private ChatServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.RoomResponseMessage createRoom(generated.RoomRequestMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<generated.RoomChatMessage> joinToRoom(
        generated.RoomRequestMessage request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getJoinToRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.RoomResponseMessage exitRoom(generated.RoomRequestMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExitRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.RoomResponseMessage sendMessageToRoom(generated.RoomChatMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendMessageToRoomMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ChatService.
   */
  public static final class ChatServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ChatServiceFutureStub> {
    private ChatServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.RoomResponseMessage> createRoom(
        generated.RoomRequestMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.RoomResponseMessage> exitRoom(
        generated.RoomRequestMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExitRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.RoomResponseMessage> sendMessageToRoom(
        generated.RoomChatMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendMessageToRoomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ROOM = 0;
  private static final int METHODID_JOIN_TO_ROOM = 1;
  private static final int METHODID_EXIT_ROOM = 2;
  private static final int METHODID_SEND_MESSAGE_TO_ROOM = 3;

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
        case METHODID_CREATE_ROOM:
          serviceImpl.createRoom((generated.RoomRequestMessage) request,
              (io.grpc.stub.StreamObserver<generated.RoomResponseMessage>) responseObserver);
          break;
        case METHODID_JOIN_TO_ROOM:
          serviceImpl.joinToRoom((generated.RoomRequestMessage) request,
              (io.grpc.stub.StreamObserver<generated.RoomChatMessage>) responseObserver);
          break;
        case METHODID_EXIT_ROOM:
          serviceImpl.exitRoom((generated.RoomRequestMessage) request,
              (io.grpc.stub.StreamObserver<generated.RoomResponseMessage>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE_TO_ROOM:
          serviceImpl.sendMessageToRoom((generated.RoomChatMessage) request,
              (io.grpc.stub.StreamObserver<generated.RoomResponseMessage>) responseObserver);
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
          getCreateRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.RoomRequestMessage,
              generated.RoomResponseMessage>(
                service, METHODID_CREATE_ROOM)))
        .addMethod(
          getJoinToRoomMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              generated.RoomRequestMessage,
              generated.RoomChatMessage>(
                service, METHODID_JOIN_TO_ROOM)))
        .addMethod(
          getExitRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.RoomRequestMessage,
              generated.RoomResponseMessage>(
                service, METHODID_EXIT_ROOM)))
        .addMethod(
          getSendMessageToRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.RoomChatMessage,
              generated.RoomResponseMessage>(
                service, METHODID_SEND_MESSAGE_TO_ROOM)))
        .build();
  }

  private static abstract class ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.ChatServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatService");
    }
  }

  private static final class ChatServiceFileDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier {
    ChatServiceFileDescriptorSupplier() {}
  }

  private static final class ChatServiceMethodDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ChatServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ChatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatServiceFileDescriptorSupplier())
              .addMethod(getCreateRoomMethod())
              .addMethod(getJoinToRoomMethod())
              .addMethod(getExitRoomMethod())
              .addMethod(getSendMessageToRoomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
