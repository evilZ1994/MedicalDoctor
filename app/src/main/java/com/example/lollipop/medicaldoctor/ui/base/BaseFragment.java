package com.example.lollipop.medicaldoctor.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.injector.component.DaggerFragmentComponent;
import com.example.lollipop.medicaldoctor.injector.component.FragmentComponent;

/**
 * Created by Lollipop on 2017/4/28.
 */

public abstract class BaseFragment extends Fragment {
    private FragmentComponent fragmentComponent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public FragmentComponent getFragmentComponent(){
        if (fragmentComponent == null){
            fragmentComponent = DaggerFragmentComponent.builder()
                    .applicationComponent(App.getApplicationComponent()).build();
        }
        return fragmentComponent;
    }
}
