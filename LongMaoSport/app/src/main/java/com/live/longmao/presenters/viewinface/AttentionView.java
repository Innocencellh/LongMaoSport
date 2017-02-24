package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.AttentionInfo;
import com.live.longmao.model.PersonAttentionInfo;

/**
 * Created by HPDN on 2016/10/8.
 */
public interface AttentionView {
    void onAttentionSucc(PersonAttentionInfo result);

    void onAttentionError(String msg);
}
