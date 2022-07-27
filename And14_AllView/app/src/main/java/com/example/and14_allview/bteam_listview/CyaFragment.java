package com.example.and14_allview.bteam_listview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.and14_allview.R;
import com.example.and14_allview.listview.List_Adapter;

import java.util.ArrayList;

public class CyaFragment extends Fragment {
    ListView cyaView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cya, container, false);
        cyaView = v.findViewById(R.id.cyaView);

        ArrayList<CyaDTO> list = new ArrayList<>();

        list.add(new CyaDTO(R.drawable.image12, R.drawable.cat1,
                "한여름 낮의 꿈", "한예슬is",
                "조회수 3천회", "43분 전" ));
        list.add(new CyaDTO(R.drawable.image13, R.drawable.cat2,
                "포트폴리오가 실력이다!", "webstoryboy",
                "조회수 81회", "8시간 전" ));
        list.add(new CyaDTO(R.drawable.image14, R.drawable.cat3,
                "강유미 & 최준", "강유미",
                "조회수 5.3만회", "14시간 전" ));
        list.add(new CyaDTO(R.drawable.image21, R.drawable.cat4,
                "a big summer book haul", "gabbyreads",
                "조회수 8.3천회", "15시간 전" ));
        list.add(new CyaDTO(R.drawable.image15, R.drawable.cat5,
                "Cheeky Henry", "Horrid Henry",
                "조회수 9.4천회", "16시간 전" ));

        CyaAdapter adapter = new CyaAdapter(list, inflater);
        cyaView.setAdapter(adapter);
        return v;
    }
}