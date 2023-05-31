package com.example.penguin_project.model.data;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @TypeConverter
    public static LocalDateTime toLocalDateTime(String value) {
        if (value == null) {
            return null;
        }
        return LocalDateTime.parse(value, formatter);
    }

    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.format(formatter);
    }
}
