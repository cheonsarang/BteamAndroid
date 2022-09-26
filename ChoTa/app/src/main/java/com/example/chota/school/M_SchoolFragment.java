package com.example.chota.school;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.chota.R;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class M_SchoolFragment extends Fragment implements View.OnClickListener {

    //날짜 관련
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat basicformat = new SimpleDateFormat("yyyyMMdd");
    DateFormat mainformat = new SimpleDateFormat("M월 d일 (E)");
    DateFormat format = new SimpleDateFormat("M월 d일 E요일");
    DateFormat format_month = new SimpleDateFormat("M월");
    DateFormat format_hour = new SimpleDateFormat("H");

    Date today = new Date();
    String today_bg = basicformat.format(today);
    String today_main = mainformat.format(today);
    String today_format = format.format(today);
    String today_m = format_month.format(today);
    String today_hour = format_hour.format(today);

    SimpleDateFormat apisd = new SimpleDateFormat("yyyyMMdd");

    String category = "";

    String school1 = "가정통신문";
    String school2 = "급식";
    String school3 = "학교게시판";
    String school4 = "공지사항";

    String class1 = "알림장";
    String class2 = "일정";
    String class3 = "시간표";
    String class4 = "상담"; //시간표 테이블 따로
    String class5 = "게시판";

    //학교, 반 프래그먼트 나누기
    TextView tv_school1, tv_school2, tv_class1, tv_class2;
    LinearLayout main_school1, main_school2;

    //전체보기
    LinearLayout all1, all2;

    //학교1 - 우리학교 목록 (학교 로고, 학교 이름, 학교 지도 연동, 날짜-date today 이용하기)
    ImageView school1_id_img, school1_map;
    TextView school1_name, school1_date;
    LinearLayout school1_1, school1_2, school1_3, school1_4;

    //학교2 - 검색 ( 전체 리스트 중 context와 title에 키워드로 검색하기 )
    LinearLayout school2_search;
    EditText school2_search_edit;
    ImageView school2_search_click;

    //학교3 - 가정통신문, 학교게시판, 공지사항 - 가장최근 제목 5개 / 오늘의 급식
    //급식 school3_2_meal 오늘날짜의 급식 받아오기
    LinearLayout school3_1, school3_2, school3_3, school3_4;
    ListView school3_1_listview, school3_3_listview, school3_4_listview;
    TextView school3_2_meal;

    //반1 - 우리반 목록 ( 학교,학년,반 / 날짜 / 채팅창 연결 )
    ImageView class1_id_img, class1_talk;
    TextView class1_grade, class1_class, class1_date;
    LinearLayout class1_1, class1_2, class1_3, class1_4, class1_5;

    //반2 - 검색 ( 전체 리스트 중 context와 title에 키워드로 검색하기 )
    LinearLayout class2_search;
    EditText class2_search_edit;
    ImageView class2_search_click;

    //반3
    LinearLayout class3_1, class3_2, class3_3;  //class3_1 클릭하면 시간표로 ㄱㄱ
    TextView class3_timetable_time, class3_timetable_name;
    TextView class3_2_month, class3_2_today_tv, class3_3_month, class3_3_today_tv;
    ListView class3_2_today_list, class3_3_today_list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_m__school, container, false);



        //학교, 반 프래그먼트 나누기
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


        //학교1 - 우리학교 목록 (학교 로고, 학교 이름, 학교 지도 연동, 날짜-date today 이용하기)
        school1_id_img = v.findViewById(R.id.school1_id_img);
        school1_map = v.findViewById(R.id.school1_map);
        school1_name = v.findViewById(R.id.school1_name);
        school1_date = v.findViewById(R.id.school1_date);
        school1_1 = v.findViewById(R.id.school1_1);
        school1_2 = v.findViewById(R.id.school1_2);
        school1_3 = v.findViewById(R.id.school1_3);
        school1_4 = v.findViewById(R.id.school1_4);

        school1_1.setOnClickListener(this);
        school1_2.setOnClickListener(this);
        school1_3.setOnClickListener(this);
        school1_4.setOnClickListener(this);

        school1_date.setText(today_main);


        //학교2 - 검색
//        school2_search = v.findViewById(R.id.school2_search);
//        school2_search.setOnClickListener(this);
        school2_search_edit = v.findViewById(R.id.school2_search_edit);
        school2_search_click = v.findViewById(R.id.school2_search_click);
        school2_search_click.setOnClickListener(this);

        //학교3 - 가정통신문, 학교게시판, 공지사항 - 가장최근 제목 5개 / 오늘의 급식
        school3_1_listview = v.findViewById(R.id.school3_1_listview);
        school3_3_listview = v.findViewById(R.id.school3_3_listview);
        school3_4_listview = v.findViewById(R.id.school3_4_listview);
        school3_2_meal = v.findViewById(R.id.school3_2_meal);

        school3_1 = v.findViewById(R.id.school3_1);
        school3_2 = v.findViewById(R.id.school3_2);
        school3_3 = v.findViewById(R.id.school3_3);
        school3_4 = v.findViewById(R.id.school3_4);

        school3_1.setOnClickListener(this);
        school3_2.setOnClickListener(this);
        school3_3.setOnClickListener(this);
        school3_4.setOnClickListener(this);

