package com.example.lollipop.medicaldoctor.injector.component;

import com.example.lollipop.medicaldoctor.injector.module.ActivityModule;
import com.example.lollipop.medicaldoctor.injector.scope.PerActivity;

import dagger.Component;

/**
 * Created by Lollipop on 2017/5/9.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

}
