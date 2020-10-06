package com.example.musicplayerproject4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MainArrayAdapter extends ArrayAdapter<Song> {
    public MainArrayAdapter(Context context, List<Song> songList){
        super(context,0,songList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        ViewHolder viewHolder;

        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_main,parent,false);
            viewHolder = new ViewHolder(itemView);
            itemView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) itemView.getTag();
        }

        Song currentSong = getItem(position);
        viewHolder.songNameTextView.setText(currentSong.getSongNameResId());
        viewHolder.singerTextView.setText(currentSong.getSingerResId());

        return itemView;
    }

    private class ViewHolder {
        TextView songNameTextView;
        TextView singerTextView;

        public ViewHolder (View itemView){
            songNameTextView = itemView.findViewById(R.id.main_song_name_textView);
            singerTextView = itemView.findViewById(R.id.main_singer_name_textView);
        }
    }
}
