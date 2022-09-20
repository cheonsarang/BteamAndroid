package com.example.chota.myInfo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chota.R;

public class InfoDetailActivity extends AppCompatActivity {
    MemberVO vo;
    TextView userid, userpw, name, school_name, phone_num, teacher_name, nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);

        userid = findViewById(R.id.userid);
        userpw = findViewById(R.id.userpw);
        name = findViewById(R.id.name);
        school_name = findViewById(R.id.school_name);
        phone_num = findViewById(R.id.phone_num);
        teacher_name = findViewById(R.id.teacher_name);
        nickname = findViewById(R.id.nickname);


    }

    public void setWidget(MemberVO vo) {
        userid.setText(vo.getUserid());
        userpw.setText(vo.getUserpw());
        name.setText(vo.getName());
        if(vo.getNickname() != null) {
            nickname.setText(vo.getNickname());
        }else {
            nickname.setText("설정하지 않음");
        }
        school_name.setText(vo.getSchool_id());
        phone_num.setText(vo.getPhone());

    }
}