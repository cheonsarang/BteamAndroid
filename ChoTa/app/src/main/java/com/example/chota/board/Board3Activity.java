package com.example.chota.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.BellActivity;
import com.example.chota.R;

import java.util.ArrayList;

public class Board3Activity extends AppCompatActivity {
    RecyclerView recv_reply;
    ImageView image_back, image_bell, image_reply_ok, image_smile, image_heart, image_scrap;
    TextView tv_title, tv_id, tv_date, tv_time, tv_read_count, tv_category, tv_content;
    LinearLayout linear_heart, linear_scrap, linear_share;
    EditText edt_reply;
    ArrayList<CommentDTO> list = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board3);

        image_back = findViewById(R.id.image_back);
        image_bell = findViewById(R.id.image_bell);
        image_heart = findViewById(R.id.image_heart);
        image_scrap = findViewById(R.id.image_scrap);
        image_reply_ok = findViewById(R.id.image_reply_ok);
        image_smile = findViewById(R.id.image_smile);


        tv_title = findViewById(R.id.tv_title);
        tv_id = findViewById(R.id.tv_id);
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        tv_read_count = findViewById(R.id.tv_read_count);
        tv_category = findViewById(R.id.tv_category);
        tv_content = findViewById(R.id.tv_content);

        linear_heart = findViewById(R.id.linear_heart);
        linear_scrap = findViewById(R.id.linear_scrap);
        linear_share = findViewById(R.id.linear_share);
        edt_reply = findViewById(R.id.edt_reply);

        recv_reply = findViewById(R.id.recv_reply);

        list.add(new CommentDTO("카페가는 시츄", "16", "수학여행 취소되고...겨우 간 건 안전체험인데 선생님들께서 10월 마지막 날에 당일치기라도 가자고 엄청 그러시긴 했는데 오늘 교장선생님 바뀌셔서 또 취소될까봐 불안...", "공감"));
        list.add(new CommentDTO("카페가는 시츄", "16", "수학여행 취소되고...겨우 간 건 안전체험인데 선생님들께서 10월 마지막 날에 당일치기라도 가자고 엄청 그러시긴 했는데 오늘 교장선생님 바뀌셔서 또 취소될까봐 불안...", "공감"));
        list.add(new CommentDTO("카페가는 시츄", "16", "수학여행 취소되고...겨우 간 건 안전체험인데 선생님들께서 10월 마지막 날에 당일치기라도 가자고 엄청 그러시긴 했는데 오늘 교장선생님 바뀌셔서 또 취소될까봐 불안...", "공감"));
        list.add(new CommentDTO("카페가는 시츄", "16", "수학여행 취소되고...겨우 간 건 안전체험인데 선생님들께서 10월 마지막 날에 당일치기라도 가자고 엄청 그러시긴 했는데 오늘 교장선생님 바뀌셔서 또 취소될까봐 불안...", "공감"));
        //댓글 보여주기
        Board3_Adapter adapter = new Board3_Adapter(getLayoutInflater(), list);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recv_reply.setLayoutManager(manager);
        recv_reply.setAdapter(adapter);

        //뒤로가기
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //벨 눌렀을때
        image_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Board3Activity.this, BellActivity.class);
                startActivity(intent);
            }
        });



        //좋아요 눌렀을때
        linear_heart.setOnClickListener(new View.OnClickListener() {
            int count = 0;

            @Override
            public void onClick(View v) {
                count++;
                if(count % 2 == 1){
                    image_heart.setImageResource(R.drawable.ic_heart_click);
                }else{
                    image_heart.setImageResource(R.drawable.ic_heart);
                }
            }
        });


        //스크랩 눌렀을때
        linear_scrap.setOnClickListener(new View.OnClickListener() {
            int count = 0;

            @Override
            public void onClick(View v) {
                count++;
                if(count % 2 == 1){
                    image_scrap.setImageResource(R.drawable.ic_scrap_click);
                }else{
                    image_scrap.setImageResource(R.drawable.ic_scrap);
                }
            }
        });

        //공유 눌렀을때
        linear_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");

                String Test_Message = tv_title.getText().toString() + tv_content.getText().toString();

                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
            }
        });



    }

}