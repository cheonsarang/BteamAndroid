package com.example.chota;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chota.join.Join1Fragment;
import com.example.chota.join.Join2Fragment;

public class JoinActivity extends AppCompatActivity {

    Join2Fragment join2Fragment = new Join2Fragment();
    Bundle b = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Intent intent = getIntent();

        if(intent.hasExtra("socialID")){
            String socialID = intent.getStringExtra("socialID");
            b.putString("socialID", socialID);
            join2Fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, join2Fragment).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new Join1Fragment()).commit();
        }
    }
}