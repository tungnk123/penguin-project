package com.example.penguin_project.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitTheme;
import com.example.penguin_project.view.adapter.HabitTheme_Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class HabitCreateThemeActivity extends AppCompatActivity {

    ListView habitThemeListView;
    ArrayList<HabitTheme> habitThemes = new ArrayList<>();
    HabitTheme_Adapter habitTheme_adapter;

    FloatingActionButton fabCreateHabitInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_create);
        habitThemeListView = findViewById(R.id.lvHabitThemeListView);

        habitThemes.add(new HabitTheme("HabitTheme0", "Xu hướng", R.drawable.icon_failed));
        habitThemes.add(new HabitTheme("HabitTheme1", "Thể dục", R.drawable.icon_failed));
        habitThemes.add(new HabitTheme("HabitTheme2", "Tập trung", R.drawable.icon_failed));
        habitThemes.add(new HabitTheme("HabitTheme3", "Ngủ", R.drawable.icon_failed));
        habitThemes.add(new HabitTheme("HabitTheme4", "Thói quen chung", R.drawable.icon_failed));
        habitThemes.add(new HabitTheme("HabitTheme5", "Khác", R.drawable.icon_failed));

        habitTheme_adapter = new HabitTheme_Adapter(this, R.layout.layout_create_item_theme, habitThemes);
        habitThemeListView.setAdapter(habitTheme_adapter);
        habitThemeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HabitTheme selectedHabitTheme = habitThemes.get(position);

                openHabitExamplePage(selectedHabitTheme);
            }
        });

        fabCreateHabitInfo = findViewById(R.id.fabCreateHabitInfo);

        fabCreateHabitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HabitCreateThemeActivity.this, HabitCreateActivity.class);
                startActivity(intent);
            }
        });

    }
    private void openHabitExamplePage(HabitTheme habitTheme) {
        Intent intent = new Intent(this, HabitCreateThemeListActivity.class);
        intent.putExtra("habitTheme", habitTheme);
        startActivity(intent);

    }

}
