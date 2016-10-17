package com.wishmobile.tomazpractice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wishmobile.tomazpractice.data.DummyDatas;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.ViewHolder>{
    private final ArrayList<DummyDatas> data;

    public RCVAdapter(ArrayList<DummyDatas> dummyDates) {
        this.data = dummyDates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle;
        TextView txtName;




        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
