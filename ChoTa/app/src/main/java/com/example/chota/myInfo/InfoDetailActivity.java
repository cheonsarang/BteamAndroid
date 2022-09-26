package com.example.chota.myInfo;

<<<<<<< HEAD
import android.os.Bundle;
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
>>>>>>> 8420268a6c97c263f54b530fb8093f9d77da044d
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chota.R;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;

public class InfoDetailActivity extends AppCompatActivity {
    MemberVO vo;
    TextView userid, userpw, name, school_name, phone_num, teacher_name, nickname, dam_info, ban_info, grade_info;
    View view_info;
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
        view_info = findViewById(R.id.view_info);
        dam_info = findViewById(R.id.dam_info);
        ban_info = findViewById(R.id.ban_info);
        grade_info = findViewById(R.id.grade_info);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String group = intent.getStringExtra("group");
        CommonConn conn = new CommonConn("detail", this);
        conn.addParams("id", id);
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                vo = new Gson().fromJson(data, MemberVO.class);
                userid.setText(vo.getUserid());
                userpw.setText(vo.getUserpw());
                name.setText(vo.getName());
                if(vo.getNickname() != null) {
                    nickname.setText(vo.getNickname());
                }else {
                    nickname.setText("설정하지 않음");
                }
                school_name.setText(vo.getSchool_name());
                phone_num.setText(vo.getPhone());
                if(group.equals("S")) {
                    teacher_name.setText(vo.getTeacher());
                }else {
                    teacher_name.setVisibility(View.GONE);
                    view_info.setVisibility(View.GONE);
                    dam_info.setVisibility(View.GONE);
                }
                ban_info.setText(vo.getGrade_class_code().substring(2, 3));
                grade_info.setText(vo.getGrade_class_code().substring(0, 1));


            }
        });
    }
}