package com.example.chota.find;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chota.R;
import com.google.android.material.textfield.TextInputEditText;


public class FindId1Fragment extends Fragment {
    String[] emailList = { "직접입력", "naver.com", "kakao.com", "hanmail.net", "google.com"};
    Spinner email_spinner;
    TextInputEditText edt_name, edt_birth, edt_email;
    Button btn_ok1, btn_ok2;
    FrameLayout frame;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_find_id1, container, false);

        edt_name = v.findViewById(R.id.edt_name);
        edt_birth = v.findViewById(R.id.edt_birth);
        edt_email = v.findViewById(R.id.edt_email);
        btn_ok1 = v.findViewById(R.id.btn_ok1);
        btn_ok2 = v.findViewById(R.id.btn_ok2);
        frame = v.findViewById(R.id.frame);


        email_spinner = (Spinner) v.findViewById(R.id.planets_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, emailList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        email_spinner.setAdapter(adapter);
        email_spinner.setSelection(0);

        email_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //선택됬을때, 직접입력했을때 어떻게 비워질것인지 어떻게 저장할것인지
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                email_spinner.setSelection(0);
            }
        });



        //빈칸이 채워졌을때 버튼색바꾸기
        if(!edt_name.equals(null) && !edt_birth.equals(null) && !edt_email.equals(null) && email_spinner.callOnClick()){
            btn_ok1.setVisibility(View.GONE);
            btn_ok2.setVisibility(View.VISIBLE);
        }

        //확인 버튼 눌렀을때
        btn_ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    frame.setVisibility(View.GONE);
                    getChildFragmentManager().beginTransaction().replace(R.id.container, new FindId2Fragment()).commit();

            }
        });



        return v;

    }
}





// spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    //@Override
    //public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    //        if(position==0 || position==1){
    //        home_in_recv.smoothScrollToPosition(0);
    //        }else if(position==2){
    //        home_in_recv.smoothScrollToPosition(3);
    //        }else if(position==3){
    //        home_in_recv.smoothScrollToPosition(6);
    //        }else if(position==4){
    //        home_in_recv.smoothScrollToPosition(9);
    //        }else if(position==5){
    //        home_in_recv.smoothScrollToPosition(12);
    //        }else if(position==6){
    //        home_in_recv.smoothScrollToPosition(15);
    //        }
    //        adapter_h.notifyDataSetChanged();
    //        }
    //
    //@Override
    //public void onNothingSelected(AdapterView<?> parent) {
    //
    //        }
    //        });