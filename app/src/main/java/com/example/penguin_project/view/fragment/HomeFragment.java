package com.example.penguin_project.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.view.adapter.DatePicker_Adapter;

import java.time.LocalDate;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView rcv_DatePicker;
    TextView txt_DayOfMonth, txt_DayOfWeek;
    TextView txt_Coin;
    ArrayList<HabitDate> arrDate;
    DatePicker_Adapter datePicker_adapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);


        rcv_DatePicker = view.findViewById(R.id.rcv_datePicker);
        txt_DayOfWeek = view.findViewById(R.id.txt_ActionBar_DayOfWeek);
        txt_DayOfMonth = view.findViewById(R.id.txt_ActionBar_DayOfMonth);
        txt_Coin = view.findViewById(R.id.txt_ActionBar_coin);

        switch (LocalDate.now().getDayOfWeek()){
            case MONDAY: txt_DayOfWeek.setText("Monday");
                break;
            case TUESDAY: txt_DayOfWeek.setText("Tuesday");
                break;
            case WEDNESDAY: txt_DayOfWeek.setText("Wednesday");
                break;
            case THURSDAY: txt_DayOfWeek.setText("Thursday");
                break;
            case FRIDAY: txt_DayOfWeek.setText("Friday");
                break;
            case SATURDAY: txt_DayOfWeek.setText("Saturday");
                break;
            case SUNDAY: txt_DayOfWeek.setText("Sunday");
                break;
        }

        txt_DayOfMonth.setText(String.valueOf(LocalDate.now().getDayOfMonth()));

        Setting_rcv();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_DatePicker.setLayoutManager(linearLayoutManager);

        datePicker_adapter.setOnItemClickListener(new DatePicker_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(LocalDate date) {
                Toast.makeText(getContext(), date.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        rcv_DatePicker.setAdapter(datePicker_adapter);

        HabitDataBase.getInstance(getContext()).habitDAO().getHabit_DayOfWeekList();
        return view;
    }

    private void Setting_rcv() {
        arrDate = new ArrayList<>();
        LocalDate date = LocalDate.now();
        switch (LocalDate.now().getDayOfWeek()){
            case MONDAY: date = date.minusDays(0);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 0);
                break;
            case TUESDAY: date = date.minusDays(1);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 1);
                break;
            case WEDNESDAY: date = date.minusDays(2);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 2);
                break;
            case THURSDAY: date = date.minusDays(3);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 3);
                break;
            case FRIDAY: date = date.minusDays(4);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 4);
                break;
            case SATURDAY: date = date.minusDays(5);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 5);
                break;
            case SUNDAY: date = date.minusDays(6);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 6);
                break;
        }
        for(int i = 0; i < 7; i++){
            arrDate.add(new HabitDate(date.plusDays(i)));
        }

    }
}