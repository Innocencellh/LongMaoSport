package com.live.longmao.presenters.viewinface;


import com.live.longmao.model.SeeLiveInfo;

/**
 * Created by HPDN on 2016/12/17.
 */
public interface SeeLiveView {
    void onSeeLiveSucc(SeeLiveInfo result);

    void onSeeLiveError(String msg);
}
