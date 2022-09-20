package com.example.chota.school;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chota.R;

import java.util.ArrayList;

public class SchoolAdapter extends BaseAdapter {
    ArrayList<SchoolDTO> list;
    LayoutInflater inflater;

    public SchoolAdapter(ArrayList<SchoolDTO> list, LayoutInflater inflater) {
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
        convertView = inflater.inflate(R.layout.item_school_list, parent, false);

        TextView item_school_category, item_school_date, item_school_title, item_school_context;
        ImageView item_school_img_id;

        item_school_category = convertView.findViewById(R.id.item_school_category);
        item_school_date = convertView.findViewById(R.id.item_school_date);
        item_school_title = convertView.findViewById(R.id.item_school_title);
        item_school_context = convertView.findViewById(R.id.item_school_context);
        item_school_img_id = convertView.findViewById(R.id.item_school_img_id);

        item_school_category.setText(list.get(position).getSchool_category());
        item_school_date.setText(list.get(position).getSchool_date());
        item_school_title.setText(list.get(position).getSchool_title());
        item_school_context.setText(list.get(position).getSchool_context());
        if(list.get(position).getSchool_img_id() == 0){
            item_school_img_id.setVisibility(View.GONE);
        }else{
            item_school_img_id.setImageResource(list.get(position).getSchool_img_id());
        }

        return convertView;
    }
}
