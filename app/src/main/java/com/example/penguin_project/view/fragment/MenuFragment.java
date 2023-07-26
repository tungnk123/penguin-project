package com.example.penguin_project.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.penguin_project.view.activity.LoginActivity;
import com.example.penguin_project.MainActivity;
import com.example.penguin_project.view.activity.SettingsAlarmActivity;
import com.example.penguin_project.view.activity.SettingsChangeModeActivity;
import com.example.penguin_project.R;
import com.example.penguin_project.view.activity.SettingsDiseaseModeActivity;
import com.example.penguin_project.view.activity.SettingsForestThemeActivity;
import com.example.penguin_project.view.activity.SettingsLanguageActivity;
import com.example.penguin_project.view.activity.SettingsSoundActivity;
import com.example.penguin_project.view.activity.SettingsVacationModeActivity;
import com.example.penguin_project.view.activity.SettingsWeekStartAt;
import com.example.penguin_project.view.activity.SuggestFeatureActivity;
import com.example.penguin_project.model.data.SettingItem;
import com.example.penguin_project.model.data.ThemeControl;
import com.example.penguin_project.view.adapter.SettingListAdapter;
import com.google.firebase.auth.FirebaseAuth;

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

    public static SharedPreferences userSP;

    private Button btnLogin;

    public TextView tvUsername;
    public TextView tvEmail;
    public ImageView profileLogo;



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

            case "Version":
                //
                Toast.makeText(getContext(), "Version", Toast.LENGTH_SHORT).show();
                String url = "https://github.com/tungnk123/penguin-project";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
            case "Privacy policy":
            case "FAQs":
            case "Help":
            case "Term of use":
                //
                url = "https://docs.google.com/document/d/1jYXQCmp1g9rwda24MZzuV-d_6nhgXM5o/edit?usp=drive_link&ouid=104030596767851385156&rtpof=true&sd=true";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
            case "Suggest feature":
                //
                intent = new Intent(getContext(), SuggestFeatureActivity.class);
                startActivity(intent);
                break;
            case "Facebook":
                //
                url = "https://www.facebook.com/funthaner";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
            case "Github":
                //
                url = "https://github.com/tungnk123/penguin-project";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        btnLogin = view.findViewById(R.id.btn_settingsFragment_btnLogin);
        tvUsername = view.findViewById(R.id.tv_fragmentMenu_username);
        tvEmail = view.findViewById(R.id.tv_fragmentMenu_email);
        profileLogo = view.findViewById(R.id.profile_picture);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnLogin.getText().equals("LOGIN")) {
                    Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                    startActivity(loginIntent);
                }
                else {
                    FirebaseAuth.getInstance().signOut();
                    SharedPreferences.Editor editor = MenuFragment.userSP.edit();
                    editor.clear();
                    editor.apply();
                    Toast.makeText(getContext(), "Log out successful! ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("SELECTED_FRAGMENT","setting");

                    startActivity(intent);
                }
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

        if(ThemeControl.getInstance(getContext()).getData("Mode", 0) == 0){
            customList.get(0).setStatus(modeSettingsSP.getString("mode_setting", "Dark mode"));
        }
        else {
            customList.get(0).setStatus(modeSettingsSP.getString("mode_setting", "Light mode"));
        }
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




        userSP = view.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        tvUsername.setText(MenuFragment.userSP.getString("username", "Username"));
        tvEmail.setText(MenuFragment.userSP.getString("email", "username@gmail.com"));
        String uriString = MenuFragment.userSP.getString("uri", "");
        if (!uriString.isEmpty()) {
            btnLogin.setText("LOGOUT");
        }
        Uri uri = Uri.parse(uriString);

//        profileLogo.setImageURI(uri);

        Glide.with(this)
                .load(uri)
                .placeholder(R.drawable.icon_userblue)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())) // Apply CircleCrop to make it circular
                .into(profileLogo);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getInstance().changeTheme();
    }


    public void setTextViewSettingStatus() {

    }

//    private void backUpData() {
//        Toast.makeText(getContext(), "Backed up successfully!", Toast.LENGTH_SHORT).show();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("backup");
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            String username = user.getDisplayName();
//            String email = user.getEmail();
//            List<Habits> habitsList = HabitDataBase.getInstance(getContext()).habitDAO().getHabitList();
//            List<HashMap<String, Object>> habitMapList = new ArrayList<>();
//
//            for (Habits habit : habitsList) {
//                HashMap<String, Object> habitMap = new HashMap<>();
//                habitMap.put("Habit_id", habit.getHabit_id());
//                habitMap.put("Title", habit.getTitle());
//                habitMap.put("TimeOfDay_id", habit.getTimeOfDay_id());
//                habitMap.put("TimePerDay", habit.getTimePerDay());
//                habitMap.put("Color", habit.getColor());
//                habitMap.put("Icon", habit.getIcon());
//                habitMap.put("CreateDay", habit.getCreateDay().toString()); // Convert LocalDate to String
//                habitMap.put("CurrentStreak", habit.getCurrentStreak());
//                habitMap.put("MaxStreak", habit.getMaxStreak());
//                habitMap.put("Tree_id", habit.getTree_id());
//                habitMapList.add(habitMap);
//            }
//
//            List<StoreItem> storeItemList = HabitDataBase.getInstance(getContext()).habitDAO().getStoreItemByTypeNotLiveData("theme");
//            List<HashMap<String, Object>> storeItemMapList = new ArrayList<>();
//
//            for (StoreItem storeItem : storeItemList) {
//                HashMap<String, Object> storeItemMap = new HashMap<>();
//                storeItemMap.put("Item_id", storeItem.getItem_id());
//                storeItemMap.put("ItemName", storeItem.getItemName());
//                storeItemMap.put("ItemPrice", storeItem.getItemPrice());
//                storeItemMap.put("ItemImg", storeItem.getItemImg());
//                storeItemMap.put("Description", storeItem.getDescription());
//                storeItemMap.put("StoreItemType", storeItem.getStoreItemType());
//                storeItemMap.put("IsPurchased", storeItem.getIsPurchased());
//
//                storeItemMapList.add(storeItemMap);
//            }
//
//            FirebaseUserHelper userHelper = new FirebaseUserHelper(username, email, habitMapList, storeItemMapList);
//            myRef.child(username).setValue(userHelper, new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                    if (error != null) {
//                        // Handle any error occurred while setting the value
//                        Log.e("FirebaseError", error.getMessage());
//                    } else {
//                        Log.d("FirebaseSuccess", "Data backup successful");
//                    }
//                }
//            });
//        }
//    }

}