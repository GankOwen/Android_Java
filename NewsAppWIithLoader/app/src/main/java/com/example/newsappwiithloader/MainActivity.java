package com.example.newsappwiithloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    AlertDialog dialog;
    ListView listView;
    List<News> news;
    SearchView searchView;
    TextView emptyTextView;
    Button searchButton;
    String baseUrl =  "https://content.guardianapis.com/search"; // https://content.guardianapis.com/search?show-tags=contributor&q=apple&q=apple&api-key=d14c82f4-c6be-45fd-a246-6c8fca5fc726       after ? will be the addition information
    String apiKey = "d14c82f4-c6be-45fd-a246-6c8fca5fc726";
    final int LOADER_ID_NEWS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.main_listView);
        searchView = findViewById(R.id.main_searchView);
        searchButton = findViewById(R.id.main_search_button);
        emptyTextView = findViewById(R.id.main_empty_news_textView);
        dialog = getDialog();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetWorkConnected()){
                    dialog.show();
                    //getSupportLoaderManager().initLoader(LOADER_ID_NEWS, null, MainActivity.this);
                    getSupportLoaderManager().restartLoader(LOADER_ID_NEWS, null, MainActivity.this);
                }else {
                    Toast.makeText(getBaseContext(),"Need to connect to the Internet",Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Uri uri = Uri.parse(news.get(position).getWeb());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

    private String getCompleteUrl (){
        Uri uri = Uri.parse(baseUrl);
        Uri.Builder builder = uri.buildUpon();

        builder.appendQueryParameter("q", searchView.getQuery().toString());
        builder.appendQueryParameter("show-tags", "contributor");
        builder.appendQueryParameter("api-key",apiKey);

        Log.v("UrlShow",builder.toString());

        return builder.toString();
    }

    private boolean isNetWorkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnected();
    }

    private AlertDialog getDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("loading");
        builder.setCancelable(false); // cannot close through click other buttons
        dialog = builder.create();
        return dialog;
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) { //check by id to see did we run that, so initial loader will only run once.
        return new NewsLoader(this,getCompleteUrl());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        dialog.dismiss();
        if(data.isEmpty()){
            emptyTextView.setVisibility(View.VISIBLE);
        }else{
            emptyTextView.setVisibility(View.INVISIBLE);
        }
        news = data;
        MainArrayAdapter adapter = new MainArrayAdapter(getBaseContext(),news);
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {

    }

}