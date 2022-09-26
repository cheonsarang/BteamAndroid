package com.example.chota.board;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chota.R;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Board2_Adapter extends RecyclerView.Adapter<Board2_Adapter.ViewHoler>{
    LayoutInflater inflater;
    ArrayList<BoardVO> list;
    Context context;

    public Board2_Adapter(LayoutInflater inflater, ArrayList<BoardVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
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
        CardView card_hot, card_new;
        TextView tv_title, tv_content, tv_id, tv_time, tv_readcnt, tv_read_heart, tv_comment, tv_scrap;
        ImageView image_file;
        LinearLayout linear_board2;




        public ViewHoler(@NonNull View v) {
            super(v);

            card_hot = v.findViewById(R.id.card_hot);//인기글(조회수가 50이상이면 hot visible, new gone)
            card_new = v.findViewById(R.id.card_new);//최신글(시간이 5분전이면 new visible, hot gone)

            tv_title = v.findViewById(R.id.tv_title);
            tv_content = v.findViewById(R.id.tv_content);
            tv_id = v.findViewById(R.id.tv_id);
            tv_time = v.findViewById(R.id.tv_time);
            tv_readcnt= v.findViewById(R.id.tv_readcnt);
            tv_read_heart = v.findViewById(R.id.tv_read_heart);
            tv_comment = v.findViewById(R.id.tv_comment);
            tv_scrap = v.findViewById(R.id.tv_scrap);
            image_file = v.findViewById(R.id.image_file);
            linear_board2 = v.findViewById(R.id.linear_board2);




        }

        public void bind(@NonNull ViewHoler h, int i){

//            String date = list.get(i).getWritedate();
//            Log.d(" 현재시간", System.currentTimeMillis()+"");
//            long writedate = Long.parseLong(date);
//            Log.d(" 작성시간", writedate+"");
//
//            formatTimeString(writedate);


            if(list.get(i).getReadcnt() >= 30 ){  //&& 인기글이면 100이상이면
                h.card_hot.setVisibility(View.VISIBLE);
                h.card_new.setVisibility(View.GONE);

            }

            h.tv_title.setText(list.get(i).getTitle());
            h.tv_content.setText(list.get(i).getContent());
            h.tv_id.setText(list.get(i).getWriter());
            h.tv_time.setText(list.get(i).getWritedate().substring(11, 16));
            h.tv_readcnt.setText(list.get(i).getReadcnt()+"");
            h.tv_read_heart.setText(list.get(i).getRead_heart()+"");
            h.tv_comment.setText(list.get(i).getComment_cnt()+"");
            h.tv_scrap.setText(list.get(i).getScrap_cnt()+"");


            // Glide로 이미지 표시하기
            if(list.get(i).getFile_path() == null){
                image_file.setVisibility(View.GONE);
            }else {
                String image_filepath = list.get(i).getFile_path();
                Glide.with(context).load(image_filepath).into(image_file);
                Glide.with(context).load(image_filepath)
                        .placeholder(R.drawable.ic_bell)
                        .error(R.drawable.ic_belloff)
                        .into(image_file);
            }




            //글리스트 상세조회 클릭시
            h.linear_board2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Board3Activity.class);

                    intent.putExtra("vo", list.get(i));
                    intent.putExtra("id", list.get(i).getWriter());
                    context.startActivity(intent);
                }
            });

        }
    }



    private static class TIME_MAXIMUM{
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }
    public static String formatTimeString(long regTime) {
        long curTime = System.currentTimeMillis();
        long diffTime = (curTime - regTime) / 1000;
        String msg = null;
        if (diffTime < TIME_MAXIMUM.SEC) {
            msg = "방금 전";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
            msg = (diffTime) + "달 전";
        } else {
            msg = (diffTime) + "년 전";
        }
        return msg;
    }

}
