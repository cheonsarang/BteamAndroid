package com.example.chota.myInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Stulist_Adapter extends RecyclerView.Adapter<Stulist_Adapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<MemberVO> list;

    public Stulist_Adapter(LayoutInflater inflater, ArrayList<MemberVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_stulist,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_info;
        TextView stu_name_info, nickname, ban, parent_phone, phone_num;
        public ViewHolder(@NonNull View v) {
            super(v);
            profile_info = v.findViewById(R.id.profile_info);
            stu_name_info = v.findViewById(R.id.stu_name_info);
            nickname = v.findViewById(R.id.nickname);
            ban = v.findViewById(R.id.ban);
            parent_phone = v.findViewById(R.id.parent_phone);
            phone_num = v.findViewById(R.id.phone_num);
        }
        public void bind(@NonNull ViewHolder holder, int i){
            //profile_info.setImageResource(list.get(i).getProfile().);
            stu_name_info.setText(list.get(i).getName());
            nickname.setText(list.get(i).getNickname());    //닉네임 없는 경우 처리하기
            ban.setText(list.get(i).getGrade_class_code());
            parent_phone.setText(list.get(i).getParent_phone());
        }
    }

}
