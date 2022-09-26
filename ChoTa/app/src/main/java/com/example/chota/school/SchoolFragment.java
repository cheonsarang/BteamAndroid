package com.example.chota.school;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chota.R;
import com.example.chota.common.Common;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SchoolFragment extends Fragment {
    TabLayout tabs_school;
    ListView listView_school;
    String school1 = "가정통신문";
    String school2 = "급식";
    String school3 = "학교게시판";
    String school4 = "공지사항";

    //날짜 관련
    SimpleDateFormat apisd = new SimpleDateFormat("yyyyMMdd");
    DateFormat formatMonth = new SimpleDateFormat("yyyyMM");
    Date today = new Date();
    String today_month = formatMonth.format(today);

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

        searchSc(school1);
//        category = school1;
//        Common common = new Common();
//        ArrayList<SchoolpostVO> list = common.searchSc(category, getContext());
//        SchoolpostAdapter adapter = new SchoolpostAdapter(list, getLayoutInflater(), category);
//        listView_school.setAdapter(adapter);

        Bundle b = getArguments();
        if(b != null){
            String name = b.getString("name");
            Log.d("TAG", "onCreateView: " + name);
            if(name.equals("school1")){
                tabs_school.selectTab(tabs_school.getTabAt(0));
                searchSc(school1);
            } else if(name.equals("school2")){
                tabs_school.selectTab(tabs_school.getTabAt(1));
                schoolmealslist();
            } else if(name.equals("school3")){
                tabs_school.selectTab(tabs_school.getTabAt(2));
            } else if(name.equals("school4")){
                tabs_school.selectTab(tabs_school.getTabAt(3));
                searchSc(school4);
            }
        }

        tabs_school.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ( tab.getPosition() == 0) {
                    searchSc(school1);
                } else if ( tab.getPosition() == 1) {
                    schoolmealslist();
                } else if ( tab.getPosition() == 2) {

                } else if ( tab.getPosition() == 3) {
                    searchSc(school4);

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

    public void searchSc(String category){
        //선생님이 말씀하신 select와 category 활용하는 방법을 잘 모르겠다... 결국 하드코딩함

        Log.d("print", "searchSc: " + category);

        CommonConn conn = new CommonConn("list.sc", getContext());
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult){
                    Log.d("데이터", "onResult: " + data);
                    ArrayList<SchoolpostVO> school_list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<SchoolpostVO>>(){
                            }.getType());
                    Log.d("데이터 리스트 길이 출력", "onResult: " + school_list.size());
                    ArrayList<SchoolpostVO> list = new ArrayList<>();
                    for (int i = 0; i < school_list.size(); i++) {
                        if( school_list.get(i).getCategory().equals(category) ){
                            list.add(school_list.get(i));
                        }else{
                        }
                    }

                    Log.d("print", "searchSc: " + list.size());

                    SchoolpostAdapter adapter = new SchoolpostAdapter(list, getLayoutInflater(), category);
                    listView_school.setAdapter(adapter);
                }
            }
        });
    }

    public void schoolmealslist() {
        String data = new Gson().toJson("jarr");

        //회원정보 이용하기
        String school_id = CommonVal.loginInfo.getSchool_id()+"";
        String office_code = CommonVal.loginInfo.getOffice_code()+"";
        Log.d("학교코드", "schoolmealslist: " + office_code);

        CommonConn conn = new CommonConn("schoolmealslist", getContext());
        conn.addParams("jarr", data);
        conn.addParams("school_id", school_id);
        conn.addParams("office_code", office_code);
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if (isResult) {
                    Log.d("데이터", "onResult: " + data);
                    ArrayList<SchoolMealsVO> schoolmeals_list = new Gson().fromJson(data, new TypeToken<ArrayList<SchoolMealsVO>>() {
                    }.getType());
                    Log.d("데이터 리스트 길이 출력", "onResult: " + schoolmeals_list.size());
                    ArrayList<SchoolMealsVO> slist = new ArrayList<>();
                    for (int i = 0; i < schoolmeals_list.size(); i++) {
                        try {
                            Date date = apisd.parse(schoolmeals_list.get(i).getDish_date());
                            String dateMonth = formatMonth.format(date);
                            if (dateMonth.equals(today_month)) {
                                slist.add(schoolmeals_list.get(i));
                            } else {
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    Log.d("한 달의 리스트 길이 출력", "onResult: " + slist.size());

                    SchoolMealsAdapter adapter = new SchoolMealsAdapter(slist, getLayoutInflater());
                    listView_school.setAdapter(adapter);

                }
            }
        });
    }
}