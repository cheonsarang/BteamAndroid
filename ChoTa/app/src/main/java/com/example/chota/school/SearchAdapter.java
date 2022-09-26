package com.example.chota.school;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHoler> {

    ArrayList<SchoolpostVO> list;
    LayoutInflater inflater;

    public SearchAdapter(ArrayList<SchoolpostVO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoler(inflater.inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        TextView search_category_tv;
        TextView search_text_tv;

        public ViewHoler(@NonNull View v) {
            super(v);
            search_category_tv = v.findViewById(R.id.search_category_tv);
            search_text_tv = v.findViewById(R.id.search_text_tv);
        }

        public void bind(ViewHoler holder, int position) {

            holder.search_category_tv.setText(list.get(position).getCategory());

            if (list.get(position).getTitle() != null) {
                holder.search_text_tv.setText(list.get(position).getTitle());
            }else{
                holder.search_text_tv.setText(list.get(position).getContext());
            }
        }
    }
}
