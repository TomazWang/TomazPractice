package com.wishmobile.tomazpractice.pageindicator;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.wishmobile.tomazpractice.data.PageData;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/20.
 */

public class InfiniteIndicateAdapter extends CircleIndicatorAdapter {

    public static final int LOOPS_COUNT = 1000;

    public InfiniteIndicateAdapter(FragmentManager fm, ArrayList<PageData> datas) {
        super(fm, datas);
    }


    public  void init(CirclePageIndicator indicator, ViewPager viewPager){
        indicator.setCustomCount(getDatas().size());
        viewPager.setCurrentItem(getDatas().size() * LOOPS_COUNT/2);
    }

    @Override
    public int getCount() {
        return super.getCount()*LOOPS_COUNT;
    }


}
