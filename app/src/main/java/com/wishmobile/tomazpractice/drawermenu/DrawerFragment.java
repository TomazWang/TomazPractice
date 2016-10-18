package com.wishmobile.tomazpractice.drawermenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wishmobile.tomazpractice.R;

public class DrawerFragment extends Fragment{

    public static DrawerFragment newInstance() {

        Bundle args = new Bundle();
        DrawerFragment fragment = new DrawerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_drawer);
    }
}
