package com.example.lollipop.medicaldoctor.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.mvp.presenter.UserInfoPresenter;
import com.example.lollipop.medicaldoctor.mvp.view.UserInfoView;
import com.example.lollipop.medicaldoctor.ui.adapter.PatientInfoAdapter;
import com.example.lollipop.medicaldoctor.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 医生个人信息页
 */
public class InfoFragment extends BaseFragment implements UserInfoView {
    private List<Map<String, String>> items = new ArrayList<>();
    private PatientInfoAdapter adapter;

    @BindView(R.id.doctor_info_list)
    ListViewCompat infoList;

    @Inject
    UserInfoPresenter presenter;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ButterKnife.bind(this, view);
        getFragmentComponent().inject(this);
        presenter.attachView(this);

        //初始化adapter
        adapter = new PatientInfoAdapter(items);
        //设置adapter
        infoList.setAdapter(adapter);

        //读取数据
        presenter.getDoctorInfo("doctor", App.getCurrentUser().getId());

        return view;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void show(List<Map<String, String>> items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
