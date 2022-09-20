package com.example.chota.school;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.chota.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ClassFragment extends Fragment implements View.OnClickListener {
    TabLayout tabs_class;
    ListView listView_class1, listView_class5;
    LinearLayout linear_class1, linear_class2, linear_class3, linear_class4, linear_class5;

    String class1 = "알림장";
    String class2 = "일정"; //게시판에 넣기
    String class3 = "시간표";
    String class4 = "상담"; //시간표 테이블 따로
    String class5 = "게시판";

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
        View v = inflater.inflate(R.layout.fragment_class, container, false);

        tabs_class = v.findViewById(R.id.tabs_class);
        listView_class1 = v.findViewById(R.id.listView_class1);
        listView_class5 = v.findViewById(R.id.listView_class5);

        linear_class1 = v.findViewById(R.id.linear_class1);
        linear_class2 = v.findViewById(R.id.linear_class2);
        linear_class3 = v.findViewById(R.id.linear_class3);
        linear_class4 = v.findViewById(R.id.linear_class4);
        linear_class5 = v.findViewById(R.id.linear_class5);

        tabs_class.addTab(tabs_class.newTab().setText(class1));
        tabs_class.addTab(tabs_class.newTab().setText(class2));
        tabs_class.addTab(tabs_class.newTab().setText(class3));
        tabs_class.addTab(tabs_class.newTab().setText(class4));
        tabs_class.addTab(tabs_class.newTab().setText(class5));

        ArrayList<SchoolDTO> school_list = new ArrayList<>();
        SchoolAdapter adapter = new SchoolAdapter(school_list, inflater);

        school_list.add(new SchoolDTO(class1, date1, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
        school_list.add(new SchoolDTO(class1, date2, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
        school_list.add(new SchoolDTO(class1, date3, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
        school_list.add(new SchoolDTO(class1, date4, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
        listView_class1.setAdapter(adapter);

        Bundle b = getArguments();

        if(b != null){
            String name = b.getString("name");
            Log.d("TAG", "onCreateView: " + name);
            if(name.equals("class0")){
                tabs_class.selectTab(tabs_class.getTabAt(0));
            } else if(name.equals("class1")){
                tabs_class.selectTab(tabs_class.getTabAt(1));
            } else if(name.equals("class2")){
                tabs_class.selectTab(tabs_class.getTabAt(2));
            } else if(name.equals("class3")){
                tabs_class.selectTab(tabs_class.getTabAt(3));
            } else if(name.equals("class4")){
                tabs_class.selectTab(tabs_class.getTabAt(4));
            }
        }



        tabs_class.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                school_list.clear();
                if (tab.getPosition() == 0) {
                    linear_class1.setVisibility(View.VISIBLE);
                    linear_class2.setVisibility(View.GONE);
                    linear_class3.setVisibility(View.GONE);
                    linear_class4.setVisibility(View.GONE);
                    linear_class5.setVisibility(View.GONE);

                    school_list.add(new SchoolDTO(class1, date1, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(class1, date2, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(class1, date3, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    school_list.add(new SchoolDTO(class1, date4, "어린이 교통 안전 교육", "어린이 교통 안전 교육(횡단보도 건널 때 3가지 수칙)", R.drawable.popup1));
                    listView_class1.setAdapter(adapter);

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

                    school_list.add(new SchoolDTO(class5, date1, "계수초 천사랑", "힘내라!", R.drawable.heart_edu));
                    school_list.add(new SchoolDTO(class5, date2, "월호초 윤신향", "너가 최고야!!", R.drawable.heart_edu));
                    listView_class5.setAdapter(adapter);
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