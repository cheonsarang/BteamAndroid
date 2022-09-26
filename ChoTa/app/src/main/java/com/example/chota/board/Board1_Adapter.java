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

import com.bumptech.glide.Glide;
import com.example.chota.R;

import java.util.ArrayList;

public class Board1_Adapter extends RecyclerView.Adapter<Board1_Adapter.ViewHoler>{
    LayoutInflater inflater;
    ArrayList<BoardVO> list;
    Context context;

    public Board1_Adapter(LayoutInflater inflater, ArrayList<BoardVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHoler(inflater.inflate(R.layout.item_recycler_board1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        LinearLayout linear_board1;
        TextView tv_rank, tv_title, tv_content, tv_readcnt, tv_comment_cnt, tv_read_heart;
        ImageView image_picture;



        public ViewHoler(@NonNull View v) {
            super(v);


            linear_board1 = v.findViewById(R.id.linear_board1);//글
            tv_rank = v.findViewById(R.id.tv_rank);//순위
            tv_title = v.findViewById(R.id.tv_title);
            tv_content = v.findViewById(R.id.tv_content);
            tv_readcnt= v.findViewById(R.id.tv_readcnt);
            tv_comment_cnt = v.findViewById(R.id.tv_comment_cnt);
            tv_read_heart = v.findViewById(R.id.tv_read_heart);
            image_picture = v.findViewById(R.id.image_picture);


        }

        public void bind(@NonNull ViewHoler h, int i){


            h.tv_rank.setText(list.get(i).getNo()+"");
            h.tv_title.setText(list.get(i).getTitle());
            h.tv_content.setText(list.get(i).getContent());
            h.tv_readcnt.setText(list.get(i).getReadcnt()+"");
            h.tv_comment_cnt.setText(list.get(i).getComment_cnt()+"");
            h.tv_read_heart.setText(list.get(i).getRead_heart()+"");
//            h.image_picture.setImageResource(list.get(i).getFilepath());


            // Glide로 이미지 표시하기
            if(list.get(i).getFile_path() == null){
                image_picture.setVisibility(View.GONE);
            }else {
                String image_filepath = list.get(i).getFile_path();
                Glide.with(context).load(image_filepath).into(image_picture);
                Glide.with(context).load(image_filepath)
                        .placeholder(R.drawable.ic_bell)
                        .error(R.drawable.ic_belloff)
                        .into(image_picture);
            }




            //글리스트 상세조회 클릭시
            h.linear_board1.setOnClickListener(new View.OnClickListener() {
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

}
