package com.wishmobile.tomazpractice.network.imageapi;

import android.support.annotation.NonNull;
import android.util.Log;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.wishmobile.tomazpractice.network.BasicResponse;
import com.wishmobile.tomazpractice.network.data.ItemResult;

import java.util.ArrayList;

import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by TomazWang on 2016/10/26.
 */

public class ImageApi {



    public static class Request extends RetrofitSpiceRequest<Response, Query> {

        private final RequestBody body;

        public Request(@NonNull RequestBody body) {
            super(Response.class, Query.class);
            this.body = body;
        }

        @Override
        public Response loadDataFromNetwork() throws Exception {
            return getService().search(body);
        }
    }

    public static class Listener implements RequestListener<Response> {

        private static final String TAG = ImageApi.class.getSimpleName() + "." + Listener.class.getSimpleName();

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.w(TAG, "onRequestFailure: "+spiceException.getCause().toString() );
        }

        @Override
        public void onRequestSuccess(Response response) {
        }
    }



    public static class Response  extends BasicResponse {
        ItemResult results;

        public Integer next;
        public Integer getNext() {
            return next;
        }
        public ItemResult getResults() {
            return results;
        }
    }



    public static class RequestBody{
        int store_id;
        int[] category_ids;
        String keyword;
        int offset;


        public RequestBody(int store_id, int[] category_ids, String keyword, int offset) {
            this.store_id = store_id;
            this.category_ids = category_ids;
            this.keyword = keyword;
            this.offset = offset;
        }

        public static class Builder{
            int store_id = 1;
            ArrayList<Integer> category_ids = new ArrayList<>();
            String keyword = "";
            int offset = 0;


            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public void addCategory_ids(int category_id) {
                this.category_ids.add(category_id);
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public RequestBody build(){


                int[] category_ids = new int[this.category_ids.size()];
                for(int i = 0; i < this.category_ids.size(); i++){
                    category_ids[i] = this.category_ids.get(i);
                }

                return new RequestBody(store_id, category_ids, keyword, offset);
            }
        }

    }

    public interface Query {
        @POST("/search")
        Response search(@Body RequestBody body);
    }

}
