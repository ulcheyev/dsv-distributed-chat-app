package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

import static cz.cvut.fel.dsv.core.infrastructure.Config.hintString;

class JoinCommand implements Command {

    @Override
    public void handle(String arg, Node node) {
        if (!arg.isEmpty())
            node.joinRoomViaLeader(arg);
        else
            System.err.println(hintString("!join"));
    }
}
