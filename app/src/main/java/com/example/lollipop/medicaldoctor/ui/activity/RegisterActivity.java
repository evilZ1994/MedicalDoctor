package com.example.lollipop.medicaldoctor.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lollipop.medicaldoctor.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁前结束LoginActivity
        LoginActivity.instance.finish();
    }
}
