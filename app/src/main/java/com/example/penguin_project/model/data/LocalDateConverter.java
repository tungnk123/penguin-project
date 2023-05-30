package com.example.penguin_project.model.data;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class LocalDateConverter {
    @TypeConverter
    public static LocalDate fromTimestamp(Long value) {
        return value == null ? null : LocalDate.ofEpochDay(value);
    }

    @TypeConverter
    public static Long toTimestamp(LocalDate date) {
        return date == null ? null : date.toEpochDay();
    }
}
