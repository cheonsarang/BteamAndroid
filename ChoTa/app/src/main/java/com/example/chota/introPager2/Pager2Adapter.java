package com.example.chota.introPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

public class Pager2Adapter extends RecyclerView.Adapter<Pager2Adapter.ViewHolder>{
    ArrayList<Pager2DTO> list;
    LayoutInflater inflater;


    public Pager2Adapter(ArrayList<Pager2DTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_noti_pager, parent, false));
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
        ImageView imgv_pager;
        TextView tv_title1, tv_title2;
        public ViewHolder(@NonNull View v) {
            super(v);
            imgv_pager = v.findViewById(R.id.imgv_pager);
            tv_title1 = v.findViewById(R.id.tv_title1);
            tv_title2 = v.findViewById(R.id.tv_title2);

//        gif_image = (ImageView) findViewById(R.id.gif_image);
//        Glide.with(this).load(R.drawable.splash_image).into(gif_image);

        }

        public void bind(@NonNull ViewHolder h, int i){
            h.imgv_pager.setImageResource(list.get(i).getImgv_pager());
            h.tv_title1.setText(list.get(i).getTv_title1());
            h.tv_title2.setText(list.get(i).getTv_title2());


        }
    }

}
