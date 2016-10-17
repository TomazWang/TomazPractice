package com.wishmobile.tomazpractice;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wishmobile.tomazpractice.data.DummyDatas;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class ListViewAdapter extends BaseAdapter{

    private static final String TAG = ListViewAdapter.class.getSimpleName();
    private final ArrayList<DummyDatas> datas;

    public ListViewAdapter(ArrayList<DummyDatas> datas) {
        this.datas = datas;
        Log.d(TAG, "ListViewAdapter: init data size = "+this.datas.size());
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
        ViewHolder holder;

        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dummydata_for_listview, parent, false);
            holder = new ViewHolder(convertView);
        }

        DummyDatas dummmyData = (DummyDatas) getItem(position);

        holder.title.setText(dummmyData.getTitle());
        holder.name.setText(dummmyData.getName());
//        holder.img.setImageDrawable(parent.getContext().getResources().getDrawable(dummmyData.getImageId()));

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView name;
        ImageView img;

        public ViewHolder(View view) {
            this.title = (TextView) view.findViewById(R.id.txt_title);
            this.name = (TextView) view.findViewById(R.id.txt_name);
            this.img = (ImageView) view.findViewById(R.id.iv_img);
            view.setTag(this);
        }
    }
}
