package com.example.chota.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

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
        TextView tv_title, tv_content, tv_id, tv_time, tv_read_count, tv_read_heart, tv_comment, tv_scrap;
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
            tv_read_count = v.findViewById(R.id.tv_read_count);
            tv_read_heart = v.findViewById(R.id.tv_read_heart);
            tv_comment = v.findViewById(R.id.tv_comment);
            tv_scrap = v.findViewById(R.id.tv_scrap);
            image_file = v.findViewById(R.id.image_file);
            linear_board2 = v.findViewById(R.id.linear_board2);

        }

        public void bind(@NonNull ViewHoler h, int i){

            if(list.get(i).getRead_count().length() == 3){  //&& 인기글이면
                h.card_hot.setVisibility(View.VISIBLE);
                h.card_new.setVisibility(View.GONE);
            }

            h.tv_title.setText(list.get(i).getBoard_title());
            h.tv_content.setText(list.get(i).getBoard_content());
            h.tv_id.setText(list.get(i).getMember_id());
            h.tv_time.setText(list.get(i).getBoard_time());
            h.tv_read_count.setText(list.get(i).getRead_count());
            h.tv_read_heart.setText(list.get(i).getRead_heart());
            h.tv_comment.setText(list.get(i).getComment());
            h.tv_scrap.setText(list.get(i).getScrap());
            h.image_file.setImageResource(list.get(i).getFile_id());


            //글리스트 상세조회 클릭시
            h.linear_board2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Board3Activity.class    );

                    intent.putExtra("vo", list.get(i));
                    intent.putExtra("id", list.get(i).getMember_id());
                    context.startActivity(intent);
                }
            });

        }
    }

}
