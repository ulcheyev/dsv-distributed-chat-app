package cz.cvut.fel.dsv.core.command;

import cz.cvut.fel.dsv.core.Node;

import java.util.regex.Pattern;

import static cz.cvut.fel.dsv.core.infrastructure.Config.hintString;

public class StartElectionCommand implements Command {
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Override
    public void handle(Node node, String ...arg) {
        if (arg.length != 0 && isNumeric(arg[0]))
            node.startElectionWithDelay(Integer.parseInt(arg[0]));
        else
            System.out.println(hintString("!election"));
    }

    private boolean isNumeric(String arg) {
        if (arg == null) return false;
        return pattern.matcher(arg).matches();
    }
}
