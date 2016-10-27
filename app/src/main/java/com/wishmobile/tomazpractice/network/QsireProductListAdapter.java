package com.wishmobile.tomazpractice.network;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.network.data.ItemResult;
import com.wishmobile.tomazpractice.view.recyclerview.UltimateDataListAdapter;
import com.wishmobile.tomazpractice.view.recyclerview.easyviewholder.EasyViewHolderHelper;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/26.
 */

public class QsireProductListAdapter extends UltimateDataListAdapter<ItemResult.Product> {


    private static final String TAG = QsireProductListAdapter.class.getSimpleName();

    private int halfWindowWidth = 0;

    public QsireProductListAdapter(ArrayList<ItemResult.Product> dataList) {
        super(dataList, R.layout.item_product_list);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder.getItemViewType() != VIEW_TYPES.NORMAL) {
            return;
        }

        EasyViewHolderHelper helper = EasyViewHolderHelper.getEasyViewHolderHelper(viewHolder);

        ItemResult.Product product = getItem(position);

        helper.setText(R.id.txt_title, product.getTitle());

        String unit = product.getPrice_unit();

        helper.setText(R.id.txt_original_price, unit + " " + product.getOriginal_price())
                .setText(R.id.txt_sale_price, unit + " " + product.getSale_price());

        // cross-line on sale price
        TextView saleText = helper.getView(R.id.txt_original_price);
        Paint paint = saleText.getPaint();
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        paint.setAntiAlias(true);


        int bgColor = Color.BLACK;
        try {
            bgColor = Color.parseColor(product.getFeature_image_color_code());

        } catch (IllegalArgumentException e) {
            bgColor = Color.parseColor("#e8afbe");
        }

        helper.setBackgroundColor(R.id.view_container, bgColor);
        ItemResult.ImageData imageData = product.getFeature_image();
        ImageView imageView = helper.getView(R.id.iv_feature_img);

        int width = getHalfWindowWidth(imageView.getContext());


        if (imageData != null) {

            float scale =  (float)width / imageData.getWidth();
            int height = (int) (scale * imageData.getHeight());

            Log.i(TAG, "onBindViewHolder: title " + product.getTitle());
            Log.d(TAG, "onBindViewHolder: image height from api = " + imageData.getHeight());
            Log.d(TAG, "onBindViewHolder: scale = "+scale);
            Log.d(TAG, "onBindViewHolder: height of " + product.getTitle() + " = " + height);

            Glide.with(imageView.getContext())
                    .load(product.getFeature_image().getUrl())
                    .fitCenter()
                    .override(width, height)
                    .into(imageView);


        }
    }

    private int getHalfWindowWidth(Context context) {
        if (this.halfWindowWidth > 0) {
            return halfWindowWidth;
        }

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        this.halfWindowWidth = display.getWidth() / 2;
        return halfWindowWidth;
    }

    @Override
    public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent) {
        return new UltimateRecyclerviewViewHolder(getSimpleItemView(parent));
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
