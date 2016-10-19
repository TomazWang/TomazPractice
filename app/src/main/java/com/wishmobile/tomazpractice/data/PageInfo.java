package com.wishmobile.tomazpractice.data;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by TomazWang on 2016/10/19.
 */

public class PageInfo {

    private String mTitle;
    private boolean isFragment;
    private Class<Fragment> fragmentClass;
    private Class<Activity> activityClass;

    public PageInfo(String mTitle, boolean isFragment, Class<Fragment> fragmentClass, Class<Activity> activityClass) {
        this.mTitle = mTitle;
        this.isFragment = isFragment;
        this.fragmentClass = fragmentClass;
        this.activityClass = activityClass;
    }
}
