package com.example.penguin_project.model.repo.local.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.penguin_project.model.repo.local.Table.HabitOfTheme;

import java.util.List;
@Dao
public interface HabitOfThemDAO {

    @Query("SELECT * FROM HabitOfTheme WHERE Theme_id = :ThemeID")
    List<HabitOfTheme> getHabitOfThemelist(String ThemeID);

    @Insert
    void insertHabitOfTheme(HabitOfTheme habitOfTheme);

}
