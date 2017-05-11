package com.example.lollipop.medicaldoctor.ui.base;

/**
 * Created by Lollipop on 2017/4/28.
 */

public interface Presenter<V extends View> {

    void attachView(V view);

    void detachView();
}
