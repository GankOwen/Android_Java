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

public class Pager3Fragment extends Fragment {
    View rootView;
    ListView listView;
    List<ScenicSpot> informList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = rootView.findViewById(R.id.main_listView);
        informList = new ArrayList<>();

        informList.add(new ScenicSpot(R.string.WH_CBD_name, R.string.WH_CBD_address, R.string.WH_CBD_hours, R.string.WH_CBD_price, R.string.WH_CBD_about, R.drawable.wuhan_cbd));
        informList.add(new ScenicSpot(R.string.WH_YHY_name, R.string.WH_YHY_address, R.string.WH_YHY_hours, R.string.WH_YHY_price, R.string.WH_YHY_about, R.drawable.wuhan_yhy));
        informList.add(new ScenicSpot(R.string.WH_hubuxiang_name, R.string.WH_hubuxiang_address, R.string.WH_hubuxiang_hours, R.string.WH_hubuxiang_price, R.string.WH_hubuxiang_about, R.drawable.wuhan_hubuxiang));
        informList.add(new ScenicSpot(R.string.WH_hankouli_name, R.string.WH_hankouli_address, R.string.WH_hankouli_hours, R.string.WH_hankouli_price, R.string.WH_hankouli_about, R.drawable.wuhan_hankouli));
        informList.add(new ScenicSpot(R.string.WH_huanghelou_name, R.string.WH_huanghelou_address, R.string.WH_huanghelou_hours, R.string.WH_huanghelou_price, R.string.WH_huanghelou_about, R.drawable.wuhan_huanghelou));

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
