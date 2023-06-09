package com.example.penguin_project.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitTheme;
import com.example.penguin_project.model.repo.local.DataBase.HabitThemeDB;
import com.example.penguin_project.model.repo.local.Table.HabitOfTheme;
import com.example.penguin_project.view.adapter.HabitOfTheme_Adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HabitCreateThemeListActivity extends AppCompatActivity {

    ArrayList<HabitOfTheme> habitOfThemes = new ArrayList<>();
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

            HabitThemeDB hbthemDB = HabitThemeDB.getInstance(this);



            habitOfThemes = (ArrayList<HabitOfTheme>) hbthemDB.habitOfThemDAO().getHabitOfThemelist(habitThemeID.getHabitThemeID());


            HabitOfTheme_Adapter habitOfThemeArrayAdapter = new HabitOfTheme_Adapter(this, R.layout.layout_create_item_theme, habitOfThemes);



            ListView HabitOfThemListView = findViewById(R.id.HabitThemeListViewDetail);

            HabitOfThemListView.setAdapter(habitOfThemeArrayAdapter);



        }

    }

}
