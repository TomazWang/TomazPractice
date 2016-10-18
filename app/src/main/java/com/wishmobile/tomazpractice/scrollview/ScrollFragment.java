package com.wishmobile.tomazpractice.scrollview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishmobile.tomazpractice.R;

public class ScrollFragment extends Fragment {

    private static final String TAG = ScrollFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;

    public ScrollFragment() {
        // Required empty public constructor
    }

    public static ScrollFragment newInstance(String param1, String param2) {
        ScrollFragment fragment = new ScrollFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Log.w(TAG, "onAttach:  must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {

            mListener = (OnFragmentInteractionListener) activity;
        } else {
            Log.w(TAG, "onAttach:  must implement OnFragmentInteractionListener");
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
