package com.wishmobile.tomazpractice.pageindicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.viewpagerindicator.CirclePageIndicator;
import com.wishmobile.tomazpractice.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CirclePageIndicatorActivity extends AppCompatActivity {

    private static final String TAG = CirclePageIndicatorActivity.class.getSimpleName();

    private static final int AUTO_SLIDE_SEC = 5;

    @BindView(R.id.block_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.page_indicator)
    CirclePageIndicator mIndicator;

    private CircleIndicatorAdapter mPagerAdapter;
    private ArrayList<String> mData;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_page_indicator);

        ButterKnife.bind(this);

        initData();

        mPagerAdapter = new CircleIndicatorAdapter(getSupportFragmentManager(), mData);
        mViewPager.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mViewPager);

    }


    private void initData() {
        mData = new ArrayList<String>();
        mData.add("One");
        mData.add("Two");
        mData.add("Three");

    }


    @OnClick(R.id.btn_nextPage)
    public void nextPageButton() {
        nextPage();
        restartTimer();
    }

    public void nextPage() {

        int currentItem = mViewPager.getCurrentItem();
        if (currentItem < mPagerAdapter.getCount()-1) {
            mViewPager.setCurrentItem(currentItem + 1);
        } else {
            mViewPager.setCurrentItem(0);
        }

    }

    @OnClick(R.id.btn_lastPage)
    public void lastPageButton() {
        lastPage();
        restartTimer();
    }

    private void lastPage() {
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem > 0) {
            mViewPager.setCurrentItem(currentItem - 1);
        } else {
            mViewPager.setCurrentItem(mPagerAdapter.getCount() - 1);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        restartTimer();
    }

    private void restartTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.scheduleAtFixedRate(new SlidPageTask(), AUTO_SLIDE_SEC*1000, AUTO_SLIDE_SEC * 1000);
        }
    }

    private class SlidPageTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    nextPage();
                }
            });
        }
    }
}
