package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.utils.Utils;

public class DisconnectCommand implements Command {

    @Override
    public void handle(Node node, String... arg) {
        System.out.println("Disconnecting...");
        node.exitRoom();
        System.out.println("Disconnected... Bye " + node.getUsername() + "!");

        Utils.tryToSleep(1);
        System.exit(0);
    }
}
