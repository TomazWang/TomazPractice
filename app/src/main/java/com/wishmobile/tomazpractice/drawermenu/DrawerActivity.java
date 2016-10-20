package com.wishmobile.tomazpractice.drawermenu;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.wishmobile.tomazpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerActivity extends AppCompatActivity {

    private static final String DRAWER_FRAGMENT_TITLE = "Navigation Drawer";
    private static final String TAG = DrawerActivity.class.getSimpleName();
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.spinner_toolBar)
    Spinner mSpinnerInToolBar;

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
        fm.beginTransaction().replace(R.id.nav_drawer, drawerFragment).commit();

    }

    private void initToolBar() {

        this.setSupportActionBar(mToolBar);
        ActionBar actionBar = this.getSupportActionBar();

        if(actionBar != null){
            Log.w(TAG, "initToolBar: null actionBar");

            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);

        }

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.navigatiion_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        final ArrayAdapter<CharSequence> spinnerInToolbarAdapter = ArrayAdapter.createFromResource(this, R.array.drawer_drop_down_2, android.R.layout.simple_spinner_dropdown_item);
        mSpinnerInToolBar.setAdapter(spinnerInToolbarAdapter);
        mSpinnerInToolBar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(mSpinnerInToolBar.getTag() == null){
                    mSpinnerInToolBar.setTag(this);
                    return;
                }

                Toast.makeText(DrawerActivity.this, spinnerInToolbarAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(DrawerActivity.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_drawer_activity, menu);

        MenuItem spinnerMenuItem = menu.findItem(R.id.miSpinner);
        final Spinner spinner = (Spinner) MenuItemCompat.getActionView(spinnerMenuItem);

        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.drawer_drop_down, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getTag() == null){
                    spinner.setTag(this);
                    return;
                }
                Toast.makeText(DrawerActivity.this, spinnerAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(DrawerActivity.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.miBug:
                Toast.makeText(this,"Click on Bug",Toast.LENGTH_SHORT).show();
                break;

            case R.id.miProfile:
                Toast.makeText(this,"Click on Face",Toast.LENGTH_SHORT).show();
                break;
        }


        return true;
    }
}
