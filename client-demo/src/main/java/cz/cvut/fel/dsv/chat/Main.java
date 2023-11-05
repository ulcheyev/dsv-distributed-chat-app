package cz.cvut.fel.dsv.chat;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Main {
    public static void main(String[] args) {
        /* *
         * Establish a connection to the server using the class ManagedChannelBuilder and the function usePlaintext().
         * The function usePlainText() should only be used for testing or for APIs where the use of such API or the data
         * exchanged is not sensitive.
         * */
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8999).usePlaintext().build();

        /* *
         * A blocking-style stub instance of Greeter service. We can have two types of stubs: blocking and async.
         * Blocking stubs are synchronous. Non-blocking stubs are asynchronous.
         * Take care if you want to call an RPC function on a blocking stub from UI thread
         * (cause an unresponsive/laggy UI).
         * */
//        proto.GreeterGrpc.GreeterBlockingStub bookStub = proto.GreeterGrpc.newBlockingStub(channel);
        proto.CalculatorGrpc.CalculatorBlockingStub calculatorStub = proto.CalculatorGrpc.newBlockingStub(channel);

        /* *
         * An asynchronous instance of the above declaration.
         * GreeterGrpc.GreeterStub bookStub = GreeterGrpc.newStub(channel);
         * */

        /* *
         * We can now use the gRPC function via our instance of GreeterBlockingStub bookStub.
         * Below we call the function sayHello(Helloworld.HelloRequest request) with name field value set to "gRPC".
         * This function returns a value of type  Helloworld.HelloReply that is saved in our instance reply.
         * We can get via generated functions every field from our message, in this example, we have just one field.
         * */
//        proto.Hello.HelloReply reply = bookStub.sayHello(proto.Hello.HelloRequest.newBuilder().setName("gRPC").build());
//        System.out.println(reply.getMessage());
        proto.CalculatorOuterClass.CalculateReply reply = calculatorStub.calculateInts(
                proto.CalculatorOuterClass.CalculateRequest.newBuilder()
                        .setFirstInt(5)
                        .setSecondInt(10)
                        .setOperation("-")
                        .build()
        );
        System.out.println(reply.getResult() + " " + reply.getMessage());
        channel.shutdown();
    }
}
