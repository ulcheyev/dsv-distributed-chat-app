package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

import static cz.cvut.fel.dsv.core.infrastructure.Config.HELP_STRING;

class HelpCommand implements Command {

    @Override
    public void handle(String arg, Node node) {
        System.out.println(HELP_STRING);
    }
}
