package com.example.chota.common;

import android.app.Activity;

import androidx.viewpager2.widget.ViewPager2;

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

}
