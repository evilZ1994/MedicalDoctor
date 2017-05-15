package com.example.lollipop.medicaldoctor.ui.adapter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lollipop.medicaldoctor.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Lollipop on 2017/5/14.
 */

public class DataDetailAdapter extends BaseAdapter {
    private List<Map<String, String>> list;
    private String tag;

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }

    public DataDetailAdapter(List<Map<String, String>> list, String tag) {
        this.list = list;
        this.tag = tag;
    }

    public DataDetailAdapter() {
        super();
    }

    private static class ViewHolder{
        public TextView dateView;
        public TextView valueView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.data_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.dateView = (TextView) view.findViewById(R.id.data_date);
            viewHolder.valueView = (TextView) view.findViewById(R.id.data_value);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (list!=null && list.size()>0){
            Map<String, String> data = list.get(i);
            String date = data.get("date");
            String value = data.get("value");
            int color;
            switch (tag){
                case "pressure":
                    color = ContextCompat.getColor(viewGroup.getContext(), R.color.simpleBlue);
                    break;
                case "temperature":
                    color = ContextCompat.getColor(viewGroup.getContext(), R.color.simpleOrange);
                    break;
                case "pulse":
                    color = ContextCompat.getColor(viewGroup.getContext(), R.color.simpleGreen);
                    break;
                default:
                    color = ContextCompat.getColor(viewGroup.getContext(), R.color.simpleBlue);
                    break;
            }
            viewHolder.dateView.setText(date);
            viewHolder.valueView.setText(value);
            viewHolder.valueView.setTextColor(color);
        }
        return view;
    }
}
