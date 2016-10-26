package com.wishmobile.tomazpractice.view.recyclerview;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by TomazWang on 2016/10/26.
 */

public class EasyViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews = new SparseArray<>();


    public EasyViewHolder(View itemView) {
        super(itemView);
    }


    public EasyViewHolder(@LayoutRes int itemLayoutRes, @NonNull ViewGroup parent){
        this(LayoutInflater.from(parent.getContext()).inflate(itemLayoutRes, parent, false));
    }


    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public EasyViewHolder setText(@IdRes int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public EasyViewHolder setText(@IdRes int viewId, @StringRes int resId, Object... formatArgs) {
        TextView tv = getView(viewId);
        tv.setText(tv.getContext().getString(resId, formatArgs));
        return this;
    }


    public EasyViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public EasyViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public EasyViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public EasyViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public EasyViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public EasyViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public EasyViewHolder setTextColorRes(@IdRes int viewId, @ColorRes int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(itemView.getResources().getColor(textColorRes));
        return this;
    }

    public EasyViewHolder setAlpha(@IdRes int viewId, @FloatRange(from=0.0, to=1.0) float value) {
        getView(viewId).setAlpha(value);
        return this;
    }

    public EasyViewHolder setVisible(@IdRes int viewId, boolean visible) {
        return setVisible(viewId, visible, View.GONE);
    }

    public EasyViewHolder setVisible(@IdRes int viewId, boolean visible, int visibilityWhenGone) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : visibilityWhenGone);
        return this;
    }








}
