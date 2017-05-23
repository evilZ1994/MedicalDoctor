package com.example.lollipop.medicaldoctor.injector.component;

import com.example.lollipop.medicaldoctor.injector.module.ApiModule;
import com.example.lollipop.medicaldoctor.ui.dialog.DoctorInfoChangeDialog;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by R2D2 on 2017/5/23.
 */
@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
    void inject(DoctorInfoChangeDialog doctorInfoChangeDialog);
}
