package com.example.chota.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.chota.BoardActivity;
import com.example.chota.R;
import com.example.chota.WriteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class M_Board1Fragment extends Fragment {
    FloatingActionButton btn_write;
    LinearLayout linear_top1, linear_top2, linear_top3, linear_top4;
    CardView card_select, card_1, card_2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_board1, container, false);

        linear_top1 = v.findViewById(R.id.linear_top1);
        linear_top2 = v.findViewById(R.id.linear_top2);
        linear_top3 = v.findViewById(R.id.linear_top3);
        linear_top4 = v.findViewById(R.id.linear_top4);
        card_select = v.findViewById(R.id.card_select);
        card_1 = v.findViewById(R.id.card_1);
        card_2 = v.findViewById(R.id.card_2);
        btn_write = v.findViewById(R.id.btn_write);


        //인기 1위글 눌렀을때 상세페이지로 가기
        linear_top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getContext(), Board3Activity.class);
               startActivity(intent);


            }
        });



        //글쓰기 버튼
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);
            }
        });

        //카테고리 이미지 눌렀을때 카테고리목록으로 가기
        card_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Board2Fragment()).commit();
            }
        });




        return v;
    }
}