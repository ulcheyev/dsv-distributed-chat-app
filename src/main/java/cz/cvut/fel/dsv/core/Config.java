package cz.cvut.fel.dsv.core;

public class Config {

    private Config() {
    }

    public static final String STUB_STRING = "stub";
    public static final String INITIAL_ROOM_NAME = "global";
    public static final Integer SLEEP_TIME_IN_CS_S = 1;

    public static final String HELP_STRING = """
            Supported commands:
                !join [room name]: enter a room named [room name];
                !online: show the list of online users in room;
                !rooms: show list of available rooms;
                !info:  show information about the current user.
            """;
    public static final String JOIN_HINT_STRING = "To run !join command, you must specify the name " +
            "of the room you want to connect to. Type !help for more information.";
}
