package com.wishmobile.tomazpractice.calculatorview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishmobile.tomazpractice.R;

public class CalculatorFragment extends Fragment {

    private static final String TAG = CalculatorFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public static CalculatorFragment newInstance() {
        CalculatorFragment fragment = new CalculatorFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Log.w(TAG, "onAttach: should implemnts OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof  OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            Log.w(TAG, "onAttach: should implemnts OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
    }
}
