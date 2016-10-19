package com.wishmobile.tomazpractice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wishmobile.tomazpractice.calculatorview.CalculatorFragment;
import com.wishmobile.tomazpractice.drawermenu.DrawerActivity;
import com.wishmobile.tomazpractice.fragment.MainMenuFragment;
import com.wishmobile.tomazpractice.listview.ListViewFragment;
import com.wishmobile.tomazpractice.pageindicator.CirclePageIndicatorActivity;
import com.wishmobile.tomazpractice.recyclerview.RecyclerViewFragment;
import com.wishmobile.tomazpractice.scrollview.ScrollFragment;
import com.wishmobile.tomazpractice.tabview.TabViewActivty;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.OnMainMenuFragmentInteractionListener {


    private static final int MAIN_FRAME = R.id.main_frame;

    private static final String STACK_KEY_MAIN_MENU_FRAGMENT = "mainMenuFrgament";
    private static final String STACK_KEY_LIST_VIEW_FRAGMENT = "listViewFragment";
    private static final String STACK_KEY_RECYCLER_VIEW_FRAGMENT = "recyclerViewFragment";
    private static final String STACK_KEY_CALCULATOR_FRAGMENT = "calculatorFragment";
    private static final String STACK_KEY_PAGE_INDICATOR = "pageIndicator";


    private static final String STACK_KEY_SCROLL_VIEW = "scrollviewFragment";
    private static final String TAG = MainActivity.class.getSimpleName();


    private String[] menuItems = new String[]{
            "ListView",
            "RecyclerView",
            "Calculator Layout",
            "Scroll View",
            "Navigation Drawer",
            "TabView",
            "Page Indicator",
            "",
            ""
            //TODO: add item
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainMenuFragment mainMenuFragment = MainMenuFragment.newInstance(menuItems);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(MAIN_FRAME, mainMenuFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void clickItem(int position) {
        Log.d(TAG, "clickItem: " + position);

        FragmentManager fm = getFragmentManager();

        switch (position) {

            case 0:
                // ListView
                ListViewFragment listViewFragment = ListViewFragment.newInstace();
                replaceFragment(listViewFragment, STACK_KEY_LIST_VIEW_FRAGMENT);

                break;

            case 1:
                // RecyclerView
                RecyclerViewFragment recyclerViewFragment = RecyclerViewFragment.newInstance();
                replaceFragment(recyclerViewFragment, STACK_KEY_RECYCLER_VIEW_FRAGMENT);
                break;

            case 2:
                // Calculator
                CalculatorFragment calculatorFragment = CalculatorFragment.newInstance();
                replaceFragment(calculatorFragment, STACK_KEY_CALCULATOR_FRAGMENT);
                break;


            case 3:
                // ScrollView
                ScrollFragment scrollFragment = ScrollFragment.newInstance();
                replaceFragment(scrollFragment, STACK_KEY_SCROLL_VIEW);
                break;


            case 4:
                // Drawer
                Intent drawerIntent = new Intent();
                drawerIntent.setClass(this, DrawerActivity.class);
                startActivity(drawerIntent);
                break;

            case 5:
                // TabView
                Intent tabViewIntent = new Intent();
                tabViewIntent.setClass(this, TabViewActivty.class);
                startActivity(tabViewIntent);
                break;

            case 6:
                // Page indicator
                Intent pagerIntent = new Intent();
                pagerIntent.setClass(this, CirclePageIndicatorActivity.class);
                startActivity(pagerIntent);
                break;



        }
    }

    public void gotoActivity(Class acvitivityClass){
        Intent intent = new Intent();
        intent.setClass(this, acvitivityClass);
        startActivity(intent);
    }


    private void replaceFragment(Fragment fragment, String stackTag) {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(MAIN_FRAME, fragment).addToBackStack(stackTag).commit();
    }
}
