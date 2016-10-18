package com.wishmobile.tomazpractice.drawermenu;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wishmobile.tomazpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerActivity extends AppCompatActivity {

    private static final String DRAWER_FRAGMENT_TITLE = "Navigation Drawer";
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        ButterKnife.bind(this);

        initToolBar();

        initDrawer();
    }

    private void initDrawer() {

        FragmentManager fm = getFragmentManager();
        DrawerFragment drawerFragment = DrawerFragment.newInstance();
        fm.beginTransaction().replace(R.id.nav_drawer, )

    }

    private void initToolBar() {

        this.setSupportActionBar(mToolBar);
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(DRAWER_FRAGMENT_TITLE);

            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

        }
    }

}
