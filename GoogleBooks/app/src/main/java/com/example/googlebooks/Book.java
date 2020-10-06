package com.example.googlebooks;

public class Book {
    private String mTitle;
    private String mAuthor;
    private String mPublisher;
    private String mPublishDate;
    private String mDescription;
    private String mLink;

    public Book(String title, String author, String publisher, String publishDate, String description, String link){
        mTitle = title;
        mAuthor = author;
        mPublisher = publisher;
        mPublishDate = publishDate;
        mDescription = description;
        mLink = link;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getPublishDate() {
        return mPublishDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLink() {
        return mLink;
    }
}
