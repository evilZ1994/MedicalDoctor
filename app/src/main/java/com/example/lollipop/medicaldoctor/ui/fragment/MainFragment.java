package com.example.lollipop.medicaldoctor.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.mvp.presenter.PatientListPresenter;
import com.example.lollipop.medicaldoctor.mvp.view.PatientListView;
import com.example.lollipop.medicaldoctor.ui.adapter.PatientListRecyclerAdapter;
import com.example.lollipop.medicaldoctor.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements PatientListView{
    private List<Map<String, Object>> userInfos = new ArrayList<>();
    private PatientListRecyclerAdapter adapter;

    @Inject
    PatientListPresenter presenter;

    @BindView(R.id.d_home_swipe)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        getFragmentComponent().inject(this);
        presenter.attachView(this);

        init();

        return view;
    }

    private void init() {
        adapter = new PatientListRecyclerAdapter(userInfos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPatientList();
            }
        });
        refreshLayout.setColorSchemeResources(R.color.simpleBlue);

        //初始化时自动刷新一次
        presenter.getPatientList();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onRefresh(List<Map<String, Object>> list) {
        userInfos = list;
        adapter.setUserInfos(userInfos);
        adapter.notifyDataSetChanged();
        if (refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
    }
}
