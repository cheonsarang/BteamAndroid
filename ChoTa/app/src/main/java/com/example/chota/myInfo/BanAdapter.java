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

public class BanAdapter extends RecyclerView.Adapter<BanAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<MemberVO> list;

    public BanAdapter(LayoutInflater inflater, ArrayList<MemberVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_stulist, parent, false);
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
       TextView stu_name_info, nickname, ban, phone_num, parent_phone;
        CircleImageView profile_info;
       public ViewHolder(@NonNull View v) {
           super(v);
           stu_name_info = v.findViewById(R.id.stu_name_info);
           nickname = v.findViewById(R.id.nickname);
           ban = v.findViewById(R.id.ban);
           phone_num = v.findViewById(R.id.phone_num);
           parent_phone = v.findViewById(R.id.parent_phone);
           profile_info = v.findViewById(R.id.profile_info);
       }
       public void bind(@NonNull ViewHolder holder, int i) {
            holder.stu_name_info.setText(list.get(i).getName());
            if(nickname == null) {
                holder.nickname.setText("없음");
            }else {
                holder.nickname.setText(list.get(i).getNickname());
            }
            holder.ban.setText(list.get(i).getGrade_class_code().substring(1, 2));
            holder.phone_num.setText(list.get(i).getPhone());
            holder.parent_phone.setText(list.get(i).getParent_phone());
            //holder.profile_info.setImageResource(list.get(i).getProfile());
       }
   }
}
