package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.AttentionInfo;
import com.live.longmao.model.PersonAttentionInfo;

/**
 * Created by HPDN on 2016/10/8.
 */
public interface FansView {
    void onFansSucc(PersonAttentionInfo result);

    void onFansError(String msg);
}
