package com.weacadt.log.Activity;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.weacadt.log.Fragment.CalendarFragment;
import com.weacadt.log.Fragment.DiaryFragment;
import com.weacadt.log.Fragment.TodoFragment;
import com.weacadt.log.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //声明底部导航控件
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView bottomNavView;
    private NavigationView sideNavView;
    //声明3大fragment，分别是待办、日记、日历
    private TodoFragment todoFragment;
    private DiaryFragment diaryFragment;
    private CalendarFragment calendarFragment;


    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter fpAdapter;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图、数据
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
        //获取到3个fragment的对象
        todoFragment = new TodoFragment();
        diaryFragment = new DiaryFragment();
        calendarFragment = new CalendarFragment();

        fragmentList = new ArrayList<Fragment>();

        //获取到Viewpager和底部导航的对象
        mDrawerLayout = findViewById(R.id.layout_drawer);
        mViewPager = findViewById(R.id.viewpager);
        bottomNavView = findViewById(R.id.bottom_nav_view);
        sideNavView = findViewById(R.id.side_nav_view);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }


        fragmentList.add(todoFragment);
        fragmentList.add(diaryFragment);
        fragmentList.add(calendarFragment);

        sideNavView.setCheckedItem(R.id.home);

        bottomNavView.setOnNavigationItemSelectedListener(itemSelectedListener);
        mViewPager.addOnPageChangeListener(pageChangeListener);
        sideNavView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.test1:
                Toast.makeText(MainActivity.this, "你点击了测试按钮1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.test2:
                Toast.makeText(MainActivity.this, "你点击了测试按钮2", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.bottom_navigation_todo:
                    mViewPager.setCurrentItem(0);
                    toolbar.setTitle("今日");
                    return true;
                case R.id.bottom_navigation_diary:
                    mViewPager.setCurrentItem(1);
                    toolbar.setTitle("日记");
                    return true;
                case R.id.bottom_navigation_calender:
                    mViewPager.setCurrentItem(2);
                    toolbar.setTitle("日历");
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
                    bottomNavView.setSelectedItemId(R.id.bottom_navigation_todo);
                    toolbar.setTitle("今日");
                    break;
                case 1:
                    bottomNavView.setSelectedItemId(R.id.bottom_navigation_diary);
                    toolbar.setTitle("日记");
                    break;
                case 2:
                    bottomNavView.setSelectedItemId(R.id.bottom_navigation_calender);
                    toolbar.setTitle("日历");
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Toast.makeText(MainActivity.this, "你点击了主页按钮!", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    return true;
                case R.id.setting:
                    Toast.makeText(MainActivity.this, "你点击了设置按钮！", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    return true;
                case R.id.about:
                    mDrawerLayout.closeDrawers();
                    AboutActivity.actionStart(MainActivity.this);
                    return true;
            }
            return false;
        }
    };

}
