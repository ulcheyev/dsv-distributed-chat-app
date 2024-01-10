package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: ChatService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ElectionServiceGrpc {

  private ElectionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "generated.ElectionService";

  // Static method descriptors that strictly reflect the proto.
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
    if ((getChangeNextNextMethod = ElectionServiceGrpc.getChangeNextNextMethod) == null) {
      synchronized (ElectionServiceGrpc.class) {
        if ((getChangeNextNextMethod = ElectionServiceGrpc.getChangeNextNextMethod) == null) {
          ElectionServiceGrpc.getChangeNextNextMethod = getChangeNextNextMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changeNextNext"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionServiceMethodDescriptorSupplier("changeNextNext"))
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
    if ((getChangePrevMethod = ElectionServiceGrpc.getChangePrevMethod) == null) {
      synchronized (ElectionServiceGrpc.class) {
        if ((getChangePrevMethod = ElectionServiceGrpc.getChangePrevMethod) == null) {
          ElectionServiceGrpc.getChangePrevMethod = getChangePrevMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Remote>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changePrev"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionServiceMethodDescriptorSupplier("changePrev"))
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
    if ((getElectionMethod = ElectionServiceGrpc.getElectionMethod) == null) {
      synchronized (ElectionServiceGrpc.class) {
        if ((getElectionMethod = ElectionServiceGrpc.getElectionMethod) == null) {
          ElectionServiceGrpc.getElectionMethod = getElectionMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "election"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionServiceMethodDescriptorSupplier("election"))
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
    if ((getElectedMethod = ElectionServiceGrpc.getElectedMethod) == null) {
      synchronized (ElectionServiceGrpc.class) {
        if ((getElectedMethod = ElectionServiceGrpc.getElectedMethod) == null) {
          ElectionServiceGrpc.getElectedMethod = getElectedMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "elected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionServiceMethodDescriptorSupplier("elected"))
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
    if ((getRepairTopologyMethod = ElectionServiceGrpc.getRepairTopologyMethod) == null) {
      synchronized (ElectionServiceGrpc.class) {
        if ((getRepairTopologyMethod = ElectionServiceGrpc.getRepairTopologyMethod) == null) {
          ElectionServiceGrpc.getRepairTopologyMethod = getRepairTopologyMethod =
              io.grpc.MethodDescriptor.<generated.Remote, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "repairTopology"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Remote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionServiceMethodDescriptorSupplier("repairTopology"))
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
    if ((getChangeNeighboursMethod = ElectionServiceGrpc.getChangeNeighboursMethod) == null) {
      synchronized (ElectionServiceGrpc.class) {
        if ((getChangeNeighboursMethod = ElectionServiceGrpc.getChangeNeighboursMethod) == null) {
          ElectionServiceGrpc.getChangeNeighboursMethod = getChangeNeighboursMethod =
              io.grpc.MethodDescriptor.<generated.JoinRequest, generated.Neighbours>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changeNeighbours"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.JoinRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Neighbours.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionServiceMethodDescriptorSupplier("changeNeighbours"))
              .build();
        }
      }
    }
    return getChangeNeighboursMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ElectionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ElectionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ElectionServiceStub>() {
        @java.lang.Override
        public ElectionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ElectionServiceStub(channel, callOptions);
        }
      };
    return ElectionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ElectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ElectionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ElectionServiceBlockingStub>() {
        @java.lang.Override
        public ElectionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ElectionServiceBlockingStub(channel, callOptions);
        }
      };
    return ElectionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ElectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ElectionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ElectionServiceFutureStub>() {
        @java.lang.Override
        public ElectionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ElectionServiceFutureStub(channel, callOptions);
        }
      };
    return ElectionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
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
  }

  /**
   * Base class for the server implementation of the service ElectionService.
   */
  public static abstract class ElectionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ElectionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ElectionService.
   */
  public static final class ElectionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ElectionServiceStub> {
    private ElectionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ElectionServiceStub(channel, callOptions);
    }

    /**
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
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ElectionService.
   */
  public static final class ElectionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ElectionServiceBlockingStub> {
    private ElectionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ElectionServiceBlockingStub(channel, callOptions);
    }

    /**
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
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ElectionService.
   */
  public static final class ElectionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ElectionServiceFutureStub> {
    private ElectionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ElectionServiceFutureStub(channel, callOptions);
    }

    /**
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
  }

  private static final int METHODID_CHANGE_NEXT_NEXT = 0;
  private static final int METHODID_CHANGE_PREV = 1;
  private static final int METHODID_ELECTION = 2;
  private static final int METHODID_ELECTED = 3;
  private static final int METHODID_REPAIR_TOPOLOGY = 4;
  private static final int METHODID_CHANGE_NEIGHBOURS = 5;

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
        .build();
  }

  private static abstract class ElectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ElectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.ChatService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ElectionService");
    }
  }

  private static final class ElectionServiceFileDescriptorSupplier
      extends ElectionServiceBaseDescriptorSupplier {
    ElectionServiceFileDescriptorSupplier() {}
  }

  private static final class ElectionServiceMethodDescriptorSupplier
      extends ElectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ElectionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ElectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ElectionServiceFileDescriptorSupplier())
              .addMethod(getChangeNextNextMethod())
              .addMethod(getChangePrevMethod())
              .addMethod(getElectionMethod())
              .addMethod(getElectedMethod())
              .addMethod(getRepairTopologyMethod())
              .addMethod(getChangeNeighboursMethod())
              .build();
        }
      }
    }
    return result;
  }
}
