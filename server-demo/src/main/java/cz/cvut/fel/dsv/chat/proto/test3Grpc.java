package proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: test.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class test3Grpc {

  private test3Grpc() {}

  public static final java.lang.String SERVICE_NAME = "test3";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<proto.Test.test,
      proto.Test.test2> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = proto.Test.test.class,
      responseType = proto.Test.test2.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.Test.test,
      proto.Test.test2> getSayHelloMethod() {
    io.grpc.MethodDescriptor<proto.Test.test, proto.Test.test2> getSayHelloMethod;
    if ((getSayHelloMethod = test3Grpc.getSayHelloMethod) == null) {
      synchronized (test3Grpc.class) {
        if ((getSayHelloMethod = test3Grpc.getSayHelloMethod) == null) {
          test3Grpc.getSayHelloMethod = getSayHelloMethod =
              io.grpc.MethodDescriptor.<proto.Test.test, proto.Test.test2>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Test.test.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Test.test2.getDefaultInstance()))
              .setSchemaDescriptor(new test3MethodDescriptorSupplier("SayHello"))
              .build();
        }
      }
    }
    return getSayHelloMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static test3Stub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<test3Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<test3Stub>() {
        @java.lang.Override
        public test3Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new test3Stub(channel, callOptions);
        }
      };
    return test3Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static test3BlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<test3BlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<test3BlockingStub>() {
        @java.lang.Override
        public test3BlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new test3BlockingStub(channel, callOptions);
        }
      };
    return test3BlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static test3FutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<test3FutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<test3FutureStub>() {
        @java.lang.Override
        public test3FutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new test3FutureStub(channel, callOptions);
        }
      };
    return test3FutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void sayHello(proto.Test.test request,
        io.grpc.stub.StreamObserver<proto.Test.test2> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service test3.
   */
  public static abstract class test3ImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return test3Grpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service test3.
   */
  public static final class test3Stub
      extends io.grpc.stub.AbstractAsyncStub<test3Stub> {
    private test3Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected test3Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new test3Stub(channel, callOptions);
    }

    /**
     */
    public void sayHello(proto.Test.test request,
        io.grpc.stub.StreamObserver<proto.Test.test2> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service test3.
   */
  public static final class test3BlockingStub
      extends io.grpc.stub.AbstractBlockingStub<test3BlockingStub> {
    private test3BlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected test3BlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new test3BlockingStub(channel, callOptions);
    }

    /**
     */
    public proto.Test.test2 sayHello(proto.Test.test request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service test3.
   */
  public static final class test3FutureStub
      extends io.grpc.stub.AbstractFutureStub<test3FutureStub> {
    private test3FutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected test3FutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new test3FutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.Test.test2> sayHello(
        proto.Test.test request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;

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
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((proto.Test.test) request,
              (io.grpc.stub.StreamObserver<proto.Test.test2>) responseObserver);
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
          getSayHelloMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              proto.Test.test,
              proto.Test.test2>(
                service, METHODID_SAY_HELLO)))
        .build();
  }

  private static abstract class test3BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    test3BaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return proto.Test.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("test3");
    }
  }

  private static final class test3FileDescriptorSupplier
      extends test3BaseDescriptorSupplier {
    test3FileDescriptorSupplier() {}
  }

  private static final class test3MethodDescriptorSupplier
      extends test3BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    test3MethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (test3Grpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new test3FileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .build();
        }
      }
    }
    return result;
  }
}
