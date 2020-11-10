package hr.danisoka.bulkmailer.app.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StringUtils {
    public static List<String> getItemsFromString(String delimiter, String value) {
        String[] array = value.trim().split(delimiter);
        List<String> list = Arrays.asList(array);
        List<String> processedList = new ArrayList<>();
        for(String item : list){
            processedList.add(item.trim());
        }
        return array.length == 1 && array[0].isEmpty() ? null : processedList;
    }
}
