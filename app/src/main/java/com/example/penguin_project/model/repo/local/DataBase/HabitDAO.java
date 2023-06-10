package com.example.penguin_project.model.repo.local.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.Steps;
import com.example.penguin_project.model.repo.local.Table.TimeOfDay;
import com.example.penguin_project.model.repo.local.Table.Todo;

import java.util.List;

@Dao
public interface HabitDAO {
    @Insert
    void insertHabit(Habits habit);
    @Insert
    void insertHabit_Day(Habit_Day habit_day);
    @Insert
    void insertHabit_DayOfWeek(Habit_DayOfWeek habit_dayOfWeek);
    @Insert
    void insert_TimeOfDay(TimeOfDay timeOfDay);


    @Query("Select * from Habits")
    List<Habits> getHabitList();
    @Query("Select * from habit_day")
    List<Habit_Day> getHabit_DayList();
    @Query("Select * from habit_dayofweek")
    List<Habit_DayOfWeek> getHabit_DayOfWeekList();
    @Query("Select * from TimeOfDay")
    List<TimeOfDay> getTimeOfDayList();

    // Todo
    @Insert
    void insertTodo(Todo todo);

    @Insert
    void insertSteps(Steps steps);

    @Query("SELECT * FROM Todo")
    List<Todo> getTodoList();

    @Query("SELECT * FROM Steps")
    List<Steps> getStepsList();

    @Query("SELECT * FROM Todo WHERE Todo_id = :todoId")
    Todo getTodoById(int todoId);

    @Query("SELECT * FROM Steps WHERE Step_id = :stepId")
    Steps getStepsById(int stepId);

    @Query("SELECT * FROM Todo WHERE Title = :title")
    Todo getTodoByTitle(String title);

    @Query("SELECT * FROM Steps WHERE Title =:title")
    Steps getStepsById(String title);


    @Query("UPDATE Todo SET Title = :newTodoName WHERE Todo_id = :todoId")
    void updateTodoName(int todoId, String newTodoName);

    @Query("DELETE FROM Todo WHERE Todo_id = :todoId")
    void deleteTodo(int todoId);


    @Query("UPDATE Steps SET Title = :newStepName WHERE Step_id=:step_id")
    void updateStepName(int step_id, String newStepName);

    @Query("DELETE FROM Steps WHERE Step_id = :step_id")
    void deleteSteps(int step_id);



    //
}
