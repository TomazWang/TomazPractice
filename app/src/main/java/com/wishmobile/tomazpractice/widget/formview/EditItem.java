package com.wishmobile.tomazpractice.widget.formview;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.wishmobile.tomazpractice.R;

/**
 * Created by TomazWang on 2016/10/21.
 */

public class EditItem extends com.linkwish.widget.formitem.EditItem{

    public EditItem(Context context) {
        super(context);
    }


    public EditItem(Context context, int icon, int text, int hint) {
        super(context, icon, text, hint);
    }

    public EditItem(Context context, int text, String hint) {
        super(context, text, hint);
    }

    public EditItem(Context context, int icon, int text, String hint) {
        super(context, icon, text, hint);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_edit_item;
    }

    @Override
    public void setClickable(boolean flag) {
        super.setClickable(flag);
    }
    public void setDirectEditable(boolean canBeDirectEdit){
        if(canBeDirectEdit) {
            getEditText().setClickable(false);
            getEditText().setFocusable(false);
        }else{
            getEditText().setClickable(false);
            getEditText().setFocusable(false);
        }
    }


    public void setEditable(boolean isEdiable) {
        if(isEdiable){
            getValueView().setTextColor(getView().getContext().getResources().getColor(R.color.form_text_gray));
        }else{
            getEditText().setTextColor(getView().getContext().getResources().getColor(R.color.light_gray));
            setDirectEditable(false);
        }
    }

    public TextView getTitleView(){
        return getTextView();
    }


    public EditText getValueView(){
        return getEditText();
    }

    public String getValue() {
        return getEditText().getText().toString();
    }
}
