package com.example.chota;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chota.join.Join1Fragment;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Join1Fragment()).commit();

    }
}