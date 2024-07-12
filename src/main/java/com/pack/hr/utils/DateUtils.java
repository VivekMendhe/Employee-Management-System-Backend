package com.pack.hr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static LocalDateTime parseDateString(String dateString) {
        return LocalDateTime.parse(dateString + " 00:00", formatter);
    }
}

