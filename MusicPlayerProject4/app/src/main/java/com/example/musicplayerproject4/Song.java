package com.example.musicplayerproject4;

import java.io.Serializable;

public class Song implements Serializable {
    private int mSongNameResId;
    private int mSingerResId;
    private int mAlbumId;
    private int mFileResID;
    private int mCover;

    public Song (int name, int singer, int album, int song, int cover){
        mSongNameResId = name;
        mSingerResId = singer;
        mAlbumId = album;
        mFileResID = song;
        mCover = cover;
    }

    public int getSongNameResId() {
        return mSongNameResId;
    }

    public int getSingerResId() {
        return mSingerResId;
    }

    public int getAlbumId() {
        return mAlbumId;
    }

    public int getFileResID() {
        return mFileResID;
    }

    public int getCover() {
        return mCover;
    }
}
