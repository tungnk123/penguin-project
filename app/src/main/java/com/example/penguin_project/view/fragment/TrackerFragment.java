package com.example.penguin_project.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;
import com.example.penguin_project.model.data.LocalDateConverter;
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.view.adapter.CalendarAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrackerFragment extends Fragment {
    private RecyclerView recyclerView;
    private CalendarAdapter calendarAdapter;
    private TextView txtCurrentStreak, txtMaxStreak;

    public TrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tracker, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));

        // Khởi tạo và gán Adapter cho RecyclerView
        calendarAdapter = new CalendarAdapter(getDaysInCurrentMonth());
        recyclerView.setAdapter(calendarAdapter);

        txtCurrentStreak = view.findViewById(R.id.HabitTracker_txtStreakDay);
        txtMaxStreak = view.findViewById(R.id.HabitTracker_txtMaxStreakDay);

        settingCurrentStreakAndMaxStreak();

        return view;
    }

    private void settingCurrentStreakAndMaxStreak() {
        List<Habit_Day> habit_dayList = HabitDataBase.getInstance(getContext()).habitDAO().getHabit_DayList();
        int currentStreak = 0;
        int maxStreak = 0;

        for(int i = 0; i < habit_dayList.size(); i++){
            Habit_Day habit_day = habit_dayList.get(i);
            if(habit_day.getHabit_Day_id() == LocalDate.now()){
                return;
            }
            if(habit_day.getIsDone()){
                currentStreak += 1;
                if(maxStreak < currentStreak){
                    currentStreak = maxStreak;
                }
            }
            else {
                currentStreak = 0;
            }
        }

        txtCurrentStreak.setText(String.valueOf(currentStreak));
        txtMaxStreak.setText(String.valueOf(maxStreak));
    }

    private List<Habit_Day> getDaysInCurrentMonth() {
        List<Habit_Day> habitDates = new ArrayList<>();

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        // Đặt ngày về đầu tháng
        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue() ;
        int maxDayOfMonth = firstDayOfMonth.lengthOfMonth();

        // Tạo danh sách các ngày trong tháng
        for (int i = 1; i <= startDayOfWeek; i++) {
            // Các ngày trước đầu tháng
            habitDates.add(null);
        }

        for (int i = 1; i <= maxDayOfMonth; i++) {
            // Các ngày trong tháng
            LocalDate day = LocalDate.of(currentYear, currentMonth, i);
            Long dateInMillis = LocalDateConverter.toTimestamp(day);
            List<Habit_Day> habit_days = HabitDataBase.getInstance(getContext()).habitDAO().getHabit_DayByID(dateInMillis);
            Habit_Day habit_day = habit_days.get(0);
            habitDates.add(habit_day);
        }
        return habitDates;

    }
}