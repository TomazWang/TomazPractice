package com.wishmobile.tomazpractice.tabview;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/18.
 */

class TabFragmentPagerAdapter extends FragmentPagerAdapter {


    private final ArrayList<BaseTabFragment> fragments;

    public TabFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<BaseTabFragment> fragments) {

        super(fragmentManager);
        if (fragments == null) {
            this.fragments = new ArrayList<BaseTabFragment>();
        }else{
            this.fragments = fragments;
        }
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
