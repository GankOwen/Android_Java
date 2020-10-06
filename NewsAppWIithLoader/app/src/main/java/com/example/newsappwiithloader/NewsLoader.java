package com.example.newsappwiithloader;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String mUrl;
    public NewsLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        List<News> news = new ArrayList<>();
        try {
            return NewsHelper.getNews(new URL(mUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return news;
    }


}
