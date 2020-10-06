package com.example.class12database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.class12database.data.ContactContract;

import java.util.List;

public class MainCursorAdapter extends CursorAdapter {
    public MainCursorAdapter (Context context, Cursor cursor){
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        itemView.setTag(viewHolder); // later to use getTag to get all of the component inside of ViewHolder
        return itemView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.name_textView.setText(cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME)));
        viewHolder.address_textView.setText(cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_ADDRESS)));
        viewHolder.phone_textView.setText(cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_PHONE)));
        viewHolder.email_textView.setText(cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_EMAIL)));

    }

    private class ViewHolder{
        TextView name_textView;
        TextView address_textView;
        TextView phone_textView;
        TextView email_textView;

        public ViewHolder (View itemView){
            name_textView = itemView.findViewById(R.id.main_name_textView);
            address_textView = itemView.findViewById(R.id.main_address_textView);
            phone_textView = itemView.findViewById(R.id.main_phone_textView);
            email_textView = itemView.findViewById(R.id.main_email_textView);
        }
    }
}
