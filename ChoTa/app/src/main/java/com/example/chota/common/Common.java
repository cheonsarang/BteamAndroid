package com.example.chota.common;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.viewpager2.widget.ViewPager2;

import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Common {
    public static void autoSlide(Boolean auto, ArrayList list, ViewPager2 pager2, Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    if (auto) {

                        final int value = i;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //비동기로 디자인 작업
                                pager2.setCurrentItem(value);
                            }
                        });

                        try {
                            Thread.sleep(5000);
                            if (i == list.size() - 1) {
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

        return;
    }


    public ArrayList<SchoolpostVO> searchSc(String category, Context context){
        ArrayList<SchoolpostVO> list = new ArrayList<>();
        CommonConn conn = new CommonConn("list.sc", context);

        Log.d("print", "searchSc: " + category);

        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult){
                    Log.d("데이터", "onResult: " + data);
                    ArrayList<SchoolpostVO> school_list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<SchoolpostVO>>(){
                            }.getType());
                    Log.d("데이터 리스트 길이 출력", "onResult: " + school_list.size());
                    //ArrayList<SchoolpostVO> list = new ArrayList<>();
                    for (int i = 0; i < school_list.size(); i++) {
                        if( school_list.get(i).getCategory().equals(category) ){
                            list.add(school_list.get(i));
                        }else{
                        }
                    }
                    Log.d("print", "searchSc: " + list.size());
                    return;
                }
            }
        });
        return list;
    }
}
