package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.utils.Utils;

public class DisconnectCommand implements Command {

    @Override
    public void handle(Node node, String ...arg) {
        System.out.println("Disconnecting...");
        node.executeExit();
        System.out.println("Disconnected... Bye " + node.getUsername() + "!");

        Utils.tryToSleep(1); // Without sleep node is exited before starting election -> error.
        System.exit(0);
    }
}
