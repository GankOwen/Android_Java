package com.example.travelguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ArrayAdapter extends android.widget.ArrayAdapter<ScenicSpot> {

    public ArrayAdapter (Context context, ArrayList<ScenicSpot> informList){
        super(context,0,informList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.pager_view_item_main,parent,false);
        }

        ScenicSpot currentScenicSpot = getItem(position);

        ImageView informImage = listItemView.findViewById(R.id.pager_item_imageView);
        informImage.setImageResource(currentScenicSpot.getPicture());

        TextView informTitle = listItemView.findViewById(R.id.pager_item_textView);
        informTitle.setText(currentScenicSpot.getName());

        return listItemView;
    }
}
