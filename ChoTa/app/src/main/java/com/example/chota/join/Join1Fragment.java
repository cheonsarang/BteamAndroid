package com.example.chota.join;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chota.R;


public class Join1Fragment extends Fragment {

    EditText edt_id, edt_pw, edt_pw_chk;
    TextView tv_id_chk_tv, tv_pw_chk_tv;
    Button btn_id_chk, btn_next, btn_prev;
    ImageButton imgb_kakao, imgb_naver;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_join1, container, false);

        edt_id = v.findViewById(R.id.edt_id);
        edt_pw = v.findViewById(R.id.edt_pw);
        edt_pw_chk = v.findViewById(R.id.edt_pw_chk);
        tv_id_chk_tv = v.findViewById(R.id.tv_id_chk_tv);
        tv_pw_chk_tv = v.findViewById(R.id.tv_pw_chk_tv);
        btn_id_chk = v.findViewById(R.id.btn_id_chk);
        btn_next = v.findViewById(R.id.btn_next);
        btn_prev = v.findViewById(R.id.btn_prev);


        //아이디 중복확인
        btn_id_chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idChk(edt_id);
            }
        });

        //비밀번호 확인할때
        tvPwChk(edt_pw_chk);



        //다음버튼 눌렀을때
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







                //원래코드
                if(edt_id.getText().toString().equals("") || edt_pw.getText().toString().equals("")
                        || edt_pw_chk.getText().toString().equals("") || !btn_id_chk.callOnClick() || !tvPwChk(edt_pw_chk)){
                    if(!btn_id_chk.callOnClick()){
                        Toast.makeText(getContext(), "아이디 중복체크를 확인해주세요", Toast.LENGTH_SHORT).show();
                    }else if(!tvPwChk(edt_pw_chk)){
                        Toast.makeText(getContext(), "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    }else if(edt_id.getText().toString().equals("") || edt_pw.getText().toString().equals("")
                            || edt_pw_chk.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "빈 칸을 채워주세요", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Join2Fragment()).commit();
                }
            }
        });


        //뒤로가기 버튼 눌렀을때
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });





        return v;
    }//onCreateView()

    //아이디 중복체크
    public boolean idChk(EditText id){
        if(id.getText().toString().equals("admin")) {
            tv_id_chk_tv.setVisibility(View.VISIBLE);
            tv_id_chk_tv.setText("아이디가 중복됩니다.");
            edt_id.setText("");
            return false;
        }else if(id.getText().toString().equals("")){
            tv_id_chk_tv.setVisibility(View.VISIBLE);
            tv_id_chk_tv.setText("아이디를 입력하세요");
            edt_id.setText("");
            return false;
        }else{
            tv_id_chk_tv.setVisibility(View.VISIBLE);
            tv_id_chk_tv.setText("아이디 사용이 가능합니다.");
            edt_pw.findFocus();
           return true;
        }
    }


    //비밀번호 체크
    public boolean tvPwChk(EditText pw_chk){
        if(pw_chk.getText().toString().length() > 0 && !pw_chk.getText().toString().equals(edt_pw.getText().toString())){
            tv_pw_chk_tv.setVisibility(View.VISIBLE);
            tv_pw_chk_tv.setText("비밀번호가 불일치합니다.");
            //edt_pw_chk.setText("");
            return false;

        }else if(pw_chk.getText().toString().equals(edt_pw.getText().toString())){
            //tv_pw_chk_tv.setVisibility(View.VISIBLE);
            tv_pw_chk_tv.setText("비밀번호가 일치합니다.");
            return true;
        }else if(pw_chk.getText().toString().length() == 0){
            tv_pw_chk_tv.setVisibility(View.GONE);
            return true;
        }
            return false;
    }







}