package com.example.penguin_project.model.data;

import androidx.room.TypeConverter;

import java.time.LocalTime;

public class LocalTimeConverter {
    @TypeConverter
    public static LocalTime fromString(String value) {
        return value == null ? null : LocalTime.parse(value);
    }

    @TypeConverter
    public static String toString(LocalTime time) {
        return time == null ? null : time.toString();
    }
}