package com.wishmobile.tomazpractice.network;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.network.data.ItemResult;
import com.wishmobile.tomazpractice.network.imageapi.ImageApi;
import com.wishmobile.tomazpractice.network.imageapi.ImageApiService;

import java.util.ArrayList;

import butterknife.BindView;

public class QsireProductFragment extends BaseSpiceFragment<ImageApiService> {

    private static final String TAG = QsireProductFragment.class.getSimpleName();
    private static final int FIRST_TIME = 0;
    private static final int REFRESH = 1;
    private static final int LOAD_MORE = 2;

    QsireProductFragment.OnFragmentInteractionListener mListener;

    @BindView(R.id.list_product)
    UltimateRecyclerView list;


    ItemResult result;
    ArrayList<ItemResult.Product> products = new ArrayList<>();


    private QsireProductListAdapter adapter;
    private Integer next;


    public QsireProductFragment() {
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
        if (context instanceof OnFragmentInteractionListener) {


            this.mListener = (OnFragmentInteractionListener) context;
        } else {
            Log.w(TAG, "onAttach: should implemet listener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) activity;
        } else {
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
        fetchData(FIRST_TIME);
    }

    // fetch data withpage
    private void fetchDataAtPage(int page, int status) {
        Log.d(TAG, "fetchDataAtPage: page = " + page);
        ImageApi.RequestBody.Builder requestBodyBuilder = new ImageApi.RequestBody.Builder();
        requestBodyBuilder.addCategory_ids(5);
        requestBodyBuilder.setOffset(page);
        fetchData(requestBodyBuilder.build(), status);
    }


    // fetch all data
    private void fetchData(int status) {
        ImageApi.RequestBody.Builder requestBuilder = new ImageApi.RequestBody.Builder();
        requestBuilder.addCategory_ids(5);
        fetchData(requestBuilder.build(), status);
    }

    private void fetchData(ImageApi.RequestBody requestBody, int status) {

        ImageApi.Request request = new ImageApi.Request(requestBody);
        getSpiceManager().execute(request, new ImageApi.Listener() {

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                super.onRequestFailure(spiceException);
            }

            @Override
            public void onRequestSuccess(ImageApi.Response response) {
                super.onRequestSuccess(response);
                Log.d(TAG, "onRequestSuccess: get response");
                QsireProductFragment.this.setResult(response.getResults());
                QsireProductFragment.this.setNext(response.getNext());
                updateUiAfaterFetchData(status);
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
        list.setDefaultOnRefreshListener(() -> {
            fetchData(REFRESH);
        });
    }

    private void updateUiAfaterFetchData(int status) {

        if (result == null) {
            return;
        }

        switch (status) {
            case FIRST_TIME:
            case REFRESH:
                adapter.clear();
                adapter.addAll(result.getProductList());
                break;
            case LOAD_MORE:
                adapter.addAll(result.getProductList());
                break;
        }


        if(!hasNext()){
            list.disableLoadmore();
        }else{
            list.enableLoadmore();
            list.setOnLoadMoreListener(loadMoreListener);
        }
    }

    private UltimateRecyclerView.OnLoadMoreListener loadMoreListener = (itemsCount, maxLastVisiblePosition) -> {
        if (result != null && hasNext()) {
            Log.d(TAG, "createListView: load more");
            fetchDataAtPage(getNext(), LOAD_MORE);
        }else{
            Log.w(TAG, "createListView: result == null");
        }
    };


    public void setResult(ItemResult result) {
        this.result = result;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getNext() {
        return next;
    }

    public boolean hasNext(){
        return next != null;
    }



    public interface OnFragmentInteractionListener {
    }

}
