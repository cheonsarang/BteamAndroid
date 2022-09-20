package com.example.chota.school;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chota.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SchoolFragment extends Fragment {

    TabLayout tabs_school;
    ListView listView_school;
    String school1 = "가정통신문";
    String school2 = "급식";
    String school3 = "학교게시판";
    String school4 = "공지사항";

    String date1 = "10월 31일 월요일";
    String date2 = "10월 28일 금요일";
    String date3 = "10월 27일 목요일";
    String date4 = "10월 26일 수요일";
    String date5 = "10월 25일 화요일";
    String date6 = "10월 24일 월요일";
    String date7 = "10월 21일 금요일";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_school, container, false);

        tabs_school = v.findViewById(R.id.tabs_school);
        listView_school = v.findViewById(R.id.listView_school);

        tabs_school.addTab(tabs_school.newTab().setText(school1));
        tabs_school.addTab(tabs_school.newTab().setText(school2));
        tabs_school.addTab(tabs_school.newTab().setText(school3));
        tabs_school.addTab(tabs_school.newTab().setText(school4));

        ArrayList<SchoolDTO> school_list = new ArrayList<>();

        tabs_school.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    school_list.add(new SchoolDTO(school1, date1, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school1, date2, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school1, date3, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school1, date4, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    SchoolAdapter adapter = new SchoolAdapter(school_list, inflater);
                    listView_school.setAdapter(adapter);
                } else if (tab.getPosition() == 1) {
                    school_list.add(new SchoolDTO(school2, date1, date1, "밥"));
                    school_list.add(new SchoolDTO(school2, date2, date2, "국"));
                    school_list.add(new SchoolDTO(school2, date3, date3, "짜장밥"));
                    school_list.add(new SchoolDTO(school2, date4, date4, "현미밥"));
                    SchoolAdapter adapter = new SchoolAdapter(school_list, inflater);
                    listView_school.setAdapter(adapter);
                } else if (tab.getPosition() == 2) {

                } else if (tab.getPosition() == 3) {

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

}