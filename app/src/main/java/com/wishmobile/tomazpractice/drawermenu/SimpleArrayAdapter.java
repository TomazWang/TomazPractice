package com.wishmobile.tomazpractice.drawermenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.wishmobile.tomazpractice.R;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/19.
 */

public class SimpleArrayAdapter extends ArrayAdapter<String> {

    public SimpleArrayAdapter(Context context, ArrayList<String> mDatas) {
        super(context, R.layout.item_simple_list,R.id.txt_item, mDatas);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return super.getView(position, convertView, parent);
    }
}
