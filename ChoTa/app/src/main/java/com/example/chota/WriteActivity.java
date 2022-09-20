package com.example.chota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;

public class WriteActivity extends AppCompatActivity {
    TextView tv_cancel, tv_register;
    Spinner spinner;
    EditText edt_title, edt_content;
    LinearLayout linear_photo;
    Intent intent;
    File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        tv_cancel = findViewById(R.id.tv_cancel);
        tv_register = findViewById(R.id.tv_register);
        spinner = findViewById(R.id.spinner);
        edt_title = findViewById(R.id.edt_title);
        edt_content = findViewById(R.id.edt_content);
        linear_photo = findViewById(R.id.linear_photo);


        //취소 눌렀을때
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

                intent = new Intent(WriteActivity.this, BoardActivity.class);
                startActivity(intent);

            }
        });

        //등록 눌렀을때
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(WriteActivity.this, BoardActivity.class);
                intent.putExtra("select", spinner.getSelectedItemId());
                intent.putExtra("title", edt_title.getText().toString());
                intent.putExtra("content", edt_content.getText().toString());
                intent.putExtra("photo", linear_photo.getId());
                startActivity(intent);
            }
        });

        //spinner 선택


        //사진



    }
}