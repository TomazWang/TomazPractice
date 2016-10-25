package com.wishmobile.tomazpractice.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;
import com.wishmobile.tomazpractice.R;

public class NetworkActivity extends AppCompatActivity {

    private SpiceManager questionApiSpiceManager = new SpiceManager(QuestionApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }


    @Override
    protected void onStart() {
        super.onStart();
        questionApiSpiceManager.start(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        // fetch data from
        QuestionApi.Requset testRequest = new QuestionApi.Requset();
        questionApiSpiceManager.execute(testRequest, new QuestionApi.Lisenter());
    }

    @Override
    protected void onStop() {
        super.onStop();
        questionApiSpiceManager.shouldStop();
    }
}



