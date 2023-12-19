package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

import java.util.regex.Pattern;

import static cz.cvut.fel.dsv.core.infrastructure.Config.hintString;

public class StartElectionCommand implements Command {
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Override
    public void handle(String arg, Node node) {
        if (!arg.isEmpty() && isNumeric(arg))
            node.startElectionWithDelay(Integer.parseInt(arg));
        else
            System.out.println(hintString("!election"));
    }

    private boolean isNumeric(String arg) {
        if (arg == null) return false;
        return pattern.matcher(arg).matches();
    }
}
