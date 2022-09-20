package com.example.chota.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

public class Main5Adapter extends RecyclerView.Adapter<Main5Adapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Main5DTO> list;

    public Main5Adapter(LayoutInflater inflater, ArrayList<Main5DTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(inflater.inflate(R.layout.item_main5_pager, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView main5_tv_1, main5_tv_2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main5_tv_1 = itemView.findViewById(R.id.main5_tv_1);
            main5_tv_2 = itemView.findViewById(R.id.main5_tv_2);
        }

        public void bind(@NonNull ViewHolder holder, int position){
            holder.main5_tv_1.setText(list.get(position).getMain5_1());
            holder.main5_tv_2.setText(list.get(position).getMain5_2());
        }
    }
}

