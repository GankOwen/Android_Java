package com.example.googlebooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MainArrayAdapter extends ArrayAdapter<Book> {
    public MainArrayAdapter (Context context, List<Book> books) {
        super (context, 0, books);
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
        Book currentBook = getItem(position);

        viewHolder.title_textView.setText(currentBook.getTitle());
        viewHolder.authors_textView.setText(currentBook.getAuthor());
        viewHolder.publisher_textView.setText(currentBook.getPublisher());
        viewHolder.publishDate_textView.setText(currentBook.getPublishDate());
        viewHolder.description_textView.setText(currentBook.getDescription());

        return itemView;
    }

    private class ViewHolder {
        TextView title_textView;
        TextView authors_textView;
        TextView publisher_textView;
        TextView publishDate_textView;
        TextView description_textView;

        public ViewHolder (View itemView){
            title_textView = itemView.findViewById(R.id.main_title_textView);
            authors_textView = itemView.findViewById(R.id.main_author_textView);
            publisher_textView = itemView.findViewById(R.id.main_publisher_textView);
            publishDate_textView = itemView.findViewById(R.id.main_publish_date_textView);
            description_textView = itemView.findViewById(R.id.main_description_textView);
        }
    }
}
