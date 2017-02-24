package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.BeanInfo;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public interface BeanView {
    void onBeanSucc(BeanInfo result);

    void onBeanError(String msg);
}
