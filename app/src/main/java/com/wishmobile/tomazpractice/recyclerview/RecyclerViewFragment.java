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
    public @interface LayoutManagerType {
    }

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

        for (int i = 0; i < 8; i++) {
            mDummyDates.add(new DummyDatas(i));
        }

        mAdapter = new RCVAdapter(mDummyDates);
    }


    private void createRecyclerView() {
        mRecyclerView.setAdapter(mAdapter);
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
    }


    private void setRecyclerViewLayoutManager(@LayoutManagerType int type) {


        switch (type) {
            case GRID_LAYOUT:
                mLayoutManager = new GridLayoutManager(getActivity(), mSpanCount);
                mCurrentLayoutManagerType = GRID_LAYOUT;
                mAdapter.setFlow(false);
                break;

            case FLOW_LAYOUT:
                mLayoutManager = new StaggeredGridLayoutManager(mSpanCount - 1, StaggeredGridLayoutManager.VERTICAL);
                mCurrentLayoutManagerType = FLOW_LAYOUT;
                mAdapter.setFlow(true);
                break;

            case LINER_LAYOUT:
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LINER_LAYOUT;
                mAdapter.setFlow(false);
                break;


        }

        mRecyclerView.setLayoutManager(mLayoutManager);

    }


    @OnClick(R.id.btn_addFromBottom)
    public void addItem() {
        int id = (mDummyDates.size() == 0) ? 0 : mDummyDates.get(mDummyDates.size() - 1).getId() + 1;
        mAdapter.addItem(new DummyDatas(id));

    }

    @OnClick(R.id.btn_addFromTop)
    public void insertItem() {
        int position = 0;
        int id = (mDummyDates.size() == 0) ? 0 : mDummyDates.get(0).getId() - 1;
        mAdapter.addItem(position, new DummyDatas(id));

    }

    @OnClick(R.id.btn_removeTop)
    public void pullItem() {
        mAdapter.removeFirstItem();
    }


    @OnClick(R.id.btn_removeBottom)
    public void removeItem() {
        mAdapter.removeLastItem();
    }

    @OnClick(R.id.btn_clear)
    public void removeAllItem() {
        mAdapter.clear();
    }

    @OnClick(R.id.btn_linear)
    public void changeToLinear() {
        setRecyclerViewLayoutManager(LINER_LAYOUT);
    }

    @OnClick(R.id.btn_grid)
    public void changeToGrid() {
        setRecyclerViewLayoutManager(GRID_LAYOUT);
    }

    @OnClick(R.id.btn_flow)
    public void changeToFlow() {
        setRecyclerViewLayoutManager(FLOW_LAYOUT);
    }


}

