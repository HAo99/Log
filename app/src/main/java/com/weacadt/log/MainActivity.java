package com.weacadt.log;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private TodoFragment todoFragment;
    private DiaryFragment diaryFragment;
    private CalenderFragment calenderFragment;

    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter fpAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        mViewPager.setAdapter(fpAdapter);


    }

    private void initData() {
        fpAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

    }

    private void initView(){
        todoFragment = new TodoFragment();
        diaryFragment = new DiaryFragment();
        calenderFragment = new CalenderFragment();

        fragmentList = new ArrayList<Fragment>();

        mViewPager = findViewById(R.id.viewpager);
        navigationView = findViewById(R.id.bottom_navigation_bar);

        fragmentList.add(todoFragment);
        fragmentList.add(diaryFragment);
        fragmentList.add(calenderFragment);

        navigationView.setOnNavigationItemSelectedListener(itemSelectedListener);
        mViewPager.addOnPageChangeListener(pageChangeListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.bottom_navigation_todo:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.bottom_navigation_diary:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.bottom_navigation_calender:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            switch (i){
                case 0:
                    navigationView.setSelectedItemId(R.id.bottom_navigation_todo);
                    break;
                case 1:
                    navigationView.setSelectedItemId(R.id.bottom_navigation_diary);
                    break;
                case 2:
                    navigationView.setSelectedItemId(R.id.bottom_navigation_calender);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}
