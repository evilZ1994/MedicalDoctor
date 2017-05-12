package com.example.lollipop.medicaldoctor.mvp.view;

import com.example.lollipop.medicaldoctor.ui.base.View;

import java.util.List;
import java.util.Map;

/**
 * Created by R2D2 on 2017/5/12.
 */

public interface PatientListView extends View {
    void onRefresh(List<Map<String, Object>> list);
}
