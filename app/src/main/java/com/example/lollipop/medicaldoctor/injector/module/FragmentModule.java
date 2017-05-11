package com.example.lollipop.medicaldoctor.injector.module;

import android.content.Context;

import com.example.lollipop.medicaldoctor.ui.base.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lollipop on 2017/4/28.
 */
@Module
public class FragmentModule {
    private BaseFragment fragment;

    public FragmentModule(BaseFragment fragment){
        this.fragment = fragment;
    }

    @Provides
    BaseFragment provideFragment(){
        return fragment;
    }

    @Provides
    Context provideContext(){
        return fragment.getContext();
    }
}
