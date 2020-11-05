package hr.danisoka.bulkmailer.app.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
    
    public static File storeFile(File file, File dir, String newName) throws Exception, IOException {
        String name = file.getName();
        if(newName != null) {
            int indexOfDot = name.lastIndexOf(".");
            String ext = name.substring(indexOfDot);
            name = String.format("%s%s", newName, ext);
        } 
        
        File newFile = new File(dir, name);
        if(newFile.exists()) {
            throw new Exception(String.format("Datoteka s imenom '%s' već postoji!", name));
        } else {
           Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return newFile;
    }
    
    public static boolean isNameEqual(File file, String newName) {
        String name = file.getName();
        int indexOfDot = name.lastIndexOf(".");
        String ext = name.substring(indexOfDot);
        String fullNewName = String.format("%s%s", newName, ext);
        return name.equals(fullNewName);
    }
    
}
