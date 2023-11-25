package cz.cvut.fel.dsv.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

// Todo  change impl
public class ConsoleHandler implements Runnable {

    private boolean reading = true;
    private BufferedReader reader = null;
    private PrintStream out = System.out;
    private PrintStream err = System.err;
    private Node myNode;


    public ConsoleHandler(Node myNode) {
        this.myNode = myNode;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }



    // todo implement some logic based on input
    private void parse_commandline(String commandline) {
        if(commandline.charAt(0) == 'c' && commandline.charAt(1) == 'r') {
            String roomName = commandline.substring(3);
            myNode.createRoom(roomName);

        }
        else if(commandline.charAt(0) == 'j') {
            String roomName = commandline.substring(2);
            new Thread( () -> myNode.joinRoom(roomName)).start();
        } else if(commandline.charAt(0) == 'r'){
            String[] tokens = commandline.split(" ");
            String roomName = tokens[1];
            String msg = tokens[2];
            new Thread( () -> myNode.sendMessageToRoom(roomName, msg)).start();
        }
        else {
            new Thread( () -> myNode.sendMessage(commandline)).start();
        }
    }


    @Override
    public void run() {
        String commandline = "";
        while (reading) {
            commandline = "";
            System.out.print(myNode.getUsername()+"> ");
            try {
                commandline = reader.readLine();
                parse_commandline(commandline);
            } catch (IOException e) {
                err.println("ConsoleHandler - error in rading console input.");
                e.printStackTrace();
                reading = false;
            }
        }
        System.out.println("Closing ConsoleHandler.");
    }

    public static void deleteCurrentLine() {
    }
}