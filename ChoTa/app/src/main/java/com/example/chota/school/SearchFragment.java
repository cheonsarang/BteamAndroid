package com.example.chota.school;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    EditText search_edit;
    ImageView search_click;
    RecyclerView search_recv;
    ArrayList<SchoolpostVO> list;
    ArrayList<SchoolpostVO> arrayList = new ArrayList<>();

    String category = "";

    String school1 = "가정통신문";
    String school4 = "공지사항";
    String class1 = "알림장";
    String class2 = "일정";

    SearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);



        search_edit = v.findViewById(R.id.search_edit);
        search_click = v.findViewById(R.id.search_click);
        search_recv = v.findViewById(R.id.search_recv);

        Bundle b = getArguments();
        if(b != null){
            String name = b.getString("name");
            Log.d("TAG", "onCreateView: " + name);
            String keyword = b.getString("search");
            Log.d("TAG", "onCreateView: " + name);
            if (name.equals("search_school")) {
                if(keyword != null){
                    search_edit.setText(keyword);
                    searchSc();
                }else{

                }


            } else if (name.equals("search_class")) {
                if(keyword != null){
                    search_edit.setText(keyword);
                    searchSc();
                }else{

                }
            }
        }

        searchSc();

//        search_edit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        search_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = search_edit.getText().toString();
                //search(text);
                searchSc();
            }
        });
        return v;
    }

    public void searchSc() {
        if(arrayList.size() > 0){
            arrayList.clear();
        }

        CommonConn conn = new CommonConn("list.sc", getContext());
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if (isResult) {
                    Log.d("데이터", "onResult: " + data);
                    list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<SchoolpostVO>>() {
                            }.getType());
                    Log.d("list size 출력", "onResult: " + list.size());
                    ArrayList<SchoolpostVO> schoollist = new ArrayList<>();
                    ArrayList<SchoolpostVO> classlist = new ArrayList<>();

                    Log.d("list", "search: " + list.size());
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getTitle() == null) {
                            if(list.get(i).getContext().contains(search_edit.getText().toString())){
                                arrayList.add(list.get(i));
                            }else{

                            }
                        } else {
                            if (list.get(i).getTitle().contains(search_edit.getText().toString()) || list.get(i).getContext().contains(search_edit.getText().toString())) {
                                arrayList.add(list.get(i));
                            }else{

                            }
                        }
                    }

                    //adapter.notifyDataSetChanged();


//                    for (int i = 0; i < list.size(); i++) {
//                        if (list.get(i).getCategory().equals(school1) || list.get(i).getCategory().equals(school4)) {
//                            schoollist.add(list.get(i));
//                        } else if (list.get(i).getCategory().equals(class1) || list.get(i).getCategory().equals(class2)) {
//                            list                        }
//                    }
//
//                    if(schoollist.size() != 0 || classlist.size() == 0) {
//                        adapter = new SearchAdapter(schoollist, getLayoutInflater());
//                    } else if(schoollist.size() == 0 || classlist.size() != 0) {
//                        adapter = new SearchAdapter(classlist, getLayoutInflater());
//                    } else {
//
//                    }


                    adapter = new SearchAdapter(arrayList, getLayoutInflater());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    search_recv.setLayoutManager(manager);
                    search_recv.setAdapter(adapter);

                }
               
            }
        });
    }


}