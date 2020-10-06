package com.example.class12database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.class12database.data.ContactContract.ContactEntry;
import com.example.class12database.data.LoaderID;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ListView listView;
    FloatingActionButton floatingActionButton;
    MainCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.main_listView);
        floatingActionButton = findViewById(R.id.main_add_floatingActionButton);

        adapter = new MainCursorAdapter(this,null);
        listView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),ContactEditorActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) { //in java, id is long type
                Intent intent = new Intent(getBaseContext(),ContactEditorActivity.class);
                Uri currentUri = ContentUris.withAppendedId(ContactEntry.CONTENT_URI,id);
                intent.setData(currentUri);
                startActivity(intent);
            }
        });

        getSupportLoaderManager().initLoader(LoaderID.CONTACT_LOADER,null, this); //the 1st one is ID, we may load multiply table.
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getSupportLoaderManager().restartLoader(LoaderID.CONTACT_LOADER,null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, ContactEntry.CONTENT_URI, null,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        //after loading data, like doInBackGround
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        //when the activity destroy;
        adapter.swapCursor(null);
    }
}