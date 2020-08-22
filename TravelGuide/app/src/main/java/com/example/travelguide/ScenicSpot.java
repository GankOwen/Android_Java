package com.example.travelguide;

import java.io.Serializable;

public class ScenicSpot implements Serializable {
    private int mAddress;
    private int mPrice;
    private int mHours;
    private int mAbout;
    private int mName;
    private int mPicture;

    public ScenicSpot(int name, int address, int hours, int price, int about, int picture){
        mName = name;
        mAddress = address;
        mHours = hours;
        mPrice = price;
        mAbout = about;
        mPicture = picture;
    }

    public int getAddress() {
        return mAddress;
    }

    public int getPrice() {
        return mPrice;
    }

    public int getHours() {
        return mHours;
    }

    public int getAbout() {
        return mAbout;
    }

    public int getName() {
        return mName;
    }

    public int getPicture() {
        return mPicture;
    }
}
