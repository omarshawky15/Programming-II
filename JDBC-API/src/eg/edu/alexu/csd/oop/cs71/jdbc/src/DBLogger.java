package eg.edu.alexu.csd.oop.cs71.jdbc.src;

import java.io.IOException;
import java.util.logging.*;

public class DBLogger {
    FileHandler fh;
    SimpleFormatter sf;
    Logger logger;
    static DBLogger dbLogger;

    static {
        try {
            dbLogger = new DBLogger();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DBLogger() throws IOException {
        fh = new FileHandler("Log.txt", true);
        fh.setFormatter(new DBLogFormatter());
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setUseParentHandlers(false);
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);
    }

    public static DBLogger getInstance() {
        return dbLogger;
    }


    public void addLog(String level, String message) {
        switch (level.toLowerCase()) {
            case "config":
                logger.config(message);
                break;
            case "severe":
                logger.severe(message);
                break;
            case "warning":
                logger.warning(message);
                break;
            case "info":
                logger.info(message);
                break;
            case "fine":
                logger.fine(message);
                break;
            case "finer":
                logger.finer(message);
                break;
            default:
                logger.finest(message);
        }
    }

}