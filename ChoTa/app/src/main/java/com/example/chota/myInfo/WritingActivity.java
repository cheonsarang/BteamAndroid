package com.example.chota.myInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chota.R;

public class WritingActivity extends AppCompatActivity {
    RecyclerView recv_writing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        recv_writing = findViewById(R.id.recv_writing);





    }

}