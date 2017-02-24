package com.live.longmao.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class ListUserInfo extends Code{
    List<UserInfo> body;

    public List<UserInfo> getBody() {
        return body;
    }

    public void setBody(List<UserInfo> body) {
        this.body = body;
    }
}
