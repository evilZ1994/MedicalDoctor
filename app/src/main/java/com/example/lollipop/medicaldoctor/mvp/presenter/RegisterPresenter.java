package com.example.lollipop.medicaldoctor.mvp.presenter;

import com.example.lollipop.medicaldoctor.api.ApiService;
import com.example.lollipop.medicaldoctor.data.response.LoginResponse;
import com.example.lollipop.medicaldoctor.data.response.RegisterResponse;
import com.example.lollipop.medicaldoctor.mvp.model.DataManager;
import com.example.lollipop.medicaldoctor.mvp.view.RegisterView;
import com.example.lollipop.medicaldoctor.ui.base.BasePresenter;

import javax.inject.Inject;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Lollipop on 2017/4/29.
 * 协调注册的各种操作以及视图的展示
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    ApiService apiService;

    @Inject
    public RegisterPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    /**
     * 解除订阅
     */
    @Override
    public void detachView() {
        super.detachView();
        if (disposable!=null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public void register(String userString){
        /*getView().showDialog();
        Consumer<RegisterResponse> consumer = new Consumer<RegisterResponse>() {
            @Override
            public void accept(@NonNull RegisterResponse registerResponse) throws Exception {
                if (registerResponse.getStatus().equals("success")){
                    //注册成功后将进度框改为正在登陆
                    getView().onSuccess();
                    getView().onLogin();
                } else {
                    getView().hideDialog();
                    getView().onError(registerResponse.getMessage());
                }
            }
        };
        Observer<LoginResponse> observer = new Observer<LoginResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //保存Disposable对象，以便之后解除订阅
                disposable = d;
            }

            @Override
            public void onNext(@NonNull LoginResponse loginResponse) {
                if (loginResponse.getStatus().equals("success")){
                    //登陆成功保存用户信息
                    //保存用户信息到本地数据库
                    storeUser(loginResponse);
                } else {
                    getView().hideDialog();
                    getView().onError(loginResponse.getMessage());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                getView().hideDialog();
                if (e.getClass().getName().equals("java.net.ConnectException")) {
                    getView().onError("哎呀..没网了！");
                }*//* else if (e.getClass().getName().equals("java.lang.NullPointerException")){
                    getView().onError("程序崩溃了..T_T");//正常原因注册失败时，flatMap会返回一个null值，导致此异常触发
                }*//*
            }

            @Override
            public void onComplete() {

            }
        };
        dataManager.register(userString, consumer, observer);*/

        getView().showDialog();
        Consumer<RegisterResponse> consumer = new Consumer<RegisterResponse>() {
            @Override
            public void accept(@NonNull RegisterResponse registerResponse) throws Exception {
                if (registerResponse.getStatus().equals("success")){
                    final String username = registerResponse.getUser().getUsername();
                    final String password = registerResponse.getUser().getPassword();
                    //注册JMessage
                    JMessageClient.register(username, password, new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            if (i == 0){
                                //注册成功后将进度框改为正在登陆
                                getView().onSuccess();
                                getView().onLogin();
                                //执行登陆
                                Observer<LoginResponse> observer = new Observer<LoginResponse>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onNext(@NonNull final LoginResponse loginResponse) {
                                        if (loginResponse.getStatus().equals("success")){
                                            //登陆JMessage
                                            JMessageClient.login(username, password, new BasicCallback() {
                                                @Override
                                                public void gotResult(int i, String s) {
                                                    if (i == 0){
                                                        //登陆成功保存用户信息
                                                        //保存用户信息到本地数据库
                                                        dataManager.storeUser(loginResponse);
                                                    }else {
                                                        getView().hideDialog();
                                                        getView().onError(loginResponse.getMessage());
                                                    }
                                                }
                                            });
                                        } else {
                                            getView().hideDialog();
                                            getView().onError(loginResponse.getMessage());
                                        }
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        e.printStackTrace();
                                        getView().hideDialog();
                                        if (e.getClass().getName().equals("java.net.ConnectException")) {
                                            getView().onError("哎呀..没网了！");
                                        }else if (e.getClass().getName().equals("java.lang.NullPointerException")){
                                            getView().onError("程序崩溃了..T_T");//正常原因注册失败时，flatMap会返回一个null值，导致此异常触发
                                        }
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                };
                                dataManager.login(username, password, observer);
                            } else {
                                //JMessage注册失败
                                getView().hideDialog();
                                getView().onError(s);
                            }
                        }
                    });
                } else {
                    getView().hideDialog();
                    getView().onError(registerResponse.getMessage());
                }
            }
        };
        dataManager.register(userString, consumer);
    }
}
