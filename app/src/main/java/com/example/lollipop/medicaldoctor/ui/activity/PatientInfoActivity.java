package com.example.lollipop.medicaldoctor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.mvp.presenter.InfoPresenter;
import com.example.lollipop.medicaldoctor.mvp.view.UserInfoView;
import com.example.lollipop.medicaldoctor.ui.adapter.InfoAdapter;
import com.example.lollipop.medicaldoctor.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientInfoActivity extends BaseActivity implements UserInfoView {

    private List<Map<String, String>> items = new ArrayList<>();
    private InfoAdapter adapter;

    @BindView(R.id.patient_info_list)
    ListViewCompat infoList;

    @Inject
    InfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        presenter.attachView(this);

        //获取需要查看的患者的用户名和id
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        int id = intent.getIntExtra("id", 0);

        //初始化adapter
        adapter = new InfoAdapter(items);
        //设置adapter
        infoList.setAdapter(adapter);

        //读取数据
        presenter.getUserInfo("patient", id);
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
