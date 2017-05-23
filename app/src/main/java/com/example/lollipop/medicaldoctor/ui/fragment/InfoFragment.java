package com.example.lollipop.medicaldoctor.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.data.response.UpdateInfoResponse;
import com.example.lollipop.medicaldoctor.mvp.presenter.UserInfoPresenter;
import com.example.lollipop.medicaldoctor.mvp.view.UserInfoView;
import com.example.lollipop.medicaldoctor.ui.adapter.InfoAdapter;
import com.example.lollipop.medicaldoctor.ui.base.BaseFragment;
import com.example.lollipop.medicaldoctor.ui.dialog.DoctorInfoChangeDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 医生个人信息页
 */
public class InfoFragment extends BaseFragment implements UserInfoView {
    private List<Map<String, String>> items = new ArrayList<>();
    private InfoAdapter adapter;

    @BindView(R.id.doctor_info_list)
    ListViewCompat infoList;

    @Inject
    UserInfoPresenter presenter;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ButterKnife.bind(this, view);
        getFragmentComponent().inject(this);
        presenter.attachView(this);

        //初始化adapter
        adapter = new InfoAdapter(items);
        //设置adapter
        infoList.setAdapter(adapter);
        infoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final InfoAdapter.ViewHolder viewHolder = (InfoAdapter.ViewHolder) view.getTag();
                Map<String, String> item = (Map<String, String>)adapter.getItem(position);
                if (!item.get("tag").equals("username")){
                    //Dialog修改完成后的回调操作，更新这个Item的值
                    Consumer<UpdateInfoResponse> consumer = new Consumer<UpdateInfoResponse>() {
                        @Override
                        public void accept(@NonNull UpdateInfoResponse response) throws Exception {
                            String result = response.getStatus();
                            if (result.equals("success")){
                                viewHolder.content.setText(response.getContent());
                            }
                        }
                    };
                    DoctorInfoChangeDialog dialog = new DoctorInfoChangeDialog(getContext(), R.style.InfoChangeDialogTheme, item.get("tag"), item.get("title"), consumer);
                    dialog.show();
                }
            }
        });
        //读取数据
        presenter.getDoctorInfo("doctor", App.getCurrentUser().getId());

        return view;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void show(List<Map<String, String>> items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
