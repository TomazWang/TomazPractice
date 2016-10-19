package com.wishmobile.tomazpractice.drawermenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.wishmobile.tomazpractice.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DrawerFragment extends Fragment {

    // -- datas
    private ArrayList<String> mDatas = new ArrayList<>();
    private ArrayList<String> mCartData;
    private ArrayList<String> mOrderData;
    private ArrayList<String> mAllData;


    // -- mode
    private int mCurrentMode = 0;

    private static final int MODE_CART = 756;
    private static final int MODE_ORDER = 340;
    private static final int MODE_ALL = 614;


    // -- widgets
    @BindView(R.id.lv_item)
    ListView mListView;

    @BindView(R.id.btn_nav_cart)
    Button mCartButton;

    @BindView(R.id.btn_nav_order)
    Button mOrderButton;

    @BindView(R.id.btn_nav_all_item)
    Button mAllItemButton;


    private Unbinder unbinder;
    private SimpleArrayAdapter mAdapter;

    public static DrawerFragment newInstance() {

        Bundle args = new Bundle();
        DrawerFragment fragment = new DrawerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_drawer, container, false);
        unbinder = ButterKnife.bind(this, view);

        initData();

        mAdapter = new SimpleArrayAdapter(this.getActivity(), mDatas);
        mListView.setAdapter(mAdapter);

        switchMode(MODE_CART);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_nav_cart)
    public void switchToCart() {
        switchMode(MODE_CART);
    }

    @OnClick(R.id.btn_nav_order)
    public void switchToOrder(){
        switchMode(MODE_ORDER);
    }

    @OnClick(R.id.btn_nav_all_item)
    public void switchAllItem(){
        switchMode(MODE_ALL);
    }

    @OnClick(R.id.btn_add)
    public void addItem(){

        switch (mCurrentMode){
            case MODE_ALL:
                mAllData.add("New Item");
                break;
            case MODE_CART:
                mCartData.add("New Cart");
                break;
            case MODE_ORDER:
                mOrderData.add("New Order");
                break;
        }

        setData(mCurrentMode);

    }

    private void switchMode(int mode) {
        if (mode == mCurrentMode) {
            return;
        }

//        setButtonColor(mCurrentMode, false);
        setData(mode);

        this.mCurrentMode = mode;

    }

    private void setData(int mode) {

        switch (mode) {
            case MODE_CART:
                mDatas.clear();
                mDatas.addAll(mCartData);

                break;

            case MODE_ORDER:
                mDatas.clear();
                mDatas.addAll(mOrderData);
                break;

            case MODE_ALL:
                mDatas.clear();
                mDatas.addAll(mAllData);
                break;
        }

        if(mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setButtonColor(int mode, boolean isSelected) {


        int colorSelected = getActivity().getResources().getColor(R.color.colorLightPrimary);
        int colorNotSelected = getActivity().getResources().getColor(R.color.colorLightPrimaryLightBlue);

        int color;
        if (isSelected) {
            color = colorSelected;
        } else {
            color = colorNotSelected;
        }

        switch (mode) {
            case MODE_CART:
                mCartButton.setBackgroundColor(color);
                break;
            case MODE_ALL:
                mAllItemButton.setBackgroundColor(color);
                break;
            case MODE_ORDER:
                mOrderButton.setBackgroundColor(color);
                break;
        }


    }


    private void initData() {
        createCartData();
        createOrderData();
        createAllItemData();
    }

    private void createCartData() {
        mCartData = new ArrayList<String>();
        mCartData.add("Cart 1");
        mCartData.add("Cart 2");

    }


    private void createOrderData() {
        mOrderData = new ArrayList<String>();
        mOrderData.add("Order 1");
        mOrderData.add("Order 2");
        mOrderData.add("Order 3");
        mOrderData.add("Order 4");
        mOrderData.add("Order 5");
        mOrderData.add("Order 6");



    }

    private void createAllItemData() {
        mAllData = new ArrayList<String>();
        mAllData.add("All Item 1");
        mAllData.add("All Item 2");
        mAllData.add("All Item 3");
        mAllData.add("All Item 4");
        mAllData.add("All Item 5");
        mAllData.add("All Item 6");
        mAllData.add("All Item 7");
    }
}
