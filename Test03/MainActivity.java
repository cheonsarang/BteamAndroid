package com.example.test03_vendingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView number_1, number_2, number_3, number_4, number_5, number_6;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6;
    LinearLayout line1, line2, line3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);

        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.line1, line1, true    );
        inflater.inflate(R.layout.line2, line2, true    );
        inflater.inflate(R.layout.line3, line3, true    );


        //Intent intent;


    }
}


