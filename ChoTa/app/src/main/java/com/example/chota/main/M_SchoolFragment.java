package com.example.chota.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chota.R;
import com.example.chota.school.SchoolActivity;


public class M_SchoolFragment extends Fragment implements View.OnClickListener{

    TextView tv_school1, tv_school2, tv_class1, tv_class2;
    LinearLayout main_school1, main_school2, all1, all2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_m__school, container, false);

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

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), SchoolActivity.class);
        if(v.getId() == R.id.tv_school1 || v.getId() == R.id.tv_school2){
            main_school1.setVisibility(View.VISIBLE);
            main_school2.setVisibility(View.GONE);
        } else if(v.getId() == R.id.tv_class1 || v.getId() == R.id.tv_class2){
            main_school1.setVisibility(View.GONE);
            main_school2.setVisibility(View.VISIBLE);
        }

        else if(v.getId() == R.id.all1){
            intent.putExtra("index", 1);
            startActivity(intent);
        } else if(v.getId() == R.id.all2){
            intent.putExtra("index", 2);
            startActivity(intent);
        }
    }
}