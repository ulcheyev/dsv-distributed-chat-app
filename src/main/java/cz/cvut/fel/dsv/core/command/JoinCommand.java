package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

import static cz.cvut.fel.dsv.core.infrastructure.Config.hintString;

class JoinCommand implements Command {

    @Override
    public void handle(Node node, String... arg) {
        if (arg.length > 1) {
            Integer delay = 0;
            try {
                delay = Integer.valueOf(arg[2]);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            node.joinRoomViaLeader(arg[1], delay);
        } else System.err.println(hintString("!join"));
    }
}
