package com.example.chota.myInfo.decorator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private int color;
    private HashSet<CalendarDay> dates;

    public EventDecorator(int color, Collection<CalendarDay> dates, Activity context) {
        drawable = context.getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_up_24);
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        //return true;
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(10, Color.BLUE)); // 날자밑에 점
    }
}
