package com.wishmobile.tomazpractice.network;

import com.linkwish.network.dynamicserverretrofit.DynamicServerRetrofitGsonSpiceService;

/**
 * Created by TomazWang on 2016/10/25.
 */

public class QuestionApiService extends DynamicServerRetrofitGsonSpiceService{

    @Override
    protected boolean isHTTPS() {
        return true;
    }

    @Override
    protected String getServerUrl() {
        return API_CONSTANTS.PRIVATE_TEST_URL;
    }

}
