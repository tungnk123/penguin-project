package com.example.penguin_project.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.view.adapter.CalendarAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrackerFragment extends Fragment {
    private RecyclerView recyclerView;
    private CalendarAdapter calendarAdapter;

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

        return view;
    }
    private List<Habit_Day> getDaysInCurrentMonth() {
        List<Habit_Day> habitDates = new ArrayList<>();
        List<LocalDate> days = new ArrayList<>();

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        // Đặt ngày về đầu tháng
        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        int maxDayOfMonth = firstDayOfMonth.lengthOfMonth();

        // Tạo danh sách các ngày trong tháng
        for (int i = 1; i < startDayOfWeek; i++) {
            // Các ngày trước đầu tháng
            days.add(null);
        }

        for (int i = 1; i <= maxDayOfMonth; i++) {
            // Các ngày trong tháng
            days.add(LocalDate.of(currentYear, currentMonth, i));
        }

        for (int i = 1; i <= days.size() - 1; i++) {
            // Các ngày trong tháng
            boolean isDone = false;
            if(i % 2 == 0) isDone = true;
            habitDates.add(new Habit_Day(days.get(i), isDone));
        }
        return habitDates;

    }
}