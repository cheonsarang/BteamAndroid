package com.example.chota.board;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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

public class Board2Fragment extends Fragment implements View.OnClickListener{
    FrameLayout frame_0, frame_1, frame_2, frame_3, frame_4, frame_5;
    CardView card_0, card_1, card_2, card_3, card_4, card_5, card_00, card_11, card_22, card_33, card_44, card_55;
    RecyclerView recv_board2;
    FloatingActionButton btn_write;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_board2, container, false);


        //카테고리 선택할것
        frame_0 = v.findViewById(R.id.frame_0); //전체
        frame_1 = v.findViewById(R.id.frame_1); //인기
        frame_2 = v.findViewById(R.id.frame_2); //자유
        frame_3 = v.findViewById(R.id.frame_3); //공부
        frame_4 = v.findViewById(R.id.frame_4); //관심
        frame_5 = v.findViewById(R.id.frame_5); //고민

        //카테고리모양
        card_0 = v.findViewById(R.id.card_0);
        card_1 = v.findViewById(R.id.card_1);
        card_2 = v.findViewById(R.id.card_2);
        card_3 = v.findViewById(R.id.card_3);
        card_4 = v.findViewById(R.id.card_4);
        card_5 = v.findViewById(R.id.card_5);

        //선택된 카테고리모양
        card_00 = v.findViewById(R.id.card_00);
        card_11 = v.findViewById(R.id.card_11);
        card_22 = v.findViewById(R.id.card_22);
        card_33 = v.findViewById(R.id.card_33);
        card_44 = v.findViewById(R.id.card_44);
        card_55 = v.findViewById(R.id.card_55);

        //리사이클러 보드
        recv_board2 = v.findViewById(R.id.recv_board2);

        //글쓰기 버튼
        btn_write = v.findViewById(R.id.btn_write);



        recv_select();//총글 메소드
        card_00.setVisibility(View.VISIBLE);
        card_0.setVisibility(View.GONE);


        //카테고리 눌렀을때
        frame_0.setOnClickListener(this);    //0.전체
        frame_1.setOnClickListener(this);    //1.인기
        frame_2.setOnClickListener(this);    //2.자유
        frame_3.setOnClickListener(this);    //3.공부
        frame_4.setOnClickListener(this);    //4.관심
        frame_5.setOnClickListener(this);    //5.고민
        btn_write.setOnClickListener(this);  //글쓰기 버튼



        return v;
    }




    @Override   //카테고리 눌렀을때
    public void onClick(View v) {
        if (v.getId() == R.id.frame_0) {
            recv_select();
            card_0.setVisibility(View.GONE);
            card_00.setVisibility(View.VISIBLE);

            card_1.setVisibility(View.VISIBLE);
            card_11.setVisibility(View.GONE);
            card_2.setVisibility(View.VISIBLE);
            card_22.setVisibility(View.GONE);
            card_3.setVisibility(View.VISIBLE);
            card_33.setVisibility(View.GONE);
            card_4.setVisibility(View.VISIBLE);
            card_44.setVisibility(View.GONE);
            card_5.setVisibility(View.VISIBLE);
            card_55.setVisibility(View.GONE);

        }else if (v.getId() == R.id.frame_1) {
            recv_select();
            card_1.setVisibility(View.GONE);
            card_11.setVisibility(View.VISIBLE);

            card_0.setVisibility(View.VISIBLE);
            card_00.setVisibility(View.GONE);
            card_2.setVisibility(View.VISIBLE);
            card_22.setVisibility(View.GONE);
            card_3.setVisibility(View.VISIBLE);
            card_33.setVisibility(View.GONE);
            card_4.setVisibility(View.VISIBLE);
            card_44.setVisibility(View.GONE);
            card_5.setVisibility(View.VISIBLE);
            card_55.setVisibility(View.GONE);

        } else if (v.getId() == R.id.frame_2) {
            recv_select(1);
            card_2.setVisibility(View.GONE);
            card_22.setVisibility(View.VISIBLE);

            card_0.setVisibility(View.VISIBLE);
            card_00.setVisibility(View.GONE);
            card_1.setVisibility(View.VISIBLE);
            card_11.setVisibility(View.GONE);
            card_3.setVisibility(View.VISIBLE);
            card_33.setVisibility(View.GONE);
            card_4.setVisibility(View.VISIBLE);
            card_44.setVisibility(View.GONE);
            card_5.setVisibility(View.VISIBLE);
            card_55.setVisibility(View.GONE);

        } else if (v.getId() == R.id.frame_3) {
            recv_select(2);
            card_3.setVisibility(View.GONE);
            card_33.setVisibility(View.VISIBLE);

            card_0.setVisibility(View.VISIBLE);
            card_00.setVisibility(View.GONE);
            card_1.setVisibility(View.VISIBLE);
            card_11.setVisibility(View.GONE);
            card_2.setVisibility(View.VISIBLE);
            card_22.setVisibility(View.GONE);
            card_4.setVisibility(View.VISIBLE);
            card_44.setVisibility(View.GONE);
            card_5.setVisibility(View.VISIBLE);
            card_55.setVisibility(View.GONE);


        } else if (v.getId() == R.id.frame_4) {
            recv_select(3);
            card_4.setVisibility(View.GONE);
            card_44.setVisibility(View.VISIBLE);

            card_0.setVisibility(View.VISIBLE);
            card_00.setVisibility(View.GONE);
            card_1.setVisibility(View.VISIBLE);
            card_11.setVisibility(View.GONE);
            card_2.setVisibility(View.VISIBLE);
            card_22.setVisibility(View.GONE);
            card_3.setVisibility(View.VISIBLE);
            card_33.setVisibility(View.GONE);
            card_5.setVisibility(View.VISIBLE);
            card_55.setVisibility(View.GONE);


        }else if (v.getId() == R.id.frame_5) {
            recv_select(4);
            card_5.setVisibility(View.GONE);
            card_55.setVisibility(View.VISIBLE);

            card_0.setVisibility(View.VISIBLE);
            card_00.setVisibility(View.GONE);
            card_1.setVisibility(View.VISIBLE);
            card_11.setVisibility(View.GONE);
            card_2.setVisibility(View.VISIBLE);
            card_22.setVisibility(View.GONE);
            card_3.setVisibility(View.VISIBLE);
            card_33.setVisibility(View.GONE);
            card_4.setVisibility(View.VISIBLE);
            card_44.setVisibility(View.GONE);

        }else if(v.getId() == R.id.btn_write){
            Intent intent = new Intent(getActivity(), WriteActivity.class);
            startActivity(intent);
        }


    }




    //스프링연동 게시판그룹별 메소드
    public void recv_select(int board_group_id){

        CommonConn conn = new CommonConn("andgrp.bo", getContext());
        conn.addParams("board_group_id", board_group_id+"");
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult){
                    Log.d("데이터", data+"");

                    ArrayList<BoardVO> list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<BoardVO>>(){}.getType());

                    Log.d("사이즈", "리스트 : " + list.size());

                    Board2_Adapter adapter = new Board2_Adapter(getLayoutInflater(), list, getContext());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    recv_board2.setAdapter(adapter);
                    recv_board2.setLayoutManager(manager);

                }
            }
        });

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

                    Board2_Adapter adapter = new Board2_Adapter(getLayoutInflater(), list, getContext());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    recv_board2.setAdapter(adapter);
                    recv_board2.setLayoutManager(manager);

                }
            }
        });

    }


}