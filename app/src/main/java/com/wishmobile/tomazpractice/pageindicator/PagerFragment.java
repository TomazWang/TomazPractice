package com.wishmobile.tomazpractice.pageindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wishmobile.tomazpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomazWang on 2016/10/19.
 */

public class PagerFragment extends Fragment{

    private static final String ARG_TITLE = "argument_title";

    private String mTitle;

    @BindView(R.id.btn_placeHolder)
    Button mPlaceHolderButton;

    public static PagerFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);


        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if(args != null){
            this.mTitle = args.getString(ARG_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page_indicator, container, false);
        ButterKnife.bind(this, view);

        mPlaceHolderButton.setText(mTitle);

        return view;
    }


}
