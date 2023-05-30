package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "HabitGroup")
public class HabitGroup {
    @PrimaryKey(autoGenerate = true)
    private int Group_id;
    private String Title;
    private int NumberOfHabit;

    public HabitGroup(int Group_id, String Title, int NumberOfHabit) {
        this.Group_id = Group_id;
        this.Title = Title;
        this.NumberOfHabit = NumberOfHabit;
    }

    public int getGroup_id() {
        return Group_id;
    }

    public void setGroup_id(int group_id) {
        Group_id = group_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getNumberOfHabit() {
        return NumberOfHabit;
    }

    public void setNumberOfHabit(int numberOfHabit) {
        NumberOfHabit = numberOfHabit;
    }
}
