package com.example.chota.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;
import com.example.chota.WriteActivity;
import com.example.chota.conn.CommonConn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class M_Board1Fragment extends Fragment {
    FloatingActionButton btn_write;
    CardView card_select, card_1, card_2, card_rank;
    RecyclerView recv_board1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_board1, container, false);

    
        card_select = v.findViewById(R.id.card_select);
        card_1 = v.findViewById(R.id.card_1);
        card_rank = v.findViewById(R.id.card_rank);
        card_2 = v.findViewById(R.id.card_2);
        btn_write = v.findViewById(R.id.btn_write);
        recv_board1 = v.findViewById(R.id.recv_board1);


        recv_select();

        //인기카드뷰 눌렀을때 상세페이지로 가기 == > 인기 클릭돼 있어야함
        card_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(intent);




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

    //스프링연동 게시판 총글 메소드
    public void recv_select(){

        CommonConn conn = new CommonConn("andlist.bo", getContext());
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult){
                    Log.d("데이터", data+"");

                    ArrayList<BoardVO> list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<BoardVO>>(){}.getType());

                    Log.d("사이즈", "리스트 : " + list.size());

                    Board1_Adapter adapter = new Board1_Adapter(getLayoutInflater(), list, getContext());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    recv_board1.setAdapter(adapter);
                    recv_board1.setLayoutManager(manager);

                }
            }
        });

    };



}