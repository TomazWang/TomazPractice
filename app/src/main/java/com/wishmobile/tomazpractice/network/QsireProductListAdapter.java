package com.wishmobile.tomazpractice.network;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.network.data.ItemResult;
import com.wishmobile.tomazpractice.view.recyclerview.DataListAdapter;
import com.wishmobile.tomazpractice.view.recyclerview.EasyViewHolder;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by TomazWang on 2016/10/26.
 */

public class QsireProductListAdapter extends DataListAdapter<ItemResult.Product, EasyViewHolder> {


    public QsireProductListAdapter(ArrayList<ItemResult.Product> dataList) {
        super(dataList, R.layout.item_product_list);
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EasyViewHolder(getSimpleItemLayoutResId(), parent);
    }

    @Override
    public void onBindViewHolder(EasyViewHolder holder, int position) {

        ItemResult.Product product = getItem(position);

        holder.setText(R.id.txt_title, product.getTitle());
        Log.d(TAG, "onBindViewHolder: title = " + product.getTitle());

        String unit = product.getPrice_unit();

        holder.setText(R.id.txt_original_price, unit + " " + product.getOriginal_price());
        holder.setText(R.id.txt_sale_price, unit + " " + product.getSale_price());

        TextView saleText = holder.getView(R.id.txt_original_price);
        Paint paint = saleText.getPaint();
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        paint.setAntiAlias(true);


        Log.d(TAG, "onBindViewHolder: color code = " + product.getFeature_image_color_code());
        int bgColor = Color.BLACK;
        try {
            bgColor = Color.parseColor(product.getFeature_image_color_code());

        } catch (IllegalArgumentException e) {
            bgColor = Color.parseColor("#e8afbe");
        }

        holder.setBackgroundColor(R.id.view_container, bgColor);
        ItemResult.ImageData imageData = product.getFeature_image();
        ImageView imageView = holder.getView(R.id.iv_feature_img);

        if (imageData != null) {

            imageView.getLayoutParams().height = imageData.getHeight();

            Glide.with(imageView.getContext())
                    .load(product.getFeature_image().getUrl())
                    .into(imageView);
        } else {
            Log.w(TAG, "onBindViewHolder: feature image is null");
        }

        Log.d(TAG, "onBindViewHolder: after binding");
    }

}
