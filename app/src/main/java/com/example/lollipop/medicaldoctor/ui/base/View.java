package com.example.lollipop.medicaldoctor.ui.base;

/**
 * Created by Lollipop on 2017/4/28.
 */

public interface View {
    void onSuccess();

    void onError(String msg);

    void showDialog();

    void hideDialog();
}
