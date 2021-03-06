package hr.danisoka.bulkmailer.app.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    
    public static void writeStringToFile(File target, String content) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            writer.write(content);
            writer.close();
        }
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
    
    public static String extractFileName(File file) {
        String name = file.getName();
        int indexOfDot = name.lastIndexOf(".");
        name = name.substring(0, indexOfDot);
        return name;
    }
    
    public static void deleteFilesRecursive(File file) {
        if(file.isDirectory()) {
            File[] dirContent = file.listFiles();
            for(int i = 0; i < dirContent.length; i++) {
                deleteFilesRecursive(dirContent[i]);
            }
        } else {
            file.delete();
        }
    }
    
    public static String readFileContentAsString(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
    }
}
