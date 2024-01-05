package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

interface Command {
    void handle(Node node, String ... arg);
}
