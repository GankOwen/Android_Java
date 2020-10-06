package com.example.class12database.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContactContract {
    public static final String CONTENT_AUTHORITY = "com.example.class12database";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY); //find the path and get the path

    public static final class ContactEntry implements BaseColumns {
        //final class cannot be extend
        public static final String TABLE_NAME = "contact"; //CamoCase

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + TABLE_NAME; //DIR means director
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + TABLE_NAME;

        public static final String _ID = BaseColumns._ID; //distinguish each row
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_EMAIL = "email";


    }

}
