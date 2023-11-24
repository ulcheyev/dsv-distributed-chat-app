package cz.cvut.fel.dsv.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

// todo  change impl
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


    private void parse_commandline(String commandline) {
        myNode.sendMessage(commandline);
    }


    @Override
    public void run() {
        String commandline = "";
        while (reading) {
            commandline = "";
            System.out.print("\n"+myNode.getUsername()+"> ");
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