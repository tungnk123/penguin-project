package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.penguin_project.model.data.LocalDateConverter;
import com.example.penguin_project.model.data.UpdateHabit_DayWorker;
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.view.fragment.HomeFragment;
import com.example.penguin_project.view.fragment.MenuFragment;
import com.example.penguin_project.view.fragment.StoreFragment;
import com.example.penguin_project.view.fragment.TodoFragment;
import com.example.penguin_project.view.fragment.TrackerFragment;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);


        // add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.mipmap.ic_navbar_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.mipmap.ic_navbar_checklist));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.mipmap.ic_navbar_forest));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.mipmap.ic_navbar_store));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.mipmap.ic_navbar_settings));
        // hien HomeFragment ngay ban dau luon
        replaceFragment(new HomeFragment());
        bottomNavigation.show(1, true);

        //
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        replaceFragment(new HomeFragment());
                        break;
                    case 2:
                        replaceFragment(new TodoFragment());
                        break;
                    case 3:
                        replaceFragment(new TrackerFragment());
                        break;
                    case 4:
                        replaceFragment(new StoreFragment());
                        break;
                    case 5:
                        replaceFragment(new MenuFragment());
                        break;

                }

                return null;
            }
        });
        
        setupHabit_Day();
        setUpHabit_DayWorker();
    }

    private void setUpHabit_DayWorker() {
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(
                UpdateHabit_DayWorker.class,
                1,
                TimeUnit.DAYS)
                .setInitialDelay(getDelayToNextMidnight(), TimeUnit.MILLISECONDS)
                .build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "update_data_work",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest);
    }
    private long getDelayToNextMidnight() {
        Calendar midnight = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, 23);
        midnight.set(Calendar.MINUTE, 59);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);
        midnight.add(Calendar.DAY_OF_MONTH, 1);

        long delay = midnight.getTimeInMillis() - System.currentTimeMillis();
        return delay;
    }

    private void setupHabit_Day() {
        LocalDate now = LocalDate.now();
        Long dateInMillis = LocalDateConverter.toTimestamp(now);
        List<Habit_Day> habit_days =  HabitDataBase.getInstance(this).habitDAO().getHabit_DayByID(dateInMillis);
        if(habit_days.size() > 0){
            return;
        }
        else {
            // Lấy ngày hiện tại
            LocalDate currentDate = LocalDate.now();
            int currentMonth = currentDate.getMonthValue();
            int currentYear = currentDate.getYear();

            // Đặt ngày về đầu tháng
            LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
            int maxDayOfMonth = firstDayOfMonth.lengthOfMonth();

            for(int i = 1; i <= maxDayOfMonth; i++){
                HabitDataBase.getInstance(this).habitDAO().insertHabit_Day(new Habit_Day(LocalDate.of(currentYear, currentMonth, i), false));
            }
        }
    }

    // function thay the fragment hien tai
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelauout, fragment);
        fragmentTransaction.commit();
    }
}