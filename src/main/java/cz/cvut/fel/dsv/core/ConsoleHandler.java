package cz.cvut.fel.dsv.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Objects;

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
        if(!commandline.isEmpty()) {
            if (commandline.charAt(0) == 'c' && commandline.charAt(1) == 'r') { // create room
                String roomName = commandline.substring(3);
                myNode.createRoom(roomName);
            } else if (commandline.charAt(0) == 'j') { // join room
                String roomName = commandline.substring(2);
                myNode.joinRoom(roomName);
            }else if(commandline.charAt(0) == 'e' && commandline.charAt(1) == 'x') { // exit room
                if(!Objects.equals(myNode.getCurrentRoomName(), "global")){ // if it is not already global room
                    myNode.joinRoom("global");
                }
            }
            else {
                myNode.sendMessageToRoom(myNode.getCurrentRoomName(), commandline);
            }
        }
    }


    @Override
    public void run() {
        String commandline = "";
        while (reading) {
            commandline = "";
            System.out.print("["+myNode.getCurrentRoomName()+"] "+myNode.getUsername()+"> ");
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

}