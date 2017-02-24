package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.CoinInfo;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public interface CoinView {
    void onCoinSucc(CoinInfo result);

    void onError(String msg);
}
