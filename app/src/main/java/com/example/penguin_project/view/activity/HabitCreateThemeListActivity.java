package com.example.penguin_project.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitTheme;

import java.io.Serializable;

public class HabitCreateThemeListActivity extends AppCompatActivity {


    TextView tvHabitThemeListTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_theme_list);


        Intent intent = getIntent();

        if (intent.hasExtra("habitTheme")) {
            HabitTheme habitThemeID = (HabitTheme) intent.getSerializableExtra("habitTheme");


            // Chỉnh tiêu đề màn hình
            tvHabitThemeListTitle = findViewById(R.id.tvHabitThemeListTitle);
            tvHabitThemeListTitle.setText(habitThemeID.getHabitThemeName());
        }






    }

}
