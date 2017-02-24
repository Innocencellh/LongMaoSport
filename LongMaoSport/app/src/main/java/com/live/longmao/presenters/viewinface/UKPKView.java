package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.UkPkInfo;

/**
 * Created by HPDN on 2016/12/14.
 */
public interface UKPKView {
    void onUKPKSucc(UkPkInfo result);
    void onUKPKError(String msg);

}
