package com.example.chota.school;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chota.R;


import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.List;

public class ClassFragment extends Fragment implements View.OnClickListener {
    TabLayout tabs_class;
    ListView class_listView;
    LinearLayout class_timetable, class_counsel;
    RecyclerView class_timetable_recv;
    TextView timetable_thisweek;

    String class1 = "알림장";
    String class2 = "일정";
    String class3 = "시간표";//시간표 테이블 따로
    //String class4 = "상담";
    String class5 = "게시판";

    String k_mon = "월", mon = "Mon";
    String k_tue = "화", tue = "Tue";
    String k_wed = "수", wed = "Wed";
    String k_thu = "목", hu = "Thu";
    String k_fri = "금", fri = "Fri";

    //날짜 관련
    SimpleDateFormat apisd = new SimpleDateFormat("yyyyMMdd");
    DateFormat basicformat = new SimpleDateFormat("yyyyMMdd");
    DateFormat formatWeek = new SimpleDateFormat("M월 W번째 주");
    DateFormat formatMonth = new SimpleDateFormat("M월");
    DateFormat formatDay = new SimpleDateFormat("E");
    Date today = new Date();
    String today_bg = basicformat.format(today);
    String today_week = formatWeek.format(today);
    String today_month = formatMonth.format(today);
    String today_day = formatDay.format(today);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_class, container, false);

        tabs_class = v.findViewById(R.id.tabs_class);

        class_counsel = v.findViewById(R.id.class_counsel);
        class_listView = v.findViewById(R.id.class_listView);
        class_timetable = v.findViewById(R.id.class_timetable);
        class_timetable_recv = v.findViewById(R.id.class_timetable_recv);

        timetable_thisweek = v.findViewById(R.id.timetable_thisweek);

        timetable_thisweek.setText(today_week);

