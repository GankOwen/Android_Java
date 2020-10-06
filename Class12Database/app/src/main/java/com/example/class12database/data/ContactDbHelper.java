package com.example.class12database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;
    public ContactDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_CONTACT_TABLE = "CREATE TABLE "+ ContactContract.ContactEntry.TABLE_NAME+"("
                + ContactContract.ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ContactContract.ContactEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + ContactContract.ContactEntry.COLUMN_PHONE + " INTEGER NOT NULL, "
                + ContactContract.ContactEntry.COLUMN_ADDRESS + " TEXT NOT NULL, "
                + ContactContract.ContactEntry.COLUMN_EMAIL + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
