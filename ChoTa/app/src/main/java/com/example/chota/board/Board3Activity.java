package com.example.chota.board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chota.BoardActivity;
import com.example.chota.R;
import com.example.chota.WriteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Board3Activity extends AppCompatActivity {
    RecyclerView recv_reply;
    ImageView image_back, image_bell, image_more, image_reply_ok, image_smile;
    TextView tv_title, tv_id, tv_date, tv_time, tv_view;
    LinearLayout linear_good, linear_save, linear_share;
    EditText edt_reply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board3);


        image_back = findViewById(R.id.image_back);
        image_bell = findViewById(R.id.image_bell);
        image_more = findViewById(R.id.image_more);
        image_reply_ok = findViewById(R.id.image_reply_ok);
        image_smile = findViewById(R.id.image_smile);
        tv_title = findViewById(R.id.tv_title);
        tv_id = findViewById(R.id.tv_id);
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        tv_view = findViewById(R.id.tv_view);
        linear_good = findViewById(R.id.linear_good);
        linear_save = findViewById(R.id.linear_save);
        linear_share = findViewById(R.id.linear_share);
        edt_reply = findViewById(R.id.edt_reply);

        recv_reply = findViewById(R.id.recv_reply);

        Board3_Adapter adapter = new Board3_Adapter(getLayoutInflater());
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recv_reply.setLayoutManager(manager);
        recv_reply.setAdapter(adapter);

        //뒤로가기
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //벨 눌렀을때


    }
}