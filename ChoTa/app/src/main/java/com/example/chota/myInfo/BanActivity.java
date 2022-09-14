package com.example.chota.myInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chota.R;

public class BanActivity extends AppCompatActivity {
    RecyclerView recview_ok, recview_notok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);

        recview_ok = findViewById(R.id.recview_ok);
        recview_notok = findViewById(R.id.recview_notok);
    }
}