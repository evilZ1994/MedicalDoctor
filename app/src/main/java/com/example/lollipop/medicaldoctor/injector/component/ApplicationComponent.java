package com.example.lollipop.medicaldoctor.injector.component;


import com.example.lollipop.medicaldoctor.api.ApiService;
import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.injector.module.ApiModule;
import com.example.lollipop.medicaldoctor.injector.module.ApplicationModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lollipop on 2017/4/28.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    App application();

    ApiService apiService();

    Gson gson();
}
