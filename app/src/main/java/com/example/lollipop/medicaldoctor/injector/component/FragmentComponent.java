package com.example.lollipop.medicaldoctor.injector.component;

import com.example.lollipop.medicaldoctor.injector.module.FragmentModule;
import com.example.lollipop.medicaldoctor.injector.scope.PerActivity;
import com.example.lollipop.medicaldoctor.ui.fragment.InfoFragment;
import com.example.lollipop.medicaldoctor.ui.fragment.LoginFragment;
import com.example.lollipop.medicaldoctor.ui.fragment.MainFragment;
import com.example.lollipop.medicaldoctor.ui.fragment.RegisterFragment;
import com.example.lollipop.medicaldoctor.ui.fragment.SettingFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lollipop on 2017/4/28.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(LoginFragment loginFragment);

    void inject(RegisterFragment registerFragment);

    void inject(MainFragment mainFragment);

    void inject(InfoFragment infoFragment);

    void inject(SettingFragment settingFragment);
}
