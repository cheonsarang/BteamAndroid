package com.example.chota.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.chota.R;
import com.example.chota.WriteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class M_BoardFragment extends Fragment {
    FloatingActionButton btn_write;
    LinearLayout linear_1, linear_2, linear_3, linear_4;
    CardView card_5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_board1, container, false);

        btn_write = v.findViewById(R.id.btn_write);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), WriteActivity.class);
                    startActivity(intent);
            }
        });




        return v;
    }
}