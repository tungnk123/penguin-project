package com.example.penguin_project.model.repo.local.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.Steps;
import com.example.penguin_project.model.repo.local.Table.TimeOfDay;
import com.example.penguin_project.model.repo.local.Table.Todo;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and TimeOfDay.TimeOfDay = 'Anytime' and Habit_DayOfWeek.IsDone = 0 and Habit_DayOfWeek.IsFailed = 0")
    List<Habits> getAnytimeHabits(int dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and TimeOfDay.TimeOfDay = 'Morning' and Habit_DayOfWeek.IsDone = 0 and Habit_DayOfWeek.IsFailed = 0")
    List<Habits> getMorningHabits(int dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and TimeOfDay.TimeOfDay = 'Afternoon' and Habit_DayOfWeek.IsDone = 0 and Habit_DayOfWeek.IsFailed = 0")
    List<Habits> getAfternoonHabits(int dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and TimeOfDay.TimeOfDay = 'Evening' and Habit_DayOfWeek.IsDone = 0 and Habit_DayOfWeek.IsFailed = 0")
    List<Habits> getEveningHabits(int dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and Habit_DayOfWeek.IsDone = 1 and Habit_DayOfWeek.IsFailed = 0")
    List<Habits> getDoneHabits(int dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and Habit_DayOfWeek.IsFailed = 1")
    List<Habits> getFailedHabits(int dayOfWeek);
    @Query("Select * from Habit_DayOfWeek where Habit_DayOfWeek.Habit_id = :habits_id and Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek")
    List<Habit_DayOfWeek> findHabitDOWByID(int habits_id, int dayOfWeek);
    //

    @Query("Select * from Habit_DayOfWeek where Habit_DayOfWeek.Habit_id = :habits_id")
    List<Habit_DayOfWeek> getListHabitDOWByID(int habits_id);

    @Query("Select * from Habit_Day where Habit_Day.Habit_Day_id = :date")
    List<Habit_Day> getHabit_DayByID(Long date);

    @Query("Select * from Habit_DayOfWeek where Habit_DayOfWeek.Habit_id = :Dow")
    List<Habit_DayOfWeek> getListHabitDowByDow(int Dow);

    @Update
    void updateHabit_Day(Habit_Day habit_day);

    @Update
    void updateHabits(Habits habits);
    @Update
    void updateHabit_DayOfWeek(Habit_DayOfWeek habit_dayOfWeek);

    @Delete
    void deleteHabit(Habits habits);
    @Delete
    void deleteHabitDayOfWeek(Habit_DayOfWeek habit_dayOfWeek);
}
