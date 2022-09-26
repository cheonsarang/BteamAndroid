package com.example.chota.myInfo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.chota.LoginActivity;
import com.example.chota.MainActivity;
import com.example.chota.R;
import com.example.chota.common.CommonVal;

import java.io.File;
import java.util.Objects;


public class M_MyInfoFragment_student extends Fragment implements View.OnClickListener{
    LinearLayout myinfo_info, scrap_info, write_info, event_info, revise_info, quit_info, logout_info;
    TextView student_name;
    LoginActivity loginActivity = new LoginActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_info_student, container, false);

        myinfo_info = v.findViewById(R.id.myinfo_info);
        scrap_info = v.findViewById(R.id.scrap_info);
        write_info = v.findViewById(R.id.write_info);
        event_info = v.findViewById(R.id.event_info);
        revise_info = v.findViewById(R.id.revise_info);
        quit_info = v.findViewById(R.id.quit_info);
        student_name = v.findViewById(R.id.student_name);
        logout_info = v.findViewById(R.id.logout_info);

        myinfo_info.setOnClickListener(this);
        scrap_info.setOnClickListener(this);
        write_info.setOnClickListener(this);
        event_info.setOnClickListener(this);
        revise_info.setOnClickListener(this);
        quit_info.setOnClickListener(this);
        logout_info.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.myinfo_info) {
            intent = new Intent(getContext(), InfoDetailActivity.class);
            //intent.putExtra("id", CommonVal.loginInfo.getMember_id());
        }else if(v.getId() == R.id.scrap_info || v.getId() == R.id.write_info) {
            intent = new Intent(getContext(), WritingActivity.class);
        }else if(v.getId() == R.id.event_info) {
            intent = new Intent(getContext(), EventActivity.class);
        }else if(v.getId() == R.id.logout_info) {
          //CommonVal.loginInfo = null;

       // loginActivity.saveLoginInfo();
          SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shardPreferences", Activity.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            File dir = getContext().getApplicationContext().getCacheDir();
            clearApplicationCache(dir);


          Toast.makeText(getContext(), "로그아웃되었습니다", Toast.LENGTH_SHORT).show();
          intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("로그아웃", "1");


        }
            startActivity(intent);

    }


    private void clearApplicationCache(java.io.File dir){

        if(dir==null)

            dir = getContext().getCacheDir();

        else;

        if(dir==null)

            return;

        else;

        java.io.File[] children = dir.listFiles();

        try{

            for(int i=0;i<children.length;i++)

                if(children[i].isDirectory())

                    clearApplicationCache(children[i]);

                else children[i].delete();

        }

        catch(Exception e){}

    }




    @Override
    public void onDestroy() {

        super.onDestroy();

        clearApplicationCache(null);

    }
}