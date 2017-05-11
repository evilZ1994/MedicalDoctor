package com.example.lollipop.medicaldoctor.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.injector.component.ActivityComponent;
import com.example.lollipop.medicaldoctor.injector.component.DaggerActivityComponent;

/**
 * Created by Lollipop on 2017/5/9.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent(){
        if (activityComponent == null){
            activityComponent = DaggerActivityComponent.builder().applicationComponent(App.getApplicationComponent()).build();
        }
        return activityComponent;
    }
}
