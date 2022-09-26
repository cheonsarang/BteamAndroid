package com.example.chota.education;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chota.R;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.example.chota.myInfo.MemberVO;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

import java.io.BufferedReader;
import java.util.ArrayList;

public class AcademyActivity extends AppCompatActivity implements OnMapReadyCallback {
    NaverMap naverMap;
    BottomSheetDialog dialog;
    MapView map_view;
    ArrayList<AcademyVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy);
        map_view = findViewById(R.id.map_view);

        dialog = new BottomSheetDialog(AcademyActivity.this);
        dialog.setContentView(R.layout.btm_dilog_map);
        dialog.show();

        NaverMapSdk.getInstance(AcademyActivity.this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("cqbq53cj4b"));
        map_view.getMapAsync(this);
        Intent intent = getIntent();

        MemberVO tempVo = (MemberVO) intent.getSerializableExtra("vo");
        String data = new Gson().toJson(tempVo);
        CommonConn academyconn = new CommonConn("academylist", this);
        academyconn.addParams("data", data);
        academyconn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                list = new Gson().fromJson(data, new TypeToken<ArrayList<AcademyVO>>(){}.getType());
                RecyclerView recv_dialog = dialog.findViewById(R.id.recv_dialog);
                AcademyAdapter adapter = new AcademyAdapter(getLayoutInflater(), list, AcademyActivity.this, event);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(AcademyActivity.this, RecyclerView.VERTICAL, false);
                recv_dialog.setAdapter(adapter);
                recv_dialog.setLayoutManager(manager);
            }
        });


    }
    MapOnclickEventCSR event = new MapOnclickEventCSR() {
        @Override
        public void cameraUpdate(double lat, double lng) {
            CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(lat, lng))
                    .animate(CameraAnimation.Fly);
            naverMap.moveCamera(cameraUpdate);
            Marker marker = new Marker();
            marker.setPosition(new LatLng(lat,lng));
            marker.setMap(naverMap);
            dialog.dismiss();


        }
    };


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

          /*  현재 위치 바꾸기.
          CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.1535583, 126.8879957))
                .animate(CameraAnimation.Fly);
        naverMap.moveCamera(cameraUpdate);*/
    }


    public interface MapOnclickEventCSR {
        void cameraUpdate(double lat, double lng);
    }


}