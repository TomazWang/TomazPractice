package com.wishmobile.tomazpractice.widget.formview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.widget.TextView;

import com.linkwish.widget.FormView;
import com.wishmobile.tomazpractice.R;

/**
 * Created by TomazWang on 2016/10/21.
 */

public class TextItem extends FormView.ItemView {
    TextView title;
    TextView subTitle;
    Context context;

    public TextItem(Context context) {
        super(context);
        this.context = context;
        title = (TextView) this.getView().findViewById(R.id.title);
        subTitle = (TextView) this.getView().findViewById(R.id.sub_title);
    }

    public TextItem(Context context, @StringRes int text) {
        this(context);
        setText(text);
    }

    public TextItem(Context context, String text) {
        this(context);
        setText(text);
    }

    public TextItem(Context context, @StringRes int text, @StringRes int subText) {
        this(context);
        setText(text);
        setSubText(subText);
    }

    public TextItem(Context context, String text, String subText) {
        this(context);
        setText(text);
        setSubText(subText);
    }

    public void setText(@StringRes int text) {
        this.title.setText(context.getString(text));
    }

    public void setText(String text) {
        this.title.setText(text);
    }

    public void setTypeface(int typeface) {

        this.title.setTypeface(null, typeface);

    }

    public void setSubText(@StringRes int text) {
        this.subTitle.setText(context.getString(text));
    }

    public void setSubText(String text) {
        this.subTitle.setText(text);
    }

    public void setTextIcon(@DrawableRes int icon) {
        this.title.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        this.title.setCompoundDrawablePadding(16);
    }

    public void setSubTextIcon(@DrawableRes int icon) {
        this.subTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0);
        this.subTitle.setCompoundDrawablePadding(16);
    }

    public TextView getSubText() {
        return this.subTitle;
    }


    @Override
    public int getLayoutId() {
        return R.layout.view_item_text;
    }


    public void setEditable(boolean isEdiable) {
        if(isEdiable){
            subTitle.setTextColor(getView().getContext().getResources().getColor(R.color.form_text_gray));
        }else{
            subTitle.setTextColor(getView().getContext().getResources().getColor(R.color.light_gray));
            setClickable(false);
        }
    }



}
