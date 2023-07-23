package com.example.penguin_project.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.LoginActivity;
import com.example.penguin_project.SettingsAlarmActivity;
import com.example.penguin_project.SettingsChangeModeActivity;
import com.example.penguin_project.R;
import com.example.penguin_project.SettingsDiseaseModeActivity;
import com.example.penguin_project.SettingsForestThemeActivity;
import com.example.penguin_project.SettingsLanguageActivity;
import com.example.penguin_project.SettingsSoundActivity;
import com.example.penguin_project.SettingsVacationModeActivity;
import com.example.penguin_project.SettingsWeekStartAt;
import com.example.penguin_project.model.data.SettingItem;
import com.example.penguin_project.view.adapter.SettingListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment implements SettingListAdapter.OnItemClickListener {

    List<SettingItem> customList;
    List<SettingItem> specialModeList;
    List<SettingItem> aboutList;
    List<SettingItem> helpAndFeedBackList;
    List<SettingItem> connectList;
    RecyclerView rcvCustomList;
    RecyclerView rcvSpecialList;
    RecyclerView rcvAboutList;
    RecyclerView rcvHAFList;
    RecyclerView rcvConnectList;

    public static SharedPreferences modeSettingsSP;
    public static SharedPreferences soundSettingsSP;
    public static SharedPreferences forestThemeSettingsSP;
    public static SharedPreferences alarmSettingsSP;
    public static SharedPreferences languageSettingsSP;
    public static SharedPreferences weekSettingsSP;
    public static SharedPreferences vacationModeSettingsSP;
    public static SharedPreferences diseaseModeSettingsSP;

    private Button btnLogin;



    @Override
    public void onItemClick(SettingItem item) {
        switch (item.getTitle()) {
            case "Change mode":
                Intent changeModeIntent = new Intent(getContext(), SettingsChangeModeActivity.class);
                startActivity(changeModeIntent);
                break;
            case "Sounds":
                Intent soundIntent = new Intent(getContext(), SettingsSoundActivity.class);
                startActivity(soundIntent);
                break;
            case "Forest theme":
                Intent forestThemeIntent = new Intent(getContext(), SettingsForestThemeActivity.class);
                startActivity(forestThemeIntent);
                break;
            case "Alarm":
                Intent alarmIntent = new Intent(getContext(), SettingsAlarmActivity.class);
                startActivity(alarmIntent);
                break;
            case "Language":
                Intent languageIntent = new Intent(getContext(), SettingsLanguageActivity.class);
                startActivity(languageIntent);
                break;
            case "Week starts at":
                Intent weekIntent = new Intent(getContext(), SettingsWeekStartAt.class);
                startActivity(weekIntent);
                break;
            case "Vacation mode":
                Intent vacationIntent = new Intent(getContext(), SettingsVacationModeActivity.class);
                startActivity(vacationIntent);
                break;
            case "Disease mode":
                Intent diseaseIntent = new Intent(getContext(), SettingsDiseaseModeActivity.class);
                startActivity(diseaseIntent);
                break;



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        btnLogin = view.findViewById(R.id.btn_settingsFragment_btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });


        //region khoi tao list va anh xa recycler view
        customList = new ArrayList<>();
        specialModeList = new ArrayList<>();
        aboutList = new ArrayList<>();
        helpAndFeedBackList = new ArrayList<>();
        connectList = new ArrayList<>();

        rcvCustomList = view.findViewById(R.id.rcv_settingsFragment_customList);
        rcvSpecialList = view.findViewById(R.id.rcv_settingsFragment_speciaModeList);
        rcvAboutList = view.findViewById(R.id.rcv_settingsFragment_aboutList);
        rcvHAFList = view.findViewById(R.id.rcv_settingsFragment_helpAndFeedbackList);
        rcvConnectList = view.findViewById(R.id.rcv_settingsFragment_socialMediaList);



        //endregion
        //region Add data into list
        customList.add(new SettingItem("Change mode", R.mipmap.icon_brush, "Dark mode"));
        customList.add(new SettingItem("Sounds", R.mipmap.icon_sound, "Fire camp"));
        customList.add(new SettingItem("Forest theme", R.mipmap.icon_foresttheme, "Normal"));
        customList.add(new SettingItem("Alarm", R.mipmap.icon_alarm, "On"));
        customList.add(new SettingItem("Language", R.mipmap.icon_language, "English"));
        customList.add(new SettingItem("Week starts at", R.mipmap.icon_week, "Monday"));


        specialModeList.add(new SettingItem("Vacation mode", R.mipmap.icon_umbrela, "Off"));
        specialModeList.add(new SettingItem("Disease mode", R.mipmap.icon_disease, "Off"));


        aboutList.add(new SettingItem("Privacy policy", R.mipmap.icon_umbrela, ""));
        aboutList.add(new SettingItem("Term of use", R.mipmap.icon_termofuse, ""));
        aboutList.add(new SettingItem("Version", R.mipmap.icon_version, "v1.0"));

        helpAndFeedBackList.add(new SettingItem("Help", R.mipmap.icon_help, ""));
        helpAndFeedBackList.add(new SettingItem("FAQs", R.mipmap.icon_faq, ""));
        helpAndFeedBackList.add(new SettingItem("Suggest feature", R.mipmap.icon_advise, ""));

        connectList.add(new SettingItem("Facebook", R.mipmap.icon_fb, ""));
        connectList.add(new SettingItem("Github", R.mipmap.icon_github, ""));
        //endregion
        //region setdivider item decoration
        DividerItemDecoration dividerItemDecorationCustom = new DividerItemDecoration(rcvCustomList.getContext(), LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecorationSpecial = new DividerItemDecoration(rcvSpecialList.getContext(), LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecorationAbout = new DividerItemDecoration(rcvAboutList.getContext(), LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecorationHAF = new DividerItemDecoration(rcvHAFList.getContext(), LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecorationConnect = new DividerItemDecoration(rcvConnectList.getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecorationCustom.setDrawable(ContextCompat.getDrawable(rcvCustomList.getContext(), R.drawable.divider));
        dividerItemDecorationSpecial.setDrawable(ContextCompat.getDrawable(rcvCustomList.getContext(), R.drawable.divider));
        dividerItemDecorationAbout.setDrawable(ContextCompat.getDrawable(rcvCustomList.getContext(), R.drawable.divider));
        dividerItemDecorationHAF.setDrawable(ContextCompat.getDrawable(rcvCustomList.getContext(), R.drawable.divider));
        dividerItemDecorationConnect.setDrawable(ContextCompat.getDrawable(rcvCustomList.getContext(), R.drawable.divider));

        rcvCustomList.addItemDecoration(dividerItemDecorationCustom);
        rcvSpecialList.addItemDecoration(dividerItemDecorationSpecial);
        rcvAboutList.addItemDecoration(dividerItemDecorationAbout);
        rcvHAFList.addItemDecoration(dividerItemDecorationHAF);
        rcvConnectList.addItemDecoration(dividerItemDecorationConnect);



        //endregion
        SettingListAdapter customAdapter = new SettingListAdapter(view.getContext(),customList);
        SettingListAdapter aboutAdapter = new SettingListAdapter(view.getContext(),aboutList);
        SettingListAdapter specialModeAdapter = new SettingListAdapter(view.getContext(),specialModeList);
        SettingListAdapter hafAdapter = new SettingListAdapter(view.getContext(),helpAndFeedBackList);
        SettingListAdapter connectAdapter = new SettingListAdapter(view.getContext(),connectList);

        //region OnClick in rcv
        customAdapter.setOnItemClickListener(this);
        aboutAdapter.setOnItemClickListener(this);
        specialModeAdapter.setOnItemClickListener(this);
        hafAdapter.setOnItemClickListener(this);
        connectAdapter.setOnItemClickListener(this);
        //endregion

        rcvCustomList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvSpecialList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvAboutList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvHAFList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvConnectList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));



        rcvCustomList.setAdapter(customAdapter);
        rcvAboutList.setAdapter(aboutAdapter);
        rcvHAFList.setAdapter(hafAdapter);
        rcvSpecialList.setAdapter(specialModeAdapter);
        rcvConnectList.setAdapter(connectAdapter);


        //region Khoi tao Shared Preferences de luu Settings
        modeSettingsSP = view.getContext().getSharedPreferences("mode_setting", Context.MODE_PRIVATE);
        soundSettingsSP = view.getContext().getSharedPreferences("sound_setting", Context.MODE_PRIVATE);
        forestThemeSettingsSP = view.getContext().getSharedPreferences("forest_setting", Context.MODE_PRIVATE);
        alarmSettingsSP = view.getContext().getSharedPreferences("alarm_setting", Context.MODE_PRIVATE);
        languageSettingsSP = view.getContext().getSharedPreferences("language_setting", Context.MODE_PRIVATE);
        weekSettingsSP = view.getContext().getSharedPreferences("week_setting", Context.MODE_PRIVATE);
        vacationModeSettingsSP = view.getContext().getSharedPreferences("vacation_setting", Context.MODE_PRIVATE);
        diseaseModeSettingsSP = view.getContext().getSharedPreferences("disease_setting", Context.MODE_PRIVATE);

        //region Lay data tu Shared Preferences va gan vao status text view

        customList.get(0).setStatus(modeSettingsSP.getString("mode_setting", "Dark mode"));
        customList.get(1).setStatus(soundSettingsSP.getString("sound_setting", "Nope"));
        customList.get(2).setStatus(forestThemeSettingsSP.getString("forest_setting", "Plain"));
        customList.get(3).setStatus(alarmSettingsSP.getString("alarm_setting", "On"));
        customList.get(4).setStatus(languageSettingsSP.getString("language_setting", "English"));
        customList.get(5).setStatus(weekSettingsSP.getString("week_setting", "Monday"));
        customAdapter.notifyDataSetChanged();

        specialModeList.get(0).setStatus(vacationModeSettingsSP.getString("vacation_setting", "Off"));
        specialModeList.get(1).setStatus(diseaseModeSettingsSP.getString("disease_setting", "Off"));
        specialModeAdapter.notifyDataSetChanged();
        //endregion





        return view;
    }

    public void setTextViewSettingStatus() {

    }
}