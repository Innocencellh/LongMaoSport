package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.ExchangeCoinInfo;
import com.live.longmao.model.HotLiveInfo;

/**
 * Created by HPDN on 2016/12/25.
 */
public interface ExchangeCoinView {
    void ExchangeCoinSucc(ExchangeCoinInfo result);
    void ExchangeCoinError(String msg);
}
