package com.example.penguin_project.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Todo;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private HabitDataBase habitDatabase;
    private LiveData<List<Todo>> todoList;
    private LiveData<List<Todo>> completedTodoList;

    public TodoViewModel(Application application) {
        super(application);
        habitDatabase = HabitDataBase.getInstance(application);
        todoList = habitDatabase.habitDAO().getTodoListByIsDone(false);
        completedTodoList = habitDatabase.habitDAO().getTodoListByIsDone(true);
    }
    public LiveData<List<Todo>> getTodoList() {
        return todoList;
    }

    public LiveData<List<Todo>> getTodoListByIsDone(boolean isDone) {
        if (isDone) {
            return completedTodoList;
        }
        return todoList;
    }

    public void insertTodo(Todo todo) {
        habitDatabase.habitDAO().insertTodo(todo);
    }

    public void updateTodoName(int todoId, String newTodoName) {
        habitDatabase.habitDAO().updateTodoName(todoId, newTodoName);
    }

    public void deleteTodo(int todoId) {
        habitDatabase.habitDAO().deleteTodo(todoId);
    }

}
