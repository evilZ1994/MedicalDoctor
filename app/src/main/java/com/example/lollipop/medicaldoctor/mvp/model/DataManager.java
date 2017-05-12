package com.example.lollipop.medicaldoctor.mvp.model;

import com.example.lollipop.medicaldoctor.api.ApiService;
import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.data.bean.User;
import com.example.lollipop.medicaldoctor.data.response.DoctorUserInfoResponse;
import com.example.lollipop.medicaldoctor.data.response.LoginResponse;
import com.example.lollipop.medicaldoctor.data.response.PatientListResponse;
import com.example.lollipop.medicaldoctor.data.response.PatientUserInfoResponse;
import com.example.lollipop.medicaldoctor.data.response.RegisterResponse;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lollipop on 2017/4/28.
 * 封装各种网络请求，网络数据操作
 */
public class DataManager {
    private final ApiService apiService;
    private final Gson gson;
    private final String TYPE = "doctor"; //请求类型，代表访问服务器的是doctor

    @Inject
    public DataManager(ApiService apiService, Gson gson){
        this.apiService = apiService;
        this.gson = gson;
    }

    /**
     * 登陆操作
     * @param username
     * @param password
     * @param observer
     */
    public void login(String username, String password, Observer<LoginResponse> observer){
        apiService.login(TYPE, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 注册操作，注册成功后继续执行登陆
     * @param userString
     * @param consumer
     * @param
     */
    public void register(String userString, Consumer<RegisterResponse> consumer){
        apiService.register(TYPE, userString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * 获取患者列表
     * @param observer
     */
    public void getPatientList(Observer<PatientListResponse> observer){
        apiService.getPatientList(App.getCurrentUser().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取用户信息
     * @param type 用户类型（doctor\patient)
     * @param id 账户id
     * @param observer 回调
     */
    public void getUserInfo(String type, int id, Observer<PatientUserInfoResponse> observer) {
        apiService.getUserInfo(type, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取医生信息
     * @param type 用户类型（doctor\patient)
     * @param id 账户id
     * @param observer 回调
     */
    public void getDoctorInfo(String type, int id, Observer<DoctorUserInfoResponse> observer){
        apiService.getDoctorInfo(type, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 登陆成功后将用户信息保存到application
     * @param loginResponse
     */
    public void storeUser(LoginResponse loginResponse) {
        User user = new User();
        user.setId(loginResponse.getUser().getId());
        user.setName(loginResponse.getUser().getName());
        user.setUsername(loginResponse.getUser().getUsername());
        user.setPassword(loginResponse.getUser().getPassword());
        user.setHospital(loginResponse.getUser().getHospital());
        user.setDepartment(loginResponse.getUser().getDepartment());
        App.setCurrentUser(user);
    }


}
