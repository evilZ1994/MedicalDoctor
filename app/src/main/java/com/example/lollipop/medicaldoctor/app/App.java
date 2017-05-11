package com.example.lollipop.medicaldoctor.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.lollipop.medicaldoctor.data.bean.User;
import com.example.lollipop.medicaldoctor.injector.component.ApplicationComponent;
import com.example.lollipop.medicaldoctor.injector.component.DaggerApplicationComponent;
import com.example.lollipop.medicaldoctor.injector.module.ApplicationModule;
import com.jakewharton.threetenabp.AndroidThreeTen;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Lollipop on 2017/5/11.
 */

public class App extends Application {
    private static Context mContext;
    private static App app;
    private static ApplicationComponent mApplicationComponent;

    private static User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        app = this;
        setupInjector();
        initTreeTen();
        initJMessage();
    }

    private void initJMessage() {
        //初始化服务，并启用消息漫游
        JMessageClient.init(this, true);
    }

    private void initTreeTen() {
        AndroidThreeTen.init(this);
    }

    private void setupInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static Context getContext(){
        return mContext;
    }

    public static App getApp(){
        return app;
    }

    public static ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    //登陆后调用该方法，保存当前登陆用户
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

}
