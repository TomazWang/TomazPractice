package com.wishmobile.tomazpractice.wallet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.linkwish.widget.FormView;
import com.linkwish.widget.formitem.LabelItem;
import com.linkwish.widget.formitem.PickerItem;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.widget.formview.EditItem;
import com.wishmobile.tomazpractice.widget.formview.NumberItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewWalletDialog extends DialogFragment {

    private static final String TAG = NewWalletDialog.class.getSimpleName();
    private AlertDialog dialog;


    @BindView(R.id.form_view)
    FormView formView;

    // form items
    private EditItem title;
    private PickerItem startDate;
    private PickerItem endDate;
    private NumberItem budget;

    public static NewWalletDialog newInstance() {

        Bundle args = new Bundle();

        NewWalletDialog fragment = new NewWalletDialog();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_wallet, null);

        ButterKnife.bind(this, view);

        initFormView();

        dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

        return dialog;
    }


    @OnClick(R.id.btn_summit)
    public void createNewWallet(){

    }


    private void initFormView() {

        FormView.Adapter adapter = new FormView.Adapter();

        title = new EditItem(getActivity(), R.string.wallet_name_label, "name");
        startDate = new PickerItem(getActivity(), R.string.start_date, R.string.start_date);
        endDate = new PickerItem(getActivity(), R.string.end_date, R.string.end_date);
        budget = new NumberItem(getActivity(), R.string.budget, "");


        adapter.add(title);
        adapter.add(new LabelItem(getActivity(), R.string.date));
        adapter.add(startDate);
        adapter.add(endDate);

        formView.setAdapter(adapter);


    }


}
