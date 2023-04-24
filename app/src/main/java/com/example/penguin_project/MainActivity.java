package com.example.penguin_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView BottomMenu;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomMenu = findViewById(R.id.BottomMenu);
        viewPager = findViewById(R.id.Viewpg);
        setupViewPager();

    }
// Fragment and ViewPager
    private void setupViewPager() {
        BottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.btn_Home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.btn_Search:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.btn_Setting:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        viewPagerAdapter mviewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(mviewPagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        BottomMenu.getMenu().findItem(R.id.btn_Home).setChecked(true);
                        break;
                    case 1:
                        BottomMenu.getMenu().findItem(R.id.btn_Search).setChecked(true);
                        break;
                    case 2:
                        BottomMenu.getMenu().findItem(R.id.btn_Setting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
// EndRegion
}