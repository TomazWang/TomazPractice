package com.wishmobile.tomazpractice;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.wishmobile.tomazpractice.fragment.ListViewFragment;
import com.wishmobile.tomazpractice.fragment.MainMenuFragment;
import com.wishmobile.tomazpractice.fragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.OnMainMenuFragmentInteractionListener {


    private static final int MAIN_FRAME = R.id.main_frame;

    private static final String STACK_KEY_MAIN_MENU_FRAGMENT = "mainMenuFrgament";
    private static final String STACK_KEY_LIST_VIEW_FRAGMENT = "listViewFragment";
    private static final String STACK_KEY_RECYCLER_VIEW_FRAGMENT = "recyclerViewFragment";
    private static final String TAG = MainActivity.class.getSimpleName();


    private String[] menuItems = new String[]{
            "ListView",
            "RecyclerView",
            ""
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
        Log.d(TAG, "clickItem: "+position);
        
        FragmentManager fm = getFragmentManager();

        switch (position){

            case 0:
                // ListView
                ListViewFragment listViewFragment = ListViewFragment.newInstace();

                fm.beginTransaction().replace(MAIN_FRAME, listViewFragment).addToBackStack(STACK_KEY_LIST_VIEW_FRAGMENT).commit();
                Toast.makeText(this,"enter ListViewFragment",Toast.LENGTH_SHORT).show();

                break;

            case 1:
                // RecyclerView
                RecyclerViewFragment recyclerViewFragment = RecyclerViewFragment.newInstance();

//                fm.beginTransaction().replace(MAIN_FRAME,recyclerViewFragment).addToBackStack(STACK_KEY_RECYCLER_VIEW_FRAGMENT).commit();
                Toast.makeText(this,"enter RecyclerFragment",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
