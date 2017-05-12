package com.example.lollipop.medicaldoctor.mvp.presenter;

import android.util.Log;

import com.example.lollipop.medicaldoctor.data.response.PatientListResponse;
import com.example.lollipop.medicaldoctor.mvp.model.DataManager;
import com.example.lollipop.medicaldoctor.mvp.view.PatientListView;
import com.example.lollipop.medicaldoctor.ui.base.BasePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by R2D2 on 2017/5/12.
 */

public class PatientListPresenter extends BasePresenter<PatientListView> {

    private DataManager dataManager;

    @Inject
    public PatientListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getPatientList() {
        Observer<PatientListResponse> observer = new Observer<PatientListResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PatientListResponse response) {
                if (response.getResult() != null && response.getResult().size() > 0) {
                    List<PatientListResponse.ResultBean> resultBeens = response.getResult();
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (PatientListResponse.ResultBean resultBean : resultBeens) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("username", resultBean.getUsername());
                        map.put("name", resultBean.getName());
                        map.put("id", resultBean.getId());
                        list.add(map);
                    }
                    getView().onRefresh(list);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                getView().onError("获取列表失败");
            }

            @Override
            public void onComplete() {

            }
        };
        dataManager.getPatientList(observer);
    }
}
