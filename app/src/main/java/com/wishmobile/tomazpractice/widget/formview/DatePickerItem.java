package com.wishmobile.tomazpractice.widget.formview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.TextView;

import com.linkwish.widget.FormView;
import com.wishmobile.tomazpractice.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomazWang on 2016/10/24.
 */

public class DatePickerItem extends FormView.ItemView{

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.value)
    TextView value;

    private DatePickerDialog datePickerDialog;
    private int mYear;
    private int mMonth;
    private int mDate;

    public DatePickerItem(@NonNull Context context) {
        this(context, R.string.date);
    }

    public DatePickerItem(@NonNull Context context, @StringRes int stringRes){

        this(context, stringRes, Calendar.getInstance());
    }

    public DatePickerItem(@NonNull Context context, @StringRes int titleStringRes, Calendar defaultDate){

        super(context);
        ButterKnife.bind(this, getView());

        title.setText(titleStringRes);

        mYear = defaultDate.get(Calendar.YEAR);
        mMonth = defaultDate.get(Calendar.MONTH);
        mDate = defaultDate.get(Calendar.DATE);

        setDate(mYear,mMonth,mDate);

        initView();


    }

    private void initView() {
        getView().setOnClickListener(v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(getView().getContext(), (DatePickerDialog.OnDateSetListener) (view, year, month, day) -> {
            mYear = year;
            mMonth = month;
            mDate = day;
            setDate(mYear, mMonth, mDate);

        },mYear, mMonth,mDate);

        datePickerDialog.show();
    }

    private void setDate(int mYear, int mMonth, int mDate) {
        value.setText(getFormattedDate(mYear,mMonth,mDate));
    }

    private String getFormattedDate(int y, int m, int d) {

        String month ="";

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();

        if( m >= 0 && m < 12){
            month = months[m-1];
        }

        return String.format("%s. %d, %d", month, d, y);

    }


    @Override
    public int getLayoutId() {
        return R.layout.view_item_date_picker;
    }


    public int getmYear() {
        return mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmDate() {
        return mDate;
    }
}
