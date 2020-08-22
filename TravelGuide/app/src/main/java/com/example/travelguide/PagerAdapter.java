package com.example.travelguide;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    Context mContext;
    static final int Number_of_Page = 4;
    public PagerAdapter (Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Pager1Fragment();
            case 1:
                return new Pager2Fragment();
            case 2:
                return new Pager3Fragment();
            case 3:
                return new Pager4Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Number_of_Page;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Guangzhou";
            case 1:
                return "Shanghai";
            case 2:
                return "Wuhan";
            case 3:
                return "Chendu";
            default:
                return null;
        }
    }
}
