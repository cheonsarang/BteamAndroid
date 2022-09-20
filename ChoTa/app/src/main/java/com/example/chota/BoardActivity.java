package com.example.chota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chota.board.Board2Fragment;

import com.example.chota.board.M_Board1Fragment;
import com.google.android.material.tabs.TabLayout;

public class BoardActivity extends AppCompatActivity {
    M_Board1Fragment board1Fragment;
    Board2Fragment board2Fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        getSupportFragmentManager().beginTransaction().replace(R.id.container, new M_Board1Fragment()).commit();

        board1Fragment = new M_Board1Fragment();
        board2Fragment = new Board2Fragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.container, new M_Board1Fragment()).commit();



        







    }
}