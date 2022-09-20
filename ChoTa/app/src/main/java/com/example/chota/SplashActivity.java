package com.example.chota;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {
    ImageView gif_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gif_image = (ImageView) findViewById(R.id.gif_image);
        Glide.with(this).load(R.drawable.splash_image).into(gif_image);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(main);
                finish();
            }
        }, 5000); // 5초 후(5000) 스플래시 화면을 닫습니다 (보통은 3초 3000)






    }
}