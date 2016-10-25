package com.wishmobile.tomazpractice.widget.formview;

import android.content.Context;
import android.support.annotation.NonNull;

import com.linkwish.widget.FormView;
import com.wishmobile.tomazpractice.R;

/**
 * Created by TomazWang on 2016/10/24.
 */

public class ScrollerPicker extends FormView.ItemView {


    public ScrollerPicker(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_scroller_picker;
    }


}
