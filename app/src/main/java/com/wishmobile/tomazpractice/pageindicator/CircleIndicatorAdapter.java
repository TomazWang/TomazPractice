package com.wishmobile.tomazpractice.pageindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;
import com.wishmobile.tomazpractice.data.PageData;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/19.
 */

public class CircleIndicatorAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    private final ArrayList<PageData> mDatas;

    public CircleIndicatorAdapter(FragmentManager fm, ArrayList<PageData> datas) {
        super(fm);
        this.mDatas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = PagerFragment.newInstance(mDatas.get(position));
        return fragment;
    }

    @Override
    public int getIconResId(int index) {
        return 0;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
