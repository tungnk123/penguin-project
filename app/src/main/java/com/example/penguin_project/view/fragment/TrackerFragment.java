package com.example.penguin_project.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
    public static ImageView imgTree1;
    public ImageView imgTree2;
    public ImageView imgTree3;
    public ImageView imgTree4;
    public ImageView imgTree5;
    public ImageView imgTree6;
    public ImageView imgTree7;
    public ImageView imgTree8;


    public TrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tracker, container, false);

        imgTree1 = view.findViewById(R.id.img_fragmentTracker_tree1);
        imgTree2 = view.findViewById(R.id.img_fragmentTracker_tree2);
        imgTree3 = view.findViewById(R.id.img_fragmentTracker_tree3);
        imgTree4 = view.findViewById(R.id.img_fragmentTracker_tree4);
        imgTree5 = view.findViewById(R.id.img_fragmentTracker_tree5);
        imgTree6 = view.findViewById(R.id.img_fragmentTracker_tree6);
        imgTree7 = view.findViewById(R.id.img_fragmentTracker_tree7);
        imgTree8 = view.findViewById(R.id.img_fragmentTracker_tree8);

//        imgTree1.setImageResource(R.mipmap.icon_tree_mam);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));

        // Khởi tạo và gán Adapter cho RecyclerView
        calendarAdapter = new CalendarAdapter(getDaysInCurrentMonth());
        recyclerView.setAdapter(calendarAdapter);

        return view;
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

    public static void setTree() {
        imgTree1.setImageResource(R.mipmap.icon_tree_hoacam);
//        Toast.makeText(imgTree1.getContext(), "set tree", Toast.LENGTH_SHORT).show();
    }
}