package com.example.lollipop.medicaldoctor.ui.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lollipop.medicaldoctor.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Lollipop on 2017/5/11.
 */

public class PatientInfoAdapter extends BaseAdapter {
    private List<Map<String, String>> items;

    private static class ViewHolder{
        AppCompatTextView title;
        AppCompatTextView content;
    }

    public PatientInfoAdapter(List<Map<String, String>> items) {
        super();
        this.items = items;
    }

    public void setItems(List<Map<String, String>> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.info_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (AppCompatTextView) view.findViewById(R.id.title);
            viewHolder.content = (AppCompatTextView) view.findViewById(R.id.content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (items != null && items.size()>0){
            Map<String, String> item = items.get(i);
            viewHolder.title.setText(item.get("title"));
            viewHolder.content.setText(item.get("content"));
        }

        return view;
    }
}
