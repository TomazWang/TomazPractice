package com.wishmobile.tomazpractice.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wishmobile.tomazpractice.R;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class SingleChoiceAdapter extends BaseAdapter{

    private final ArrayList<String> datas;

    public SingleChoiceAdapter(ArrayList<String> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }






    class ViewHolder{

        TextView txt_title;
        RadioButton btn_select;

        public ViewHolder(View view) {
            txt_title = (TextView)view.findViewById(R.id.txt_title);
            btn_select = (TextView)view.findViewById(R.id.btn_select);
        }
    }



}
