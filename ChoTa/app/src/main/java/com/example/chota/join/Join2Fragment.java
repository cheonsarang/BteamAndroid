package com.example.chota.join;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chota.R;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;


public class Join2Fragment extends Fragment {
    EditText edt_name, edt_birth, edt_phone1, edt_phone2, edt_school, edt_grade, edt_class, edt_nickname;
    ImageView img_picture;
    RadioGroup rdo_grp;
    RadioButton rdo_man, rdo_woman;
    Button btn_next, btn_prev;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_join2, container, false);

        edt_name = v.findViewById(R.id.edt_name);
        edt_birth = v.findViewById(R.id.edt_birth);
        edt_phone1 = v.findViewById(R.id.edt_phone1);
        edt_phone2 = v.findViewById(R.id.edt_phone2);
        edt_school = v.findViewById(R.id.edt_school);
        edt_grade = v.findViewById(R.id.edt_grade);
        edt_class = v.findViewById(R.id.edt_class);
        edt_nickname = v.findViewById(R.id.edt_nickname);
        img_picture = v.findViewById(R.id.img_picture);
        rdo_grp = v.findViewById(R.id.rdo_grp);
        rdo_man = v.findViewById(R.id.rdo_man);
        rdo_woman = v.findViewById(R.id.rdo_woman);
        btn_next = v.findViewById(R.id.btn_next);
        btn_prev = v.findViewById(R.id.btn_prev);


        edt_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //다음 버튼 눌렀을때
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Join3Fragment()).commit();
            }
        });


        //뒤로가기 버튼 눌렀을때
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Join1Fragment()).commit();
            }
        });


        return v;
    }
}





        //라디오그룹 선택할때
//        rdo_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(checkedId == R.id.rdo_man){
//                    //vo.setGender("남");
//                }else{
//                   // vo.setGender("여");
//                }
//            }
//        });