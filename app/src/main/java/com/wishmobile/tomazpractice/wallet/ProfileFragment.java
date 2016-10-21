package com.wishmobile.tomazpractice.wallet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.linkwish.widget.FormView;
import com.linkwish.widget.formitem.LabelItem;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.view.BaseFragment;
import com.wishmobile.tomazpractice.wallet.data.User;
import com.wishmobile.tomazpractice.widget.formview.EditItem;
import com.wishmobile.tomazpractice.widget.formview.TextItem;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.form_view)
    FormView formView;


    EditItem name;
    TextItem gender;
    EditItem phone;
    EditItem email;
    private String[] genderArray;
    private FormView.Adapter formAdapter;
    private User user;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_profile;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStaticData();
        initUserData();
    }

    private void initUserData() {
        user = new User("Tom", "tom@wishmobile.com", "0912345678", 0);
    }

    private void initStaticData() {
        genderArray = getActivity().getResources().getStringArray(R.array.genders);
    }

    @Override
    protected void onCreateView(View view) {

        initFormView();
        setAttriToForm();
    }

    private void initFormView() {


        formAdapter = new FormView.Adapter();

        // TODO: add items
        name = new EditItem(getActivity(), R.string.label_name, "");
        gender = new TextItem(getActivity(), R.string.label_gender);
        phone = new EditItem(getActivity(), R.string.label_phone, "");
        email = new EditItem(getActivity(), R.string.label_email, "");

        formAdapter.add(new LabelItem(getActivity()));

        formAdapter.add(name);
        formAdapter.add(gender);

        formAdapter.add(new LabelItem(getActivity(), R.string.label_contact_info));

        formAdapter.add(phone);
        formAdapter.add(email);

        formView.setAdapter(formAdapter);


        // name


        // email


        // phone
        phone.setEditable(false);

        // gender
        gender.getView().setOnClickListener(v -> showGenderDialog());

    }


    private void showGenderDialog() {

        if (genderArray == null) {
            genderArray = getActivity().getResources().getStringArray(R.array.genders);
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.label_gender);

        builder.setSingleChoiceItems(genderArray, user.getGender(),
                (dialog, which) -> {
                    // item selected logic
                    user.setGender(which);
                    gender.getSubText().setText(genderArray[user.getGender()]);
                    formAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void setAttriToForm() {

        name.getEditText().setText(user.getName());
        phone.getEditText().setText(user.getPhone());
        email.getEditText().setText(user.getMail());

        if(user.getGender() >= 0) {
            gender.getSubText().setText(genderArray[user.getGender()]);
        }


    }

    public interface OnFragmentInteractionListener {
    }

}
