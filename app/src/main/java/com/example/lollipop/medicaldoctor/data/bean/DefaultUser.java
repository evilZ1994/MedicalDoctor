package com.example.lollipop.medicaldoctor.data.bean;

import cn.jiguang.imui.commons.models.IUser;

/**
 * Created by Lollipop on 2017/5/9.
 */

public class DefaultUser implements IUser {

    private String id;
    private String displayName;
    private String avatar;

    public DefaultUser(String id, String displayName, String avatar) {
        this.id = id;
        this.displayName = displayName;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getAvatarFilePath() {
        return avatar;
    }
}
