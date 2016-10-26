package com.wishmobile.tomazpractice.network.imageapi;

import com.linkwish.network.dynamicserverretrofit.DynamicServerRetrofitGsonSpiceService;
import com.wishmobile.tomazpractice.network.API_CONSTANTS;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by TomazWang on 2016/10/26.
 */

public class ImageApiService extends DynamicServerRetrofitGsonSpiceService {


    @Override
    protected String getServerUrl() {
        return API_CONSTANTS.QSIRE_URL;
    }

    RequestInterceptor requestInterceptor = request ->
    {
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept-Language", "zh-tw");
    };

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return super.createRestAdapterBuilder().setRequestInterceptor(requestInterceptor);
    }


}
