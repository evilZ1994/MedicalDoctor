package com.example.lollipop.medicaldoctor.injector.module;

import android.content.Context;

import com.example.lollipop.medicaldoctor.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lollipop on 2017/4/28.
 */
@Module
public class ApplicationModule {
    private App mApplication;

    public ApplicationModule(App application){
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public App provideApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mApplication;
    }
}
