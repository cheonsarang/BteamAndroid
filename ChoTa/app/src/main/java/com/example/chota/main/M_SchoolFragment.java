package com.example.chota.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chota.R;
import com.example.chota.school.SchoolActivity;
import com.example.chota.school.SchoolDTO;
import com.example.chota.school.SchoolpostAdapter;

import java.util.ArrayList;


public class M_SchoolFragment extends Fragment implements View.OnClickListener{

    //학교, 반 프래그먼트 나누기
    TextView tv_school1, tv_school2, tv_class1, tv_class2;
    LinearLayout main_school1, main_school2;

    //전체보기
    LinearLayout all1, all2;

    //학교1 - 우리학교 목록 (학교 로고, 학교 이름, 학교 지도 연동, 날짜-date today 이용하기)
    ImageView school1_id_img, school1_map;
    TextView school1_name, school1_date;
    LinearLayout school1_1, school1_2, school1_3, school1_4;

    //학교2 - 검색
    LinearLayout school2_serach;

    //학교3 - 가정통신문, 학교게시판, 공지사항 - 가장최근 제목 5개 / 오늘의 급식
    //급식 school3_2_meal 오늘날짜의 급식 받아오기
    ListView school3_1_listview, school3_3_listview, school3_4_listview;
    TextView school3_2_meal;

    //반1 - 우리반 목록 ( 학교,학년,반 / 날짜 / 채팅창 연결 )
    ImageView class1_id_img, class1_talk;
    TextView class1_grade, class1_class, class1_date;
    LinearLayout class1_1, class1_2, class1_3, class1_4, class1_5;

    //반2 - 검색

    //반3

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_m__school, container, false);

        //학교, 반 프래그먼트 나누기
        main_school1 = v.findViewById(R.id.main_school1);
        main_school2 = v.findViewById(R.id.main_school2);

        tv_school1 = v.findViewById(R.id.tv_school1);
        tv_school2 = v.findViewById(R.id.tv_school2);
        tv_class1 = v.findViewById(R.id.tv_class1);
        tv_class2 = v.findViewById(R.id.tv_class2);
        all1 = v.findViewById(R.id.all1);
        all2 = v.findViewById(R.id.all2);

        tv_school1.setOnClickListener(this);
        tv_school2.setOnClickListener(this);
        tv_class1.setOnClickListener(this);
        tv_class2.setOnClickListener(this);
        all1.setOnClickListener(this);
        all2.setOnClickListener(this);

        //학교1 - 우리학교 목록 (학교 로고, 학교 이름, 학교 지도 연동, 날짜-date today 이용하기)
        school1_id_img = v.findViewById(R.id.school1_id_img);
        school1_map = v.findViewById(R.id.school1_map);
        school1_name = v.findViewById(R.id.school1_name);
        school1_date = v.findViewById(R.id.school1_date);
        school1_1 = v.findViewById(R.id.school1_1);
        school1_2 = v.findViewById(R.id.school1_2);
        school1_3 = v.findViewById(R.id.school1_3);
        school1_4 = v.findViewById(R.id.school1_4);

        school1_1.setOnClickListener(this);
        school1_2.setOnClickListener(this);
        school1_3.setOnClickListener(this);
        school1_4.setOnClickListener(this);


        //학교3 - 가정통신문, 학교게시판, 공지사항 - 가장최근 제목 5개 / 오늘의 급식
        school3_1_listview = v.findViewById(R.id.school3_1_listview);
        school3_3_listview = v.findViewById(R.id.school3_3_listview);
        school3_4_listview = v.findViewById(R.id.school3_4_listview);
        school3_2_meal = v.findViewById(R.id.school3_2_meal);

        //반1
        class1_id_img = v.findViewById(R.id.class1_id_img);
        class1_talk = v.findViewById(R.id.class1_talk);
        class1_grade = v.findViewById(R.id.class1_grade);
        class1_class = v.findViewById(R.id.class1_class);
        class1_date = v.findViewById(R.id.class1_date);
        class1_1 = v.findViewById(R.id.class1_1);
        class1_2 = v.findViewById(R.id.class1_2);
        class1_3 = v.findViewById(R.id.class1_3);
        class1_4 = v.findViewById(R.id.class1_4);
        class1_5 = v.findViewById(R.id.class1_5);

        class1_1.setOnClickListener(this);
        class1_2.setOnClickListener(this);
        class1_3.setOnClickListener(this);
        class1_4.setOnClickListener(this);
        class1_5.setOnClickListener(this);

        ArrayList<SchoolDTO> schoolpost_list = new ArrayList<>();
        SchoolpostAdapter adapter = new SchoolpostAdapter();

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), SchoolActivity.class);

        //학교, 반 프래그먼트 나누기
        if(v.getId() == R.id.tv_school1 || v.getId() == R.id.tv_school2){
            main_school1.setVisibility(View.VISIBLE);
            main_school2.setVisibility(View.GONE);
        } else if(v.getId() == R.id.tv_class1 || v.getId() == R.id.tv_class2){
            main_school1.setVisibility(View.GONE);
            main_school2.setVisibility(View.VISIBLE);
        }

        //전체보기 화면
        else if(v.getId() == R.id.all1 || v.getId() == R.id.school1_1){
            intent.putExtra("index", 0);
            startActivity(intent);
        } else if(v.getId() == R.id.all2 || v.getId() == R.id.class1_1){
            intent.putExtra("index", 4);
            startActivity(intent);
        }

        //학교1 - 지도

        //학교1 - 포스트들
        else if(v.getId() == R.id.school1_2){
            intent.putExtra("index", 1);
            startActivity(intent);
        } else if(v.getId() == R.id.school1_3){
            intent.putExtra("index", 2);
            startActivity(intent);
        } else if(v.getId() == R.id.school1_4){
            intent.putExtra("index", 3);
            startActivity(intent);
        }

        //반1 - 포스트들
        else if(v.getId() == R.id.class1_2){
            intent.putExtra("index", 5);
            startActivity(intent);
        } else if(v.getId() == R.id.class1_3){
            intent.putExtra("index", 6);
            startActivity(intent);
        } else if(v.getId() == R.id.class1_4){
            intent.putExtra("index", 7);
            startActivity(intent);
        } else if(v.getId() == R.id.class1_5){
            intent.putExtra("index", 8);
            startActivity(intent);
        }

    }
}