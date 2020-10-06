package com.example.newsappwiithloader;

public class News {
    private String mTitle;
    private String mDate;
    private String mWeb;
    private String mAuthors;

    public News(String title, String date, String web, String authors) {
        mTitle = title;
        mDate = date;
        mWeb = web;
        mAuthors = authors;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getWeb() {
        return mWeb;
    }

    public String getAuthors() {
        return mAuthors;
    }
}
