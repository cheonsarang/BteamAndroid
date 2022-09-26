package com.example.chota.myInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

public class MyInfo_Adapter extends RecyclerView.Adapter<MyInfo_Adapter.ViewHolder>{
    ArrayList<WritingVO> scrap_list;
    LayoutInflater inflater;

    public MyInfo_Adapter(ArrayList<WritingVO> scrap_list, LayoutInflater inflater) {
        this.scrap_list = scrap_list;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_rec_board, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return scrap_list.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView board_name, writer, writedate, board_content, read_heart, comment_cnt, title_write;
        public ViewHolder(@NonNull View v) {
            super(v);
            board_name = v.findViewById(R.id.board_name);
            writer = v.findViewById(R.id.writer);
            writedate = v.findViewById(R.id.writedate);
            board_content = v.findViewById(R.id.board_content);
            read_heart = v.findViewById(R.id.read_heart);
            comment_cnt = v.findViewById(R.id.comment_cnt);
            title_write = v.findViewById(R.id.title_write);

        }
        public void bind(@NonNull ViewHolder holder, int position) {
            holder.board_name.setText(scrap_list.get(position).getBoard_name());
            holder.writer.setText(scrap_list.get(position).getWriter());
            holder.writedate.setText(scrap_list.get(position).getWritedate());
            holder.board_content.setText(scrap_list.get(position).getBoard_content());
            holder.read_heart.setText(scrap_list.get(position).getRead_heart());
            holder.comment_cnt.setText(scrap_list.get(position).getComment_cnt());

        }
    }
}
