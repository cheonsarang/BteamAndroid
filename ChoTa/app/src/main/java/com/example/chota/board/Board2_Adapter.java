package com.example.chota.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

public class Board2_Adapter extends RecyclerView.Adapter<Board2_Adapter.ViewHoler>{
    LayoutInflater inflater;
    ArrayList<Board2DTO> list;

    public Board2_Adapter(LayoutInflater inflater, ArrayList<Board2DTO> list) {
        this.inflater = inflater;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        return new ViewHoler(inflater.inflate(R.layout.item_recycler_board2, parent, false));
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
        TextView tv_title1, tv_title2, tv_id, tv_speak, tv_scrap;
        ImageView image;



        public ViewHoler(@NonNull View v) {
            super(v);

            tv_title1 = v.findViewById(R.id.tv_title1);
            tv_title2 = v.findViewById(R.id.tv_title2);
            tv_id = v.findViewById(R.id.tv_id);
            tv_speak = v.findViewById(R.id.tv_speak);
            tv_scrap = v.findViewById(R.id.tv_scrap);
            image = v.findViewById(R.id.image);

        }

        public void bind(@NonNull ViewHoler h, int i){

            h.tv_title1.setText(list.get(i).getTv_title1());
            h.tv_title2.setText(list.get(i).getTv_title2());
            h.tv_id.setText(list.get(i).getTv_id());
            h.tv_speak.setText(list.get(i).getTv_speak());
            h.tv_scrap.setText(list.get(i).getTv_scrap());
            h.image.setImageResource(list.get(i).getImage());

        }
    }

}
