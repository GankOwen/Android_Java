package com.example.newsappwiithloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.newsappwiithloader.News;
import com.example.newsappwiithloader.R;

import java.util.List;

public class MainArrayAdapter extends ArrayAdapter<News> {
    public MainArrayAdapter(Context context, List<News> news){
        super(context,0,news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        ViewHolder viewHolder;
        if(itemView==null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false);
            viewHolder = new ViewHolder(itemView);
            itemView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)itemView.getTag();
        }
        News currentNews = getItem(position);

        viewHolder.title.setText(currentNews.getTitle());
        viewHolder.date.setText(currentNews.getDate());
        viewHolder.web.setText(currentNews.getWeb());
        viewHolder.authors.setText(currentNews.getAuthors());

        return itemView;
    }
    private class ViewHolder{
        TextView title;
        TextView date;
        TextView web;
        TextView authors;

        public ViewHolder (View itemView){
            title = itemView.findViewById(R.id.main_title);
            date = itemView.findViewById(R.id.main_date);
            web = itemView.findViewById(R.id.main_web);
            authors = itemView.findViewById(R.id.main_author);
        }
    }
}
