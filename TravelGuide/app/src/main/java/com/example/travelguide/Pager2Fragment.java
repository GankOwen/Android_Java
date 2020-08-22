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

public class Pager2Fragment extends Fragment {
    View rootView;
    ListView listView;
    List<ScenicSpot> informList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = rootView.findViewById(R.id.main_listView);
        informList = new ArrayList<>();

        informList.add(new ScenicSpot(R.string.SH_CBD_name, R.string.SH_CBD_address, R.string.SH_CBD_hours, R.string.SH_CBD_price, R.string.GZT_about, R.drawable.lujiazui));
        informList.add(new ScenicSpot(R.string.SH_GuangFuLin_name, R.string.SH_GuangFuLin_address, R.string.SH_GuangFuLin_hours, R.string.SH_GuangFuLin_price, R.string.SH_GuangFuLin_about, R.drawable.guangfulin));
        informList.add(new ScenicSpot(R.string.SH_ChengHuangMiao_name, R.string.SH_ChengHuangMiao_address, R.string.SH_ChengHuangMiao_hours, R.string.SH_ChengHuangMiao_price, R.string.SH_ChengHuangMiao_about, R.drawable.chenhuangmiao));
        informList.add(new ScenicSpot(R.string.SH_XunYiCao_name, R.string.SH_XunYiCao_address, R.string.SH_XunYiCao_hours, R.string.SH_XunYiCao_price, R.string.SH_XunYiCao_about, R.drawable.xunyicao));
        informList.add(new ScenicSpot(R.string.SH_FengJing_name, R.string.SH_FengJing_address, R.string.SH_FengJing_hours, R.string.SH_FengJing_price, R.string.SH_FengJing_about, R.drawable.fengjinguzhen));

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
