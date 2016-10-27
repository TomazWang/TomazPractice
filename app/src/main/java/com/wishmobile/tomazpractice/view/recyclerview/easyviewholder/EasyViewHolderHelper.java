package com.wishmobile.tomazpractice.view.recyclerview.easyviewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wishmobile.tomazpractice.R;

/**
 * Created by TomazWang on 2016/10/27.
 */

public class EasyViewHolderHelper {


    private static final int EASY_VIEW_HOLDER_HELPER = R.id.easy_view_holder_key;

    SparseArray<View> mViews = new SparseArray<>();
    View mItemView;

    public static EasyViewHolderHelper getEasyViewHolderHelper(RecyclerView.ViewHolder viewHolder){
        Object tag = viewHolder.itemView.getTag(EASY_VIEW_HOLDER_HELPER);
        EasyViewHolderHelper helper;
        if(tag == null || !(tag instanceof EasyViewHolderHelper)){
            helper = new EasyViewHolderHelper(viewHolder);
            viewHolder.itemView.setTag(EASY_VIEW_HOLDER_HELPER, helper);
        }else{
            helper = (EasyViewHolderHelper)tag;
        }

        return helper;
    }


    private EasyViewHolderHelper(RecyclerView.ViewHolder viewHolder) {
        this.mItemView = viewHolder.itemView;
    }


    public View getItemView(){
        return mItemView;
    }


    public <T extends View> T getView(@IdRes int viewId){
        View view = mViews.get(viewId);
        if(view == null){
            view = getItemView().findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }


    public EasyViewHolderHelper setText(@IdRes int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public EasyViewHolderHelper setText(@IdRes int viewId, @StringRes int resId, Object... formatArgs) {
        TextView tv = getView(viewId);
        tv.setText(tv.getContext().getString(resId, formatArgs));
        return this;
    }


    public EasyViewHolderHelper setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public EasyViewHolderHelper setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public EasyViewHolderHelper setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public EasyViewHolderHelper setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public EasyViewHolderHelper setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public EasyViewHolderHelper setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public EasyViewHolderHelper setTextColorRes(@IdRes int viewId, @ColorRes int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(getItemView().getResources().getColor(textColorRes));
        return this;
    }

    public EasyViewHolderHelper setAlpha(@IdRes int viewId, @FloatRange(from=0.0, to=1.0) float value) {
        getView(viewId).setAlpha(value);
        return this;
    }

    public EasyViewHolderHelper setVisible(@IdRes int viewId, boolean visible) {
        return setVisible(viewId, visible, View.GONE);
    }

    public EasyViewHolderHelper setVisible(@IdRes int viewId, boolean visible, int visibilityWhenGone) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : visibilityWhenGone);
        return this;
    }


}
