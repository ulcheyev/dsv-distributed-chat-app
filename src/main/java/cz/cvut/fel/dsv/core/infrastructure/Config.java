package cz.cvut.fel.dsv.core.infrastructure;

public class Config {

    private Config() {
    }

    public static final String STUB_STRING = "stub";
    public static final String INITIAL_ROOM_NAME = "global";
    public static final Integer SLEEP_TIME_IN_CS_S = 3;

    public static final String ANSI_GREEN_NODE = "\u001B[32m";
    public static final String ANSI_PURPLE_SERVICE = "\u001B[35m";


    public static final String ANSI_RED_SERVICE = "\u001B[31m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_INTERCEPTOR = "\u001B[95m";

    public static final String HELP_STRING = """
            Supported commands:
                !join [room name]: enter a room named [room name];
                !online: show the list of online users in room;
                !rooms: show list of available rooms;
                !info:  show information about the current user;
                !election [seconds]: start election with [seconds] delay;
                !exit: disconnect from the chat.
            """;

    public static String hintString(String command) {
        return "To run " + command + " command you must specify arguments. " +
                "Type !help for more information.";
    }
}
