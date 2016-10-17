package com.wishmobile.tomazpractice.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.data.DummyDatas;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.ViewHolder> {
    private static final int TYPE_B = 0;
    private static final int TYPE_A = 1;
    private final ArrayList<DummyDatas> data;

    public RCVAdapter(ArrayList<DummyDatas> dummyDates) {
        this.data = dummyDates;
        Log.d(TAG, "RCVAdapter: data size = "+data.size());
    }

    @Override
    public int getItemViewType(int position) {

        if(position%2 == 0){
            return TYPE_A;
        }else{
            return TYPE_B;
        }



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: creating view holder");
        int layoutId = R.layout.item_dummydata_for_listview;

        if(viewType == TYPE_B){
            layoutId = R.layout.item_dummydata_for_listview_reverse;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        if(viewType == TYPE_A){
            view.setBackgroundColor(Color.CYAN);
        }

        ViewHolder holder = new ViewHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DummyDatas dummyData = data.get(position);

        holder.txtTitle.setText(dummyData.getTitle());
        holder.txtName.setText(dummyData.getName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(DummyDatas dummyDatas) {
        int position = data.size();
        data.add(dummyDatas);
        this.notifyItemChanged(position);
    }

    public void removeLastItem() {
        int position = data.size()-1;
        data.remove(position);
        this.notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtName;
        ImageView img;


        public ViewHolder(View itemView) {
            super(itemView);

            this.txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            this.txtName = (TextView) itemView.findViewById(R.id.txt_name);
            this.img = (ImageView)itemView.findViewById(R.id.iv_img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: click to change content;
                }
            });

        }
    }
}
