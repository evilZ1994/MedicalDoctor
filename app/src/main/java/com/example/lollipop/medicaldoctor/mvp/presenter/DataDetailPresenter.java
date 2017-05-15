package com.example.lollipop.medicaldoctor.mvp.presenter;

import android.util.Log;

import com.example.lollipop.medicaldoctor.data.response.DataDetailResponse;
import com.example.lollipop.medicaldoctor.mvp.model.DataManager;
import com.example.lollipop.medicaldoctor.mvp.view.DataDetailView;
import com.example.lollipop.medicaldoctor.ui.base.BasePresenter;
import com.example.lollipop.medicaldoctor.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Lollipop on 2017/5/15.
 */

public class DataDetailPresenter extends BasePresenter<DataDetailView> {
    private DataManager dataManager;

    @Inject
    public DataDetailPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public void GetDataDetail(int id, String tag){
        Observer<DataDetailResponse> observer = new Observer<DataDetailResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull DataDetailResponse dataDetailResponse) {
                String tag = dataDetailResponse.getTag();
                List<DataDetailResponse.ResultBean> resultList = dataDetailResponse.getResult();
                if (resultList!=null && resultList.size()>0) {
                    Date now = new Date();
                    int currentMinute = DateUtil.getMinute(now);
                    int currentHour = DateUtil.getHour(now);
                    int currentDay = DateUtil.getDay(now);
                    Date date = DateUtil.stringToDate(resultList.get(0).getCreate_time());
                    int minute = DateUtil.getMinute(date);
                    int hour = DateUtil.getHour(date);
                    int day = DateUtil.getDay(date);
                    if (tag.equals("angle")) {
                        String value = resultList.get(0).getAngle() + "";
                        getView().refreshCurrentData(value);
                    } else {
                        if (day == currentDay && hour == currentHour && minute == currentMinute) {
                            DataDetailResponse.ResultBean bean = resultList.get(0);
                            String value = "0";
                            if (tag.equals("pressure")) {
                                value = bean.getPressure() + "";
                            } else if (tag.equals("temperature")) {
                                value = bean.getTemperature() + "";
                            } else if (tag.equals("pulse")) {
                                value = bean.getPulse() + "";
                            }
                            Log.i("current data", value);
                            getView().refreshCurrentData(value);
                        }
                        List<Map<String, String>> list = new ArrayList<>();
                        for (DataDetailResponse.ResultBean data : resultList) {
                            Map<String, String> map = new HashMap<>();
                            map.put("date", data.getCreate_time());
                            String value = "0";
                            if (tag.equals("pressure")) {
                                value = data.getPressure() + "";
                            } else if (tag.equals("temperature")) {
                                value = data.getTemperature() + "";
                            } else if (tag.equals("pulse")) {
                                value = data.getPulse() + "";
                            }
                            map.put("value", value);
                            list.add(map);
                        }
                        getView().refreshDataList(list);
                    }
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        dataManager.getDataDetail(id, tag, observer);
    }
}
