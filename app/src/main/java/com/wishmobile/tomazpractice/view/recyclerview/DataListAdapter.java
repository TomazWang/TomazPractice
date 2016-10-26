package com.wishmobile.tomazpractice.view.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by TomazWang on 2016/10/26.
 */

public abstract class DataListAdapter<D, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {


    private int simpleItemLayoutResId = -1;

    private ArrayList<D> mDataList = new ArrayList<>();
    private Random mRandom = new Random();
    private int randomMax = 840;
    private int randomMin = 300;

    public DataListAdapter(ArrayList<D> dataList, @LayoutRes int itemLayoutResId) {
        this.mDataList = dataList;
        this.simpleItemLayoutResId = itemLayoutResId;
    }

    public DataListAdapter(ArrayList<D> dataList) {
        this(dataList, -1);
    }


    public View getSimpleItemView(ViewGroup parent) {
        if (simpleItemLayoutResId > 0) {
            return LayoutInflater.from(parent.getContext()).inflate(getSimpleItemLayoutResId(), parent, false);
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    // item getting
    public D getItem(int position){
        return mDataList.get(position);
    }



    // -- item add/remove
    public void addAll(List<D>list){
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }


    public int addItemToFirst(D item) {
        return addItem(0, item);
    }


    public int addItem(int position, D item) {
        this.mDataList.add(position, item);
        this.notifyItemRangeChanged(position, 1);
        return position;
    }

    public int addItem(D item) {
        int position = mDataList.size();
        addItem(position, item);
        return position;
    }

    public int removeFirstItem() {
        return removeItem(0);
    }

    public int removeItem() {
        return removeFirstItem();
    }

    public int removeItem(int position) {
        if (mDataList.size() == 0) {
            return position;
        }
        mDataList.remove(position);
        this.notifyItemRangeRemoved(position, 1);
        return position;
    }

    public void clear() {
        mDataList.clear();
        this.notifyDataSetChanged();
    }

    /**
     * get a randon integer for height/width setting when using StaggeredGridLayoutManager
     *
     * @return a random integer from {@link #getRandomMin()} to {@link #getRandomMax()}.
     */
    public int getRandomInt() {
        int max = getRandomMax();
        int min = getRandomMin();

        return mRandom.nextInt(max - min) + min;
    }

    // -- getters

    public int getRandomMax() {
        return randomMax;
    }

    public int getRandomMin() {
        return randomMin;
    }

    public ArrayList<D> getDataList() {
        return mDataList;
    }


    public int getSimpleItemLayoutResId() {
        return simpleItemLayoutResId;
    }
}
