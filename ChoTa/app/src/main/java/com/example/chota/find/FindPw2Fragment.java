package com.example.chota.find;

import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.chota.R;

import java.util.Date;


public class FindPw2Fragment extends Fragment {
    TextView tv_num1, tv_num2, tv_num3, tv_num4, tv_time, tv_resend;
    Button btn_next;
    FrameLayout frame;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_find_pw2, container, false);

        tv_num1 = v.findViewById(R.id.tv_num1);
        tv_num2 = v.findViewById(R.id.tv_num2);
        tv_num3 = v.findViewById(R.id.tv_num3);
        tv_num4 = v.findViewById(R.id.tv_num4);
        tv_time = v.findViewById(R.id.tv_time);
        tv_resend = v.findViewById(R.id.tv_resend);
        btn_next = v.findViewById(R.id.btn_next);
        frame = v.findViewById(R.id.frame);


        Time time = new Time();
        int minute = time.minute;
        Date today = new Date();
        today.toString();
        tv_time.setText(minute+"");



        //확인버튼
       btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frame.setVisibility(View.GONE);
                getChildFragmentManager().beginTransaction().replace(R.id.container, new FindPw3Fragment()).commit();
            }

        });


        return v;
    }
}