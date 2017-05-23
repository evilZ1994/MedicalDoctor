package com.example.lollipop.medicaldoctor.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.TextInputEditText;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.api.ApiService;
import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.data.response.UpdateInfoResponse;
import com.example.lollipop.medicaldoctor.injector.component.DaggerApiComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by R2D2 on 2017/5/23.
 */

public class DoctorInfoChangeDialog extends Dialog {
    private String tag;
    private String title;
    private Consumer<UpdateInfoResponse> consumer;

    @Inject
    ApiService apiService;

    @BindView(R.id.title)
    TextView titleText;
    @BindView(R.id.content)
    TextInputEditText contentInput;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.ok)
    TextView ok;

    @OnClick(R.id.cancel)
    void clickCancel(){
        this.dismiss();
    }
    @OnClick(R.id.ok)
    void clickOk(){
        //点击确定后，修改信息
        final String content = contentInput.getText().toString();
        apiService.updateInfo("doctor", App.getCurrentUser().getId(),tag, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
        this.dismiss();
    }

    public DoctorInfoChangeDialog(@NonNull Context context, @StyleRes int themeResId, String tag, String title, Consumer<UpdateInfoResponse> consumer) {
        super(context, themeResId);
        this.tag = tag;
        this.title = title;
        this.consumer = consumer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏提示框的标题，此项必须写在setContentView之前
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.info_change_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ButterKnife.bind(this);
        DaggerApiComponent.builder().build().inject(this);

        titleText.setText("修改"+title);
    }
}
