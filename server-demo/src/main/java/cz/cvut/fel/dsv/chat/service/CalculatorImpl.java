package cz.cvut.fel.dsv.chat.service;

import io.grpc.stub.StreamObserver;
import proto.CalculatorGrpc;
import proto.CalculatorOuterClass;

public class CalculatorImpl extends CalculatorGrpc.CalculatorImplBase {

    @Override
    public void calculateInts(CalculatorOuterClass.CalculateRequest request,
                              StreamObserver<CalculatorOuterClass.CalculateReply> responseObserver) {
        CalculatorOuterClass.CalculateReply reply;
        switch (request.getOperation()) {
            case "+":
                reply = CalculatorOuterClass.CalculateReply.newBuilder()
                        .setMessage("golova")
                        .setResult(request.getFirstInt() + request.getSecondInt())
                        .build();
                break;
            case "-":
                reply = CalculatorOuterClass.CalculateReply.newBuilder()
                        .setMessage("pisya")
                        .setResult(request.getFirstInt() - request.getSecondInt())
                        .build();
                break;
            default:
                reply = CalculatorOuterClass.CalculateReply.newBuilder()
                        .setMessage("popa")
                        .setResult(request.getFirstInt() * request.getSecondInt())
                        .build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
