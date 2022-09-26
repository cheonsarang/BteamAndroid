package com.example.chota.myInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.chota.LoginActivity;
import com.example.chota.R;
import com.example.chota.common.CommonVal;


public class M_MyInfoFragment_student extends Fragment implements View.OnClickListener{
    LinearLayout myinfo_info, scrap_info, write_info, event_info, quit_info, logout_info;
    TextView student_name;
    LoginActivity loginActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_info_student, container, false);

        myinfo_info = v.findViewById(R.id.myinfo_info);
        scrap_info = v.findViewById(R.id.scrap_info);
        write_info = v.findViewById(R.id.write_info);
        event_info = v.findViewById(R.id.event_info);
        quit_info = v.findViewById(R.id.quit_info);
        student_name = v.findViewById(R.id.student_name);
        logout_info = v.findViewById(R.id.logout_info);

        myinfo_info.setOnClickListener(this);
        scrap_info.setOnClickListener(this);
        write_info.setOnClickListener(this);
        event_info.setOnClickListener(this);
        quit_info.setOnClickListener(this);
        logout_info.setOnClickListener(this);

        student_name.setText(CommonVal.loginInfo.getName());

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.myinfo_info) {
            intent = new Intent(getContext(), InfoDetailActivity.class);
            intent.putExtra("id", CommonVal.loginInfo.getUserid());
            intent.putExtra("group", CommonVal.loginInfo.getMember_grp());
        }else if(v.getId() == R.id.scrap_info || v.getId() == R.id.write_info) {
            intent = new Intent(getContext(), WritingActivity.class);
        }else if(v.getId() == R.id.event_info) {
            intent = new Intent(getContext(), EventActivity.class);
        }else if(v.getId() == R.id.logout_info) {
        CommonVal.loginInfo = null;
        loginActivity.saveLoginInfo();
        intent = new Intent(getContext(), LoginActivity.class);
        }else if(v.getId() == R.id.quit_info) {

        }
            startActivity(intent);
    }
}