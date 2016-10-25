package com.wishmobile.tomazpractice.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wishmobile.tomazpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WalletDrawerFragment extends Fragment {


    private static final String KEY_LIST = "key_list";
    @BindView(R.id.lv_drawerList)
    ListView listView;

    private ArrayAdapter<String> adapter;
    private OnFragmentInteractionListener mListener;

    private static final String TAG = WalletDrawerFragment.class.getSimpleName();
    private String[] mList;


    public static WalletDrawerFragment newInstance(String[] list) {

        Bundle args = new Bundle();
        args.putCharSequenceArray(KEY_LIST,list);

        WalletDrawerFragment fragment = new WalletDrawerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            this.mList = (String[]) bundle.getCharSequenceArray(KEY_LIST);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet_drawer, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_wallet_drawer, R.id.txt_item, mList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> mListener.onListItemClick(position));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
        } else {
            Log.w(TAG, "onAttach: should implements OnFragmentInteractionListener");
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) activity;
        } else {
            Log.w(TAG, "onAttach: should implements OnFragmentInteractionListener");
        }

    }

    public interface OnFragmentInteractionListener {

        void onListItemClick(int position);

    }

}
