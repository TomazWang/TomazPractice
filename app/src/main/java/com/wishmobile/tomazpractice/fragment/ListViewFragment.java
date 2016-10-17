package com.wishmobile.tomazpractice.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wishmobile.tomazpractice.ListViewAdapter;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.data.DummyDatas;

import java.util.ArrayList;

public class ListViewFragment extends Fragment{


    private static final String LISTVIEW_FRAGMENT_TITLE = "ListView";
    private static final String TAG = ListViewAdapter.class.getSimpleName();

    private ListViewAdapter mListAdapter;
    private SingleChoiceAdapter mSingleAdapter;
    private ArrayList<DummyDatas> dummyDatases = new ArrayList<>();

    private Toolbar mToolbar;


    private boolean hasImage = true;
    private ListView mDummyList;
    private ListView mSingleList;


    public static ListViewFragment newInstace() {
        ListViewFragment fragment = new ListViewFragment();
        Bundle bundle = new Bundle();
        // put arguments

        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initListData();
        initSingleData();

    }

    private void initSingleData() {

        ArrayList<String> singleData = new ArrayList<>();

        for(int i =0;i<40;i++){
            singleData.add("Title "+i);
        }

        mSingleAdapter = new SingleChoiceAdapter(singleData);

    }

    private void initListData() {

        // dummy icons
        int[] icons = new int[]{
                R.drawable.analytics,
                R.drawable.business_partnership,
                R.drawable.checklist,
                R.drawable.chess,
                R.drawable.competition,
                R.drawable.contract,
                R.drawable.goal,
                R.drawable.line_chart,
                R.drawable.megaphone,
                R.drawable.paper_plane,
                R.drawable.plant,
                R.drawable.rocket,
                R.drawable.strategy,
                R.drawable.trophy,
                R.drawable.target
        };



        for(int i=0; i<=36; i++){

            DummyDatas dummy = new DummyDatas(
                    "Title "+i,
                    "Name "+i,
                    icons[i%icons.length]
            );

            dummyDatases.add(dummy);
        }

        mListAdapter = new ListViewAdapter(dummyDatases);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);




        // get components
        mDummyList = (ListView)view.findViewById(R.id.lv_dummyList);
        mSingleList = (ListView)view.findViewById(R.id.lv_singleList);
        mToolbar = (Toolbar) view.findViewById(R.id.fragment_toolbar);

        initToolbar();
        createList(mDummyList);
        createSingle(mSingleList);



        return view;
    }

    private void createList(ListView dummyList) {

        dummyList.setAdapter(mListAdapter);
        dummyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = dummyDatases.get(position).getTitle();
                Toast.makeText(getActivity(), "You've touched "+title, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void createSingle(ListView singleList) {

        singleList.setAdapter(mSingleAdapter);
        singleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick");
                mSingleAdapter.setSelectedIndex(position);
                mSingleAdapter.notifyDataSetChanged();
            }
        });

    }



    private void initToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);

        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar != null){
            actionBar.setDefaultDisplayHomeAsUpEnabled(false);
        }

        actionBar.setTitle(LISTVIEW_FRAGMENT_TITLE);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.listview_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_listWithImg:
                if(!hasImage){
                    changeToListWithImage();
                }
                break;

            case R.id.menu_listSingleChoice:
                if(hasImage){
                    changeToListSingleChoice();
                }
                break;
        }


        return true;

    }

    private void changeToListSingleChoice() {
        mDummyList.setVisibility(View.GONE);
        mSingleList.setVisibility(View.VISIBLE);
        hasImage = false;
    }

    private void changeToListWithImage() {
        mDummyList.setVisibility(View.VISIBLE);
        mSingleList.setVisibility(View.GONE);
        hasImage = true;

    }
}
