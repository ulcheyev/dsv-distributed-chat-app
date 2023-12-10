package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

import static cz.cvut.fel.dsv.core.Config.JOIN_HINT_STRING;

class JoinCommand implements Command {

    @Override
    public void handle(String arg, Node node) {
        if (!arg.isEmpty())
            node.joinRoomViaLeader(arg);
        else
            System.err.println(JOIN_HINT_STRING);
    }
}