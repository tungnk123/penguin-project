package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.penguin_project.fragments.HomeFragment;
import com.example.penguin_project.fragments.MenuFragment;
import com.example.penguin_project.fragments.StoreFragment;
import com.example.penguin_project.fragments.TodoFragment;
import com.example.penguin_project.fragments.TrackerFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        // add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_notifications_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_settings_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.baseline_settings_24));
        // hien HomeFragment ngay ban dau luon
        replaceFragment(new HomeFragment());
        bottomNavigation.show(1, true);

        //
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        replaceFragment(new HomeFragment());
                        break;
                    case 2:
                        replaceFragment(new TodoFragment());
                        break;
                    case 3:
                        replaceFragment(new TrackerFragment());
                        break;
                    case 4:
                        replaceFragment(new StoreFragment());
                        break;
                    case 5:
                        replaceFragment(new MenuFragment());
                        break;

                }

                return null;
            }
        });
    }

    // function thay the fragment hien tai
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelauout, fragment);
        fragmentTransaction.commit();
    }
}