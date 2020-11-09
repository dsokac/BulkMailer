package hr.danisoka.bulkmailer.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CsvUtils {
    
    public static List<String> getHeaderFromFile(File file, String delimiter) throws FileNotFoundException, IOException {        
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        String[] header = line.split(delimiter);
        br.close();
        return Arrays.asList(header);
    }
    
    public static List<List<String>> getDataRowFromFile(File file, String delimiter, int row, int limit) throws FileNotFoundException, IOException {        
        List<List<String>> results = null;
        List<String> lines = null;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            lines = br.lines().skip(row + 1).limit(limit).collect(Collectors.toList());
        }   
        results = new ArrayList<>();
        for(String line : lines) {
            String[] lineArray = line.split(delimiter);
            results.add(Arrays.asList(lineArray));
        }
        return results;
    }
    
    public static List<List<String>> getDataAllRowFromFile(File file, String delimiter) throws FileNotFoundException, IOException {
         List<List<String>> results = new ArrayList<>();
         int limit = 10;
         int row = 0;
         List<List<String>> data = null;
         do {             
             data = getDataRowFromFile(file, delimiter, row, limit);
             row = row + limit;
             if(data != null && !data.isEmpty()) {
                 results.addAll(data);
             }
         } while(data != null && !data.isEmpty());
         
         return results;
    }
}
