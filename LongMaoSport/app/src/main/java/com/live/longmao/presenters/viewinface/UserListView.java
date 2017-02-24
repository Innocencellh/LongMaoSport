package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.MemberInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/22.
 */
public interface UserListView {
    void showMembersList(ArrayList<MemberInfo> data);
}
