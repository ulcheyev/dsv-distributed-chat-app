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
  private static volatile io.grpc.MethodDescriptor<generated.ChatMessage,
      generated.ChatMessage> getChatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "chat",
      requestType = generated.ChatMessage.class,
      responseType = generated.ChatMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.ChatMessage,
      generated.ChatMessage> getChatMethod() {
    io.grpc.MethodDescriptor<generated.ChatMessage, generated.ChatMessage> getChatMethod;
    if ((getChatMethod = ChatServiceGrpc.getChatMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getChatMethod = ChatServiceGrpc.getChatMethod) == null) {
          ChatServiceGrpc.getChatMethod = getChatMethod =
              io.grpc.MethodDescriptor.<generated.ChatMessage, generated.ChatMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "chat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.ChatMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("chat"))
              .build();
        }
      }
    }
    return getChatMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.CandidateId,
      generated.Empty> getElectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "election",
      requestType = generated.CandidateId.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.CandidateId,
      generated.Empty> getElectionMethod() {
    io.grpc.MethodDescriptor<generated.CandidateId, generated.Empty> getElectionMethod;
    if ((getElectionMethod = ChatServiceGrpc.getElectionMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getElectionMethod = ChatServiceGrpc.getElectionMethod) == null) {
          ChatServiceGrpc.getElectionMethod = getElectionMethod =
              io.grpc.MethodDescriptor.<generated.CandidateId, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "election"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.CandidateId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("election"))
              .build();
        }
      }
    }
    return getElectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Candidate,
      generated.Empty> getElectedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "elected",
      requestType = generated.Candidate.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Candidate,
      generated.Empty> getElectedMethod() {
    io.grpc.MethodDescriptor<generated.Candidate, generated.Empty> getElectedMethod;
    if ((getElectedMethod = ChatServiceGrpc.getElectedMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getElectedMethod = ChatServiceGrpc.getElectedMethod) == null) {
          ChatServiceGrpc.getElectedMethod = getElectedMethod =
              io.grpc.MethodDescriptor.<generated.Candidate, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "elected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Candidate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("elected"))
              .build();
        }
      }
    }
    return getElectedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Address,
      generated.Empty> getChangeNextNextMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changeNextNext",
      requestType = generated.Address.class,
      responseType = generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Address,
      generated.Empty> getChangeNextNextMethod() {
    io.grpc.MethodDescriptor<generated.Address, generated.Empty> getChangeNextNextMethod;
    if ((getChangeNextNextMethod = ChatServiceGrpc.getChangeNextNextMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getChangeNextNextMethod = ChatServiceGrpc.getChangeNextNextMethod) == null) {
          ChatServiceGrpc.getChangeNextNextMethod = getChangeNextNextMethod =
              io.grpc.MethodDescriptor.<generated.Address, generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changeNextNext"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Address.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("changeNextNext"))
              .build();
        }
      }
    }
    return getChangeNextNextMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Address,
      generated.Address> getChangePrevMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changePrev",
      requestType = generated.Address.class,
      responseType = generated.Address.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Address,
      generated.Address> getChangePrevMethod() {
    io.grpc.MethodDescriptor<generated.Address, generated.Address> getChangePrevMethod;
    if ((getChangePrevMethod = ChatServiceGrpc.getChangePrevMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getChangePrevMethod = ChatServiceGrpc.getChangePrevMethod) == null) {
          ChatServiceGrpc.getChangePrevMethod = getChangePrevMethod =
              io.grpc.MethodDescriptor.<generated.Address, generated.Address>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changePrev"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Address.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Address.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("changePrev"))
              .build();
        }
      }
    }
    return getChangePrevMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Address,
      generated.Neighbour> getJoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "join",
      requestType = generated.Address.class,
      responseType = generated.Neighbour.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Address,
      generated.Neighbour> getJoinMethod() {
    io.grpc.MethodDescriptor<generated.Address, generated.Neighbour> getJoinMethod;
    if ((getJoinMethod = ChatServiceGrpc.getJoinMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getJoinMethod = ChatServiceGrpc.getJoinMethod) == null) {
          ChatServiceGrpc.getJoinMethod = getJoinMethod =
              io.grpc.MethodDescriptor.<generated.Address, generated.Neighbour>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "join"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Address.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Neighbour.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("join"))
              .build();
        }
      }
    }
    return getJoinMethod;
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
    default io.grpc.stub.StreamObserver<generated.ChatMessage> chat(
        io.grpc.stub.StreamObserver<generated.ChatMessage> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getChatMethod(), responseObserver);
    }

    /**
     */
    default void election(generated.CandidateId request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getElectionMethod(), responseObserver);
    }

    /**
     */
    default void elected(generated.Candidate request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getElectedMethod(), responseObserver);
    }

    /**
     */
    default void changeNextNext(generated.Address request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChangeNextNextMethod(), responseObserver);
    }

    /**
     */
    default void changePrev(generated.Address request,
        io.grpc.stub.StreamObserver<generated.Address> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChangePrevMethod(), responseObserver);
    }

    /**
     */
    default void join(generated.Address request,
        io.grpc.stub.StreamObserver<generated.Neighbour> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getJoinMethod(), responseObserver);
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
    public io.grpc.stub.StreamObserver<generated.ChatMessage> chat(
        io.grpc.stub.StreamObserver<generated.ChatMessage> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getChatMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void election(generated.CandidateId request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void elected(generated.Candidate request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeNextNext(generated.Address request,
        io.grpc.stub.StreamObserver<generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChangeNextNextMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changePrev(generated.Address request,
        io.grpc.stub.StreamObserver<generated.Address> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChangePrevMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void join(generated.Address request,
        io.grpc.stub.StreamObserver<generated.Neighbour> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request, responseObserver);
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
    public generated.Empty election(generated.CandidateId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getElectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty elected(generated.Candidate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getElectedMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Empty changeNextNext(generated.Address request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChangeNextNextMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Address changePrev(generated.Address request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChangePrevMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Neighbour join(generated.Address request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getJoinMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> election(
        generated.CandidateId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> elected(
        generated.Candidate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Empty> changeNextNext(
        generated.Address request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChangeNextNextMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Address> changePrev(
        generated.Address request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChangePrevMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Neighbour> join(
        generated.Address request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ELECTION = 0;
  private static final int METHODID_ELECTED = 1;
  private static final int METHODID_CHANGE_NEXT_NEXT = 2;
  private static final int METHODID_CHANGE_PREV = 3;
  private static final int METHODID_JOIN = 4;
  private static final int METHODID_CHAT = 5;

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
        case METHODID_ELECTION:
          serviceImpl.election((generated.CandidateId) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_ELECTED:
          serviceImpl.elected((generated.Candidate) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_CHANGE_NEXT_NEXT:
          serviceImpl.changeNextNext((generated.Address) request,
              (io.grpc.stub.StreamObserver<generated.Empty>) responseObserver);
          break;
        case METHODID_CHANGE_PREV:
          serviceImpl.changePrev((generated.Address) request,
              (io.grpc.stub.StreamObserver<generated.Address>) responseObserver);
          break;
        case METHODID_JOIN:
          serviceImpl.join((generated.Address) request,
              (io.grpc.stub.StreamObserver<generated.Neighbour>) responseObserver);
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
        case METHODID_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chat(
              (io.grpc.stub.StreamObserver<generated.ChatMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getChatMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              generated.ChatMessage,
              generated.ChatMessage>(
                service, METHODID_CHAT)))
        .addMethod(
          getElectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.CandidateId,
              generated.Empty>(
                service, METHODID_ELECTION)))
        .addMethod(
          getElectedMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Candidate,
              generated.Empty>(
                service, METHODID_ELECTED)))
        .addMethod(
          getChangeNextNextMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Address,
              generated.Empty>(
                service, METHODID_CHANGE_NEXT_NEXT)))
        .addMethod(
          getChangePrevMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Address,
              generated.Address>(
                service, METHODID_CHANGE_PREV)))
        .addMethod(
          getJoinMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.Address,
              generated.Neighbour>(
                service, METHODID_JOIN)))
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
              .addMethod(getChatMethod())
              .addMethod(getElectionMethod())
              .addMethod(getElectedMethod())
              .addMethod(getChangeNextNextMethod())
              .addMethod(getChangePrevMethod())
              .addMethod(getJoinMethod())
              .build();
        }
      }
    }
    return result;
  }
}
