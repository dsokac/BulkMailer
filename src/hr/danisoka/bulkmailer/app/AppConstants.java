package hr.danisoka.bulkmailer.app;

import java.io.File;

public final class AppConstants {
    
    public interface AppSettings {
        String ROOT_FOLDER = "C:";//System.getProperty("user.home");
        String APP_FOLDER = ROOT_FOLDER + File.separator + "BulkMailer";
        String DATA_FOLDER = APP_FOLDER + File.separator + "data";
        String LOG_FOLDER = DATA_FOLDER + File.separator + "logs";
        String TEMPLATES_FOLDER = DATA_FOLDER + File.separator + "templates";
        String DB_FOLDER = DATA_FOLDER + File.separator + "db";
        String DB_FILE = DB_FOLDER + File.separator + "bulkMailer.db";
    }
    
}
