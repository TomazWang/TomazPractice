package com.wishmobile.tomazpractice.wallet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.wallet.data.Wallet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TomazWang on 2016/10/21.
 */

public class WalletListAdapter extends RecyclerView.Adapter<WalletListAdapter.ViewHolder> {

    private static final int LIST_LAYOUT = 0;
    private static final int FLOW_LAYOUT = 1;

    private final ArrayList<Wallet> wallets;
    private final boolean isFlowLayout;
    private final int[] colors;
    private Random mRandom = new Random();
    private OnItemClickListener itemClickListener;

    public WalletListAdapter(ArrayList<Wallet> wallets, boolean isFlowLayout, int[] colors) {
        this.wallets = wallets;
        this.isFlowLayout = isFlowLayout;
        this.colors = colors;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutResId = R.layout.item_wallet_list;

        if (viewType == FLOW_LAYOUT) {
            layoutResId = R.layout.item_wallet_flow;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public int getItemViewType(int position) {
        return (isFlowLayout) ? FLOW_LAYOUT : LIST_LAYOUT;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Wallet wallet = wallets.get(position);

        holder.title.setText(wallet.getTitle());
        holder.balance.setText("$ " + wallet.getBalance());

        if (isFlowLayout) {
            holder.container.getLayoutParams().height = getRandomInt();
        }

        int colorId = position % colors.length;

        holder.container.setBackgroundColor(colors[colorId]);

        if(itemClickListener != null) {
            holder.container.setOnClickListener(v -> itemClickListener.onItemClick(wallet.getId()));
        }
    }


    private int getRandomInt() {
        int max = 800;
        int min = 320;

        return mRandom.nextInt(max - min) + min;

    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView balance;
        ViewGroup container;


        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.txt_title);
            balance = (TextView) itemView.findViewById(R.id.txt_balance);
            container = (ViewGroup) itemView.findViewById(R.id.view_container);


        }
    }

    interface OnItemClickListener {
        void onItemClick(long walletId);
    }

}
