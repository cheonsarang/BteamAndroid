package com.example.chota.myInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chota.R;
<<<<<<< HEAD
=======
import com.example.chota.common.Common;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
>>>>>>> 8420268a6c97c263f54b530fb8093f9d77da044d
import com.example.chota.myInfo.decorator.EventDecorator;
import com.example.chota.myInfo.decorator.SaturdayDecorator;
import com.example.chota.myInfo.decorator.SundayDecorator;
import com.example.chota.myInfo.decorator.TodayDecorator;
<<<<<<< HEAD
=======
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
>>>>>>> 8420268a6c97c263f54b530fb8093f9d77da044d
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.threeten.bp.LocalDate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Schedule_Activity extends AppCompatActivity {
    MaterialCalendarView calendar_info;
    Button cha_Btn,del_Btn,save_Btn;
    TextView diaryTextView,textView2, textView3;
    EditText contextEditText;
    ScheduleVO vo = new ScheduleVO();
    public String str=null;
    ArrayList<CalendarDay> dates = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        calendar_info = findViewById(R.id.calendar_info);

        calendar_info.setSelectedDate(CalendarDay.today());
        calendar_info.addDecorator(new TodayDecorator());
        calendar_info.addDecorator(new SundayDecorator());
        calendar_info.addDecorator(new SaturdayDecorator());
        

        
        cha_Btn = findViewById(R.id.cha_Btn);
        del_Btn = findViewById(R.id.del_Btn);
        save_Btn = findViewById(R.id.save_Btn);
        diaryTextView = findViewById(R.id.diaryTextView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        contextEditText = findViewById(R.id.contextEditText);
        vo.setGrade_class_code(Integer.parseInt(CommonVal.loginInfo.getGrade_class_code()));
        vo.setSchool_id(Integer.parseInt(CommonVal.loginInfo.getSchool_id()));
        vo.setCategory("일정");


        String data = new Gson().toJson(vo);
        CommonConn schedule_all = new CommonConn("scheduleall", Schedule_Activity.this);

        schedule_all.addParams("data", data);
        schedule_all.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                ScheduleVO[] sch  = new Gson().fromJson(data, new TypeToken<ScheduleVO[]>(){}.getType());
                Log.d("sch", "onResult: " + sch);
                //dates.add(CalendarDay.from(CalendarDay.today().getDate()));
                for (int i = 0; i < sch.length; i++) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        ParsePosition pos = new ParsePosition(0);
                        Date test_date = format.parse(sch[i].getYmd(), pos);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(test_date);
                        dates.add(CalendarDay.from(CalendarDay.today().getDate()));
                        dates.add(CalendarDay.from(test_date));
                        calendar_info.addDecorators(new EventDecorator(Color.RED, dates,Schedule_Activity.this));
                        Log.d("뭔데", "onResult: " + cal);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                calendar_info.setOnDateChangedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                        String year = String.valueOf(date.getYear());
                        String day = String.valueOf(date.getDay());
                        String month = String.valueOf(date.getMonth()+1);
                        if(month.length() == 1) {
                            month = "0" + month;
                        }
                        String nal = year + month + day;
                        vo.setYmd(nal);
                        String data = new Gson().toJson(vo);
                        CommonConn listconn = new CommonConn("schedulelist", Schedule_Activity.this);
                        listconn.addParams("data", data);
                        listconn.excuteConn(new CommonConn.ConnCallback() {
                            @Override
                            public void onResult(boolean isResult, String data) {
                                ScheduleVO[] sch  = new Gson().fromJson(data, new TypeToken<ScheduleVO[]>(){}.getType());
                                Log.d("제바ㅣㄹ", "onResult: " + sch);
                                if(sch.length == 0) {   //한건 불러왔을 때 정보가 없는 경우
                                    diaryTextView.setVisibility(View.VISIBLE);
                                    textView3.setVisibility(View.INVISIBLE);
                                    save_Btn.setVisibility(View.VISIBLE);
                                    contextEditText.setVisibility(View.VISIBLE);
                                    textView2.setVisibility(View.INVISIBLE);
                                    cha_Btn.setVisibility(View.INVISIBLE);
                                    del_Btn.setVisibility(View.INVISIBLE);
                                    diaryTextView.setText(String.format("%d / %d / %d",date.getYear(),date.getMonth()+1,date.getDay()));
                                    contextEditText.setText("");
                                    cha_Btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            contextEditText.setVisibility(View.VISIBLE);
                                            contextEditText.setText(str);
                                            cha_Btn.setVisibility(View.INVISIBLE);
                                            del_Btn.setVisibility(View.INVISIBLE);
                                            textView3.setVisibility(View.INVISIBLE);
                                            save_Btn.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    save_Btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(str == null) {
                                                textView3.setVisibility(View.VISIBLE);
                                                str=contextEditText.getText().toString();
                                                textView3.setText(str);
                                                save_Btn.setVisibility(View.INVISIBLE);
                                                cha_Btn.setVisibility(View.VISIBLE);
                                                del_Btn.setVisibility(View.VISIBLE);
                                                contextEditText.setVisibility(View.INVISIBLE);
                                                textView2.setVisibility(View.GONE);
                                                vo.setCategory("일정");
                                                vo.setYmd(nal);
                                                vo.setSchool_id(Integer.parseInt(CommonVal.loginInfo.getSchool_id()));
                                                vo.setGrade_class_code(Integer.parseInt(CommonVal.loginInfo.getGrade_class_code()));
                                                vo.setContext(str);
                                                String data = new Gson().toJson(vo);
                                                CommonConn insertconn = new CommonConn("scheduleinsert", Schedule_Activity.this);
                                                insertconn.addParams("data", data);
                                                insertconn.excuteConn(new CommonConn.ConnCallback() {
                                                    @Override
                                                    public void onResult(boolean isResult, String data) {
                                                    }
                                                });
                                            }
                                        }
                                    });
                                    del_Btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                        }
                                    });
                                }else {
                                    String context = sch[0].getContext();
                                    str = context;
                                    textView2.setVisibility(View.INVISIBLE);
                                    textView3.setVisibility(View.VISIBLE);
                                    textView3.setText(context);
                                    save_Btn.setVisibility(View.GONE);
                                    diaryTextView.setVisibility(View.INVISIBLE);
                                    contextEditText.setVisibility(View.INVISIBLE);
                                    cha_Btn.setVisibility(View.VISIBLE);
                                    del_Btn.setVisibility(View.VISIBLE);
                                    diaryTextView.setVisibility(View.VISIBLE);
                                    diaryTextView.setText(String.format("%d / %d / %d",date.getYear(),date.getMonth()+1,date.getDay()));
                                    save_Btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            textView3.setVisibility(View.VISIBLE);
                                            str=contextEditText.getText().toString();
                                            textView3.setText(str);
                                            save_Btn.setVisibility(View.INVISIBLE);
                                            cha_Btn.setVisibility(View.VISIBLE);
                                            del_Btn.setVisibility(View.VISIBLE);
                                            contextEditText.setVisibility(View.INVISIBLE);
                                            textView2.setVisibility(View.GONE);
                                            vo.setCategory("일정");
                                            vo.setYmd(nal);
                                            vo.setSchool_id(Integer.parseInt(CommonVal.loginInfo.getSchool_id()));
                                            vo.setGrade_class_code(Integer.parseInt(CommonVal.loginInfo.getGrade_class_code()));
                                            vo.setContext(str);
                                            String data = new Gson().toJson(vo);
                                            CommonConn sujeongconn = new CommonConn("scheduleupdate", Schedule_Activity.this);
                                            sujeongconn.addParams("data", data);
                                            sujeongconn.excuteConn(new CommonConn.ConnCallback() {
                                                @Override
                                                public void onResult(boolean isResult, String data) {
                                                }
                                            });
                                        }
                                    });
                                    cha_Btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            contextEditText.setVisibility(View.VISIBLE);
                                            contextEditText.setText(str);
                                            cha_Btn.setVisibility(View.INVISIBLE);
                                            del_Btn.setVisibility(View.INVISIBLE);
                                            textView3.setVisibility(View.INVISIBLE);
                                            save_Btn.setVisibility(View.VISIBLE);
                                        }
                                    });
                                }
                            }
                        });

                    }
                });
            }
        });


    }
}