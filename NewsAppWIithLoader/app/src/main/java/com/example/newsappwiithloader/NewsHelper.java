package com.example.newsappwiithloader;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewsHelper {

    public static List<News> getNews(URL url){
        String jsonResponds = getJsonString(url);
        return extraJson(jsonResponds);
    }

    private static String getJsonString(URL url) {
        String jsonString = "";
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) {
                    jsonString = scanner.next();
                } else {
                    jsonString = "fail";
                }
                inputStream.close();
            } else {
                Log.e("Failed","get Json string fail "+url.toString());
            }
        } catch (IOException e) {
            Log.e("Input error", "error");
        }
        Log.v("verse1", jsonString);
        urlConnection.disconnect();
        return jsonString;
    }

    private static List<News> extraJson (String string){
        List<News> news = new ArrayList<>();
        try{
            JSONObject rootObject = new JSONObject(string);
            JSONObject responseObject = rootObject.getJSONObject("response");
            JSONArray jsonArray = responseObject.getJSONArray("results");
            for(int i = 0; i <jsonArray.length(); i++){
                JSONObject jsonCurrentObject = jsonArray.getJSONObject(i);
                String title = jsonCurrentObject.getString("webTitle");
                String date = jsonCurrentObject.getString("webPublicationDate");
                String web = jsonCurrentObject.getString("webUrl");
                JSONArray jsonArrayTag = jsonCurrentObject.getJSONArray("tags");
                String contributors = "";
                for(int j = 0; j < jsonArrayTag.length(); j++){
                    JSONObject jsonCurrentObject2 = jsonArrayTag.getJSONObject(j);
                    contributors += jsonCurrentObject2.getString("firstName")+" ";
                    if(j==(jsonArrayTag.length()-1)){
                        contributors += jsonCurrentObject2.getString("lastName")+".";
                    }else {
                        contributors += jsonCurrentObject2.getString("lastName") + ",";
                    }
                }
                News singleNews = new News(title,date,web,contributors);
                news.add(singleNews);
            }
        }catch (JSONException e){
            Log.e("extraJson", "error");
        }
        return news;
    }
}
