package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.HotLiveInfo;

/**
 * Created by HPDN on 2016/11/28.
 */
public interface HotListView {
    void hotListViewSucc(HotLiveInfo result);
    void hotListViewError(String msg);
}
