package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class HabitsInfoActivity extends AppCompatActivity {

    ToggleButton tgb2, tgb3, tgb4, tgb5, tgb6, tgb7, tgbCN;
    LinearLayout btn_anytime, btn_morning, btn_afternoon, btn_evening;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_info);

        setUpToggleButton();
        tgb2.setChecked(true);

        btn_anytime = findViewById(R.id.Habits_info_btnAnytime);

    }

    private void setUpToggleButton() {
        tgb2 = findViewById(R.id.HabitsInfo_tgb2);
        tgb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb2.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb2.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb3 = findViewById(R.id.HabitsInfo_tgb3);
        tgb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb3.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb3.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb4 = findViewById(R.id.HabitsInfo_tgb4);
        tgb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb4.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb4.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb5 = findViewById(R.id.HabitsInfo_tgb5);
        tgb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb5.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb5.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb6 = findViewById(R.id.HabitsInfo_tgb6);
        tgb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb6.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb6.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb7 = findViewById(R.id.HabitsInfo_tgb7);
        tgb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb7.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb7.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgbCN = findViewById(R.id.HabitsInfo_tgbCN);
        tgbCN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgbCN.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgbCN.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
    }
}