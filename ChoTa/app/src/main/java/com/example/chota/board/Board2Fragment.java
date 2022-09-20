package com.example.chota.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chota.R;

import java.util.ArrayList;

public class Board2Fragment extends Fragment {
    RecyclerView recv_commu2;
    ArrayList<Board2DTO> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_board2, container, false);

        list.add(new Board2DTO("너네는 학교 교칙중에", "이해 안가는거 있음?","정교한애벌레 · 약 1시간전 · 조회 112회", "19", "3", R.drawable.image_pocket));
        list.add(new Board2DTO("고등학생분들은 학원 안 다니는 분들 많이...", "중3이고 곧 고등학교 가는데","안경쓴오징어 · 약 2시간전 · 조회 136회", "11", "1", R.drawable.image_file1));
        list.add(new Board2DTO("오늘 친구집 간다", "기대된당ㅎㅎㅎ","자랑스러운비버 · 약 3시간전 · 조회 548", "29", "1", R.drawable.image_pocket));
        list.add(new Board2DTO("모기 알레르기", ".","점잖은당나귀 · 28분전 · 조회 1회", "댓글", "", R.drawable.image_file1));
        list.add(new Board2DTO("사회생활 잘 하는법!", ".","난리치는 도마뱀 · 29분전 · 조회 2회", "1", "1", R.drawable.image_file1));
        list.add(new Board2DTO("닉 그려줄게", "마음에 드는 애는 5명 뽑아서 저녁까지 올릴게","초롱한호랑이 · 39분전 · 조회 10회", "2", "", R.drawable.image_pocket));
        list.add(new Board2DTO("담주 날씨 실환가?", "정녕 8월이 맞는가?","새로운비버 · 44분전 · 조회 22회", "", "", R.drawable.image_file1));
        list.add(new Board2DTO("학교에서 교칙과의 전쟁시작한지 수시간째", ".","밤새는달랑게 · 약 1시간전 · 조회 5회", "1", "2", R.drawable.image_file1));
        list.add(new Board2DTO("모의 판매전략 세오는데 상품 추천 좀", "자율동아리에서 각자 판매전략...","어메이징한소 · 6분전 · 조회 2회", "댓글", "", R.drawable.image_pocket));
        list.add(new Board2DTO("얘들아", "뭐해?","오학만하는 고릴라 · 15분전 · 조회 2회", "공감", "", R.drawable.image_file1));

        recv_commu2 = v.findViewById(R.id.recv_board2);

        Board2_Adapter adapter = new Board2_Adapter(inflater, list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recv_commu2.setLayoutManager(manager);
        recv_commu2.setAdapter(adapter);

        return v;
    }
}