package com.example.chota.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

public class Board3_Adapter extends RecyclerView.Adapter<Board3_Adapter.ViewHoler>{
    LayoutInflater inflater;


    public Board3_Adapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Board3_Adapter.ViewHoler(inflater.inflate(R.layout.item_recycler_board3, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }



}



