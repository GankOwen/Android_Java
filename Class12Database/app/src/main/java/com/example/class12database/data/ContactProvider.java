package com.example.class12database.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.class12database.data.ContactContract.ContactEntry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.RowId;

public class ContactProvider extends ContentProvider {
    private static final int CONTACTS = 100;
    private static final int CONTACT_ID = 101; //100 and 101 can be any number

    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH); //match contacts and contact_id

    static {
        sUriMatcher.addURI(ContactContract.CONTENT_AUTHORITY, ContactEntry.TABLE_NAME, CONTACTS);
        sUriMatcher.addURI(ContactContract.CONTENT_AUTHORITY, ContactEntry.TABLE_NAME + "/#", CONTACT_ID);
    }//because we don't have constructor, so when we run it at the first time, it will run the above code

    ContactDbHelper dBHelper;

    @Override
    public boolean onCreate() {
        dBHelper = new ContactDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match){
            case CONTACTS:
                cursor = db.query(ContactEntry.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CONTACT_ID:
                selection = ContactEntry._ID + "=?"; //in case query security
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(ContactEntry.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query your uri "+uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);//update list view\
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) { // give to other app when they visit our database
        int match = sUriMatcher.match(uri);
        switch(match){
            case CONTACTS:
                return ContactEntry.CONTENT_LIST_TYPE;
            case CONTACT_ID:
                return ContactEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("unknown uri "+uri +" , and match "+match);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        switch (match){
            case CONTACTS:
                getContext().getContentResolver().notifyChange(uri,null);
                return db.delete(ContactEntry.TABLE_NAME,selection,selectionArgs);
            case CONTACT_ID:
                selection = ContactEntry._ID + "=?"; //in case query security
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                getContext().getContentResolver().notifyChange(uri,null);
                return db.delete(ContactEntry.TABLE_NAME,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case CONTACTS:
                return insertContact(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for "+uri);
        }
    }

    private Uri insertContact (Uri uri, ContentValues values){
        SQLiteDatabase db = dBHelper.getWritableDatabase();
        long newRowId = db.insert(ContactEntry.TABLE_NAME, null, values);
        if(newRowId == -1){
            Log.e("Insert Contact","Insert Failed");
            return null;
        }else{
         getContext().getContentResolver().notifyChange(uri,null); //update list view
         return ContentUris.withAppendedId(uri,newRowId);
        }
    }



    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgus) {
        int match = sUriMatcher.match(uri);
        switch(match){
            case CONTACTS:
                return UpdateContact(uri, values, selection, selectionArgus);
            case CONTACT_ID:
                selection = ContactEntry._ID+"=?";
                selectionArgus = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return UpdateContact(uri, values, selection, selectionArgus);
            default:
                throw new IllegalArgumentException("Update is not supported for "+uri);
        }
    }
    private  int UpdateContact(Uri uri, ContentValues values, String selection, String[] selectionArgus){
        SQLiteDatabase db = dBHelper.getWritableDatabase();
        if(values.size()==0){
            return 0;
        }else {
            getContext().getContentResolver().notifyChange(uri,null); //tells loader we updated the data
            return db.update(ContactEntry.TABLE_NAME, values, selection,selectionArgus);
        }
    }
}
