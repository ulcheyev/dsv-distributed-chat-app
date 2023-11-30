package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: ChatService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ChatPermissionServiceGrpc {

  private ChatPermissionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "generated.ChatPermissionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.PermissionRequest,
      generated.PermissionResponse> getReceiveRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveRequest",
      requestType = generated.PermissionRequest.class,
      responseType = generated.PermissionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.PermissionRequest,
      generated.PermissionResponse> getReceiveRequestMethod() {
    io.grpc.MethodDescriptor<generated.PermissionRequest, generated.PermissionResponse> getReceiveRequestMethod;
    if ((getReceiveRequestMethod = ChatPermissionServiceGrpc.getReceiveRequestMethod) == null) {
      synchronized (ChatPermissionServiceGrpc.class) {
        if ((getReceiveRequestMethod = ChatPermissionServiceGrpc.getReceiveRequestMethod) == null) {
          ChatPermissionServiceGrpc.getReceiveRequestMethod = getReceiveRequestMethod =
              io.grpc.MethodDescriptor.<generated.PermissionRequest, generated.PermissionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.PermissionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.PermissionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChatPermissionServiceMethodDescriptorSupplier("receiveRequest"))
              .build();
        }
      }
    }
    return getReceiveRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.PermissionResponse,
      generated.PermissionResponse> getReceiveResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveResponse",
      requestType = generated.PermissionResponse.class,
      responseType = generated.PermissionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.PermissionResponse,
      generated.PermissionResponse> getReceiveResponseMethod() {
    io.grpc.MethodDescriptor<generated.PermissionResponse, generated.PermissionResponse> getReceiveResponseMethod;
    if ((getReceiveResponseMethod = ChatPermissionServiceGrpc.getReceiveResponseMethod) == null) {
      synchronized (ChatPermissionServiceGrpc.class) {
        if ((getReceiveResponseMethod = ChatPermissionServiceGrpc.getReceiveResponseMethod) == null) {
          ChatPermissionServiceGrpc.getReceiveResponseMethod = getReceiveResponseMethod =
              io.grpc.MethodDescriptor.<generated.PermissionResponse, generated.PermissionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.PermissionResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.PermissionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChatPermissionServiceMethodDescriptorSupplier("receiveResponse"))
              .build();
        }
      }
    }
    return getReceiveResponseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatPermissionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatPermissionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatPermissionServiceStub>() {
        @java.lang.Override
        public ChatPermissionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatPermissionServiceStub(channel, callOptions);
        }
      };
    return ChatPermissionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatPermissionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatPermissionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatPermissionServiceBlockingStub>() {
        @java.lang.Override
        public ChatPermissionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatPermissionServiceBlockingStub(channel, callOptions);
        }
      };
    return ChatPermissionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatPermissionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatPermissionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatPermissionServiceFutureStub>() {
        @java.lang.Override
        public ChatPermissionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatPermissionServiceFutureStub(channel, callOptions);
        }
      };
    return ChatPermissionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void receiveRequest(generated.PermissionRequest request,
        io.grpc.stub.StreamObserver<generated.PermissionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveRequestMethod(), responseObserver);
    }

    /**
     */
    default void receiveResponse(generated.PermissionResponse request,
        io.grpc.stub.StreamObserver<generated.PermissionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveResponseMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ChatPermissionService.
   */
  public static abstract class ChatPermissionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ChatPermissionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ChatPermissionService.
   */
  public static final class ChatPermissionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ChatPermissionServiceStub> {
    private ChatPermissionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatPermissionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatPermissionServiceStub(channel, callOptions);
    }

    /**
     */
    public void receiveRequest(generated.PermissionRequest request,
        io.grpc.stub.StreamObserver<generated.PermissionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveResponse(generated.PermissionResponse request,
        io.grpc.stub.StreamObserver<generated.PermissionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveResponseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ChatPermissionService.
   */
  public static final class ChatPermissionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ChatPermissionServiceBlockingStub> {
    private ChatPermissionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatPermissionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatPermissionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.PermissionResponse receiveRequest(generated.PermissionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.PermissionResponse receiveResponse(generated.PermissionResponse request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveResponseMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ChatPermissionService.
   */
  public static final class ChatPermissionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ChatPermissionServiceFutureStub> {
    private ChatPermissionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatPermissionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatPermissionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.PermissionResponse> receiveRequest(
        generated.PermissionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.PermissionResponse> receiveResponse(
        generated.PermissionResponse request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveResponseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_REQUEST = 0;
  private static final int METHODID_RECEIVE_RESPONSE = 1;

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
        case METHODID_RECEIVE_REQUEST:
          serviceImpl.receiveRequest((generated.PermissionRequest) request,
              (io.grpc.stub.StreamObserver<generated.PermissionResponse>) responseObserver);
          break;
        case METHODID_RECEIVE_RESPONSE:
          serviceImpl.receiveResponse((generated.PermissionResponse) request,
              (io.grpc.stub.StreamObserver<generated.PermissionResponse>) responseObserver);
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
          getReceiveRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.PermissionRequest,
              generated.PermissionResponse>(
                service, METHODID_RECEIVE_REQUEST)))
        .addMethod(
          getReceiveResponseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.PermissionResponse,
              generated.PermissionResponse>(
                service, METHODID_RECEIVE_RESPONSE)))
        .build();
  }

  private static abstract class ChatPermissionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatPermissionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.ChatServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatPermissionService");
    }
  }

  private static final class ChatPermissionServiceFileDescriptorSupplier
      extends ChatPermissionServiceBaseDescriptorSupplier {
    ChatPermissionServiceFileDescriptorSupplier() {}
  }

  private static final class ChatPermissionServiceMethodDescriptorSupplier
      extends ChatPermissionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ChatPermissionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ChatPermissionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatPermissionServiceFileDescriptorSupplier())
              .addMethod(getReceiveRequestMethod())
              .addMethod(getReceiveResponseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
