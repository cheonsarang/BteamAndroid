package com.example.chota.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.chota.LoginActivity;
import com.example.chota.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class FindPw3Fragment extends Fragment {

    EditText edt_pw, edt_pw_chk;
    Button btn_next;
    FrameLayout frame;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_find_pw3, container, false);

        edt_pw = v.findViewById(R.id.edt_pw);
        edt_pw_chk = v.findViewById(R.id.edt_pw_chk);
        btn_next = v.findViewById(R.id.btn_next);
        frame = v.findViewById(R.id.frame);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_pw.getText().toString().equals(edt_pw_chk.getText().toString()) || edt_pw.getText().toString().equals("") || edt_pw_chk.getText().equals("")){
                   if(!edt_pw.getText().toString().equals(edt_pw_chk.getText().toString())){
                       Toast.makeText(getContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();

                    }else if(edt_pw.getText().toString().equals("") || edt_pw_chk.getText().equals("") ) {
                       Toast.makeText(getContext(), "빈 칸을 채워주세요", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Snackbar.make(v, "비밀번호가 성공적으로 변경되었습니다!", BaseTransientBottomBar.LENGTH_LONG).setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }).show();




                }
            }
        });


        return v;
    }
}