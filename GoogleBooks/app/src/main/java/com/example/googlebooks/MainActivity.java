package com.example.googlebooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Book> books;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.main_listView);
        searchView = findViewById(R.id.main_searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getRespondsFromUrl(searchView.getQuery().toString());
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.v("Link",books.get(position).getLink());
                        Uri uri = Uri.parse(books.get(position).getLink());
                       // Uri uri = Uri.parse("http://books.google.com/books?id=xv6uIwJJo1MC&dq=computer&hl=&source=gbs_api");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void getRespondsFromUrl (String searchWord){
        URL url = null;
        try{
            url = new URL("https://www.googleapis.com/books/v1/volumes?q="+searchWord+"&maxResults=40");
            new LoadingTask().execute(url);
        }catch(MalformedURLException e){
            Log.e("loading error","link loading error");
        }
    }

    private class LoadingTask extends AsyncTask <URL, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(URL... urls) {
            String jsonString = null;
            HttpURLConnection urlConnection = null;
            try{
                urlConnection = (HttpURLConnection) urls[0].openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();
                if(urlConnection.getResponseCode()==200) {
                    InputStream inputStream = urlConnection.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    scanner.useDelimiter("\\A");
                    if(scanner.hasNext()){
                        jsonString = scanner.next();
                    }else{
                        jsonString = "fail";
                    }
                    inputStream.close();
                }else{
                    Toast.makeText(MainActivity.this,"Failed" + urlConnection.getResponseCode(),Toast.LENGTH_LONG).show();
                }
            }catch (IOException e){
                Log.e("Input error","error");
            }
            urlConnection.disconnect();
            return jsonString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            books = extraJson(s);
            MainArrayAdapter adapter = new MainArrayAdapter(getBaseContext(),books);
            listView.setAdapter(adapter);
        }
    }
    private static List<Book> extraJson (String string){
        List <Book> books = new ArrayList<>();
        try{
            JSONObject rootObject = new JSONObject(string);
            JSONArray itemArray = rootObject.getJSONArray("items");
            for(int i = 0; i < itemArray.length(); i++){
                JSONObject jsonCurrentObject = itemArray.getJSONObject(i);
                JSONObject volumeInformJsonObject = (JSONObject) jsonCurrentObject.get("volumeInfo");
                String title = volumeInformJsonObject.get("title").toString();
                String authors = "";
                if(!volumeInformJsonObject.isNull("author")){
                    JSONArray authorArray = volumeInformJsonObject.getJSONArray("authors");
                    for(int j = 0; j < authorArray.length(); j++){
                        if(j == authorArray.length()-1){
                            authors += authorArray.getString(j)+". ";
                        }else {
                            authors += authorArray.getString(j) + ", ";
                        }
                    }
                }
                String publisher = "N/A";
                String publishDate = "N/A";
                String description = "N/A";
                String web = "N/A";
                if(!volumeInformJsonObject.isNull("author")){
                    publisher = volumeInformJsonObject.get("publisher").toString();
                }
                if(!volumeInformJsonObject.isNull("publishedDate")){
                    publishDate = volumeInformJsonObject.get("publishedDate").toString();
                }
                if(!volumeInformJsonObject.isNull("description")){
                    description = volumeInformJsonObject.get("description").toString();
                }
                if(!volumeInformJsonObject.isNull("infoLink")){
                    web = volumeInformJsonObject.get("infoLink").toString();
                }
                Book currentBook = new Book (title,authors,publisher, publishDate,description,web);
                books.add(currentBook);
            }
        }catch (JSONException e){
            Log.e("extraJson", "error");
        }
        return books;
    }
}