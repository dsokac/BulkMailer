package hr.danisoka.bulkmailer.app.utils;

import java.io.File;

public final class FileUtils {
    
    public static File getDirectory(String dirPath) {
        File directory = new File(dirPath);
        if(directory != null) {
            directory.mkdirs();
        }
        return directory;
    }
    
    public static File getFile(String path) {
        return new File(path);
    }    
}
