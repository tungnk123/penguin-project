package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Steps", foreignKeys = {
    @ForeignKey(entity = Todo.class, parentColumns = "Todo_id", childColumns = "Todo_id", onDelete = ForeignKey.CASCADE)
})
public class Steps {
    @PrimaryKey(autoGenerate = true)
    private int Step_id;
    private String Title;
    private int Todo_id;
    private boolean isDone;

    public int getStep_id() {
        return Step_id;
    }

    public void setStep_id(int step_id) {
        Step_id = step_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getTodo_id() {
        return Todo_id;
    }

    public void setTodo_id(int todo_id) {
        Todo_id = todo_id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
