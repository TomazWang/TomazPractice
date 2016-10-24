package com.wishmobile.tomazpractice.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.linkwish.widget.FormView;
import com.linkwish.widget.formitem.LabelItem;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.widget.formview.DatePickerItem;
import com.wishmobile.tomazpractice.widget.formview.EditItem;
import com.wishmobile.tomazpractice.widget.formview.NumberItem;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewWalletActivity extends AppCompatActivity {

    public static final int RESULT_NEW_WALLET = 337;

    public static final String KEY_NEW_WALLET_TITLE = "key_new_walllet_title";
    public static final String KEY_BUDGET = "key_budget";

    @BindView(R.id.form_view)
    FormView formView;

    // form items
    private EditItem title;
    private DatePickerItem startDate;
    private DatePickerItem endDate;
    private NumberItem budget;


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wallet);

        ButterKnife.bind(this);
        initToolbar();
        initFormView();
    }


    private void initFormView() {

        FormView.Adapter adapter = new FormView.Adapter();

        title = new EditItem(this, R.string.wallet_name_label, "name");
        title.getEditText().setMaxLines(1);
        title.getEditText().setSingleLine(true);
        title.getEditText().setLines(1);
        title.getTextView().setSelected(false);
        title.getTextView().setImeOptions(EditorInfo.IME_ACTION_DONE);

        // date
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        endCal.set(
                startCal.get(Calendar.YEAR),
                startCal.get(Calendar.MONTH),
                startCal.get(Calendar.DATE) + 7);


        startDate = new DatePickerItem(this, R.string.start_date);
        endDate = new DatePickerItem(this, R.string.end_date);

        // budget
        budget = new NumberItem(this, R.string.budget, "");
        budget.getValueView().setHint("0");

        // add into adapter
        adapter.add(new LabelItem(this, R.string.blank));
        adapter.add(title);

        adapter.add(new LabelItem(this, R.string.date));
        adapter.add(startDate);
        adapter.add(endDate);

        adapter.add(new LabelItem(this, R.string.blank));
        adapter.add(budget);

        formView.setAdapter(adapter);


    }


    private void initToolbar() {

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.btn_summit)
    public void createNewWallet() {
        Toast.makeText(this, "create wallet", Toast.LENGTH_SHORT).show();

        String walletName = title.getValue();
        int budget = this.budget.getNumberValue();

        if(walletName.equals("")){
            Toast.makeText(this, "blank wallet name", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent();
        Bundle args = new Bundle();
        args.putString(KEY_NEW_WALLET_TITLE, walletName);
        args.putInt(KEY_BUDGET, budget);

        intent.putExtras(args);

        setResult(RESULT_OK, intent);

        finish();
    }


}
