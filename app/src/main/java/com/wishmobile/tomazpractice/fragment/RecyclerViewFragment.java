package com.wishmobile.tomazpractice.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.RCVAdapter;
import com.wishmobile.tomazpractice.data.DummyDatas;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by TomazWang on 2016/10/17.
 */

public class RecyclerViewFragment extends Fragment {

    @BindView(R.id.list_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.btn_linear)
    Button linearBtn;

    @BindView(R.id.btn_grid)
    Button gridBtn;

    @BindView(R.id.btn_flow)
    Button flowBtn;


    private ArrayList<DummyDatas> mDummyDates = new ArrayList<>();

    // for RecyclerView
    private RCVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    // layout type
    public static final int LINER_LAYOUT = 0;
    public static final int GRID_LAYOUT = 1;
    private static final String KEY_LAYOOUT_MABAGER_TYPE = "layoutManagerType";

    @IntDef({LINER_LAYOUT, GRID_LAYOUT})
    public @interface LayoutManagerType {}

    @LayoutManagerType
    protected int mCurrentLayoutManagerType = LINER_LAYOUT;





    public static RecyclerViewFragment newInstance() {

        RecyclerViewFragment fragment = new RecyclerViewFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        ButterKnife.bind(this, view);

        initData();
        createRecyclerView();



        return view;
    }

    private void initData() {

        for(int i=0; i<8; i++){
            String title = "Title "+i;
            String name = "Name "+i;
            mDummyDates.add(new DummyDatas(title, name, R.drawable.analytics));
        }
    }


    private void createRecyclerView() {

        mAdapter = new RCVAdapter(mDummyDates);



    }


    @OnClick(R.id.btn_add)
    public void addItem(){

    }


    @OnClick(R.id.btn_remove)
    public void removeItem(){

    }



}

