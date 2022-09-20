package com.example.chota.join;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chota.LoginActivity;
import com.example.chota.R;

public class Join3Fragment extends Fragment {
    TextView tv_change1, tv_change2;
    EditText edt_chknum1, edt_chknum2, edt_chknum3;
    Button btn_reset, btn_join;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_join3, container, false);

        tv_change1 = v.findViewById(R.id.tv_change1);
        tv_change2 = v.findViewById(R.id.tv_change2);
        edt_chknum1 = v.findViewById(R.id.edt_chknum1);
        edt_chknum2 = v.findViewById(R.id.edt_chknum2);
        edt_chknum3 = v.findViewById(R.id.edt_chknum3);
        btn_reset = v.findViewById(R.id.btn_reset);
        btn_join = v.findViewById(R.id.btn_join);


        //취소버튼 눌렀을때
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //회원가입버튼 눌렀을때
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_chknum1.getText().toString().equals("0000") && edt_chknum2.getText().toString().equals("1111")
                    && edt_chknum3.getText().toString().equals("2222") ){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Join4Fragment()).commit();

                }else {
                    Toast.makeText(getContext(), "코드를 다시 한번 확인해주세요", Toast.LENGTH_SHORT).show();

                }

            }
        });



        return v;
    }
}