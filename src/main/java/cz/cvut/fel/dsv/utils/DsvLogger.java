package cz.cvut.fel.dsv.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class DsvLogger {

    private static final DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm:ss:S");

    public static Logger getLogger(Class claz){
        Logger log = Logger.getLogger(claz.getName());
        LogFormatter formatter = new LogFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        log.setUseParentHandlers(false);
        handler.setFormatter(formatter);
        log.addHandler(handler);
        return log;
    }


    private static class LogFormatter extends Formatter{
            @Override
            public String format(LogRecord record) {
                StringBuilder builder = new StringBuilder(1000);
                builder.append(df.format(new Date(record.getMillis()))).append(" ");
                builder.append("[").append(record.getLevel()).append("] ");
                builder.append("[").append(record.getSourceMethodName()).append("] ");
                builder.append(formatMessage(record));
                builder.append("\n");
                return builder.toString();
            }

            public String getHead(Handler h) {
                return super.getHead(h);
            }

            public String getTail(Handler h) {
                return super.getTail(h);
            }

    }
}

