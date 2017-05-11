package com.example.lollipop.medicaldoctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lollipop.medicaldoctor.R;

public class LoginActivity extends AppCompatActivity {
    public static LoginActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        instance = this;
    }
}
