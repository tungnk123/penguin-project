package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.penguin_project.model.data.LocalDateTimeConverter;

import java.time.LocalDateTime;

@Entity(tableName = "Todo")
@TypeConverters(LocalDateTimeConverter.class)
public class Todo {

    @PrimaryKey(autoGenerate = true)
    private int Todo_id;
    private String Title;
    private String Description;

    private boolean IsDone;
    private LocalDateTime DueDate;
    private LocalDateTime RemindTime;

    public Todo(int Todo_id, String Title, String Description, boolean IsDone, LocalDateTime DueDate, LocalDateTime RemindTime) {
        this.Todo_id = Todo_id;
        this.Title = Title;
        this.Description = Description;
        this.IsDone = IsDone;
        this.DueDate = DueDate;
        this.RemindTime = RemindTime;
    }


    public boolean getIsDone() {
        return IsDone;
    }

    public void setIsDone(boolean IsDone) {
        this.IsDone = IsDone;
    }

    public int getTodo_id() {
        return Todo_id;
    }

    public void setTodo_id(int Todo_id) {
        this.Todo_id = Todo_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



    public LocalDateTime getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        DueDate = dueDate;
    }

    public LocalDateTime getRemindTime() {
        return RemindTime;
    }

    public void setRemindTime(LocalDateTime remindTime) {
        RemindTime = remindTime;
    }
}
