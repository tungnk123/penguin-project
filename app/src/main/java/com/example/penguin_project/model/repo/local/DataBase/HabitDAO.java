package com.example.penguin_project.model.repo.local.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.penguin_project.model.repo.local.Table.HabitGroup;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.RemindTime;
import com.example.penguin_project.model.repo.local.Table.Steps;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
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

    @Query("DELETE FROM StoreItem WHERE Item_id =:item_id")
    void deleteItem(int item_id);


    //endregion
}
