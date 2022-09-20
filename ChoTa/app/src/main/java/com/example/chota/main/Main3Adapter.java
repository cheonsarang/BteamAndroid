package com.example.chota.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chota.R;

import java.util.ArrayList;

public class Main3Adapter extends BaseAdapter {

    ArrayList<Main3DTO> list;
    LayoutInflater inflater;

    public Main3Adapter(ArrayList<Main3DTO> list, LayoutInflater inflater) {
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
        convertView = inflater.inflate(R.layout.item_main3_grid, parent, false);

        GridViewHolder viewHolder = new GridViewHolder(convertView);
        viewHolder.bind(list.get(position));

        return convertView;
    }

    public class GridViewHolder{

        ImageView img_id;
        TextView tv_title;

        View view;

        public GridViewHolder(View view) {
            img_id = view.findViewById(R.id.img_id);
            tv_title =view.findViewById(R.id.tv_title);
        }

        public void bind(Main3DTO dto){
            img_id.setImageResource(dto.getImg_id());
            tv_title.setText(dto.getTitle());
        }
    }
}

