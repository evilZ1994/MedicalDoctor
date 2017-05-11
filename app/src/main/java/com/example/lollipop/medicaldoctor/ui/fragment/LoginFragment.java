package com.example.lollipop.medicaldoctor.ui.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.mvp.presenter.LoginPresenter;
import com.example.lollipop.medicaldoctor.mvp.view.LoginView;
import com.example.lollipop.medicaldoctor.ui.activity.MainActivity;
import com.example.lollipop.medicaldoctor.ui.activity.RegisterActivity;
import com.example.lollipop.medicaldoctor.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements LoginView {
    private ProgressDialog progressDialog;

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.login_name)
    EditText username;
    @BindView(R.id.login_pass)
    EditText password;
    //登陆按钮点击，执行登陆操作
    @OnClick(R.id.login)
    void login(){
        loginPresenter.login(username.getText().toString(), password.getText().toString());
    }
    //注册按钮点击，跳转到注册界面
    @OnClick(R.id.register)
    void register(){
        Intent intent = new Intent(getContext(), RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        getFragmentComponent().inject(this);
        //将View传递给Presenter
        loginPresenter.attachView(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //解除订阅
        loginPresenter.detachView();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getContext(), "登陆成功啦！", Toast.LENGTH_SHORT).show();
        //执行登陆成功后的跳转动作
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);

        //结束当前Activity
        getActivity().finish();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        progressDialog = ProgressDialog.show(getContext(), "登陆", "正在登陆...", true, false);
    }

    @Override
    public void hideDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
