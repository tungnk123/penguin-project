package com.example.penguin_project.model.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;

import java.time.LocalDate;
import java.util.List;

public class UpdateHabit_DayWorker extends Worker {
    public UpdateHabit_DayWorker(
            @NonNull Context context,
            @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Thực hiện công việc cập nhật dữ liệu ở đây
        // Gọi phương thức hoặc thực hiện tác vụ cần thiết

        List<Habit_DayOfWeek> habitsList = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getListHabitDowByDow(LocalDate.now().getDayOfWeek().getValue());
        int numberHabitsDOWfalse = 0;
        for(Habit_DayOfWeek habit_dayOfWeek : habitsList){
            if(!habit_dayOfWeek.getIsDone()){
                numberHabitsDOWfalse++;
                // update habit_streak
                List<Habits> listHabit = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getHabitsByID(habit_dayOfWeek.getHabit_id());
                Habits habits = listHabit.get(0);
                habits.setCurrentStreak(habits.getCurrentStreak() + 1);
                if(habits.getMaxStreak() <= habits.getCurrentStreak()){
                    habits.setMaxStreak(habits.getCurrentStreak());
                }
            }
        }
        // update habit_day
        LocalDate day = LocalDate.now();
        Long dateInMillis = LocalDateConverter.toTimestamp(day);
        List<Habit_Day> habit_days = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getHabit_DayByID(dateInMillis);
        Habit_Day habit_day = habit_days.get(0);
        if(numberHabitsDOWfalse > 0){
            habit_day.setIsDone(false);
            HabitDataBase.getInstance(getApplicationContext()).habitDAO().updateHabit_Day(habit_day);
        }
        else {
            habit_day.setIsDone(true);
            HabitDataBase.getInstance(getApplicationContext()).habitDAO().updateHabit_Day(habit_day);
        }
        // Trả về kết quả thành công
        return Result.success();
    }
}
