package com.example.chota.education;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

public class Play_Adapter extends  RecyclerView.Adapter<Play_Adapter.RecHolder>{
    LayoutInflater inflater;
    ArrayList<PlayVO> list;

    public Play_Adapter(LayoutInflater inflater, ArrayList<PlayVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_play_recv, parent, false);
        return new RecHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, int position) {
        holder.hwaldong.setText(list.get(position).getHwaldong());
        holder.addr.setText(list.get(position).getAddr());
        holder.mojibstart.setText(list.get(position).getMojibstart());
        holder.mojibend.setText(list.get(position).getMojibend());
        holder.jangso.setText(list.get(position).getJangso());
        //holder.school_name_edu.setText(CommonVal.loginInf);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecHolder extends RecyclerView.ViewHolder {
        TextView hwaldong, addr, mojibstart, mojibend, jangso, school_name_edu;
        public RecHolder(@NonNull View v) {
            super(v);
            hwaldong = v.findViewById(R.id.hwaldong);
            addr = v.findViewById(R.id.addr);
            mojibstart = v.findViewById(R.id.mojibstart);
            mojibend = v.findViewById(R.id.mojibend);
            jangso = v.findViewById(R.id.jangso);
            school_name_edu = v.findViewById(R.id.school_name_edu);

        }
    }
}
