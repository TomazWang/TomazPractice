package com.wishmobile.tomazpractice.network;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.network.data.Question;
import com.wishmobile.tomazpractice.network.view.QuestionListAdapetr;
import com.wishmobile.tomazpractice.wallet.NewWalletActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionApiActivity extends AppCompatActivity {

    private static final String TAG = NewWalletActivity.class.getSimpleName();

    private SpiceManager questionApiSpiceManager = new SpiceManager(QuestionApiService.class);
    private ArrayList<Question> reultQuestions = new ArrayList<>();
    private QuestionListAdapetr adapter;


    @BindView(R.id.list_results)
    RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);

        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        questionApiSpiceManager.start(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        startProgressingDialog();

        initData();
    }

    private void startProgressingDialog() {
        if (this.progressDialog == null || !this.progressDialog.isShowing()) {
            this.progressDialog = ProgressDialog.show(this, "", "Connecting...");
        }
    }

    private void closeProgressingDialog() {
        if (this.progressDialog != null && this.progressDialog.isShowing()){
            this.progressDialog.dismiss();
        }
    }

    private void initView() {
        adapter = new QuestionListAdapetr(reultQuestions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        // fetch data from
        QuestionApi.Requset testRequest = new QuestionApi.Requset();
        questionApiSpiceManager.execute(testRequest, new QuestionApi.Lisenter() {
            @Override
            public void onRequestSuccess(Question response) {
                super.onRequestSuccess(response);
                Log.d(TAG, "onRequestSuccess: add to adpter");
                adapter.addItem(response);
                closeProgressingDialog();
            }


        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        questionApiSpiceManager.shouldStop();
    }
}



