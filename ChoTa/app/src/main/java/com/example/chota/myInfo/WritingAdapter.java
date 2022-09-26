package com.example.chota.myInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

public class WritingAdapter extends RecyclerView.Adapter<WritingAdapter.classHolder>{
    LayoutInflater inflater;

    @NonNull
    @Override
    public classHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_rec_board, parent, false);
        return new classHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull classHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class classHolder extends RecyclerView.ViewHolder {
        TextView title, board_name, writer, writedate, board_content, read_heart, comment_cnt, school_name_edu;
        public classHolder(@NonNull View v) {
            super(v);
            title = v.findViewById(R.id.title);
            board_name = v.findViewById(R.id.board_name);
            writer = v.findViewById(R.id.writer);
            writedate = v.findViewById(R.id.writedate);
            board_content = v.findViewById(R.id.board_content);
            read_heart = v.findViewById(R.id.read_heart);
            comment_cnt = v.findViewById(R.id.comment_cnt);
            school_name_edu = v.findViewById(R.id.school_name_edu);



        }
    }
}
