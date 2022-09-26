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

public class SchoolMealsAdapter extends BaseAdapter {

    ArrayList<SchoolMealsVO> list;
    LayoutInflater inflater;

    SimpleDateFormat apisd = new SimpleDateFormat("yyyyMMdd");
    DateFormat format = new SimpleDateFormat("M월 d일 E요일");

    public SchoolMealsAdapter(ArrayList<SchoolMealsVO> list, LayoutInflater inflater) {
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
        convertView = inflater.inflate(R.layout.item_schoolpost_list, parent, false);

        TextView item_school_category, item_school_date, item_school_title, item_school_context;
        ImageView item_school_img_id;

        item_school_category = convertView.findViewById(R.id.item_school_category);
        item_school_date = convertView.findViewById(R.id.item_school_date);
        item_school_title = convertView.findViewById(R.id.item_school_title);
        item_school_context = convertView.findViewById(R.id.item_school_context);
        item_school_img_id = convertView.findViewById(R.id.item_school_img_id);

        item_school_category.setText("급식");
        item_school_context.setText(list.get(position).getDish_name());
        item_school_img_id.setVisibility(View.GONE);

        try {
            Date date = apisd.parse(list.get(position).getDish_date());
            format.format(date);
            Log.d("TAG", "getView: " + format.format(date).toString() );
            item_school_date.setText(format.format(date).toString() );
        }catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }
}
