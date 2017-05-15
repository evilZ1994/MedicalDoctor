package com.example.lollipop.medicaldoctor.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.TextView;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.mvp.presenter.DataDetailPresenter;
import com.example.lollipop.medicaldoctor.mvp.view.DataDetailView;
import com.example.lollipop.medicaldoctor.ui.adapter.DataDetailAdapter;
import com.example.lollipop.medicaldoctor.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataDetailActivity extends BaseActivity implements DataDetailView{
    private String tag;
    private int patient_id;
    private int color;
    private Timer timer;

    private DataDetailAdapter adapter;
    private List<Map<String, String>> list = new ArrayList<>();

    @Inject
    DataDetailPresenter presenter;

    @BindView(R.id.current_data)
    TextView currentDataView;
    @BindView(R.id.data_unit)
    TextView dataUnit;
    @BindView(R.id.data_list)
    ListViewCompat dataListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        presenter.attachView(this);

        tag = getIntent().getStringExtra("tag");
        patient_id = getIntent().getIntExtra("id", 0);

        adapter = new DataDetailAdapter(list, tag);
        if (!tag.equals("angle")) {
            dataListView.setAdapter(adapter);
        }

        //初始化颜色,单位
        switch (tag){
            case "pressure":
                color = ContextCompat.getColor(this, R.color.simpleBlue);
                dataUnit.setText("Pa");
                break;
            case "temperature":
                color = ContextCompat.getColor(this, R.color.simpleOrange);
                dataUnit.setText("℃");
                break;
            case "pulse":
                color = ContextCompat.getColor(this, R.color.simpleGreen);
                dataUnit.setText("次/分");
                break;
            case "angle":
                color = ContextCompat.getColor(this, R.color.simpleAmber);
                dataUnit.setText("度");
                break;
            default:
                color = ContextCompat.getColor(this, R.color.simpleBlue);
                dataUnit.setText("");
                break;
        }
        currentDataView.setTextColor(color);
        //设置定时器
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                presenter.GetDataDetail(patient_id, tag);
            }
        }, 0, 2000);
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
    public void refreshCurrentData(String data) {
        currentDataView.setText(data);
    }

    @Override
    public void refreshDataList(List<Map<String, String>> list) {
        this.list = list;
        adapter.setList(this.list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
