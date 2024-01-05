package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

import static cz.cvut.fel.dsv.core.infrastructure.Config.HELP_STRING;

class HelpCommand implements Command {

    @Override
    public void handle(Node node, String ... arg) {
        System.out.println(HELP_STRING);
    }
}
