package com.example.chota.education;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    RecyclerView recv_play;
    EditText searchBar;
    ArrayList<PlayVO> list;
    ArrayList<PlayVO> arrayList = new ArrayList<>();
    Play_Adapter adapter;
    ImageView search_play, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        recv_play = findViewById(R.id.recv_play);
        searchBar = findViewById(R.id.searchBar);
        search_play = findViewById(R.id.search_play);
        back = findViewById(R.id.back);

        recv_play();
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        search_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = searchBar.getText().toString();
                search(text);
            }
        });
    }
    public void search(String charText) {
        if(charText.length() == 0) {
            list.addAll(arrayList);
        }else {
            arrayList.addAll(list);
            list.clear();
            for (int i = 0; i < arrayList.size(); i++) {
                //search_play.setVisibility(View.VISIBLE);
                if(arrayList.get(i).getAddr().contains(charText) || arrayList.get(i).getHwaldong().contains(charText) || arrayList.get(i).getJangso().contains(charText)) {
                    list.add(arrayList.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void recv_play() {
        String data = new Gson().toJson("jarr");
        CommonConn playConn = new CommonConn("playlist", this);
        playConn.addParams("jarr", data);
        playConn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult) {
                    list = new Gson().fromJson(data, new TypeToken<ArrayList<PlayVO>>(){}.getType());
                    adapter = new Play_Adapter(getLayoutInflater(), list);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(PlayActivity.this, RecyclerView.VERTICAL, false);
                    recv_play.setLayoutManager(manager);
                    recv_play.setAdapter(adapter);
                }
            }
        });
    }

}