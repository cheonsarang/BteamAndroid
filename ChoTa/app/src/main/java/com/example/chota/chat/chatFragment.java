package com.example.chota.chat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;
import com.example.chota.common.CommonVal;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


public class chatFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;

    ImageView mimageviewofuser;

    FirestoreRecyclerAdapter<firebasemodel,NoteViewHolder> chatAdapter;

    RecyclerView mrecyclerview;

    LinearLayout counsel_linear;

    Button counsel_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_chat,container,false);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        mrecyclerview=v.findViewById(R.id.recyclerview);

        counsel_linear = v.findViewById(R.id.counsel_linear);
        counsel_btn = v.findViewById(R.id.counsel_btn);

        Bundle b = getArguments();

        if (b != null) {
            String name = b.getString("name");
            Log.d("TAG", "onCreateView: " + name);
            //선생님은 목록이 뜨고 학생은 바로 상담화면 뜨게 함
            if (name.equals("counsel")&& CommonVal.loginInfo.getMember_grp().equals("S")) {
                mrecyclerview.setVisibility(View.GONE);
                counsel_linear.setVisibility(View.VISIBLE);
            }else{
                mrecyclerview.setVisibility(View.VISIBLE);
                counsel_linear.setVisibility(View.GONE);
            }
        }


        // Query query=firebaseFirestore.collection("Users");
        Query query=firebaseFirestore.collection("Users").whereNotEqualTo("uid",firebaseAuth.getUid());
        FirestoreRecyclerOptions<firebasemodel> allusername=new FirestoreRecyclerOptions.Builder<firebasemodel>().setQuery(query,firebasemodel.class).build();


        counsel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //msenderuid="O8FARjVt0YVbY2k7BUpGN008oFC2";
                //mrecieveruid="XbaT9fCh4QhGSsW64mi2AJw9oq53";
                //mrecievername="hyung won";
                Intent intent=new Intent(getActivity(), SpecificChatActivity.class);
                intent.putExtra("name","hyung won");
                intent.putExtra("receiveruid","XbaT9fCh4QhGSsW64mi2AJw9oq53");
                intent.putExtra("imageuri"," https://firebasestorage.googleapis.com/v0/b/chatapp-57272.appspot.com/o/Images%2FXbaT9fCh4QhGSsW64mi2AJw9oq53%2FProfile%20Pic?alt=media&token=7c6ae05f-4871-4a67-93fd-bc6e0cf82a5b");
                startActivity(intent);
            }
        });


        chatAdapter=new FirestoreRecyclerAdapter<firebasemodel, NoteViewHolder>(allusername) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i, @NonNull firebasemodel firebasemodel) {

                noteViewHolder.particularusername.setText(firebasemodel.getName());
                String uri=firebasemodel.getImage();

                Picasso.get().load(uri).into(mimageviewofuser);
                if(firebasemodel.getStatus().equals("Online"))
                {
                    noteViewHolder.statusofuser.setText(firebasemodel.getStatus());
                    noteViewHolder.statusofuser.setTextColor(Color.GREEN);
                }
                else
                {
                    noteViewHolder.statusofuser.setText(firebasemodel.getStatus());
                }

                noteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), SpecificChatActivity.class);
                        intent.putExtra("name",firebasemodel.getName());
                        intent.putExtra("receiveruid",firebasemodel.getUid());
                        intent.putExtra("imageuri",firebasemodel.getImage());
                        startActivity(intent);
                    }
                });




            }

            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.chatviewlayout,parent,false);
                return new NoteViewHolder(view);
            }
        };





        mrecyclerview.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerview.setLayoutManager(linearLayoutManager);
        mrecyclerview.setAdapter(chatAdapter);


        return v;




    }


    public class NoteViewHolder extends RecyclerView.ViewHolder
    {

        private TextView particularusername;
        private TextView statusofuser;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            particularusername=itemView.findViewById(R.id.nameofuser);
            statusofuser=itemView.findViewById(R.id.statusofuser);
            mimageviewofuser=itemView.findViewById(R.id.imageviewofuser);




        }
    }

    @Override
    public void onStart() {
        super.onStart();
        chatAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(chatAdapter!=null)
        {
            chatAdapter.stopListening();
        }
    }
}
