package com.wishmobile.tomazpractice.wallet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.wallet.data.CostItem;
import com.wishmobile.tomazpractice.wallet.data.Wallet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomazWang on 2016/10/24.
 */

public class CostItemListAdapter extends RecyclerView.Adapter<CostItemListAdapter.ViewHolder>{

    Wallet wallet;

    public CostItemListAdapter(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cost_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CostItem costItem = wallet.getCostItem(position);

        holder.costName.setText(costItem.getName());
        holder.costText.setText("$" + costItem.getCost());


    }

    @Override
    public int getItemCount() {
        return wallet.getCostItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_cost)
        TextView costText;

        @BindView(R.id.txt_cost_name)
        TextView costName;

        @BindView(R.id.iv_img)
        ImageView img;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
