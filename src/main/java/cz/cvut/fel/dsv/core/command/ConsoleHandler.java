package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConsoleHandler implements Runnable {
    private final Node node = Node.getInstance();
    private boolean reading = true;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Map<String, Command> commandMap = new HashMap<>();

    public ConsoleHandler() {
        initMap();
    }

    private void handleCommand(String command, String arg) {
        Command listener = commandMap.get(command);
        if (listener != null)
            listener.handle(arg, this.node);
        else
            System.out.println("Command: " + command + " does not exist! Type !help for more information.");
    }

    private void parseCommandLine(String commandLine) {
        if (!commandLine.isEmpty()) {
            if (commandLine.charAt(0) == '!') {
                String[] split = commandLine.split("\\s+");
                if (split.length > 1)
                    handleCommand(split[0], split[1]);
                else
                    handleCommand(split[0], "");
            } else node.sendMessage(commandLine);
        }
    }

    private void initMap() {
        commandMap.put("!join", new JoinCommand());
        commandMap.put("!info", new InfoCommand());
        commandMap.put("!online", new OnlineCommand());
        commandMap.put("!help", new HelpCommand());
        commandMap.put("!rooms", new RoomsCommand());
        commandMap.put("!election", new StartElectionCommand());
        commandMap.put("!exit", new DisconnectCommand());
    }

    @Override
    public void run() {
        String commandline;
        while (reading) {
            commandline = "";
            System.out.print("[" + node.getCurrentRoom() + "] " + node.getUsername() + "> ");
            try {
                commandline = reader.readLine();
                parseCommandLine(commandline);
            } catch (IOException e) {
                System.err.println("ConsoleHandler - error while reading console input.\n" + e.getMessage());
                reading = false;
            }
        }
        System.out.println("Closing ConsoleHandler.");
    }
}
