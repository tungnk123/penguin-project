package com.example.penguin_project.model.data;

import java.time.LocalDate;

public class HabitDate {
    private LocalDate Date;

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public HabitDate(LocalDate date) {
        Date = date;
    }
}
