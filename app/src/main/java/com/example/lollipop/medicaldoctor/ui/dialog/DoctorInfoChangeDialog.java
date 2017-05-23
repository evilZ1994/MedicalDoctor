package com.example.lollipop.medicaldoctor.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.Window;
import android.widget.TextView;

import com.example.lollipop.medicaldoctor.R;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by R2D2 on 2017/5/23.
 */

public class DoctorInfoChangeDialog extends Dialog {
    private Context context;
    private String tag;
    private String title;
    private Consumer<String> consumer;

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
        
    }

    public DoctorInfoChangeDialog(@NonNull Context context, String tag, String title, Consumer<String> consumer) {
        super(context);
        this.context = context;
        this.tag = tag;
        this.title = title;
        this.consumer = consumer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_change_dialog);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        titleText.setText("修改"+title);
    }
}
