package com.example.lollipop.medicaldoctor.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.ui.activity.ChatActivity;
import com.example.lollipop.medicaldoctor.ui.activity.DataActivity;
import com.example.lollipop.medicaldoctor.ui.activity.PatientInfoActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by R2D2 on 2017/5/12.
 */

public class PatientListRecyclerAdapter extends RecyclerView.Adapter<PatientListRecyclerAdapter.ViewHolder>{
    private List<Map<String, Object>> userInfos;

    public void setUserInfos(List<Map<String, Object>> userInfos) {
        this.userInfos = userInfos;
    }

    public PatientListRecyclerAdapter(List<Map<String, Object>> userInfos) {
        super();
        this.userInfos = userInfos;
    }

    public class OnItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            Map<String, Object> info = (Map<String, Object>) v.getTag();
            Intent intent = new Intent();
            switch (id){
                case R.id.image:
                    intent = new Intent(v.getContext(), PatientInfoActivity.class);
                    intent.putExtra("username", (String) info.get("username"));
                    intent.putExtra("id", (int)info.get("id"));
                    break;
                case R.id.item:
                    intent = new Intent(v.getContext(), DataActivity.class);
                    intent.putExtra("username", (String) info.get("username"));
                    intent.putExtra("id", (int)info.get("id"));
                    break;
                case R.id.chat:
                    intent = new Intent(v.getContext(), ChatActivity.class);
                    intent.putExtra("username", (String) info.get("username"));
                    break;
            }
            v.getContext().startActivity(intent);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private AppCompatImageView image;
        private RelativeLayout item;
        private AppCompatTextView username;
        private AppCompatTextView name;
        private AppCompatImageView chat;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (AppCompatImageView) itemView.findViewById(R.id.image);
            item = (RelativeLayout) itemView.findViewById(R.id.item);
            username = (AppCompatTextView) itemView.findViewById(R.id.username);
            name = (AppCompatTextView) itemView.findViewById(R.id.name);
            chat = (AppCompatImageView) itemView.findViewById(R.id.chat);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (userInfos!=null && userInfos.size()>0){
            Map<String, Object> userInfo = userInfos.get(userInfos.size()-position-1);
            holder.name.setText((CharSequence) userInfo.get("name"));
            holder.username.setText((CharSequence) userInfo.get("username"));
            OnItemClickListener listener = new OnItemClickListener();
            holder.image.setTag(userInfo);
            holder.item.setTag(userInfo);
            holder.chat.setTag(userInfo);
            holder.image.setOnClickListener(listener);
            holder.item.setOnClickListener(listener);
            holder.chat.setOnClickListener(listener);
        }
    }

    @Override
    public int getItemCount() {
        return userInfos.size();
    }
}
