package com.wishmobile.tomazpractice.listview;

import android.view.LayoutInflater;
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

public class SingleChoiceAdapter extends BaseAdapter {

    private final ArrayList<String> datas;
    private int mSelectedIndex = 0;

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

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_choice, parent, false);
        }

        ViewHolder holder = ViewHolder.getHolder(convertView);
        holder.txt_title.setText((String) getItem(position));
        holder.btn_select.setChecked(position == mSelectedIndex);

        return convertView;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.mSelectedIndex = selectedIndex;
    }


    static class ViewHolder {

        TextView txt_title;
        RadioButton btn_select;

        public ViewHolder(View view) {
            txt_title = (TextView) view.findViewById(R.id.txt_title);
            btn_select = (RadioButton) view.findViewById(R.id.btn_select);

            view.setTag(this);
        }

        static ViewHolder getHolder(View view) {
            if (view.getTag() == null || !(view.getTag() instanceof ViewHolder)) {
                return new ViewHolder(view);
            } else {
                return (ViewHolder) view.getTag();
            }
        }


    }


}