//        ArrayList<TimeTableRecvVO> list = new ArrayList<>();
//        list.add(0, new TimeTableRecvVO( "", "월", "화", "수", "목", "금"));
//        list.add(1, new TimeTableRecvVO( "1", "사회", "국어", "사회", "국어", "과학"));
//        list.add(2, new TimeTableRecvVO( "2", "음악", "국어", "체육", "국어", "과학"));
//        list.add(3, new TimeTableRecvVO( "3", "영어", "영어", "수학", "수학", "국어"));
//        list.add(4, new TimeTableRecvVO( "4", "도덕", "과학", "영어", "체육", "자율활동"));
//        list.add(5, new TimeTableRecvVO( "5", "수학", "사회", "음악", "미술", "실과"));
//        list.add(6, new TimeTableRecvVO( "6", "수학", "자율활동", "", "미술", "실과"));
//
//        Log.d("timetable", "onTabSelected: " +list.size());
//        TimeTableAdapter timeTableAdapter = new TimeTableAdapter(inflater, list);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        class_timetable_recv.setAdapter(timeTableAdapter);
//        class_timetable_recv.setLayoutManager(manager);


        tabs_class.addTab(tabs_class.newTab().setText(class1));
        tabs_class.addTab(tabs_class.newTab().setText(class2));
        tabs_class.addTab(tabs_class.newTab().setText(class3));
        //tabs_class.addTab(tabs_class.newTab().setText(class4));
        tabs_class.addTab(tabs_class.newTab().setText(class5));

        searchSc(class1);

        Bundle b = getArguments();

        if (b != null) {
            String name = b.getString("name");
            Log.d("TAG", "onCreateView: " + name);
            if (name.equals("class1")) {
                tabs_class.selectTab(tabs_class.getTabAt(0));
            } else if (name.equals("class2")) {
                tabs_class.selectTab(tabs_class.getTabAt(1));
                class_listView.setVisibility(View.VISIBLE);
                class_counsel.setVisibility(View.GONE);
                class_timetable.setVisibility(View.GONE);
                searchSc(class2);
            } else if (name.equals("class3")) {
                tabs_class.selectTab(tabs_class.getTabAt(2));
                class_listView.setVisibility(View.GONE);
                class_counsel.setVisibility(View.GONE);
                class_timetable.setVisibility(View.VISIBLE);
                timetable();
//            } else if (name.equals("class4")) {
//                tabs_class.selectTab(tabs_class.getTabAt(3));
//                class_listView.setVisibility(View.GONE);
//                class_counsel.setVisibility(View.VISIBLE);
//                class_timetable.setVisibility(View.GONE);
            } else if (name.equals("class5")) {
                tabs_class.selectTab(tabs_class.getTabAt(4));
                class_listView.setVisibility(View.VISIBLE);
                class_counsel.setVisibility(View.GONE);
                class_timetable.setVisibility(View.GONE);
                searchSc(class1);
            }
        }


        tabs_class.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    class_listView.setVisibility(View.VISIBLE);
                    class_counsel.setVisibility(View.GONE);
                    class_timetable.setVisibility(View.GONE);
                    searchSc(class1);

                } else if (tab.getPosition() == 1) {
                    class_listView.setVisibility(View.VISIBLE);
                    class_counsel.setVisibility(View.GONE);
                    class_timetable.setVisibility(View.GONE);
                    searchSc(class2);

                } else if (tab.getPosition() == 2) {
                    class_listView.setVisibility(View.GONE);
                    class_counsel.setVisibility(View.GONE);
                    class_timetable.setVisibility(View.VISIBLE);
                    timetable();

//                } else if (tab.getPosition() == 3) {
//                    class_listView.setVisibility(View.GONE);
//                    class_counsel.setVisibility(View.VISIBLE);
//                    class_timetable.setVisibility(View.GONE);
//
//                } else if (tab.getPosition() == 4) {
//                    class_listView.setVisibility(View.VISIBLE);
//                    class_counsel.setVisibility(View.GONE);
//                    class_timetable.setVisibility(View.GONE);
//                    searchSc(class1);
//                }

                } else if (tab.getPosition() == 3) {
                    class_listView.setVisibility(View.VISIBLE);
                    class_counsel.setVisibility(View.GONE);
                    class_timetable.setVisibility(View.GONE);
                    searchSc(class1);
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

    public void searchSc(String category) {
        CommonConn conn = new CommonConn("list.sc", getContext());
        //선생님이 말씀하신 select와 category 활용하는 방법을 잘 모르겠다... 결국 하드코딩함

        Log.d("print", "searchSc: " + category);

        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if (isResult) {
                    Log.d("데이터", "onResult: " + data);
                    ArrayList<SchoolpostVO> school_list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<SchoolpostVO>>() {
                            }.getType());
                    Log.d("데이터 리스트 길이 출력", "onResult: " + school_list.size());
                    ArrayList<SchoolpostVO> list = new ArrayList<>();
                    for (int i = 0; i < school_list.size(); i++) {
                        if (school_list.get(i).getCategory().equals(category)) {
                            list.add(school_list.get(i));
                        } else {
                        }
                    }

                    Log.d("print", "searchSc: " + list.size());

                    SchoolpostAdapter adapter = new SchoolpostAdapter(list, getLayoutInflater(), category);
                    class_listView.setAdapter(adapter);
                }
            }
        });
    }


    public void timetable() {
        String data = new Gson().toJson("jarr");

        //회원정보 이용하기
        String school_id = CommonVal.loginInfo.getSchool_id() + "";
        String office_code = CommonVal.loginInfo.getOffice_code() + "";
        String grade_class_code = CommonVal.loginInfo.getGrade_class_code();
        String grade = grade_class_code.substring(0,1) + "";
        String class_nm = grade_class_code.substring(2) + "";
        Log.d("반코드", "timetable: " + class_nm);

        CommonConn conn = new CommonConn("timetable", getContext());
        conn.addParams("jarr", data);
        conn.addParams("school_id", school_id);
        conn.addParams("office_code", office_code);
        conn.addParams("grade", grade);
        conn.addParams("class_nm", class_nm);

        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if (isResult) {
                    Log.d("데이터", "onResult: " + data);
                    ArrayList<TimeTableVO> timetable_list = new Gson().fromJson(data, new TypeToken<ArrayList<TimeTableVO>>() {
                    }.getType());
                    Log.d("데이터 리스트 길이 출력", "onResult: " + timetable_list.size());
                    ArrayList<TimeTableVO> tlist = new ArrayList<>();
                    for (int i = 0; i < timetable_list.size(); i++) {
                        try {
                            Date date = apisd.parse(timetable_list.get(i).getYmd());
                            String dateWeek = formatWeek.format(date);
                            if (dateWeek.equals(today_week)) {
                                tlist.add(timetable_list.get(i));
                            } else {
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    Log.d("한 주의 리스트 길이 출력", "onResult: " + tlist.size());

                    String period1[] = {"","","","","",""};
                    String period2[] = {"","","","","",""};
                    String period3[] = {"","","","","",""};
                    String period4[] = {"","","","","",""};
                    String period5[] = {"","","","","",""};
                    String period6[] = {"","","","","",""};

                    int cnt1 = 0;
                    int cnt2 = 0;
                    int cnt3 = 0;
                    int cnt4 = 0;
                    int cnt5 = 0;
                    int cnt6 = 0;
                    for (int i = 0; i < tlist.size(); i++) {
                        if(tlist.get(i).getPeriod().equals("1")){
                            period1[cnt1] = tlist.get(i).getSubject();
                            cnt1++;
                        }else if(tlist.get(i).getPeriod().equals("2")){
                            period2[cnt2] = tlist.get(i).getSubject();
                            cnt2++;
                        }else if(tlist.get(i).getPeriod().equals("3")){
                            period3[cnt3] = tlist.get(i).getSubject();
                            cnt3++;
                        }else if(tlist.get(i).getPeriod().equals("4")){
                            period4[cnt4] = tlist.get(i).getSubject();
                            cnt4++;
                        }else if(tlist.get(i).getPeriod().equals("5")){
                            period5[cnt5] = tlist.get(i).getSubject();
                            cnt5++;
                        }else if(tlist.get(i).getPeriod().equals("6")){
                            period6[cnt6] = tlist.get(i).getSubject();
                            cnt6++;
                        }else{

                        }
                    }

                    ArrayList<TimeTableRecvVO> list = new ArrayList<>();
                    list.add(0, new TimeTableRecvVO("", "월", "화", "수", "목", "금"));
                    list.add(1, new TimeTableRecvVO("1", period1[0], period1[1], period1[2], period1[3], period1[4]));
                    list.add(2, new TimeTableRecvVO("2", period2[0], period2[1], period2[2], period2[3], period2[4]));
                    list.add(3, new TimeTableRecvVO("3", period3[0], period3[1], period3[2], period3[3], period3[4]));
                    list.add(4, new TimeTableRecvVO("4", period4[0], period4[1], period4[2], period4[3], period4[4]));
                    list.add(5, new TimeTableRecvVO("5", period5[0], period5[1], period5[2], period5[3], period5[4]));
                    list.add(6, new TimeTableRecvVO("6", period6[0], period6[1], "", period6[2], period6[3]));

                    Log.d("timetable", "onTabSelected: " + list.size());
                    TimeTableAdapter timeTableAdapter = new TimeTableAdapter(getLayoutInflater(), list);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    class_timetable_recv.setAdapter(timeTableAdapter);
                    class_timetable_recv.setLayoutManager(manager);


                }
            }
        });
    }


    @Override
    public void onClick(View v) {

    }
}