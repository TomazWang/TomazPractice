package com.wishmobile.tomazpractice.tabview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wishmobile.tomazpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomazWang on 2016/10/18.
 */

public class ImageTabFragment extends BaseTabFragment{

    private static final String KEY_TAB_TITLE = "key_tabTitle";
    private static final String KEY_IMG_RES = "key_imageResource";
    private String mTabTitle;
    private int mImageRes;

    @BindView(R.id.iv_img)
    ImageView mImgView;

    public static ImageTabFragment newInstance(String tabTitle, int imgRes) {

        Bundle args = new Bundle();
        args.putString(KEY_TAB_TITLE, tabTitle);
        args.putInt(KEY_IMG_RES, imgRes);

        ImageTabFragment fragment = new ImageTabFragment();
        fragment.setTitle(tabTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            this.mTabTitle = bundle.getString(KEY_TAB_TITLE);
            this.mImageRes = bundle.getInt(KEY_IMG_RES);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_content, container, false);
        ButterKnife.bind(this, view);
        mImgView.setImageDrawable(getResources().getDrawable(mImageRes));
        return view;
    }
}
