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
import com.example.chota.myInfo.decorator.EventDecorator;
import com.example.chota.myInfo.decorator.SaturdayDecorator;
import com.example.chota.myInfo.decorator.SundayDecorator;
import com.example.chota.myInfo.decorator.TodayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Schedule_Activity extends AppCompatActivity {
    MaterialCalendarView calendar_info;
    Button cha_Btn,del_Btn,save_Btn;
    TextView diaryTextView,textView2, textView3;
    EditText contextEditText;
    public String fname=null;
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

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String member_id = intent.getStringExtra("member_id");








        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);
                textView3.setVisibility(View.VISIBLE);
                str=contextEditText.getText().toString();
                textView3.setText(str);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);

            }
        });

        calendar_info.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String eq = date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDay()+".txt";
                Log.d("eqqqq", "onDateSelected: " + eq);
                Log.d("fnameqqq", "onDateSelected: " + fname);
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                diaryTextView.setText(String.format("%d / %d / %d",date.getYear(),date.getMonth()+1,date.getDay()));
                contextEditText.setText("");
                checkDay(date.getYear() ,date.getMonth(),date.getDay());

                if(fname.equals(eq)) {
                    Log.d("eqqqq", "onDateSelected: " + eq);
                    Log.d("fnameqqq", "onDateSelected: " + fname);
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.GONE);
                    contextEditText.setText(str);

                    textView2.setText(contextEditText.getText());
                    textView3.setVisibility(View.VISIBLE);

                }else {
                    textView3.setVisibility(View.GONE);
                }

                if(textView3 != null) {
                    calendar_info.addDecorator(new EventDecorator(Color.BLUE, dates, Schedule_Activity.this));
                }
            }
        });
    }

    public void  checkDay(int cYear,int cMonth,int cDay){
        fname=cYear+"-"+(cMonth+1)+"-"+cDay+".txt";//저장할 파일 이름설정
        FileInputStream fis=null;//FileStream fis 변수

        try{
            fis=openFileInput(fname);

            byte[] fileData=new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str=new String(fileData, "utf-8");

            contextEditText.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(str);

            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);


            cha_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText(str);

                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    textView2.setText(contextEditText.getText());
                }

            });
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText("");
                    contextEditText.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    removeDiary(fname);
                }
            });
            if(textView2.getText()==null){
                textView2.setVisibility(View.INVISIBLE);
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
            }else {

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay){
        FileOutputStream fos=null;

        try{
            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String content="";
            fos.write((content).getBytes());
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay){
        FileOutputStream fos=null;

        try{
            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String content=contextEditText.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }



    }


}