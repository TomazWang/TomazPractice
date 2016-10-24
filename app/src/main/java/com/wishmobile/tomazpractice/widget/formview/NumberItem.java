package com.wishmobile.tomazpractice.widget.formview;

import android.content.Context;
import android.text.InputType;

/**
 * Created by TomazWang on 2016/10/24.
 */

public class NumberItem extends EditItem {

    public NumberItem(Context context) {
        super(context);
        setToNumber();
    }

    public NumberItem(Context context, int icon, int text, int hint) {
        super(context, icon, text, hint);
        setToNumber();
    }

    public NumberItem(Context context, int text, String hint) {
        super(context, text, hint);
        setToNumber();
    }

    public NumberItem(Context context, int icon, int text, String hint) {
        super(context, icon, text, hint);
        setToNumber();
    }

    private void setToNumber(){
        getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
    }

}
