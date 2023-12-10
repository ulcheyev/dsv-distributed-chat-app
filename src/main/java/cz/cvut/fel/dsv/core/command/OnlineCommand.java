package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

class OnlineCommand implements Command {

    @Override
    public void handle(String arg, Node node) {
        System.out.println(node.getNodeListInCurrentRoom());
    }
}
