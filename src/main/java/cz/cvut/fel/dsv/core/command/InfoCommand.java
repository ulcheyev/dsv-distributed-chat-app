package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

class InfoCommand implements Command {

    @Override
    public void handle(Node node, String ...arg) {
        System.out.println(node.toString());
    }
}
