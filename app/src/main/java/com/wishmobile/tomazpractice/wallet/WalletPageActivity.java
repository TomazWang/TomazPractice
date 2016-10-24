package com.wishmobile.tomazpractice.wallet;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.wallet.data.CostItem;
import com.wishmobile.tomazpractice.wallet.data.Wallet;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WalletPageActivity extends AppCompatActivity {


    private Wallet wallet;


    @BindView(R.id.txt_balance_value)
    TextView balanceText;

    @BindView(R.id.txt_budget_value)
    TextView budgetText;

    @BindView(R.id.list_costItems)
    RecyclerView costItemList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private CostItemListAdapter adapter;

    private String TAG = WalletPageActivity.class.getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_page);

        initData();
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {

        adapter = new CostItemListAdapter(wallet);
        costItemList.setAdapter(adapter);
        costItemList.setLayoutManager(new LinearLayoutManager(this));

    }



    private void initData() {

        // get data from bundle
        Bundle bundle = this.getIntent().getExtras();
        long id = bundle.getLong(DataBundle.KEY_ID);

        this.wallet = getDataFromDb(id);
    }

    private Wallet getDataFromDb(long id) {

        // TODO: fetch data from db

        // dummy data
        Wallet wallet = new Wallet(id, Wallet.CASH, "Pocket", 8000);
        wallet.addCostItem(new CostItem(0, 60, "lunch"));
        wallet.addCostItem(new CostItem(0, 299, "USB cable"));
        wallet.addCostItem(new CostItem(0, 30, "tissue paper"));

        return wallet;
    }


    public static class DataBundle {

        private static final String KEY_ID = "920";


        private static Bundle bundle;

        private static Bundle getBunble() {
            if (bundle == null) {
                bundle = new Bundle();
            }

            return bundle;
        }


        public static Bundle setId(long id) {
            getBunble().putLong(KEY_ID, id);
            return bundle;
        }

    }

}
