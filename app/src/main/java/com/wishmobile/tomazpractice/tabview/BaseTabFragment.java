package com.wishmobile.tomazpractice.tabview;

import android.graphics.Color;
import android.support.v4.app.Fragment;

/**
 * Created by TomazWang on 2016/10/18.
 */

public class BaseTabFragment extends Fragment {

    private int color = Color.CYAN;
    private int dividerColor = Color.TRANSPARENT;
    private String title = "123";


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
    }



}

