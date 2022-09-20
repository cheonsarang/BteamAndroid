package com.example.chota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chota.introPager2.Pager2Adapter;
import com.example.chota.introPager2.Pager2DTO;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {
    Button btn_start;
    ViewPager2 pager2;
    ArrayList<Pager2DTO> list = new ArrayList<>();
    WormDotsIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //시작하기버튼 로그인화면으로 이동
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });




        //뷰페이저로 쓰일 gif 삽입과정
        list.add(new Pager2DTO(R.drawable.intro_image1, "홈 화면에서", "여러 정보를 알수 있어요"));
        list.add(new Pager2DTO(R.drawable.intro_image2, "손 안에서", "바로 보는 급식 알림"));
        list.add(new Pager2DTO(R.drawable.intro_image3, "전국의 친구들과", "다양한 주제로 대화해보세요"));
        list.add(new Pager2DTO(R.drawable.intro_image4, "주변교육시설을", "한 번에 볼수 있어요"));
        list.add(new Pager2DTO(R.drawable.intro_image5, "내 정보를", "한 번에 볼수 있어요"));

        pager2 = findViewById(R.id.pager2);
        Pager2Adapter adapter = new Pager2Adapter(list, getLayoutInflater());
        pager2.setAdapter(adapter);

        indicator = findViewById(R.id.indicator);
        indicator.setViewPager2(pager2);

    }
}