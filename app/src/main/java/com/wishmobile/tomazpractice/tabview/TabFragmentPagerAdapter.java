package com.wishmobile.tomazpractice.tabview;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/18.
 */

public class TabFragmentPagerAdapter extends PagerAdapter {


    private final ArrayList<BaseTabFragment> fragments;

    public TabFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<BaseTabFragment> fragments) {

        super(fragmentManager);
        if (fragments == null) {
            this.fragments = new ArrayList<BaseTabFragment>();
        }else{
            this.fragments = fragments;
        }
    }

getI
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
