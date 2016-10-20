package com.wishmobile.tomazpractice.scrollview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wishmobile.tomazpractice.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollFragment extends Fragment {

    private static final String TAG = ScrollFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;

    private int currentCount = -1;


    @BindView(R.id.frame_cart)
    LinearLayout mCartView;
    private ArrayList<View> mItemViews = new ArrayList<>();

    public ScrollFragment() {
        // Required empty public constructor
    }

    public static ScrollFragment newInstance() {
        ScrollFragment fragment = new ScrollFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll, container, false);

        ButterKnife.bind(this, view);

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


    @OnClick(R.id.btn_remove)
    public void removeItem() {

        if(mItemViews.size() <= 0){
            return;
        }

        View view = mItemViews.get(mItemViews.size() - 1);

        mCartView.removeView(view);

        mItemViews.remove(view);

    }

    @OnClick(R.id.btn_add)
    public void addItem() {

        currentCount++;
        String itemName = "Item " + currentCount;

        TextView tv = new TextView(getActivity());
        tv.setText(itemName);
        tv.setTextSize(32f);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(64, 24, 64, 6);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);

        tv.setBackgroundColor(getActivity().getResources().getColor(R.color.colorLightPrimary));
        tv.setTextColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
        tv.setPadding(6,32,6,32);

        mCartView.addView(tv);

        mItemViews.add(tv);
    }


    public interface OnFragmentInteractionListener {
    }
}
