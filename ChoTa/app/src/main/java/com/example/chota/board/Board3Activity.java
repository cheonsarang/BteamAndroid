package com.example.chota.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chota.BellActivity;
import com.example.chota.R;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Board3Activity extends AppCompatActivity {
    RecyclerView recv_reply;
    ImageView image_back, image_bell, image_reply_ok, image_smile, image_heart, image_scrap, image_picture;
    TextView tv_title, tv_id, tv_date, tv_time, tv_readcnt, tv_category, tv_content;
    LinearLayout linear_heart, linear_scrap, linear_share;
    EditText edt_reply;
    ArrayList<CommentVO> list = new ArrayList<>();
    BoardVO vo;




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
        image_picture = findViewById(R.id.image_picture);


        tv_title = findViewById(R.id.tv_title);
        tv_id = findViewById(R.id.tv_id);
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        tv_readcnt = findViewById(R.id.tv_readcnt);
        tv_category = findViewById(R.id.tv_category);
        tv_content = findViewById(R.id.tv_content);

        linear_heart = findViewById(R.id.linear_heart);//좋아요
        linear_scrap = findViewById(R.id.linear_scrap);//스크랩
        linear_share = findViewById(R.id.linear_share);//공유하기


        //인텐트값 가져오기(상세보기)
        Intent intent = getIntent();
        BoardVO vo1 = (BoardVO) intent.getSerializableExtra("vo");
        CommonConn conn = new CommonConn("anddetail.bo", this);
        conn.addParams("id", vo1.getId()+"");
        Log.d("id", "onResult: " + vo1.getId()+"");
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                Log.d("디테일", "onResult: " + data);
                vo = new Gson().fromJson(data, BoardVO.class);
                setWidget(vo);
            }
        });




        edt_reply = findViewById(R.id.edt_reply);//댓글쓰기

        recv_reply = findViewById(R.id.recv_reply); //댓글리스트

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



        //좋아요 눌렀을때 (쿼리문 좋아요 넣어야함)
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


        //스크랩 눌렀을때 (쿼리문 스크랩 넣어야함)
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


    public void setWidget(BoardVO vo){
        tv_title.setText(vo.getTitle());
        tv_id.setText(vo.getWriter());
        tv_date.setText(vo.getWritedate().substring(0, 10));
        tv_time.setText(vo.getWritedate().substring(11, 16));
        tv_readcnt.setText(vo.getReadcnt()+"");
        tv_category.setText(vo.getBoard_name());
        tv_content.setText(vo.getContent());


        // String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //image_picture
        if(vo.getFile_path() == null){
            image_picture.setVisibility(View.GONE);
        }else{
            // Glide로 이미지 표시하기
            String image_filepath = vo.getFile_path();
            Glide.with(this).load(image_filepath).into(image_picture);

            Glide.with(this).load(image_filepath)
                    .placeholder(R.drawable.ic_bell)
                    .error(R.drawable.ic_belloff)
                    .into(image_picture);
        }



    }




}