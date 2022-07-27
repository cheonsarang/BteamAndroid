package com.example.and14_allview.listview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.and14_allview.R;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listview = v.findViewById(R.id.listview);

        ArrayList<AheDTO> list = new ArrayList<>();
        //5건 추가하기 1. 개, 멍멍이, R.drable.image
        list.add(new AheDTO("lazy", "cat1", 5, R.drawable.cat1));
        list.add(new AheDTO("cute", "cat2", 1, R.drawable.cat2));
        list.add(new AheDTO("gray", "cat3", 1, R.drawable.cat3));
        list.add(new AheDTO("black", "cat4", 3, R.drawable.cat4));
        list.add(new AheDTO("spotted", "cat5", 2, R.drawable.cat5));

        List_Adapter adapter= new List_Adapter(list,inflater);
        listview.setAdapter(adapter);

        /*1.목록을 가지는 모든 뷰는 Adapter가 필요함.
         *    -1-1.한칸마다 보여질 데이터를 묶어놓은 객체 == DTO(ArrayList)
         *    -1-2.한칸마다 보여질 데이터를 디자인해놓은 파일 == res\layout\xml
         * 2.Adapter 생성
         *    -2-1. 클래스를 추가한다.
         *    -2-2. extends(상속)을 받아서 어댑터가 된다.
         * 3.ListView<=>Adapter연결
         * */

        return v;
    }
}