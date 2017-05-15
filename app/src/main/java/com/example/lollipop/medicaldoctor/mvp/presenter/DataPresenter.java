package com.example.lollipop.medicaldoctor.mvp.presenter;

import android.text.format.DateUtils;
import android.util.Log;

import com.example.lollipop.medicaldoctor.data.bean.Data;
import com.example.lollipop.medicaldoctor.data.response.DataDownloadResponse;
import com.example.lollipop.medicaldoctor.mvp.model.DataManager;
import com.example.lollipop.medicaldoctor.mvp.view.DataView;
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
 * Created by Lollipop on 2017/5/8.
 */

public class DataPresenter extends BasePresenter<DataView> {
    private DataManager dataManager;

    @Inject
    public DataPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public void getDataList(int id){
        Observer<DataDownloadResponse> observer = new Observer<DataDownloadResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull DataDownloadResponse dataDownloadResponse) {
                List<DataDownloadResponse.DataBean> dataList = dataDownloadResponse.getData();
                if (dataList!=null && dataList.size() > 0){
                    Date now = new Date();
                    int currentMinute = DateUtil.getMinute(now);
                    int currentHour = DateUtil.getHour(now);
                    int currentDay = DateUtil.getDay(now);
                    List<DataDownloadResponse.DataBean> list = new ArrayList<>();
                    for (DataDownloadResponse.DataBean dataBean : dataList){
                        Date date = DateUtil.stringToDate(dataBean.getCreate_time());
                        int minute = DateUtil.getMinute(date);
                        int hour = DateUtil.getHour(date);
                        int day = DateUtil.getDay(date);
                        if (day==currentDay && hour==currentHour && minute==currentMinute){
                            list.add(dataBean);
                        }
                    }
                    if (list.size()>0){
                        DataDownloadResponse.DataBean dataBean = list.get(list.size()-1);
                        Map<String, Object> map = new HashMap<>();
                        DataDownloadResponse.DataBean currentData = list.get(0);
                        map.put("pressure", currentData.getPressure());
                        map.put("angle", currentData.getAngle());
                        map.put("temperature", currentData.getTemperature());
                        map.put("pulse", currentData.getPulse());
                        //更新数据
                        getView().refreshData(map);
                        int index = DateUtil.getSecond(DateUtil.stringToDate(dataBean.getCreate_time()))/5;
                        List<Integer> pressureList = new ArrayList<>();
                        for (int i=0; i<index; i++){
                            pressureList.add(0);
                        }
                        for (int i=0; i<list.size(); i++){
                            pressureList.add(list.get(list.size()-1-i).getPressure());
                        }
                        //更新图表
                        getView().refreshChart(pressureList);
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
        dataManager.getDataList(id, observer);
    }
}
