package com.wishmobile.tomazpractice.wallet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.linkwish.widget.FormView;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.view.BaseFragment;

import butterknife.BindView;

import static android.content.ContentValues.TAG;


public class DisplayFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    // bind components
    @BindView(R.id.form_view)
    FormView formView;

    public DisplayFragment() {
        // Required empty public constructor
    }

    public static DisplayFragment newInstance() {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Log.w(TAG, "onAttach: "
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            Log.w(TAG, "onAttach: "
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_display;
    }

    @Override
    protected void onCreateView(View view) {

        initFromView();

    }

    private void initFromView() {

        FormView.Adapter adapter = new FormView.Adapter();
        // TODO: add item.

    }


    public interface OnFragmentInteractionListener {
    }
}
