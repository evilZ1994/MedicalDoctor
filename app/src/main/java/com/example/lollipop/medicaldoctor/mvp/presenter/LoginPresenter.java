package com.example.lollipop.medicaldoctor.mvp.presenter;

import com.example.lollipop.medicaldoctor.data.response.LoginResponse;
import com.example.lollipop.medicaldoctor.mvp.model.DataManager;
import com.example.lollipop.medicaldoctor.mvp.view.LoginView;
import com.example.lollipop.medicaldoctor.ui.base.BasePresenter;

import javax.inject.Inject;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Lollipop on 2017/4/28.
 * 协调登陆相关操作及View展示
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public LoginPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    /**
     * 解除订阅
     */
    @Override
    public void detachView(){
        super.detachView();
        if (disposable!=null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public void login(final String username, final String password){
        getView().showDialog();
        Observer<LoginResponse> observer = new Observer<LoginResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //保存Disposable对象，以便之后解除订阅
                disposable = d;
            }

            @Override
            public void onNext(@NonNull final LoginResponse loginResponse) {
                String status = loginResponse.getStatus();
                if (status.equals("success")){
                    //JMessage登陆
                    JMessageClient.login(username, password, new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            if (i == 0){
                                //登陆成功保存用户
                                //将当前登陆用户保存到application
                                dataManager.storeUser(loginResponse);
                                getView().hideDialog();
                                getView().onSuccess();
                            } else {
                                getView().hideDialog();
                                getView().onError(s);
                            }
                        }
                    });
                } else{
                    getView().hideDialog();
                    getView().onError(loginResponse.getMessage());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().hideDialog();
                e.printStackTrace();
                if (e.getClass().getName().equals("java.net.ConnectException")) {
                    getView().onError("哎呀..没网了！");
                } else if (e.getClass().getName().equals("java.lang.NullPointerException")){
                    getView().onError("程序崩溃了..T_T");
                }
            }

            @Override
            public void onComplete() {

            }
        };
        dataManager.login(username, password, observer);
    }
}
