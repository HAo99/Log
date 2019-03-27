package com.weacadt.log;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private TodoFragment todoFragment;
    private DiaryFragment diaryFragment;
    private CalenderFragment calenderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();



    }

    private void initView(){
        todoFragment = new TodoFragment();
        diaryFragment = new DiaryFragment();
        calenderFragment = new CalenderFragment();

        navigationView = findViewById(R.id.bottom_navigation_bar);
        navigationView.setOnNavigationItemSelectedListener(itemSelectedListener);
        replaceFragment(todoFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.bottom_navigation_todo:
                    replaceFragment(todoFragment);
                    return true;
                case R.id.bottom_navigation_diary:
                    replaceFragment(diaryFragment);
                    return true;
                case R.id.bottom_navigation_calender:
                    replaceFragment(calenderFragment);
                    return true;

            }
            return false;
        }
    };

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


}
