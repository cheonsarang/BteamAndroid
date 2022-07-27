package com.example.and14_allview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.and14_allview.bteam_listview.CyaFragment;
import com.example.and14_allview.listview.ListFragment;

public class MainActivity extends AppCompatActivity {
    // ListView , GridView , RycyclerView(※)
    // Adapter ( 사용자가 구현하려는 모양이 저마다 다르기때문에 각각의 위젯들을 어댑터라는 규칙을 통해서 구현)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. ViewGroup <- Layout종류를 준비 : 프래그먼트가 붙을 공간을 마련한다. -o
        //2. Fragment <- ↑ : 붙일 프래그먼트를 준비함. - o
        //3. FragmentManager : 붙임(코딩)
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, new ListFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CyaFragment()).commit();


    }
}