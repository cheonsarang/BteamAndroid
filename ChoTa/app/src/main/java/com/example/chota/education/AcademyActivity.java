package com.example.chota.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.chota.R;

public class AcademyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy);

        Intent intent = getIntent();
        intent.getStringExtra("schoor_id");
        intent.getStringExtra("office_code");


    }
}