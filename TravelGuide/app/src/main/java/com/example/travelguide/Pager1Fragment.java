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

public class Pager1Fragment extends Fragment {
    View rootView;
    ListView listView;
    List<ScenicSpot> informList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = rootView.findViewById(R.id.main_listView);
        informList = new ArrayList<>();

        informList.add(new ScenicSpot(R.string.GZT_name, R.string.GZT_address, R.string.GZT_hours, R.string.GZT_price, R.string.GZT_about,R.drawable.guangzhou_tower));
        informList.add(new ScenicSpot(R.string.GZ_theatre_name, R.string.GZ_theatre_price, R.string.GZ_theatre_hours, R.string.GZ_theatre_price, R.string.GZ_theatre_about,R.drawable.guangzhou_grand_theatre));
        informList.add(new ScenicSpot(R.string.GZ_shangxia9_name, R.string.GZ_shangxia9_address, R.string.GZ_shangxia9_hours, R.string.GZ_shangxia9_price, R.string.GZ_shangxia9_about,R.drawable.shangxia9));
        informList.add(new ScenicSpot(R.string.GZ_church_name, R.string.GZ_church_address, R.string.GZ_church_hours, R.string.GZ_church_price, R.string.GZ_church_about,R.drawable.church_guangzhou));
        informList.add(new ScenicSpot(R.string.GZ_baiyunshan_name, R.string.GZ_baiyunshan_address, R.string.GZ_baiyunshan_hours, R.string.GZ_baiyunshan_price, R.string.GZ_baiyunshan_about,R.drawable.baiyun_mountain));


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
