package cz.cvut.fel.dsv.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Objects;

public class ConsoleHandler implements Runnable{

    private boolean reading = true;
    private final BufferedReader reader;
    private final PrintStream out = System.out;
    private final PrintStream err = System.err;
    private final NodeImpl myNode;



    public ConsoleHandler(NodeImpl myNode) {
        this.myNode = myNode;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void parseCommandLine(String commandline) {
        if(!commandline.isEmpty()) {
             if (commandline.charAt(0) == 'j') { // join room
                String roomName = commandline.substring(2);
                myNode.joinRoomViaLeader(roomName);
             }
             else if (Objects.equals(commandline, "info")){
                 System.out.println(myNode.toString());
             }
//             else if(commandline.charAt(0) == 'e' && commandline.charAt(1) == 'x') { // exit room
//
//             }
            else {
                myNode.sendMessage(commandline);
            }
        }
    }

    @Override
    public void run() {
        String commandline = "";
        while (reading) {
            commandline = "";
            System.out.print("["+myNode.getCurrentRoom()+"] "+myNode.getUsername()+"> ");
            try {
                commandline = reader.readLine();
                parseCommandLine(commandline);
            } catch (IOException e) {
                err.println("ConsoleHandler - error while reading console input.\n" + e.getMessage());
                reading = false;
            }
        }
        System.out.println("Closing ConsoleHandler.");
    }

}
