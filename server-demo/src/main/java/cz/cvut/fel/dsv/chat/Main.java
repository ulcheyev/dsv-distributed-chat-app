package cz.cvut.fel.dsv.chat;

import cz.cvut.fel.dsv.chat.service.CalculatorImpl;
import cz.cvut.fel.dsv.chat.service.GreeterImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = ServerBuilder.forPort(8999).addService(new GreeterImpl()).addService(new CalculatorImpl()).build();

            server.start();
            System.out.println("Server started at " + server.getPort());
            server.awaitTermination();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }
}
