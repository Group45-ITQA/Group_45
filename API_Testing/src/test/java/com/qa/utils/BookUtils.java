package com.qa.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookUtils {
    public static String generateUniqueTitle(String baseTitle) {
        return baseTitle + " " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

}