package com.example.chota.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chota.BoardActivity;
import com.example.chota.R;

public class Board3Fragment extends Fragment {
    RecyclerView recv_reply;
    ImageView image_back, image_bell, image_more, image_reply_ok, image_smile;
    TextView tv_title, tv_id, tv_date, tv_time, tv_view;
    LinearLayout linear_good, linear_save, linear_share;
    EditText edt_reply;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_board3, container, false);

        image_back = v.findViewById(R.id.img_back);
        image_bell = v.findViewById(R.id.image_bell);
        image_more = v.findViewById(R.id.image_more);
        image_reply_ok = v.findViewById(R.id.image_reply_ok);
        image_smile = v.findViewById(R.id.image_smile);
        tv_title = v.findViewById(R.id.tv_title);
        tv_id = v.findViewById(R.id.tv_id);
        tv_date = v.findViewById(R.id.tv_date);
        tv_time = v.findViewById(R.id.tv_time);
        tv_view = v.findViewById(R.id.tv_view);
        linear_good = v.findViewById(R.id.linear_good);
        linear_save = v.findViewById(R.id.linear_save);
        linear_share = v.findViewById(R.id.linear_share);
        edt_reply = v.findViewById(R.id.edt_reply);

        recv_reply = v.findViewById(R.id.recv_reply);


        Board3_Adapter adapter = new Board3_Adapter(inflater);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recv_reply.setLayoutManager(manager);
        recv_reply.setAdapter(adapter);






        //뒤로가기
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardActivity.class);
                startActivity(intent);
            }
        });

        //벨 눌렀을때




        return v;
    }
}