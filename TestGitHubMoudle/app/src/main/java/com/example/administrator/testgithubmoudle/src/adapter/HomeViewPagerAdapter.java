package com.example.administrator.testgithubmoudle.src.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.administrator.testgithubmoudle.src.fragment.MainHomeFragment;
import com.example.administrator.testgithubmoudle.src.fragment.PlatformFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/27.
 */
public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MainHomeFragment();
                break;
            case 1:
                fragment = new PlatformFragment();
                break;
            default:
                fragment = new PlatformFragment();
        }
       return fragment;
//        if (null != mData && mData.size() > 0) {
//            if (position > 0 && position < mData.size()) {
//                fragment = mData.get(position - 1);
//            }
//        }
//        return fragment;//cause null pointer exception because of position
    }

    @Override
    public int getCount() {
//        int count = 0;
//        if (null != mData) {
//            count = mData.size();
//        }
        return 2;
    }

}
