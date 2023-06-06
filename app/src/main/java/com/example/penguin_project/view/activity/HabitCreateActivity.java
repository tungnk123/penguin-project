package com.example.penguin_project.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;
import com.example.penguin_project.model.data.TimeOFWeek;
import com.vanniktech.emoji.Emoji;
import com.vanniktech.emoji.EmojiManager;

import java.util.ArrayList;

public class HabitCreateActivity extends AppCompatActivity {

    RecyclerView rv_HabitTimeOfWeek;
    ArrayList<TimeOFWeek> timeOFWeek;
    Button btnEditEmojiButton;
    TextView tvTimeOfWeek;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_create_info);

        rv_HabitTimeOfWeek = findViewById(R.id.rv_HabitTimeOfWeek);

        btnEditEmojiButton = findViewById(R.id.btnEditEmojiButton);



        btnEditEmojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void RecycleViewValue(){
        timeOFWeek = new ArrayList<>();



    }


}
