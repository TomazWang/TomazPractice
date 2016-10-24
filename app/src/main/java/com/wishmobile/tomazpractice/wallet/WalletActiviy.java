package com.wishmobile.tomazpractice.wallet;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.wishmobile.tomazpractice.R;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActiviy extends AppCompatActivity
        implements WalletListFragment.OnFragmentInteractionListener, WalletDrawerFragment.OnFragmentInteractionListener, SettingFragment.OnFragmentInteractionListener {

    private static final int PAGE_WALLET_LIST = 0;
    private static final int PAGE_SETTING = 1;

    private String TAG = WalletActiviy.class.getSimpleName();

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    private ArrayList<MenuItem> mMenuButtons = new ArrayList<>();

    // TODO: get string array from resource

    private int currentPageId = 0;

    // fragments
    private WalletListFragment walletListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_activiy);

        ButterKnife.bind(this);
        initToolBar();
        initDrawer();
        gotoPage(currentPageId);
    }

    private void initDrawer() {
        FragmentManager fm = getFragmentManager();

        String[] drawerList = getResources().getStringArray(R.array.wallet_drawer_list);

        WalletDrawerFragment walletDrawerFragment = WalletDrawerFragment.newInstance(drawerList);
        fm.beginTransaction().replace(R.id.nav_drawer, walletDrawerFragment).commit();
    }

    private void initToolBar() {
        this.setSupportActionBar(mToolBar);
        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(getString(R.string.title_wallet));
        }

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.navigatiion_drawer_open, R.string.navigation_drawer_close) {


            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                showMenuButton();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideMenuButton();
            }
        };


        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void hideMenuButton() {


        if (mMenuButtons.size() > 0) {
            for (MenuItem menuBtn : mMenuButtons) {
                menuBtn.setVisible(false);
            }
        }
    }

    private void showMenuButton() {


        if (mMenuButtons.size() > 0) {
            for (MenuItem menuBtn : mMenuButtons) {
                menuBtn.setVisible(true);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "onActivityResult: requestCode =" + requestCode + ", resultCode =" + resultCode);

        if (requestCode == NewWalletActivity.RESULT_NEW_WALLET) {
            switch (resultCode) {
                case RESULT_OK:
                    Bundle bundle = data.getExtras();

                    if (bundle != null) {
                        String title = bundle.getString(NewWalletActivity.KEY_NEW_WALLET_TITLE);
                        int budget = bundle.getInt(NewWalletActivity.KEY_BUDGET);
                        walletListFragment.addNewWallet(title, budget);

                    } else {
                        Log.w(TAG, "onActivityResult: argument bundle == null");
                    }
            }
        }


    }


    // -- Fragment interaction listeners

    // WalletListFragmet
    @Override
    public void setMenuButtons(MenuItem... menuButtons) {
        Collections.addAll(this.mMenuButtons, menuButtons);
    }


    @Override
    public void gotoWallet(long walletId) {
        Log.d(TAG, "gotoWallet");

        Intent intent = new Intent();
        intent.setClass(this, WalletPageActivity.class);
        intent.putExtras(WalletPageActivity.DataBundle.setId(walletId));

        startActivity(intent);
    }

    // DrawerFragment
    @Override
    public void onListItemClick(int position) {
        gotoPage(position);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void gotoPage(int pageId) {
        Log.d(TAG, "switchToPage: switch to " + pageId);

        switch (pageId) {
            case PAGE_WALLET_LIST:
                // wallet list
                switchToWalletList();
                break;
            case PAGE_SETTING:
                // Setting
                switchToSetting();
                break;
            case PAGE_PROFILE:
                // profile setting
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case PAGE_DISPLAY_SETTING:
                // display setting
                startActivity(new Intent(this, DisplaySettingActivity.class));
                break;
        }
    }

    private void switchToWalletList() {
        FragmentManager fm = getFragmentManager();
        walletListFragment = WalletListFragment.newInstance();
        fm.beginTransaction().replace(R.id.frame_content, walletListFragment).commit();

        currentPageId = PAGE_WALLET_LIST;
    }

    private void switchToSetting() {
        FragmentManager fm = getFragmentManager();
        SettingFragment settingFragment = SettingFragment.newInstance();
        fm.beginTransaction().replace(R.id.frame_content, settingFragment).commit();

        currentPageId = PAGE_SETTING;
    }



}
