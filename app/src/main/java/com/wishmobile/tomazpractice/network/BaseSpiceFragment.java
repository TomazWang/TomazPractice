package com.wishmobile.tomazpractice.network;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.SpiceService;
import com.wishmobile.tomazpractice.view.BaseFragment;

/**
 * Created by TomazWang on 2016/10/26.
 */

public abstract  class BaseSpiceFragment<T extends SpiceService> extends BaseFragment{

    private SpiceManager spiceManager;

    public abstract Class<T> getSpiceServiceClass();

    public BaseSpiceFragment() {
        this.spiceManager = new SpiceManager(getSpiceServiceClass());
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }


    @Override
    public void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }


    public SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