//        Common common = new Common();
//        if(v.getId() == R.id.school3_1_listview ){
//            category = school1;
//        }else if (v.getId() == R.id.school3_2_meal){
//
//        }else if (v.getId() == R.id.school3_3_listview){
//
//        }else if (v.getId() == R.id.school3_4_listview){
//            category = school4;
//        }

//        ArrayList<SchoolpostVO> school3_1_list = common.searchSc(school1, getContext());
//        School3Adapter school3_1_adapter = new School3Adapter(school3_1_list, inflater, school1);
//        school3_1_listview.setAdapter(school3_1_adapter);


        //반1
        class1_id_img = v.findViewById(R.id.class1_id_img);
        class1_talk = v.findViewById(R.id.class1_talk);
        class1_grade = v.findViewById(R.id.class1_grade);
        class1_class = v.findViewById(R.id.class1_class);
        class1_date = v.findViewById(R.id.class1_date);
        class1_1 = v.findViewById(R.id.class1_1);
        class1_2 = v.findViewById(R.id.class1_2);
        class1_3 = v.findViewById(R.id.class1_3);
        class1_4 = v.findViewById(R.id.class1_4);
        class1_5 = v.findViewById(R.id.class1_5);

        class1_talk.setOnClickListener(this);
        class1_1.setOnClickListener(this);
        class1_2.setOnClickListener(this);
        class1_3.setOnClickListener(this);
        class1_4.setOnClickListener(this);
        class1_5.setOnClickListener(this);

        class1_date.setText(today_main);


        //반2 - 검색
