package com.wishmobile.tomazpractice.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.wallet.data.Wallet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletListFragment extends Fragment {

    private ArrayList<Wallet> mWallets = new ArrayList<Wallet>();
    private WalletListAdapter walletListAdapter;



    public interface OnFragmentInteractionListener {
        void setMenuButtons(MenuItem... menuButtons);
    }

    private OnFragmentInteractionListener mListener;


    // menu items
    private MenuItem mNewWalletMenuButton;

    // bind components
    @BindView(R.id.list_wallet)
    RecyclerView walletList;



    // --- All about recycler view
    // layout type
    public static final int LINER_LAYOUT = 0;
    public static final int GRID_LAYOUT = 1;
    public static final int FLOW_LAYOUT = 2;

    private static final String KEY_LAYOOUT_MABAGER_TYPE = "layoutManagerType";
    private int mSpanCount = 2;

    private RecyclerView.LayoutManager mLayoutManager;

    @IntDef({LINER_LAYOUT, GRID_LAYOUT, FLOW_LAYOUT})
    public @interface LayoutManagerType {
    }

    @WalletListFragment.LayoutManagerType
    protected int mLayoutType = LINER_LAYOUT;

    public int getLayoutType() {
        return mLayoutType;
    }



    public WalletListFragment() { /* empty constructor */}

    private static final String TAG = WalletListFragment.class.getSimpleName();


    public static WalletListFragment newInstance() {
        WalletListFragment fragment = new WalletListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Log.w(TAG, "onAttach: "
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            Log.w(TAG, "onAttach: "
                    + " must implement OnFragmentInteractionListener");

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_wallet, menu);
        mNewWalletMenuButton = menu.findItem(R.id.miNew);
        mListener.setMenuButtons(mNewWalletMenuButton);
        super.onCreateOptionsMenu(menu, inflater);


        mNewWalletMenuButton.setOnMenuItemClickListener(item -> {
            newWallet();
            return true;
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wallet_list,container, false);
        ButterKnife.bind(this,view);
        initView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initData() {
        // fetch from database

        // add dummy data
        mWallets.add(new Wallet(0, Wallet.CASH, "Pocket", 7000));
        mWallets.add(new Wallet(0, Wallet.CARD, "MasterCard", 60000));
        mWallets.add(new Wallet(0, Wallet.CARD, "VISA", 45000));

        TypedArray colorArray = getActivity().getResources().obtainTypedArray(R.array.wallet_background);
        int[] colors = new int[colorArray.length()];
        for (int i =0; i < colors.length; i++){
            colors[i] = colorArray.getColor(i,0);
        }


        walletListAdapter = new WalletListAdapter(mWallets, getLayoutType() == FLOW_LAYOUT, colors);

    }


    private void initView() {

        walletList.setAdapter(walletListAdapter);

        switch (mLayoutType){
            case LINER_LAYOUT:
                walletList.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case FLOW_LAYOUT:
                walletList.setLayoutManager(new StaggeredGridLayoutManager(mSpanCount, StaggeredGridLayoutManager.VERTICAL));
                   break;

        }

    }



    private void newWallet() {

        // TODO: new wallet dialog

    }


}
