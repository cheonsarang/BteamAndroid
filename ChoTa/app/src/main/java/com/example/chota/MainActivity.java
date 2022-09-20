package com.example.chota;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.chota.board.M_BoardFragment;
import com.example.chota.common.CommonVal;
import com.example.chota.education.M_EduFragment;
import com.example.chota.main.M_SchoolFragment;
import com.example.chota.main.MainFragment;
import com.example.chota.myInfo.M_MyInfoFragment_student;
import com.example.chota.myInfo.M_MyInfoFragment_teacher;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationView btm_nav;

    //Toolbar에 title생성했었는데 클릭하면 위로 올라가게 해야해서 뺐음
    Toolbar toolbar;
    TextView toolbar_title;

    //네비게이션 드로어 activity_main2
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MainFragment mainFragment = new MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btm_nav = findViewById(R.id.btm_nav);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar_title =findViewById(R.id.toolbar_title);
        toolbar_title.setOnClickListener(this);
        //네비게이션 드로어 activity_main2

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        View v = navigationView.getHeaderView(0);
        v.findViewById(R.id.btn_off).setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();

        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.tab1){
                    Log.d("탭", "onNavigationItemSelected: 탭1" );
                    //toolbar.setTitle("홈");
                    toolbar_title.setText("홈");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container , mainFragment).commit();
                }else if(item.getItemId() == R.id.tab2){
                    Log.d("탭", "onNavigationItemSelected: 탭2" );
                    toolbar_title.setText("게시판");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container , new M_BoardFragment()).commit();
                }else if(item.getItemId() == R.id.tab3){
                    Log.d("탭", "onNavigationItemSelected: 탭3" );
                    toolbar_title.setText("우리 학교는");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container , new M_SchoolFragment()).commit();
                }else if(item.getItemId() == R.id.tab4){
                    Log.d("탭", "onNavigationItemSelected: 탭4" );
                    toolbar_title.setText("교육정보");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container , new M_EduFragment()).commit();
                }else if(item.getItemId() == R.id.tab5){
                    Log.d("탭", "onNavigationItemSelected: 탭5" );
                    toolbar_title.setText("마이페이지");
                    if(CommonVal.loginInfo.getMember_grp().equals("S")) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container , new M_MyInfoFragment_student()).commit();
                    }else if(CommonVal.loginInfo.getMember_grp().equals("T")) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container , new M_MyInfoFragment_teacher()).commit();
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.container , new M_MyInfoFragment_teacher()).commit();

                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //activyty_main2
    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }


            case R.id.menu1:{
                Intent intent = new Intent(MainActivity.this, BellActivity.class);
                intent.putExtra("index",1);
                startActivity(intent);
                return true;
            }

            case R.id.menu2:{
                drawerLayout.openDrawer(Gravity.RIGHT);
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() { //뒤로가기 했을 때
        if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
            drawerLayout.closeDrawer(Gravity.END);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_off) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }else if(v.getId() == R.id.toolbar_title){
            if(mainFragment.scrollView != null){
                mainFragment.scrollView.smoothScrollTo(0,0);
            }
        }





    }

}