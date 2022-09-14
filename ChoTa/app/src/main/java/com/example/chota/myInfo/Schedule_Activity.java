package com.example.chota.myInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chota.myInfo.decorator.SaturdayDecorator;
import com.example.chota.myInfo.decorator.SundayDecorator;
import com.example.chota.myInfo.decorator.TodayDecorator;
import com.example.chota.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;

public class Schedule_Activity extends AppCompatActivity {
    MaterialCalendarView calendar_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        calendar_info = findViewById(R.id.calendar_info);
        calendar_info.setSelectedDate(CalendarDay.today());
        calendar_info.addDecorator(new TodayDecorator());
        calendar_info.addDecorator(new SundayDecorator());
        calendar_info.addDecorator(new SaturdayDecorator());

        ArrayList<CalendarDay> dates = new ArrayList<>();
        //calendarView.addDecorator(new EventDecorator(Color.RED, dates, getContext()));

        calendar_info.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

            }
        });
    }
}