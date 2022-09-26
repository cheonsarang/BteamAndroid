package com.example.chota.join;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.chota.LoginActivity;
import com.example.chota.R;

public class Join4Fragment extends Fragment {
    TextView tv_id, tv_name, tv_email;
    Button btn_login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_join4, container, false);

        tv_id = v.findViewById(R.id.tv_id);
        tv_name = v.findViewById(R.id.tv_name);
        tv_email = v.findViewById(R.id.tv_email);
        btn_login = v.findViewById(R.id.btn_login);

//        tv_id.setText();
//        tv_name.setText();
//        tv_email.setText();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }
}