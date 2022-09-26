package com.example.chota.school;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHoler> {

    LayoutInflater inflater;
    ArrayList<TimeTableRecvVO> list;

    public TimeTableAdapter(LayoutInflater inflater, ArrayList<TimeTableRecvVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoler(inflater.inflate(R.layout.item_timetable_recv, parent, false));
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

        TextView period;
        TextView mon;
        TextView tue;
        TextView wed;
        TextView thu;
        TextView fri;

        public ViewHoler(@NonNull View v) {
            super(v);
            period = v.findViewById(R.id.timetable_0);
            mon = v.findViewById(R.id.timetable_1);
            tue = v.findViewById(R.id.timetable_2);
            wed = v.findViewById(R.id.timetable_3);
            thu = v.findViewById(R.id.timetable_4);
            fri = v.findViewById(R.id.timetable_5);

        }

        public void bind(ViewHoler holder, int position) {
            holder.period.setText(list.get(position).getPeriod());
            holder.mon.setText(list.get(position).getMon());
            holder.tue.setText(list.get(position).getTue());
            holder.wed.setText(list.get(position).getWed());
            holder.thu.setText(list.get(position).getThu());
            holder.fri.setText(list.get(position).getFri());

        }
    }


}
