package hr.danisoka.bulkmailer.app.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

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
    
    public static String getFileContent(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        return String.join("\n", lines);
    }
    
}
