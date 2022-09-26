package com.example.chota.education;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
<<<<<<< HEAD

import com.example.chota.R;
import com.example.chota.common.CommonVal;
=======
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.example.chota.myInfo.MemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
>>>>>>> 8420268a6c97c263f54b530fb8093f9d77da044d


public class M_EduFragment extends Fragment implements View.OnClickListener{
    TextView school_name_edu, num_edu;
    CardView edu2_find1, edu2_find2, edu2_find3;
    LinearLayout edu4_school;
    MemberVO vo = CommonVal.loginInfo;
    RecyclerView container_edu;
    ArrayList<AcademyVO> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_m__edu, container, false);

        //edu1_locate = v.findViewById(R.id.edu1_locate); //위치변경
        school_name_edu = v.findViewById(R.id.school_name_edu);
        num_edu = v.findViewById(R.id.num_edu);
        edu2_find1 = v.findViewById(R.id.edu2_find1);   //교육시설
        edu2_find2 = v.findViewById(R.id.edu2_find2);   //체험활동
        edu2_find3 = v.findViewById(R.id.edu2_find3);   //추천도서
        edu4_school = v.findViewById(R.id.edu4_school); //나의 찜한시설 등록하기
        container_edu = v.findViewById(R.id.container_edu); //추천 교육시설

        edu2_find1.setOnClickListener(this);
        edu2_find2.setOnClickListener(this);
        edu2_find3.setOnClickListener(this);
        edu4_school.setOnClickListener(this);

        school_name_edu.setText(vo.getSchool_name());

        String data = new Gson().toJson(vo);
        CommonConn academyconn = new CommonConn("academylist", getContext());
        academyconn.addParams("data", data);
        academyconn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                list = new Gson().fromJson(data, new TypeToken<ArrayList<AcademyVO>>(){}.getType());
                //int count = list.size();
                DecimalFormat format = new DecimalFormat("###,###");
                num_edu.setText(String.valueOf(format.format(list.size())));
                ArrayList<AcademyVO> temp_List  = new ArrayList<>();


                Random random = new Random();
                int[] randomArr = new int[5];



                for(int i = 0; i < 5; i++) {
                    randomArr[i] = random.nextInt(100);
                }

                temp_List.add(list.get(randomArr[0]));
                temp_List.add(list.get(randomArr[1]));
                temp_List.add(list.get(randomArr[2]));
                temp_List.add(list.get(randomArr[3]));
                temp_List.add(list.get(randomArr[4]));

                //Collections.shuffle(temp_List);



                AcademyAdapter adapter = new AcademyAdapter(getLayoutInflater(), temp_List, getContext(), randomArr);
                //AcademyAdapter adapter = new AcademyAdapter(getLayoutInflater(), temp_List, getContext());
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                container_edu.setAdapter(adapter);
                container_edu.setLayoutManager(manager);



            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.edu2_find1) {  //교육시설
            intent.setClass(getContext(), AcademyActivity.class);
            intent.putExtra("vo", vo);
            Log.d("누림 교육시설", "onClick: ");

        }else if(v.getId() == R.id.edu2_find2) {    //체험활동
            intent.setClass(getContext(), PlayActivity.class);
            Log.d("누림 체험활동", "onClick: ");

        }else if(v.getId() == R.id.edu2_find3) {    //추천도서

        }else if(v.getId() == R.id.edu4_school) {
            intent.setClass(getContext(), LikeAcademyActivity.class);
            //intent.putExtra("id", CommonVal.loginInfo.getUserid());
            intent.putExtra("vo", vo);
        }
        startActivity(intent);
    }
}