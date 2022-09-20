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

public class Board3_Adapter extends RecyclerView.Adapter<Board3_Adapter.ViewHoler>{
    LayoutInflater inflater;
    ArrayList<CommentDTO> list;
    CommentDTO dto;

    public Board3_Adapter(LayoutInflater inflater, ArrayList<CommentDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoler(inflater.inflate(R.layout.item_recycler_board3, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView tv_comment_id, tv_comment_time, tv_comment_content, tv_heart;
        ImageView image_heart;
        int count = 0;

        public ViewHoler(@NonNull View v) {
            super(v);

            tv_comment_id = v.findViewById(R.id.tv_comment_id);
            tv_comment_time = v.findViewById(R.id.tv_comment_time);
            tv_comment_content = v.findViewById(R.id.tv_comment_content);
            tv_heart = v.findViewById(R.id.tv_heart);
            image_heart = v.findViewById(R.id.image_heart);


            image_heart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    count++;
                    if(count % 2 == 1) {
                        image_heart.setImageResource(R.drawable.ic_heart_click);
                    }else{
                        image_heart.setImageResource(R.drawable.ic_heart);
                    }
                }
            });


        }

        public void bind(@NonNull ViewHoler h, int i){
            h.tv_comment_id.setText(list.get(i).getComment_id());
            h.tv_comment_time.setText(list.get(i).getComment_time());
            h.tv_comment_content.setText(list.get(i).getComment_content());
            h.tv_heart.setText(list.get(i).getComment_heart());

            if(list.get(i).getComment_heart().length() > 0){
                h.tv_heart.setText(list.get(i).getComment_heart());
            }else{
                h.tv_heart.setText("공감");
            }

        }


    }



}