//        class2_search = v.findViewById(R.id.class2_search);
//        class2_search.setOnClickListener(this);
        class2_search_edit = v.findViewById(R.id.class2_search_edit);
        class2_search_click = v.findViewById(R.id.class2_search_click);
        class2_search_click.setOnClickListener(this);


        //반3
        class3_1 = v.findViewById(R.id.class3_1);
        class3_2 = v.findViewById(R.id.class3_2);
        class3_3 = v.findViewById(R.id.class3_3);

        class3_timetable_time = v.findViewById(R.id.class3_timetable_time);
        class3_timetable_name = v.findViewById(R.id.class3_timetable_name);

        class3_2_month = v.findViewById(R.id.class3_2_month);
        class3_3_month = v.findViewById(R.id.class3_3_month);

        class3_2_today_tv = v.findViewById(R.id.class3_2_today_tv);
        class3_2_today_list = v.findViewById(R.id.class3_2_today_list);

        class3_3_today_tv = v.findViewById(R.id.class3_3_today_tv);
        class3_3_today_list = v.findViewById(R.id.class3_3_today_list);

        class3_1.setOnClickListener(this);
        class3_2.setOnClickListener(this);
        class3_3.setOnClickListener(this);

        class3_2_month.setText(today_m);
        class3_3_month.setText(today_m);

        schoolmealslist();

        timetable();

        searchSc();

        return v;
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
                        //today_bg
                        //"20220922"
                        if (schoolmeals_list.get(i).getDish_date().equals(today_bg)) {
                            Log.d("데이터", "onResult: 급식메뉴" + schoolmeals_list.get(i).getDish_name());
                            school3_2_meal.setText(schoolmeals_list.get(i).getDish_name() + "");
                        } else {

                        }
                    }
                }
            }
        });
    }

    //class3_timetable_time, class3_timetable_name
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
                        Log.d("Time", "onResult: " + today_hour);
                        //today_bg, today_hour
                        //"20220922", ("10"
                        if (timetable_list.get(i).getYmd().equals(today_bg)) {
                            String date_hour = (Integer.parseInt(timetable_list.get(i).getPeriod()) + 8) + "";
                            Log.d("Time", "onResult: " + date_hour);
                            if (date_hour.equals(today_hour)) {
                                class3_timetable_time.setText(timetable_list.get(i).getPeriod());
                                class3_timetable_name.setText(timetable_list.get(i).getSubject() + "시간입니다.");
                            } else {
                            }
                        } else {

                        }

                    }
                }
            }
        });
    }

    public void searchSc() {
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
                    ArrayList<SchoolpostVO> school1list = new ArrayList<>();
                    ArrayList<SchoolpostVO> school4list = new ArrayList<>();

                    ArrayList<SchoolpostVO> class1list = new ArrayList<>();
                    ArrayList<SchoolpostVO> class2list = new ArrayList<>();


                    for (int i = 0; i < school_list.size(); i++) {
                        if (school_list.get(i).getCategory().equals(school1)) {
                            school1list.add(school_list.get(i));
                        } else if (school_list.get(i).getCategory().equals(school4)) {
                            school4list.add(school_list.get(i));
                        } else if (school_list.get(i).getCategory().equals(class1)) {
                            class1list.add(school_list.get(i));
                        } else if (school_list.get(i).getCategory().equals(class2)) {
                            class2list.add(school_list.get(i));
                        }
                    }

                    Log.d("printS1", "searchSc: " + school1list.size());
                    School3Adapter adapters1 = new School3Adapter(school1list, getLayoutInflater(), school1);
                    school3_1_listview.setAdapter(adapters1);

                    Log.d("printS4", "searchSc: " + school4list.size());
                    School3Adapter adapters4 = new School3Adapter(school4list, getLayoutInflater(), category);
                    school3_4_listview.setAdapter(adapters4);

                    Log.d("printC1", "searchSc: " + school1list.size());
                    int cnt1 = 0;
                    ArrayList<SchoolpostVO> list1 = new ArrayList<>();
                    for (int i = 0; i < class1list.size(); i++) {
                        try {
                            Date date = sd.parse(class1list.get(i).getYmd());
                            String date_m = format_month.format(date);
                            if (date_m.equals(today_m)) {
                                list1.add(class1list.get(i));
                                cnt1++;
                            } else {
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cnt1 > 0) {
                        School3Adapter adapterc1 = new School3Adapter(list1, getLayoutInflater(), category);
                        class3_3_today_list.setAdapter(adapterc1);
                    } else {
                        class3_3_today_list.setVisibility(View.GONE);
                        class3_3_today_tv.setVisibility(View.VISIBLE);
                    }

                    Log.d("printC2", "searchSc: " + school4list.size());
                    int cnt2 = 0;
                    ArrayList<SchoolpostVO> list2 = new ArrayList<>();
                    for (int i = 0; i < class2list.size(); i++) {
                        try {
                            Date date = sd.parse(class2list.get(i).getYmd());
                            String date_m = format_month.format(date);
                            if (date_m.equals(today_m)) {
                                list2.add(class2list.get(i));
                                cnt2++;
                            } else {
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cnt2 > 0) {
                        School3Adapter adapterc2 = new School3Adapter(list2, getLayoutInflater(), category);
                        class3_2_today_list.setAdapter(adapterc2);
                    } else {
                        class3_2_today_list.setVisibility(View.GONE);
                        class3_2_today_tv.setVisibility(View.VISIBLE);
                    }


                } else {

                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), SchoolActivity.class);

        //학교, 반 프래그먼트 나누기
        if (v.getId() == R.id.tv_school1 || v.getId() == R.id.tv_school2) {
            main_school1.setVisibility(View.VISIBLE);
            main_school2.setVisibility(View.GONE);
        } else if (v.getId() == R.id.tv_class1 || v.getId() == R.id.tv_class2) {
            main_school1.setVisibility(View.GONE);
            main_school2.setVisibility(View.VISIBLE);
        }

        //전체보기 화면
        else if (v.getId() == R.id.all1 || v.getId() == R.id.school1_1 || v.getId() == R.id.school3_1) {
            intent.putExtra("index", 0);
            startActivity(intent);
        } else if (v.getId() == R.id.all2 || v.getId() == R.id.class1_1) {
            intent.putExtra("index", 4);
            startActivity(intent);
        }

        //학교1 - 지도

        //학교1 - 포스트들
        else if (v.getId() == R.id.school1_2 || v.getId() == R.id.school3_2) {
            intent.putExtra("index", 1);
            startActivity(intent);
        } else if (v.getId() == R.id.school1_3 || v.getId() == R.id.school3_3) {
            intent.putExtra("index", 2);
            startActivity(intent);
        } else if (v.getId() == R.id.school1_4 || v.getId() == R.id.school3_4) {
            intent.putExtra("index", 3);
            startActivity(intent);
        }

        //학교2 - 검색
        else if (v.getId() == R.id.school2_search_click) {
            intent.putExtra("index", 21);
            if(school2_search_edit.getText() != null){
                intent.putExtra("search", school2_search_edit.getText().toString());
            }
            startActivity(intent);
        }

        //반1 - 포스트들
        else if(v.getId() == R.id.class1_talk) {
            intent.putExtra("index", 9);
            startActivity(intent);
        }

        else if (v.getId() == R.id.class1_2) {
            intent.putExtra("index", 5);
            startActivity(intent);
        } else if (v.getId() == R.id.class1_3) {
            intent.putExtra("index", 6);
            startActivity(intent);
        } else if (v.getId() == R.id.class1_4) {
            //Intent chatintent = new Intent(getContext(), chatActivity.class);
            //Intent chatintent = new Intent(getContext(), SpecificChatActivity.class);
            intent.putExtra("index", 7);
            startActivity(intent);
        } else if (v.getId() == R.id.class1_5) {
            intent.putExtra("index", 8);
            startActivity(intent);
        }

        //반2 - 검색
        else if (v.getId() == R.id.class2_search_click) {
            intent.putExtra("index", 22);
            if(class2_search_edit.getText() != null){
                Log.d("검색어", "onClick: " + class2_search_edit.getText().toString());
                intent.putExtra("search", class2_search_edit.getText().toString());
            }
            startActivity(intent);
        }

    }


}