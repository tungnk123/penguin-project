package com.example.penguin_project.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;
import com.example.penguin_project.model.data.TimeOFWeek;
import com.example.penguin_project.view.adapter.CreateHabit_DateOfWeekPicker_Adapter;
import com.vanniktech.emoji.Emoji;
import com.vanniktech.emoji.EmojiManager;

import java.util.ArrayList;

public class HabitCreateActivity extends AppCompatActivity {

    RecyclerView rv_HabitTimeOfWeek;
    private ArrayList<TimeOFWeek> timeOFWeek = new ArrayList<>();
    private CreateHabit_DateOfWeekPicker_Adapter adapter;
    Button btnDateOfWeek;
    Button btnEditEmojiButton;
    TextView tvTimeOfWeek;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_create_info);

        rv_HabitTimeOfWeek = findViewById(R.id.rv_HabitTimeOfWeek);
        rv_HabitTimeOfWeek.setLayoutManager(new LinearLayoutManager(this));
        rv_HabitTimeOfWeek.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        adapter = new CreateHabit_DateOfWeekPicker_Adapter(this, timeOFWeek);
        rv_HabitTimeOfWeek.setAdapter(adapter);

        CreateListOfDate();

        btnEditEmojiButton = findViewById(R.id.btnEditEmojiButton);

        btnEditEmojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click event
            }
        });
    }


    private void CreateListOfDate() {
        timeOFWeek = new ArrayList<>();

        for (int i = 2; i < 7; i++) {
            TimeOFWeek timeOfWeek = new TimeOFWeek();
            timeOfWeek.setTimeOfWeek(i);
            timeOFWeek.add(timeOfWeek);
        }

        adapter.setTimeOfWeeks(timeOFWeek);
    }



}
