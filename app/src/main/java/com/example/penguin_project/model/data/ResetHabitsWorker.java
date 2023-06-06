package com.example.penguin_project.model.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;

import java.util.List;

public class ResetHabitsWorker extends Worker {
    public ResetHabitsWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Thực hiện truy vấn và cập nhật thuộc tính "isDone" của bảng Habit thành false
        // sử dụng RoomDatabase tại đây
        // ...
        List<Habit_DayOfWeek> habit_dayOfWeekList = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getHabit_DayOfWeekList();
        for(Habit_DayOfWeek habit_dayOfWeek : habit_dayOfWeekList){
            habit_dayOfWeek.setIsDone(false);
            habit_dayOfWeek.setIsFailed(false);
            habit_dayOfWeek.setProgress(0);
            HabitDataBase.getInstance(getApplicationContext()).habitDAO().updateHabit_DayOfWeek(habit_dayOfWeek);
        }


        return Result.success();
    }
}