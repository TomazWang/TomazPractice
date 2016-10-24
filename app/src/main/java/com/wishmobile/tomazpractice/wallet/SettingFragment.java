package com.wishmobile.tomazpractice.wallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.linkwish.widget.FormView;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.view.BaseFragment;
import com.wishmobile.tomazpractice.widget.formview.TextItem;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.wishmobile.tomazpractice.wallet.SettingFragment.OnFragmentInteractionListener.PAGE_DISPLAY_SETTING;
import static com.wishmobile.tomazpractice.wallet.SettingFragment.OnFragmentInteractionListener.PAGE_PROFILE;

public class SettingFragment extends BaseFragment {

    private static final String TAG = SettingFragment.class.getSimpleName();

    private OnFragmentInteractionListener mListener;


    // bind views
    private Unbinder unbinder;

    @BindView(R.id.form_view)
    FormView formView;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public int getContentLayout() {
        return R.layout.fragment_setting;
    }


    @Override
    protected void onCreateView(View view) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFormView();
    }

    private void initFormView() {


        FormView.Adapter adapter = new FormView.Adapter();

        String[] settingList = getActivity().getResources().getStringArray(R.array.settingList);
        for (int i = 0; i < settingList.length; i++) {

            TextItem textItem = new TextItem(getActivity(), settingList[i]);
//            textItem.setSubTextIcon(R.drawable.ic_keyboard_arrow_right_white_24dp);

            switch (i) {
                case 0:
                    // profile
                    textItem.getView().setOnClickListener(v -> {
                        if(mListener != null){
                            mListener.gotoPage(PAGE_PROFILE);
                        }
                    });

                    break;
                case 1:
                    // display setting
                    textItem.getView().setOnClickListener(v -> {
                        if(mListener != null){
                            mListener.gotoPage(PAGE_DISPLAY_SETTING);
                        }
                    });
                    break;
                case 2:
                    // rate
                    textItem.getView().setOnClickListener(v -> {
                            gotoGooglePlay();
                    });

                    break;
                case 3:
                    // feedback
                    textItem.getView().setOnClickListener(v -> {
                            sendMail();
                    });
                    break;


            }


            adapter.add(textItem);
        }

        formView.setAdapter(adapter);

    }

    private void sendMail() {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"tomaz@wishmobile.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from Wallet");
        i.putExtra(Intent.EXTRA_TEXT   , "replace feedback template content here.");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }

    private void gotoGooglePlay() {

        String appPackageName = getString(R.string.mcddailyPackageName);

        try {
            getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Log.w(TAG, "onAttach: "
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            Log.w(TAG, "onAttach: "
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        int PAGE_PROFILE = 600;
        int PAGE_DISPLAY_SETTING = 601;

        void gotoPage(int pageId);

    }
}
