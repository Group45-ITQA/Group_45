package com.qa.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BookUtils {
    private static final Random random = new Random();

    public static String generateUniqueTitle(String baseTitle) {
        return baseTitle + " " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                "_" + String.format("%03d", random.nextInt(1000));
    }
}