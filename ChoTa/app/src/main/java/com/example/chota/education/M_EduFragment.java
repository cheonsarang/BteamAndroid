package com.example.chota.education;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chota.BellActivity;
import com.example.chota.MainActivity;
import com.example.chota.LoginActivity;
import com.example.chota.R;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;


public class M_EduFragment extends Fragment implements View.OnClickListener{

    Button edu1_locate, btn1, btn2, btn3;
    TextView school_name_edu, num_edu;
    CardView edu2_find1, edu2_find2, edu2_find3;
    LinearLayout edu4_school, edu_linear1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_m__edu, container, false);

        //edu1_locate = v.findViewById(R.id.edu1_locate); //위치변경
        school_name_edu = v.findViewById(R.id.school_name_edu);
        num_edu = v.findViewById(R.id.num_edu);
        edu2_find1 = v.findViewById(R.id.edu2_find1);   //교육시설
        edu2_find2 = v.findViewById(R.id.edu2_find2);   //체험활동
        edu2_find3 = v.findViewById(R.id.edu2_find3);   //추천도서
        edu4_school = v.findViewById(R.id.edu4_school); //나의 찜한시설 등록하기

        edu_linear1 = v.findViewById(R.id.edu_linear1);

        btn1 = v.findViewById(R.id.btn1);
        btn2 = v.findViewById(R.id.btn2);
        btn3 = v.findViewById(R.id.btn3);

        edu1_locate.setOnClickListener(this);
        edu2_find1.setOnClickListener(this);
        edu2_find2.setOnClickListener(this);
        edu2_find3.setOnClickListener(this);
        edu4_school.setOnClickListener(this);

        school_name_edu.setText(CommonVal.loginInfo.getSchool_name());


        edu_linear1.setOnClickListener(this);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.edu2_find1) {  //교육시설
            intent.setClass(getContext(), AcademyActivity.class);
            intent.putExtra("schoor_id", CommonVal.loginInfo.getSchool_id());
            intent.putExtra("office_code", CommonVal.loginInfo.getOffice_code());

        }else if(v.getId() == R.id.edu2_find2) {    //체험활동
            intent.setClass(getContext(), PlayActivity.class);
            Log.d("누림", "onClick: ");

        }else if(v.getId() == R.id.edu2_find3) {    //추천도서

        }
        startActivity(intent);
    }
}