package com.example.chota.myInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chota.R;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BanActivity extends AppCompatActivity {
    RecyclerView recview_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);

        Intent intent = getIntent();
        recview_ok = findViewById(R.id.recview_ok);
        MemberVO tempVo = (MemberVO) intent.getSerializableExtra("vo");
        String data = new Gson().toJson(tempVo);
        CommonConn conn = new CommonConn("banlist", this);
        conn.addParams("data", data);
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                ArrayList<MemberVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<MemberVO>>(){}.getType());
                BanAdapter adapter = new BanAdapter(getLayoutInflater(), list);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(BanActivity.this, RecyclerView.VERTICAL, false);
                recview_ok.setLayoutManager(manager);
                recview_ok.setAdapter(adapter);
            }
        });


    }


}