package com.example.chota.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.example.chota.R;

public class FindPw1Fragment extends Fragment {
    FrameLayout frame;
    EditText edt_phone;
    Button btn_send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_find_pw1, container, false);

        container = v.findViewById(R.id.container);
        frame = v.findViewById(R.id.frame);
        edt_phone = v.findViewById(R.id.edt_phone);
        btn_send = v.findViewById(R.id.btn_send);





        /*전송버튼 눌렀을때 */
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame.setVisibility(View.GONE);
                getChildFragmentManager().beginTransaction().replace(R.id.container, new FindPw2Fragment()).commit();


            }
        });


        return v;
    }
}