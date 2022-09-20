package com.example.chota.school;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
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

    //SchoolFragment schoolfragment = (SchoolFragment) getFragmentManager().findFragmentByTag("school1");

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
        SchoolAdapter adapter = new SchoolAdapter(school_list, inflater);

        school_list.add(new SchoolDTO(school1, date1, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
        school_list.add(new SchoolDTO(school1, date2, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
        school_list.add(new SchoolDTO(school1, date3, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
        school_list.add(new SchoolDTO(school1, date4, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
        listView_school.setAdapter(adapter);

        Bundle b = getArguments();

        if(b != null){
            String name = b.getString("name");
            Log.d("TAG", "onCreateView: " + name);
            if(name.equals("school0")){
                tabs_school.selectTab(tabs_school.getTabAt(0));
            } else if(name.equals("school1")){
                tabs_school.selectTab(tabs_school.getTabAt(1));
            } else if(name.equals("school2")){
                tabs_school.selectTab(tabs_school.getTabAt(2));
            } else if(name.equals("school3")){
                tabs_school.selectTab(tabs_school.getTabAt(3));
            }
        }

        tabs_school.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                school_list.clear();
                if ( tab.getPosition() == 0) {
                    school_list.add(new SchoolDTO(school1, date1, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school1, date2, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school1, date3, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school1, date4, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    listView_school.setAdapter(adapter);
                } else if ( tab.getPosition() == 1) {
                    school_list.add(new SchoolDTO(school2, date1, date1, "찰보리밥\n" +
                            "소고기무국 (5.6.16.)\n" +
                            "콩나물무침 (5.)\n" +
                            "오리불고기 (5.6.13.)\n" +
                            "열무김치\n" +
                            "자두쥬스"));
                    school_list.add(new SchoolDTO(school2, date2, date2, "카레라이스 (5.6.10.13.)\n" +
                            "팽이버섯된장국 (5.6.)\n" +
                            "무채장아찌무침\n" +
                            "어니언떡갈비 (5.6.10.13.)\n" +
                            "배추김치 (9.)\n" +
                            "망고주스2 (13.)"));
                    school_list.add(new SchoolDTO(school2, date3, date3, "친환경찰현미밥\n" +
                            "청국장찌개 (5.6.9.)\n" +
                            "미역초무침 (5.6.8.9.13.)\n" +
                            "스파게티 (2.5.6.12.13.)\n" +
                            "깍두기 (9.)\n" +
                            "거봉"));
                    school_list.add(new SchoolDTO(school2, date4, date4, "혼합10곡밥 (5.)\n" +
                            "갈비감자탕 (5.6.10.13.)\n" +
                            "방풍나물 (5.6.13.)\n" +
                            "삼치엿장조림 (5.6.13.)\n" +
                            "배추김치 (9.)\n" +
                            "요거트다쿠아즈 (1.2.6.)"));
                    listView_school.setAdapter(adapter);
                } else if ( tab.getPosition() == 2) {
                    school_list.add(new SchoolDTO(school3, date1, "계수초 천사랑", "힘내라!", R.drawable.heart_edu));
                    school_list.add(new SchoolDTO(school3, date2, "월호초 윤신향", "너가 최고야!!", R.drawable.heart_edu));
                    listView_school.setAdapter(adapter);
                } else if ( tab.getPosition() == 3) {
                    school_list.add(new SchoolDTO(school4, date1, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school4, date2, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school4, date3, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(school4, date4, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    listView_school.setAdapter(adapter);
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