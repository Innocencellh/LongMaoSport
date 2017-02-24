package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.BrokenLineInfo;

/**
 * Created by Administrator on 2016/9/27.
 */
public interface BrokenLineView {
    void onBrokenLineSucc(BrokenLineInfo result);

    void onBrokenLineError(String msg);
}
