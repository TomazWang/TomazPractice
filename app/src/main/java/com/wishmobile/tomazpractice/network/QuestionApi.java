package com.wishmobile.tomazpractice.network;

import android.util.Log;

import com.google.gson.Gson;
import com.linkwish.network.dynamicserverretrofit.DynamicServerRetrofitSpiceRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import retrofit.http.POST;

/**
 * Created by TomazWang on 2016/10/25.
 */

public class QuestionApi {

    private static final String TAG = QuestionApi.class.getSimpleName();

    public static class Requset extends DynamicServerRetrofitSpiceRequest<Question, Query> {

        public Requset() {
            super(Question.class, Query.class);
        }

        @Override
        public Question loadDataFromNetwork() throws Exception {
            Log.d(TAG, "loadDataFromNetwork");
            return getService().questions();
        }
    }

    public static class Lisenter implements RequestListener<Question>{

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.w(TAG, "onRequestFailure: "+spiceException.getCause().toString() );
        }

        @Override
        public void onRequestSuccess(Question response) {
            Log.d(TAG, "onRequestSuccess: Gson resulets = "+new Gson().toJson(response));
            Log.d(TAG, "onRequestSuccess: question = "+response.question);
            Log.d(TAG, "onRequestSuccess: vote_date = "+response.vote_date);
        }
    }


    public static class Question {

        String question;
        String vote_date;

    }



    // QueryInterface
    public interface Query {
        @POST("/questions")
        Question questions();


    }



}
