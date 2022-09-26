package com.example.chota.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.chota.R;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.example.chota.myInfo.MemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LikeAcademyActivity extends AppCompatActivity {
    AcademyVO vo_aca = new AcademyVO();
    String userid="";
    RecyclerView heart_list_edu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_academy);

        heart_list_edu = findViewById(R.id.heart_list_edu);

        Intent intent = getIntent();
        //String userid = intent.getStringExtra("id");
        userid = CommonVal.loginInfo.getUserid();
        CommonConn heartconn = new CommonConn("heartlist", LikeAcademyActivity.this);
        heartconn.addParams("userid", userid);
        heartconn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult){
                    ArrayList<AcademyVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<AcademyVO>>(){}.getType());
                    AcademyAdapter adapter = new AcademyAdapter(getLayoutInflater(), list, LikeAcademyActivity.this);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(LikeAcademyActivity.this, RecyclerView.VERTICAL, false);
                    heart_list_edu.setAdapter(adapter);
                    heart_list_edu.setLayoutManager(manager);
                }

            }
        });
    }
}