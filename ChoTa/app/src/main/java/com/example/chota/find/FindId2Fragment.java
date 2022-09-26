package com.example.chota.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.chota.LoginActivity;
import com.example.chota.R;


public class FindId2Fragment extends Fragment {
    TextView tv_name, tv_id;
    LinearLayout linear_find_pw;
    Button btn_loin_back;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_find_id2, container, false);

        tv_name = v.findViewById(R.id.tv_name);
        tv_id = v.findViewById(R.id.tv_id);
        linear_find_pw = v.findViewById(R.id.linear_find_pw);
        btn_loin_back = v.findViewById(R.id.btn_loin_back);





       //비밀번호찾기 눌렀을때
       linear_find_pw.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       //로그인으로 돌아가기 눌렀을때
       btn_loin_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), LoginActivity.class);
               startActivity(intent);
           }
       });


       return v;
    }
}