package com.example.penguin_project.model.repo.remote;

import androidx.lifecycle.LiveData;

import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseUserHelper {
    public String username;
    public String email;

    public List<HashMap<String, Object>> habitMapList;

    public List<HashMap<String, Object>> storeItemMapList;

    public FirebaseUserHelper(String username, String email, List<HashMap<String, Object>> habitMapList, List<HashMap<String, Object>> storeItemMapList) {
        this.username = username;
        this.email = email;
        this.habitMapList = habitMapList;
        this.storeItemMapList = storeItemMapList;
    }
}
