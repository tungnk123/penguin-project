package com.example.penguin_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ImageViewCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class EditHabitActivity extends AppCompatActivity {

    EditText edt_habitName, edt_habitTimePerDay;
    ImageButton btn_color1, btn_color2, btn_color3, btn_color4, btn_color5, btn_color6, btn_color7, btn_color8, btn_iconPicker;
    ImageView habitItemIcon;
    RelativeLayout habitItemColor;
    ToggleButton tgb2, tgb3, tgb4, tgb5, tgb6, tgb7, tgbCN;
    LinearLayout btn_anytime, btn_morning, btn_afternoon, btn_evening;
    int imgResource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);

        settingToggleButton();
        settingTimeOfDay();
        settingColorButton();
        settingOtherControls();
        settingIconPicker();
        imgResource = R.mipmap.icon_drink_water;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if (resultCode == Activity.RESULT_OK) {
                // Xử lý kết quả trả về
                if (data != null) {
                    // Lấy dữ liệu kết quả từ Intent
                    int result = data.getIntExtra("Icon", 0);
                    habitItemIcon.setImageResource(result);
                    imgResource = result;
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // Xử lý khi người dùng hủy bỏ hoặc không có kết quả trả về
            }
        }
    }

    private void settingIconPicker() {
        btn_iconPicker = findViewById(R.id.EditHabit_btn_iconPicker);
        btn_iconPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IconPickerActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void settingOtherControls() {
        edt_habitName = findViewById(R.id.EdtiHabit_edtHabitName);
        edt_habitTimePerDay = findViewById(R.id.EditHabit_edtTimePerDay);
        habitItemIcon = findViewById(R.id.EditHabit_habitItem_Icon);
    }

    private void settingColorButton() {
        habitItemColor = findViewById(R.id.EditHabit_habitItem_Color);
        btn_color1 = findViewById(R.id.EditHabit_btnColor1);
        btn_color2 = findViewById(R.id.EditHabit_btnColor2);
        btn_color3 = findViewById(R.id.EditHabit_btnColor3);
        btn_color4 = findViewById(R.id.EditHabit_btnColor4);
        btn_color5 = findViewById(R.id.EditHabit_btnColor5);
        btn_color6 = findViewById(R.id.EditHabit_btnColor6);
        btn_color7 = findViewById(R.id.EditHabit_btnColor7);
        btn_color8 = findViewById(R.id.EditHabit_btnColor8);

        btn_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape);
            }
        });
        btn_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_2);
            }
        });
        btn_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_3);
            }
        });
        btn_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_4);
            }
        });
        btn_color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_5);
            }
        });
        btn_color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_6);
            }
        });
        btn_color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_7);
            }
        });
        btn_color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_8);
            }
        });
    }

    private void settingTimeOfDay() {
        btn_anytime = findViewById(R.id.EditHabit_btnAnytime);
        btn_morning = findViewById(R.id.EditHabit_btnMorning);
        btn_afternoon = findViewById(R.id.EditHabit_btnAfternoon);
        btn_evening = findViewById(R.id.EditHabit_btnEvening);

        btn_anytime.setBackgroundResource(R.drawable.item_timeofday_shape);

        btn_anytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_anytime.setBackgroundResource(R.drawable.item_timeofday_shape);
                btn_morning.setBackgroundResource(R.drawable.item_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_shape);
                btn_evening.setBackgroundResource(R.drawable.item_shape);
            }
        });
        btn_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_anytime.setBackgroundResource(R.drawable.item_shape);
                btn_morning.setBackgroundResource(R.drawable.item_timeofday_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_shape);
                btn_evening.setBackgroundResource(R.drawable.item_shape);
            }
        });
        btn_afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_anytime.setBackgroundResource(R.drawable.item_shape);
                btn_morning.setBackgroundResource(R.drawable.item_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_timeofday_shape);
                btn_evening.setBackgroundResource(R.drawable.item_shape);
            }
        });
        btn_evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_anytime.setBackgroundResource(R.drawable.item_shape);
                btn_morning.setBackgroundResource(R.drawable.item_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_shape);
                btn_evening.setBackgroundResource(R.drawable.item_timeofday_shape);
            }
        });

    }

    private void settingToggleButton() {
        tgb2 = findViewById(R.id.EditHabit_tgb2);
        tgb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb2.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb2.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb3 = findViewById(R.id.EditHabit_tgb3);
        tgb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb3.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb3.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb4 = findViewById(R.id.EditHabit_tgb4);
        tgb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb4.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb4.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb5 = findViewById(R.id.EditHabit_tgb5);
        tgb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb5.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb5.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb6 = findViewById(R.id.EditHabit_tgb6);
        tgb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb6.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb6.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb7 = findViewById(R.id.EditHabit_tgb7);
        tgb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb7.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb7.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgbCN = findViewById(R.id.EditHabit_tgbCN);
        tgbCN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgbCN.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgbCN.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
    }
}