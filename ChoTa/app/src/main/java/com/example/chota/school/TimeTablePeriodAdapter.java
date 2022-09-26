package com.example.chota.school;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

public class TimeTablePeriodAdapter extends RecyclerView.Adapter<TimeTablePeriodAdapter.ViewHoler> {

    LayoutInflater inflater;
    ArrayList<TimeTablePeriodVO> list;

    public TimeTablePeriodAdapter(LayoutInflater inflater, ArrayList<TimeTablePeriodVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoler(inflater.inflate(R.layout.item_timtable_period_recv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        TextView day;
        TextView period1;
        TextView period2;
        TextView period3;
        TextView period4;
        TextView period5;
        TextView period6;


        public ViewHoler(@NonNull View v) {
            super(v);
            day = v.findViewById(R.id.timetable_0);
            period1 = v.findViewById(R.id.timetable_1);
            period2 = v.findViewById(R.id.timetable_2);
            period3 = v.findViewById(R.id.timetable_3);
            period4 = v.findViewById(R.id.timetable_4);
            period5 = v.findViewById(R.id.timetable_5);
            period6 = v.findViewById(R.id.timetable_5);

        }

        public void bind(ViewHoler holder, int position) {
            holder.day.setText(list.get(position).getDay());
            holder.period1.setText(list.get(position).getPeriod1());
            holder.period2.setText(list.get(position).getPeriod2());
            holder.period3.setText(list.get(position).getPeriod3());
            holder.period4.setText(list.get(position).getPeriod4());
            holder.period5.setText(list.get(position).getPeriod5());
            holder.period6.setText(list.get(position).getPeriod6());

        }
    }


}
