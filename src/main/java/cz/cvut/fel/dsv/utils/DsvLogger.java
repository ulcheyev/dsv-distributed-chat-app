package cz.cvut.fel.dsv.utils;

import cz.cvut.fel.dsv.core.service.DsvServerInterceptorImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;


public class DsvLogger {

    private static final DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm:ss:S");


    public static Logger getLogger(String layer, String ansiCol, Class claz){
        Logger log = Logger.getLogger(claz.getName());
        LogFormatter formatter = new LogFormatter(layer, ansiCol);
        ConsoleHandler handler = new ConsoleHandler();
        log.setUseParentHandlers(false);
        handler.setFormatter(formatter);
        log.addHandler(handler);
        return log;
    }


    private static class LogFormatter extends Formatter{

        public static final String ANSI_RESET = "\u001B[0m";

        private String ansiCol;
        private String layer;

        private LogFormatter(){}


        protected LogFormatter(String layer, String ansiCol) {
            super();
            this.ansiCol = ansiCol;
            this.layer = layer;
        }

        @Override
            public String format(LogRecord record) {
                StringBuilder builder = new StringBuilder(1000);
                String nodeIp = DsvServerInterceptorImpl.getClientIP() == null ? "" : " by " + DsvServerInterceptorImpl.getClientIP();
//                builder.append('\r');
                builder.append(ansiCol);
                builder.append(df.format(new Date(record.getMillis()))).append(" ");
                builder.append("[").append(record.getLevel()).append("] ");
                builder.append("[").append(layer).append("] ");
                builder.append("[").append(record.getSourceMethodName());
                builder.append(nodeIp).append("] ");
                builder.append(formatMessage(record));
                builder.append("\n");
                builder.append(ANSI_RESET);
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

