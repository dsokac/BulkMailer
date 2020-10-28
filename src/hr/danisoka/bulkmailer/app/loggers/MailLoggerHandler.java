package hr.danisoka.bulkmailer.app.loggers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public final class MailLoggerHandler{

    public static void setFileHandler(LoggerErrorListener listener) {
        Logger rootLogger = Logger.getLogger("");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String logDirectory = System.getProperty("log_directory");
        File dir = new File(logDirectory);
        if(dir != null) {
            dir.mkdirs();
        }
        String logFile = (logDirectory == null ? "" : logDirectory) + "/myapp_" + date + "_%u.log";
        FileHandler logHandler = null;
        try {
            logHandler = new FileHandler(logFile, 524288000, // 500 MB max size
                    1, // one log file at a time
                    true // if it exists: append, don't overwrite
            );
            Level defaultLevel = Level.INFO;
            logHandler.setFormatter(new XMLFormatter());
            logHandler.setLevel(java.util.logging.Level.FINE);
            for (Handler h : rootLogger.getHandlers()) {
                rootLogger.removeHandler(h);
            }
            rootLogger.setLevel(defaultLevel);
            rootLogger.addHandler(logHandler);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(MailLoggerHandler.class.getName()).log(Level.SEVERE, null, ex);
            listener.onErrorOccurred(ex, ex.getMessage());
        }
    }
    
    public interface LoggerErrorListener {
        void onErrorOccurred(Exception ex, String message);
    }
}