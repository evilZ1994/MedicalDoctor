package com.example.lollipop.medicaldoctor.mvp.presenter;

import com.example.lollipop.medicaldoctor.data.bean.Data;
import com.example.lollipop.medicaldoctor.mvp.model.DataManager;
import com.example.lollipop.medicaldoctor.mvp.view.DataView;
import com.example.lollipop.medicaldoctor.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


/**
 * Created by Lollipop on 2017/5/8.
 */

public class DataPresenter extends BasePresenter<DataView> {
    private DataManager dataManager;

    @Inject
    public DataPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public void updateData(){

    }
    private void refreshChart(){

    }
    private List<Data> getDataList(){

        return null;
    }
}
