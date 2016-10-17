package com.wishmobile.tomazpractice.recyclerview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishmobile.tomazpractice.R;
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



    private ArrayList<DummyDatas> mDummyDates = new ArrayList<>();

    // for RecyclerView
    private RCVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    // layout type
    public static final int LINER_LAYOUT = 0;
    public static final int GRID_LAYOUT = 1;
    public static final int FLOW_LAYOUT = 2;

    private static final String KEY_LAYOOUT_MABAGER_TYPE = "layoutManagerType";
    private int mSpanCount = 4;


    @IntDef({LINER_LAYOUT, GRID_LAYOUT, FLOW_LAYOUT})
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
            String title = "=Title "+i;
            String name = "-Name "+i;
            mDummyDates.add(new DummyDatas(title, name, R.drawable.analytics));
        }

        mAdapter = new RCVAdapter(mDummyDates);
    }


    private void createRecyclerView() {
        mRecyclerView.setAdapter(mAdapter);
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
    }


    private void setRecyclerViewLayoutManager(@LayoutManagerType int type) {


        switch (type){
            case GRID_LAYOUT:
                mLayoutManager = new GridLayoutManager(getActivity(),mSpanCount);
                mCurrentLayoutManagerType = GRID_LAYOUT;

                break;

            case FLOW_LAYOUT:
                mLayoutManager = new StaggeredGridLayoutManager(mSpanCount, StaggeredGridLayoutManager.HORIZONTAL);
                mCurrentLayoutManagerType = FLOW_LAYOUT;
                break;

            case LINER_LAYOUT:
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LINER_LAYOUT;

                break;


        }

        mRecyclerView.setLayoutManager(mLayoutManager);

    }


    @OnClick(R.id.btn_add)
    public void addItem(){
        // TODO: add item
        int id = mDummyDates.size();
        mAdapter.addItem(new DummyDatas("=Title "+id, "-Name "+id, R.drawable.analytics));

    }


    @OnClick(R.id.btn_remove)
    public void removeItem(){
        // TODO: remove item
        mAdapter.removeLastItem();
    }

    @OnClick(R.id.btn_linear)
    public void changeToLinear(){
        setRecyclerViewLayoutManager(LINER_LAYOUT);
    }

    @OnClick(R.id.btn_grid)
    public void changeToGrid(){
        setRecyclerViewLayoutManager(GRID_LAYOUT);
    }

    @OnClick(R.id.btn_flow)
    public void changeToFlow(){
        setRecyclerViewLayoutManager(FLOW_LAYOUT);
    }





}

