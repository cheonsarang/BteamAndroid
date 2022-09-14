package com.example.chota.myInfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chota.R;
import com.example.chota.common.CommonVal;


public class M_MyInfoFragment_teacher extends Fragment implements View.OnClickListener{
    LinearLayout myinfo_info, scrap_info, student_info, schedule_info, homework_info, timetable_info, freeboard_info, revise_info, quit_info;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_info_teacher, container, false);

        myinfo_info = v.findViewById(R.id.myinfo_info);
        scrap_info = v.findViewById(R.id.scrap_info);
        student_info = v.findViewById(R.id.student_info);
        schedule_info = v.findViewById(R.id.schedule_info);

        myinfo_info.setOnClickListener(this);
        scrap_info.setOnClickListener(this);
        student_info.setOnClickListener(this);
        schedule_info.setOnClickListener(this);







        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.myinfo_info) {
            intent = new Intent(getContext(), InfoDetailActivity.class);
            intent.putExtra("id", CommonVal.loginInfo.getMember_id());
        }else if(v.getId() == R.id.scrap_info) {
            intent = new Intent(getContext(), WritingActivity.class);
        }else if(v.getId() == R.id.student_info) {
            intent = new Intent(getContext(), BanActivity.class);
            intent.putExtra("grade_class_code", CommonVal.loginInfo.getGrade_class_code());
        }else if(v.getId() == R.id.schedule_info){

        }
        startActivity(intent);
    }
}