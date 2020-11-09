package hr.danisoka.bulkmailer.app.utils;

import java.util.Arrays;
import java.util.List;

public final class StringUtils {
    public static List<String> getItemsFromString(String delimiter, String value) {
        return Arrays.asList(value.split(delimiter));
    }
}
