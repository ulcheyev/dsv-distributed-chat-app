package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: ChatService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MyRemotesServiceGrpc {

  private MyRemotesServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "generated.MyRemotesService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.RemoteResponse> getAddRemoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addRemote",
      requestType = generated.Remote.class,
      responseType = generated.RemoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.RemoteResponse> getAddRemoteMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.RemoteResponse> getAddRemoteMethod;
    if ((getAddRemoteMethod = MyRemotesServiceGrpc.getAddRemoteMethod) == null) {
      synchronized (MyRemotesServiceGrpc.class) {
        if ((getAddRemoteMethod = MyRemotesServiceGrpc.getAddRemoteMethod) == null) {
          MyRemotesServiceGrpc.getAddRemoteMethod = getAddRemoteMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.RemoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addRemote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RemoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MyRemotesServiceMethodDescriptorSupplier("addRemote"))
              .build();
        }
      }
    }
    return getAddRemoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.RemoteResponse> getRemoveRemoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeRemote",
      requestType = generated.Remote.class,
      responseType = generated.RemoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.RemoteResponse> getRemoveRemoteMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.RemoteResponse> getRemoveRemoteMethod;
    if ((getRemoveRemoteMethod = MyRemotesServiceGrpc.getRemoveRemoteMethod) == null) {
      synchronized (MyRemotesServiceGrpc.class) {
        if ((getRemoveRemoteMethod = MyRemotesServiceGrpc.getRemoveRemoteMethod) == null) {
          MyRemotesServiceGrpc.getRemoveRemoteMethod = getRemoveRemoteMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.RemoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeRemote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RemoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MyRemotesServiceMethodDescriptorSupplier("removeRemote"))
              .build();
        }
      }
    }
    return getRemoveRemoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Remote,
      generated.RemoteResponse> getUpdateRemotesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateRemotes",
      requestType = generated.Remote.class,
      responseType = generated.RemoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.Remote,
      generated.RemoteResponse> getUpdateRemotesMethod() {
    io.grpc.MethodDescriptor<generated.Remote, generated.RemoteResponse> getUpdateRemotesMethod;
    if ((getUpdateRemotesMethod = MyRemotesServiceGrpc.getUpdateRemotesMethod) == null) {
      synchronized (MyRemotesServiceGrpc.class) {
        if ((getUpdateRemotesMethod = MyRemotesServiceGrpc.getUpdateRemotesMethod) == null) {
          MyRemotesServiceGrpc.getUpdateRemotesMethod = getUpdateRemotesMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.RemoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateRemotes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.RemoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MyRemotesServiceMethodDescriptorSupplier("updateRemotes"))
              .build();
        }
      }
    }
    return getUpdateRemotesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MyRemotesServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyRemotesServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyRemotesServiceStub>() {
        @java.lang.Override
        public MyRemotesServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyRemotesServiceStub(channel, callOptions);
        }
      };
    return MyRemotesServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MyRemotesServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyRemotesServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyRemotesServiceBlockingStub>() {
        @java.lang.Override
        public MyRemotesServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyRemotesServiceBlockingStub(channel, callOptions);
        }
      };
    return MyRemotesServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MyRemotesServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyRemotesServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyRemotesServiceFutureStub>() {
        @java.lang.Override
        public MyRemotesServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyRemotesServiceFutureStub(channel, callOptions);
        }
      };
    return MyRemotesServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void addRemote(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.RemoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddRemoteMethod(), responseObserver);
    }

    /**
     */
    default void removeRemote(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.RemoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveRemoteMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<generated.Remote> updateRemotes(
        io.grpc.stub.StreamObserver<generated.RemoteResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getUpdateRemotesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MyRemotesService.
   */
  public static abstract class MyRemotesServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MyRemotesServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MyRemotesService.
   */
  public static final class MyRemotesServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MyRemotesServiceStub> {
    private MyRemotesServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyRemotesServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyRemotesServiceStub(channel, callOptions);
    }

    /**
     */
    public void addRemote(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.RemoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddRemoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeRemote(generated.Remote request,
        io.grpc.stub.StreamObserver<generated.RemoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveRemoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.Remote> updateRemotes(
        io.grpc.stub.StreamObserver<generated.RemoteResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getUpdateRemotesMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MyRemotesService.
   */
  public static final class MyRemotesServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MyRemotesServiceBlockingStub> {
    private MyRemotesServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyRemotesServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyRemotesServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.RemoteResponse addRemote(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddRemoteMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.RemoteResponse removeRemote(generated.Remote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveRemoteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MyRemotesService.
   */
  public static final class MyRemotesServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MyRemotesServiceFutureStub> {
    private MyRemotesServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyRemotesServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyRemotesServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.RemoteResponse> addRemote(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddRemoteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.RemoteResponse> removeRemote(
        generated.Remote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveRemoteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_REMOTE = 0;
  private static final int METHODID_REMOVE_REMOTE = 1;
  private static final int METHODID_UPDATE_REMOTES = 2;

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
        case METHODID_ADD_REMOTE:
          serviceImpl.addRemote((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.RemoteResponse>) responseObserver);
          break;
        case METHODID_REMOVE_REMOTE:
          serviceImpl.removeRemote((generated.Remote) request,
              (io.grpc.stub.StreamObserver<generated.RemoteResponse>) responseObserver);
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
        case METHODID_UPDATE_REMOTES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.updateRemotes(
              (io.grpc.stub.StreamObserver<generated.RemoteResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getAddRemoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.RemoteResponse>(
                service, METHODID_ADD_REMOTE)))
        .addMethod(
          getRemoveRemoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Remote,
              generated.RemoteResponse>(
                service, METHODID_REMOVE_REMOTE)))
        .addMethod(
          getUpdateRemotesMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              generated.Remote,
              generated.RemoteResponse>(
                service, METHODID_UPDATE_REMOTES)))
        .build();
  }

  private static abstract class MyRemotesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MyRemotesServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.ChatServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MyRemotesService");
    }
  }

  private static final class MyRemotesServiceFileDescriptorSupplier
      extends MyRemotesServiceBaseDescriptorSupplier {
    MyRemotesServiceFileDescriptorSupplier() {}
  }

  private static final class MyRemotesServiceMethodDescriptorSupplier
      extends MyRemotesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MyRemotesServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (MyRemotesServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MyRemotesServiceFileDescriptorSupplier())
              .addMethod(getAddRemoteMethod())
              .addMethod(getRemoveRemoteMethod())
              .addMethod(getUpdateRemotesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
