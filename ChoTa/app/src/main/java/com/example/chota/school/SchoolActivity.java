package com.example.chota.school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.chota.R;
import com.google.android.material.tabs.TabLayout;


public class SchoolActivity extends AppCompatActivity {

    SchoolFragment schoolFragment = new SchoolFragment();
    ClassFragment classFragment = new ClassFragment();
    Bundle b = new Bundle(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        Log.d("index", "onCreate: " + index);

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.school_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("다음페이지"); // 툴바 제목 설정

        if (index == 0) {
            getSupportActionBar().setTitle("우리학교");
            b.putString("name", "school0");
            schoolFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, schoolFragment).commit();
            return;
        } else if (index == 1) {
            getSupportActionBar().setTitle("우리학교");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, schoolFragment).commit();
            b.putString("name", "school1");
            schoolFragment.setArguments(b);
            return;
        } else if (index == 2) {
            getSupportActionBar().setTitle("우리학교");
            b.putString("name", "school2");
            schoolFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, schoolFragment).commit();
            return;
        } else if (index == 3) {
            getSupportActionBar().setTitle("우리학교");
            b.putString("name", "school3");
            schoolFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, schoolFragment).commit();
            return;
        } else if (index == 4) {
            getSupportActionBar().setTitle("우리반");
            b.putString("name", "class0");
            classFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, classFragment).commit();
            return;
        } else if (index == 5) {
            getSupportActionBar().setTitle("우리반");
            b.putString("name", "class1");
            classFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, classFragment).commit();
            return;
        } else if (index == 6) {
            getSupportActionBar().setTitle("우리반");
            b.putString("name", "class2");
            classFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, classFragment).commit();
            return;

        } else if (index == 7) {
            getSupportActionBar().setTitle("우리반");
            b.putString("name", "class3");
            classFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, classFragment).commit();
            return;

        } else if (index == 8) {
            getSupportActionBar().setTitle("우리반");
            b.putString("name", "class4");
            classFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, classFragment).commit();
            return;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                // 액티비티 이동
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }



}