package com.example.chota.school;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chota.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SchoolpostAdapter extends BaseAdapter {
    ArrayList<SchoolpostVO> list;
    LayoutInflater inflater;
    String category;

    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat format = new SimpleDateFormat("M월 d일 E요일");

    public SchoolpostAdapter(ArrayList<SchoolpostVO> list, LayoutInflater inflater, String category) {
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
        convertView = inflater.inflate(R.layout.item_schoolpost_list, parent, false);

        TextView item_school_category, item_school_date, item_school_title, item_school_context;
        ImageView item_school_img_id;

        item_school_category = convertView.findViewById(R.id.item_school_category);
        item_school_date = convertView.findViewById(R.id.item_school_date);
        item_school_title = convertView.findViewById(R.id.item_school_title);
        item_school_context = convertView.findViewById(R.id.item_school_context);
        item_school_img_id = convertView.findViewById(R.id.item_school_img_id);

        item_school_category.setText(list.get(position).getCategory());
        item_school_title.setText(list.get(position).getTitle());
        item_school_context.setText(list.get(position).getContext());

        if (list.get(position).getYmd() != null) {
            try {

                Date date = sd.parse(list.get(position).getYmd());
                format.format(date);
                Log.d("TAG", "getView: " + format.format(date).toString() );
                item_school_date.setText(format.format(date).toString() );
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (list.get(position).getYmd() == null) {
            item_school_date.setText(list.get(position).getWritedate());
        }


        if (list.get(position).getSchoolpost_img_id() == 0) {
            item_school_img_id.setVisibility(View.GONE);
        } else {
            item_school_img_id.setImageResource(list.get(position).getSchoolpost_img_id());
        }

        return convertView;
    }
}
