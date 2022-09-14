package com.example.chota.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.chota.R;
import com.example.chota.common.Common;

import java.util.ArrayList;

public class MainFragment extends Fragment implements View.OnClickListener {

    LinearLayout main2_school_set, main4_scrap, main4_mine, main7_bob, main7_schedule, main8_board, main9_counsel, main10_edu_locate, main10_edu_book;

    TextView main8_tv1, main8_tv2, main8_tv3, main8_tv4, main8_tv5, main8_tv6, main8_tv7, main8_tv8;

    GridView gridview;

    ViewPager2 main5_pager2;
    ArrayList<Main5DTO> main5_list = new ArrayList<>();
    boolean auto = true;
    public ScrollView scrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        scrollView = v.findViewById(R.id.scrollView);
        /* 메인2 이벤트 연결 등 */
        main2_school_set = v.findViewById(R.id.main2_school_set);
        main2_school_set.setOnClickListener(this);

        /* 메인3 - 주요서비스 */
        gridview = v.findViewById(R.id.gridview);
        ArrayList<Main3DTO> main3_list = new ArrayList<>();
        main3_list.add(new Main3DTO("알림장", R.drawable.main3_icon1));
        main3_list.add(new Main3DTO("가정통신문", R.drawable.main3_icon2));
        main3_list.add(new Main3DTO("급식", R.drawable.main3_icon3));
        main3_list.add(new Main3DTO("반게시판", R.drawable.main3_icon4));
        main3_list.add(new Main3DTO("우리학교", R.drawable.main3_icon5));
        main3_list.add(new Main3DTO("상담", R.drawable.main3_icon6));
        main3_list.add(new Main3DTO("톡톡톡", R.drawable.main3_icon7));
        main3_list.add(new Main3DTO("교육청", R.drawable.main3_icon8));
        Main3Adapter main3Adapter = new Main3Adapter(main3_list, inflater);
        gridview.setAdapter(main3Adapter);

        /* 메인4 스크랩, 내글보기 */
        main4_scrap = v.findViewById(R.id.main4_scrap);
        main4_mine = v.findViewById(R.id.main4_mine);
        main4_scrap.setOnClickListener(this);
        main4_mine.setOnClickListener(this);

        /* 메인5 공지사항 오토슬라이드 */
        main5_pager2 = v.findViewById(R.id.main5_pager2);
        main5_list.add(new Main5DTO("공지사항", "첫 구매 고객 최대 5,00원 할인쿠폰 증정"));
        main5_list.add(new Main5DTO("공지사항", "스토어 상품 구매하면 조건없이 3%..."));
        main5_list.add(new Main5DTO("공지사항", "최고급 스페인산 100% 석류즙 100포"));
        main5_list.add(new Main5DTO("체험", "아이엠스쿨 '체험'서비스 종료 안내 [8/15(..."));

        //슬라이드 세로로 바꿔줌
        main5_pager2.setOrientation(main5_pager2.ORIENTATION_VERTICAL);

        Main5Adapter main5Adapter = new Main5Adapter(inflater, main5_list);
        main5_pager2.setAdapter(main5Adapter);

        Common.autoSlide(auto, main5_list, main5_pager2, getActivity());

        /* 메인6 소셜 스타 */

        /* 메인7 오늘의 학교-급식,시간표 */
        main7_bob = v.findViewById(R.id.main7_bob);
        main7_schedule = v.findViewById(R.id.main7_schedule);

        /* 메인8 우리들의 수다 */
        main8_board = v.findViewById(R.id.main8_board);
        main8_tv1 = v.findViewById(R.id.main8_tv1);
        main8_tv2 = v.findViewById(R.id.main8_tv2);
        main8_tv3 = v.findViewById(R.id.main8_tv3);
        main8_tv4 = v.findViewById(R.id.main8_tv4);
        main8_tv5 = v.findViewById(R.id.main8_tv5);
        main8_tv6 = v.findViewById(R.id.main8_tv6);
        main8_tv7 = v.findViewById(R.id.main8_tv7);
        main8_tv8 = v.findViewById(R.id.main8_tv8);

        /* 메인9 선생님 상담 */
        main9_counsel = v.findViewById(R.id.main9_counsel);
        main9_counsel.setOnClickListener(this);

        /* 메인10 교육 */
        main10_edu_locate = v.findViewById(R.id.main10_edu_locate);
        main10_edu_book = v.findViewById(R.id.main10_edu_book);
        main10_edu_locate.setOnClickListener(this);
        main10_edu_book.setOnClickListener(this);

        return v;
    }

    public void autoSlide() {
        // new Handler ← SplashActivity 페이지 전환용으로 사용
        // runOnUiThread(Activity 에서만 접근 가능) ← 페이지 내부에서 디자인 바뀌는용으로 쓰레드 사용 시
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < main5_list.size(); i++) {
                    if (auto) {

                        final int value = i;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //비동기로 디자인 작업
                                main5_pager2.setCurrentItem(value);
                            }
                        });

                        try {
                            Thread.sleep(5000);
                            if (i == main5_list.size() - 1) {
                                i = -1;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        intent = new Intent(getContext(), HomeActivity.class);
        if (v.getId() == R.id.main2_school_set) {
            intent.putExtra("index", 2);
        }
        else if(v.getId() == R.id.main10_edu_locate){

            intent.putExtra("index", 3);
        }
        else if(v.getId() == R.id.main10_edu_book){
            intent.putExtra("index", 4);
        }

        startActivity(intent);
    }

    //autoSlide - 다른 화면으로 전환될 때 꺼주려고 onDestroy 활용함 Fragment니까)
    @Override
    public void onDestroy() {
        super.onDestroy();
        auto = false;
    }

}