package com.wishmobile.tomazpractice.data;

import android.util.Log;

import com.wishmobile.tomazpractice.R;

import static android.content.ContentValues.TAG;

/**
 * Created by TomazWang on 2016/10/19.
 */

public class PageData {

    public final static int[] allImgs = new int[]{
            R.drawable.analytics,
            R.drawable.business_partnership,
            R.drawable.checklist,
            R.drawable.chess,
            R.drawable.competition,
            R.drawable.contract,
            R.drawable.goal,
            R.drawable.line_chart,
            R.drawable.megaphone,
            R.drawable.paper_plane,
            R.drawable.plant,
            R.drawable.rocket,
            R.drawable.strategy,
            R.drawable.target,
            R.drawable.trophy
    };

    String title;
    int imgNum;

    public PageData(String title, int img) {
        this.title = title;
        this.imgNum = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageNum() {
        return imgNum;
    }

    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }

    public int getImageResId(){
        return allImgs[imgNum];
    }

    public void setRandomImg() {
        Log.d(TAG, "setRandomImg: current img = "+imgNum);
        int newId = 0;
        do{
            newId = (int) Math.floor(Math.random() * allImgs.length);
        }while (newId == imgNum);

        this.imgNum = newId;

        Log.d(TAG, "setRandomImg: new img = "+imgNum);
    }

}
