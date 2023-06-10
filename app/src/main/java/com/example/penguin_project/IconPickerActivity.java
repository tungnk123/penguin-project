package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class IconPickerActivity extends AppCompatActivity {

    private ImageView icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9, icon10, icon11, icon12, icon13, icon14, icon15, icon16, icon17, icon18, icon19, icon20, icon21, icon22, icon23, icon24, icon25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_picker);

        settingIcon();

    }

    private void settingIcon() {
        icon1 = findViewById(R.id.icon1);
        icon2 = findViewById(R.id.icon2);
        icon3 = findViewById(R.id.icon3);
        icon4 = findViewById(R.id.icon4);
        icon5 = findViewById(R.id.icon5);
        icon6 = findViewById(R.id.icon6);
        icon7 = findViewById(R.id.icon7);
        icon8 = findViewById(R.id.icon8);
        icon9 = findViewById(R.id.icon9);
        icon10 = findViewById(R.id.icon10);
        icon11 = findViewById(R.id.icon11);
        icon12 = findViewById(R.id.icon12);
        icon13 = findViewById(R.id.icon13);
        icon14 = findViewById(R.id.icon14);
        icon15 = findViewById(R.id.icon15);
        icon16 = findViewById(R.id.icon16);
        icon17 = findViewById(R.id.icon17);
        icon18 = findViewById(R.id.icon18);
        icon19 = findViewById(R.id.icon19);
        icon20 = findViewById(R.id.icon20);
        icon21 = findViewById(R.id.icon21);
        icon22 = findViewById(R.id.icon22);
        icon23 = findViewById(R.id.icon23);
        icon24 = findViewById(R.id.icon24);
        icon25 = findViewById(R.id.icon25);

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_keep_track_of_expense);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_list_of_weekly_shopping);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_plan_expenses);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_pay_bills);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_stretching);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_write_diary);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_listen_to_podcast);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_avoid_cafein);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_read_book);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_sleep_8_hour);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_dont_touch_phone);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_think_about_your_day);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_relax);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_walk);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_drink_water);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_connect_nature);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_meditation);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_make_bed);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_clean_the_house);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_study);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_healthy_meal);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_healthy_mind);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_exercise);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_focus);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        icon25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Đặt dữ liệu kết quả (nếu có)
                intent.putExtra("Icon", R.mipmap.icon_good_night);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}