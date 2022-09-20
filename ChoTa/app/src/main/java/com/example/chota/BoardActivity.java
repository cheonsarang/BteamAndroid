package com.example.chota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chota.board.Board2Fragment;
import com.example.chota.board.M_BoardFragment;
import com.google.android.material.tabs.TabLayout;

public class BoardActivity extends AppCompatActivity {
    ImageView img_bell, img_search;
    TabLayout tabs;
    int position;
    M_BoardFragment board1Fragment;
    Board2Fragment board2Fragment;
//    Commu3Fragment commu3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        img_bell = findViewById(R.id.img_bell);
        img_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this, BellActivity.class);
                startActivity(intent);
            }
        });

        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("홈"));
        tabs.addTab(tabs.newTab().setText("수다방"));
        tabs.addTab(tabs.newTab().setText("인기글"));


        board1Fragment = new M_BoardFragment();
        board2Fragment = new Board2Fragment();
//        commu3Fragment = new Commu3Fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new M_BoardFragment()).commit();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            //탭 선택시 활성화
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                position = tab.getPosition();   //탭위치

                Fragment selected = null;

                if(position == 0) {
                    selected = board1Fragment;

                }else if(position == 1){
                    selected = board2Fragment;

                }
 //               else if(position == 2){
//                    selected = commu3Fragment;
//
//                }
//
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();


            }
        });





    }
}