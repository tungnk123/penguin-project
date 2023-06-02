package com.example.penguin_project.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Todo;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private HabitDataBase habitDataBase;
    private LiveData<List<Todo>> todoList;

    public TodoViewModel(Application application) {
        super(application);
        habitDataBase = HabitDataBase.getInstance(application);
    }

}
