package com.example.chota.school;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chota.R;

import java.util.ArrayList;

public class School3Adapter extends BaseAdapter {
    ArrayList<SchoolpostVO> list;
    LayoutInflater inflater;
    String category;

    public School3Adapter(ArrayList<SchoolpostVO> list, LayoutInflater inflater, String category) {
        this.list = list;
        this.inflater = inflater;
        this.category = category;
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

        convertView = inflater.inflate(R.layout.item_schoolpost_home_list, parent, false);

        TextView schoolpost_text;
        schoolpost_text = convertView.findViewById(R.id.schoolpost_text);

        if (list.get(position).getTitle() != null) {
            schoolpost_text.setText(list.get(position).getTitle());
        }else{
            schoolpost_text.setText(list.get(position).getContext());
        }

        return convertView;
    }
}
