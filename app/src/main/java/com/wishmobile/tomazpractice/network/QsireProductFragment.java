package com.wishmobile.tomazpractice.network;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.network.data.ItemResult;
import com.wishmobile.tomazpractice.network.imageapi.ImageApi;
import com.wishmobile.tomazpractice.network.imageapi.ImageApiService;

import java.util.ArrayList;

import butterknife.BindView;

public class QsireProductFragment extends BaseSpiceFragment<ImageApiService> {

    private static final String TAG = QsireProductFragment.class.getSimpleName();

    QsireProductFragment.OnFragmentInteractionListener mListener;

    @BindView(R.id.list_product)
    RecyclerView list;


    ItemResult result;
    ArrayList<ItemResult.Product> products = new ArrayList<>();


    private QsireProductListAdapter adapter;


    public QsireProductFragment(){
        // Require empty constructor.
    }

    @Override
    public Class<ImageApiService> getSpiceServiceClass() {
        return ImageApiService.class;
    }


    public static QsireProductFragment newInstance() {
        Bundle args = new Bundle();
        QsireProductFragment fragment = new QsireProductFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener) {


            this.mListener = (OnFragmentInteractionListener) context;
        }else{
            Log.w(TAG, "onAttach: should implemet listener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) activity;
        }else{
            Log.w(TAG, "onAttach: should implemet listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_qsire_product;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchData();
    }

    // fetch data withpage
    private void fetchDataAtPage(int page){
        ImageApi.RequestBody.Builder requestBodyBuilder = new ImageApi.RequestBody.Builder();
        requestBodyBuilder.addCategory_ids(5);
        requestBodyBuilder.setOffset(page);
        fetchData(requestBodyBuilder.build());
    }


    // fetch all data
    private void fetchData() {
        ImageApi.RequestBody.Builder requestBuilder = new ImageApi.RequestBody.Builder();
        requestBuilder.addCategory_ids(5);
        fetchData(requestBuilder.build());
    }

    private void fetchData(ImageApi.RequestBody requestBody){

        ImageApi.Request request = new ImageApi.Request(requestBody);
        getSpiceManager().execute(request, new ImageApi.Listener(){

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                super.onRequestFailure(spiceException);
            }

            @Override
            public void onRequestSuccess(ImageApi.Response response) {
                super.onRequestSuccess(response);
                Log.d(TAG, "onRequestSuccess: get response");
                QsireProductFragment.this.setResult(response.getResults());
                updateUiAfaterFetchData();
            }
        });
    }


    @Override
    protected void onCreateView(View view) {
        createListView();
    }

    private void createListView() {
        adapter = new QsireProductListAdapter(products);
        list.setAdapter(adapter);
        list.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    private void updateUiAfaterFetchData(){
        Log.d(TAG, "updateUiAfaterFetchData");

        if(result == null){
            Log.w(TAG, "updateUiAfaterFetchData: result == null");
            return;
        }

        adapter.clear();
        adapter.addAll(result.getProductList());
    }

    public void setResult(ItemResult result) {
        this.result = result;
    }


    public interface OnFragmentInteractionListener{
    }

}
