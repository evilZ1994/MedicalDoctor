package com.example.lollipop.medicaldoctor.mvp.view;

import com.example.lollipop.medicaldoctor.ui.base.View;

import java.util.List;
import java.util.Map;

/**
 * Created by Lollipop on 2017/5/11.
 */

public interface UserInfoView extends View {
    void show(List<Map<String, String>> items);
}
