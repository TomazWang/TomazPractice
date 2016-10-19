package com.wishmobile.tomazpractice.pageindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.data.PageData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by TomazWang on 2016/10/19.
 */

public class PagerFragment extends Fragment {

    private static final String ARG_TITLE = "argument_title";

    private PageData pageData;


    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.iv_img)
    ImageView mImage;

    public static PagerFragment newInstance(PageData data) {

        PagerFragment fragment = new PagerFragment();
        fragment.setPageData(data);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this, view);

        if (pageData != null) {
            mTitle.setText(pageData.getTitle());
            displayImage();
        }
        return view;
    }

    public void displayImage() {
        Glide.with(getActivity())
                .load(pageData.getImageResId())
                .into(mImage);
    }

    @OnClick(R.id.btn_change_img)
    public void onChangeImage() {
        pageData.setRandomImg();
        displayImage();
    }


    public void setPageData(PageData pageData) {
        this.pageData = pageData;
    }
}
