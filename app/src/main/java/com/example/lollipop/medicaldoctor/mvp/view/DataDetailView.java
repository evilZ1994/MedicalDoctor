package com.example.lollipop.medicaldoctor.mvp.view;

import com.example.lollipop.medicaldoctor.ui.base.View;

import java.util.List;
import java.util.Map;

/**
 * Created by Lollipop on 2017/5/15.
 */

public interface DataDetailView extends View {
    void refreshCurrentData(String data);

    void refreshDataList(List<Map<String, String>> list);
}
