package com.wishmobile.tomazpractice.tabview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishmobile.tomazpractice.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomazWang on 2016/10/18.
 */

public class TabFragment extends Fragment {

    private static final String TAG = TabFragment.class.getSimpleName();
    private TabFragmentPagerAdapter mPagerAdapter;

    private ArrayList<BaseTabFragment> mFragments;

    @BindView(R.id.block_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.block_tabs)
    SlideLayout mTabs;

    public TabFragment() {

    }


    public static TabFragment newInstance() {
        TabFragment fragment = new TabFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        ButterKnife.bind(this, view);

        Log.d(TAG, "onCreateView: createview ");

        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mFragments = getFragments();
        mPagerAdapter = new TabFragmentPagerAdapter(getFragmentManager(), mFragments);

        // pager
        mViewPager.setAdapter(mPagerAdapter);

        // tab colors
        mTabs.setCustomTabColorizer(new SlideLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return mFragments.get(position).getColor();
            }

            @Override
            public int getDividerColor(int position) {
                return mFragments.get(position).getDividerColor();
            }
        });

        // custom tab
        mTabs.setCustomTabView(R.layout.tab, R.id.txt_tab_title);

        mTabs.setViewPager(mViewPager);

    }

    public ArrayList<BaseTabFragment> getFragments() {

        ArrayList<BaseTabFragment> fragments = new ArrayList<>();
        fragments.add(ImageTabFragment.newInstance("First", R.drawable.checklist));
        fragments.add(ImageTabFragment.newInstance("Second", R.drawable.competition));
        fragments.add(ImageTabFragment.newInstance("Third", R.drawable.goal));
        fragments.add(ImageTabFragment.newInstance("Fourth", R.drawable.trophy));
        fragments.add(ImageTabFragment.newInstance("Fifth",R.drawable.target));
        fragments.add(ImageTabFragment.newInstance("Sixth",R.drawable.trophy));
        fragments.add(ImageTabFragment.newInstance("Seventh",R.drawable.business_partnership));

        return fragments;
    }


}
