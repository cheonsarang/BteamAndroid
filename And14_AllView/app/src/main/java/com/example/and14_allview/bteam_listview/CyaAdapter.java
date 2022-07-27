package com.example.and14_allview.bteam_listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.and14_allview.R;

import java.util.ArrayList;

public class CyaAdapter extends BaseAdapter {
    ArrayList<CyaDTO> list;
    LayoutInflater inflater;

    public CyaAdapter(ArrayList<CyaDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_cyaview, parent, false);


        ImageView img_id = convertView.findViewById(R.id.img_id);
        img_id.setImageResource(list.get(position).getImg_id());

        ImageView user_id_img = convertView.findViewById(R.id.user_id_img);
        user_id_img.setImageResource(list.get(position).user_id_img);

        TextView title = convertView.findViewById(R.id.title);
        title.setText(list.get(position).getTitle());

        TextView user_name = convertView.findViewById(R.id.user_name);
        user_name.setText(list.get(position).getUser_name());

        TextView count = convertView.findViewById(R.id.count);
        count.setText(list.get(position).getCount()+"");

        TextView time = convertView.findViewById(R.id.time);
        time.setText(list.get(position).getTime());

        return convertView;
    }
}
