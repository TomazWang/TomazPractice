package com.wishmobile.tomazpractice.recyclerview;

import android.support.v4.util.ArrayMap;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.ViewHolder> {
    private static final int TYPE_B = 0;
    private static final int TYPE_A = 1;
    private final ArrayList<DummyDatas> data;
    private Random mRandom = new Random();
    private boolean isFlow = false;
    private ArrayMap<Integer,Integer> mHeights = new ArrayMap<>();
    private int mBasicViewHeight = 0;

    public RCVAdapter(ArrayList<DummyDatas> dummyDates) {
        this.data = dummyDates;
        Log.d(TAG, "RCVAdapter: data size = " + data.size());
    }

    @Override
    public int getItemViewType(int position) {

        if (getItemId(position) % 2 == 0) {
            return TYPE_A;
        } else {
            return TYPE_B;
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = R.layout.item_dummydata_for_recyclerview;

        if (viewType == TYPE_B) {
            layoutId = R.layout.item_dummydata_for_recyclerview_reverse;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        DummyDatas dummyData = data.get(position);

        holder.txtTitle.setText(dummyData.getTitle());
        holder.txtName.setText(dummyData.getName());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(position);
            }
        });


        if (isFlow()) {
            if (mHeights.get(data.get(position).getId()) == null) {
                if(mBasicViewHeight == 0 ){
                    mBasicViewHeight = holder.container.getLayoutParams().height;
                }
                mHeights.put(data.get(position).getId(),holder.container.getLayoutParams().height = getRandomInt());

            } else {
                holder.container.getLayoutParams().height = mHeights.get(data.get(position).getId());
            }
        }else{
            if(mBasicViewHeight == 0){
                mBasicViewHeight = holder.container.getLayoutParams().height;
                return;
            }
            holder.container.getLayoutParams().height = mBasicViewHeight;
        }


    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    private int getRandomInt() {
        int max = 840;
        int min = 300;

        return mRandom.nextInt(max - min) + min;

    }

    private void changeValue(int position) {
        data.get(position).addValue();
        this.notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public int addItem(int position, DummyDatas dummyDatas) {
        data.add(position, dummyDatas);
        this.notifyItemRangeInserted(position, 1);
        return position;
    }

    public int addItem(DummyDatas dummyDatas) {
        int position = data.size();
        addItem(position, dummyDatas);
        return position;
    }

    public int removeLastItem() {
        int position = data.size() - 1;
        removeItem(position);
        return position;
    }

    public int removeFirstItem() {
        return removeItem(0);
    }

    public int removeItem(int position) {
        if (data.size() == 0) {
            return position;
        }
        data.remove(position);
        this.notifyItemRangeRemoved(position, 1);
        return position;
    }

    public void clear() {
        data.clear();
        this.notifyDataSetChanged();
    }

    public boolean isFlow() {

        return isFlow;
    }

    public void setFlow(boolean flow) {
        isFlow = flow;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView txtTitle;

        @BindView(R.id.txt_name)
        TextView txtName;

        @BindView(R.id.iv_img)
        ImageView img;

        @BindView(R.id.view_container)
        ViewGroup container;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(ViewHolder.this, itemView);
        }
    }
}
