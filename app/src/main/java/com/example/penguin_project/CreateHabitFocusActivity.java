package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CreateHabitFocusActivity extends AppCompatActivity {
    ImageButton btn_back;
    RelativeLayout btn_learnSomethingNew, btn_read20Page, btn_listenToPodcast, btn_writeDiary, btn_stretching;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit_focus);

        settingButton();
    }

    private void settingButton() {
        btn_back = findViewById(R.id.CreateHabitFocus_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_learnSomethingNew = findViewById(R.id.CreateHabitFocus_btnHabitLearnSomethingNew);
        btn_read20Page = findViewById(R.id.CreateHabitFocus_btnHabitRead20page);
        btn_listenToPodcast = findViewById(R.id.CreateHabitFocus_btnHabitListenToPodcast);
        btn_writeDiary = findViewById(R.id.CreateHabitFocus_btnHabitWriteDiary);
        btn_stretching = findViewById(R.id.CreateHabitFocus_btnHabitStretching);
    }

}