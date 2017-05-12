package com.example.lollipop.medicaldoctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.mvp.view.DataView;
import com.example.lollipop.medicaldoctor.ui.base.BaseActivity;

import java.util.List;
import java.util.Map;

public class DataActivity extends BaseActivity implements DataView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
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
    public void refreshChart(List<Integer> list) {

    }

    @Override
    public void refreshData(Map<String, Object> map) {

    }
}
