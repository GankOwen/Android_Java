package com.example.travelguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Pager4Fragment extends Fragment {
    View rootView;
    ListView listView;
    List<ScenicSpot> informList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = rootView.findViewById(R.id.main_listView);
        informList = new ArrayList<>();

        informList.add(new ScenicSpot(R.string.CD_anshun_name, R.string.CD_anshun_address, R.string.CD_anshun_hours, R.string.CD_anshun_price, R.string.CD_anshun_about, R.drawable.chendu_anshun));
        informList.add(new ScenicSpot(R.string.CD_taiguli_name, R.string.CD_taiguli_address, R.string.CD_taiguli_hours, R.string.CD_taiguli_price, R.string.CD_taiguli_about, R.drawable.chendu_taiguli));
        informList.add(new ScenicSpot(R.string.CD_jinli_name, R.string.CD_jinli_address, R.string.CD_jinli_hours, R.string.CD_jinli_price, R.string.CD_jinli_about, R.drawable.chendu_jinli));
        informList.add(new ScenicSpot(R.string.CD_siguniang_name, R.string.CD_siguniang_address, R.string.CD_siguniang_hours, R.string.CD_siguniang_price, R.string.CD_siguniang_about, R.drawable.chendu_4guniangshan));
        informList.add(new ScenicSpot(R.string.CD_dufucaotang_name, R.string.CD_dufucaotang_address, R.string.CD_dufucaotang_hours, R.string.CD_dufucaotang_price, R.string.CD_dufucaotang_about, R.drawable.chendu_dufucaotang));

        ArrayAdapter adapter = new ArrayAdapter(listView.getContext(), (ArrayList<ScenicSpot>) informList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ScenicSpotActivity.class);
                intent.putExtra("ScenicSpot", informList.get(i));
                startActivity(intent);
            }
        });

        return rootView;
    }
}
