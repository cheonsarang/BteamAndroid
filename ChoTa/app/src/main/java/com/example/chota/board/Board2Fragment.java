package com.example.chota.board;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chota.R;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Board2Fragment extends Fragment implements View.OnClickListener{
    CardView card_1, card_2, card_3, card_4, card_5, card_6;
    RecyclerView recv_board2;
    ArrayList<BoardVO> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_board2, container, false);

        list.add(new BoardVO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레","1","112", "19", "3", "1", R.drawable.image_pocket));
        list.add(new BoardVO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레","1","112", "19", "3", "1", R.drawable.image_pocket));
        list.add(new BoardVO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레","1","112", "19", "3", "1", R.drawable.image_pocket));
        list.add(new BoardVO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레","1","112", "19", "3", "1", R.drawable.image_pocket));
        list.add(new BoardVO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레","1","112", "19", "3", "1", R.drawable.image_pocket));
        list.add(new BoardVO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레","1","112", "19", "3", "1", R.drawable.image_pocket));
        list.add(new BoardVO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레","1","112", "19", "3", "1", R.drawable.image_pocket));
        list.add(new BoardVO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레","1","112", "19", "3", "1", R.drawable.image_pocket));

        //카테고리
        card_1 = v.findViewById(R.id.card_1);
        card_2 = v.findViewById(R.id.card_2);
        card_3 = v.findViewById(R.id.card_3);
        card_4 = v.findViewById(R.id.card_4);
        card_5 = v.findViewById(R.id.card_5);
        card_6 = v.findViewById(R.id.card_6);

        //리사이클러 보드
        recv_board2 = v.findViewById(R.id.recv_board2);

        //임시데이터 연동
        Board2_Adapter adapter = new Board2_Adapter(inflater, list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recv_board2.setLayoutManager(manager);
        recv_board2.setAdapter(adapter);








        //카테고리 눌렀을때
        card_1.setOnClickListener(this);    //1.자유
        card_2.setOnClickListener(this);    //2.공부
        card_3.setOnClickListener(this);    //3.관심
        card_4.setOnClickListener(this);    //4.고민
        card_5.setOnClickListener(this);    //5.학교
        card_6.setOnClickListener(this);    //6.반



        return v;
    }



    @SuppressLint("ResourceAsColor")
    @Override   //카테고리 눌렀을때
    public void onClick(View v) {

        if(v.getId() == R.id.card_1){
            recv_select(1);
            card_1.setCardBackgroundColor(R.color.chota_blue);
        }else if(v.getId() == R.id.card_2){
            recv_select(2);
            card_2.setCardBackgroundColor(R.color.chota_blue);
        }else if(v.getId() == R.id.card_3){
            recv_select(3);
            card_3.setCardBackgroundColor(R.color.chota_blue);
        }else if(v.getId() == R.id.card_4){
            recv_select(4);
            card_4.setCardBackgroundColor(R.color.chota_blue);
        }else if(v.getId() == R.id.card_5){
            recv_select(5);
            card_5.setCardBackgroundColor(R.color.chota_blue);
        }else if(v.getId() == R.id.card_6){
            recv_select(6);
            card_6.setCardBackgroundColor(R.color.chota_blue);
        }


    }




    //스프링연동 게시판 메소드
    public void recv_select(int grp_id){

        CommonConn conn = new CommonConn("boardlist", getContext());
        conn.addParams("select", grp_id+"");
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