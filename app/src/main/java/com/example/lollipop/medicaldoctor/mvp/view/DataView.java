package com.example.lollipop.medicaldoctor.mvp.view;


import com.example.lollipop.medicaldoctor.ui.base.View;

import java.util.List;
import java.util.Map;

/**
 * Created by Lollipop on 2017/5/8.
 */

public interface DataView extends View {
    void refreshChart(List<Integer> list);

    void refreshData(Map<String, Object> map);
}
