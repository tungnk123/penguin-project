package com.example.penguin_project.model.repo.local.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.penguin_project.model.repo.local.Table.HabitGroup;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.RemindTime;
import com.example.penguin_project.model.repo.local.Table.TimeOfDay;

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
    void insertHabitGroup(HabitGroup habitGroup);
    @Insert
    void insertRemindTime(RemindTime remindTime);
    @Insert
    void insert_TimeOfDay(TimeOfDay timeOfDay);

    @Query("Select * from Habits")
    List<Habits> getHabitList();
    @Query("Select * from habit_day")
    List<Habit_Day> getHabit_DayList();
    @Query("Select * from habit_dayofweek")
    List<Habit_DayOfWeek> getHabit_DayOfWeekList();
    @Query("Select * from habitgroup")
    List<HabitGroup> getHabitGroupList();
    @Query("Select * from RemindTime")
    List<RemindTime> getRemindTimeList();
    @Query("Select * from TimeOfDay")
    List<TimeOfDay> getTimeOfDayList();

    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and TimeOfDay.TimeOfDay = 'Anytime' and Habit_DayOfWeek.IsDone = 0")
    List<Habits> getAnytimeHabits(DayOfWeek dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and TimeOfDay.TimeOfDay = 'Morning' and Habit_DayOfWeek.IsDone = 0")
    List<Habits> getMorningHabits(DayOfWeek dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and TimeOfDay.TimeOfDay = 'Afternoon' and Habit_DayOfWeek.IsDone = 0")
    List<Habits> getAfternoonHabits(DayOfWeek dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and TimeOfDay.TimeOfDay = 'Evening' and Habit_DayOfWeek.IsDone = 0")
    List<Habits> getEveningHabits(DayOfWeek dayOfWeek);
    @Query("Select * from Habits join TimeOfDay on Habits.TimeOfDay_id = TimeOfDay.TimeOfDay_id join Habit_DayOfWeek on Habit_DayOfWeek.Habit_id = Habits.Habit_id where Habit_DayOfWeek.Habit_DayOfWeek_id = :dayOfWeek and Habit_DayOfWeek.IsDone = 1")
    List<Habits> getDoneHabits(DayOfWeek dayOfWeek);
}
