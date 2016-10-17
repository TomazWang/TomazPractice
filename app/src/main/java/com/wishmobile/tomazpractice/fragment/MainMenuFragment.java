package com.wishmobile.tomazpractice.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wishmobile.tomazpractice.MenuButtonListAdapter;
import com.wishmobile.tomazpractice.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainMenuFragment extends Fragment {
    private static final String KEY_MENU_ITEMS = "key_menu_items";
    private static final String TAG = MainMenuFragment.class.getSimpleName();


    private OnMainMenuFragmentInteractionListener mListener;
    private ListView menuButtons;
    private MenuButtonListAdapter menuButtonListAdapter;
    private String[] menuItems;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    public static MainMenuFragment newInstance(String[] menuItems) {
        MainMenuFragment fragment = new MainMenuFragment();
        // add bundle arguments
        Bundle bundle = new Bundle();
        bundle.putCharSequenceArray(KEY_MENU_ITEMS, menuItems);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();


        if (bundle != null) {
            this.menuItems = (String[]) bundle.getCharSequenceArray(KEY_MENU_ITEMS);
        }
        initAdapter();
    }

    private void initAdapter() {
        menuButtonListAdapter = new MenuButtonListAdapter(new ArrayList<>(Arrays.asList(menuItems)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        menuButtons = (ListView) view.findViewById(R.id.lv_menuButtons);
        menuButtons.setAdapter(menuButtonListAdapter);
        menuButtons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + menuButtonListAdapter.getItem(position));
                mListener.clickItem(position);
            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        if (context instanceof OnMainMenuFragmentInteractionListener) {
            mListener = (OnMainMenuFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMainMenuFragmentInteractionListener");
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnMainMenuFragmentInteractionListener) {
            mListener = (OnMainMenuFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnMainMenuFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMainMenuFragmentInteractionListener {
        void clickItem(int position);
    }
}
