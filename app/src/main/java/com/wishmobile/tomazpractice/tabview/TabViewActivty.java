package com.wishmobile.tomazpractice.tabview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wishmobile.tomazpractice.R;

public class TabViewActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_activty);

        initTabFragment();
    }

    private void initTabFragment() {
        TabFragment tabFragment = TabFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, tabFragment).commit();

    }


}
