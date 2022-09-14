package com.example.chota.school;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chota.R;
import com.google.android.material.tabs.TabLayout;

public class ClassFragment extends Fragment implements View.OnClickListener {
    TabLayout tabs_class;
    LinearLayout linear_class1, linear_class2, linear_class3, linear_class4, linear_class5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_class, container, false);

        tabs_class = v.findViewById(R.id.tabs_class);
        linear_class1 = v.findViewById(R.id.linear_class1);
        linear_class2 = v.findViewById(R.id.linear_class2);
        linear_class3 = v.findViewById(R.id.linear_class3);
        linear_class4 = v.findViewById(R.id.linear_class4);
        linear_class5 = v.findViewById(R.id.linear_class5);

        tabs_class.addTab(tabs_class.newTab().setText("알림장"));
        tabs_class.addTab(tabs_class.newTab().setText("일정"));
        tabs_class.addTab(tabs_class.newTab().setText("상담"));
        tabs_class.addTab(tabs_class.newTab().setText("시간표"));
        tabs_class.addTab(tabs_class.newTab().setText("자유게시판"));

        tabs_class.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    linear_class1.setVisibility(View.VISIBLE);
                    linear_class2.setVisibility(View.GONE);
                    linear_class3.setVisibility(View.GONE);
                    linear_class4.setVisibility(View.GONE);
                    linear_class5.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) {
                    linear_class1.setVisibility(View.GONE);
                    linear_class2.setVisibility(View.VISIBLE);
                    linear_class3.setVisibility(View.GONE);
                    linear_class4.setVisibility(View.GONE);
                    linear_class5.setVisibility(View.GONE);
                } else if (tab.getPosition() == 2) {
                    linear_class1.setVisibility(View.GONE);
                    linear_class2.setVisibility(View.GONE);
                    linear_class3.setVisibility(View.VISIBLE);
                    linear_class4.setVisibility(View.GONE);
                    linear_class5.setVisibility(View.GONE);
                } else if (tab.getPosition() == 3) {
                    linear_class1.setVisibility(View.GONE);
                    linear_class2.setVisibility(View.GONE);
                    linear_class3.setVisibility(View.GONE);
                    linear_class4.setVisibility(View.VISIBLE);
                    linear_class5.setVisibility(View.GONE);
                } else if (tab.getPosition() == 4) {
                    linear_class1.setVisibility(View.GONE);
                    linear_class2.setVisibility(View.GONE);
                    linear_class3.setVisibility(View.GONE);
                    linear_class4.setVisibility(View.GONE);
                    linear_class5.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}