package com.example.penguin_project.model.repo.local.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.Steps;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
import com.example.penguin_project.model.repo.local.Table.TimeOfDay;
import com.example.penguin_project.model.repo.local.Table.Todo;
import com.example.penguin_project.model.repo.local.Table.Tree;

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

    @Query("SELECT * FROM TIMEOFDAY WHERE TimeOfDay_id =:timeOfDay_id")
    TimeOfDay getTimeOfDayById(int timeOfDay_id);

    //region TodoQuery
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

    @Query("Select * from Habit_DayOfWeek where Habit_DayOfWeek.Habit_DayOfWeek_id = :Dow")
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
    //

    //region Tree
    @Insert
    void insertTree(Tree tree);

    @Query("SELECT * FROM TREE")
    List<Tree> getTreeForestList();

    @Query("SELECT * FROM TREE WHERE Tree_id =:tree_id")
    Tree getTreeForestById(int tree_id);

    @Query("UPDATE Tree SET Title = :title WHERE Tree_id =:tree_id")
    void updateTreeForestById(int tree_id, String title);

    @Query("DELETE FROM TREE WHERE Tree_id =:tree_id")
    void deleteTreeForestById(int tree_id);

    @Query("UPDATE Tree SET IsPurchased =:is_purchased WHERE Tree_id =:tree_id")
    void updateTreeForestIsPurchased(int tree_id, boolean is_purchased);
    //endregion

    //region Query for Store Item
    @Query("SELECT * FROM StoreItem")
    LiveData<List<StoreItem>> getStoreItem();

    @Query("SELECT * FROM StoreItem WHERE StoreItemType = :itemType")
    LiveData<List<StoreItem>> getStoreItemByType(String itemType);

    @Query("SELECT * FROM StoreItem WHERE Item_id=:item_id")
    LiveData<List<StoreItem>> getStoreItemById(int item_id);

    @Query("SELECT * FROM StoreItem WHERE ItemName = :itemName")
    LiveData<List<StoreItem>> getStoreItemByName(String itemName);

    @Query("Select * from Habits where Habits.Habit_id = :id")
    List<Habits> getHabitsByID(int id);

    @Insert
    void insertStoreItem(StoreItem storeItem);

    @Query("UPDATE StoreItem SET ItemName = :itemName WHERE Item_id =:item_id")
    void updateItemName(int item_id, String itemName);

    @Query("UPDATE StoreItem SET ItemImg = :itemImg WHERE Item_id =:item_id")
    void updateItemImg(int item_id, int itemImg);

    @Query("UPDATE StoreItem SET ItemPrice =:itemPrice WHERE Item_id =:item_id")
    void updateItemPrice(int item_id, int itemPrice);

    @Query("UPDATE StoreItem SET IsPurchased =:isPurchased WHERE Item_id =:item_id")
    void updateIsPurchasedById(int item_id, boolean isPurchased);

    @Query("SELECT COUNT(*) FROM StoreItem")
    int getStoreItemSize();
    @Query("DELETE FROM StoreItem WHERE Item_id =:item_id")
    void deleteItem(int item_id);




    //endregion


}
