package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.CointoBeanInfo;
import com.live.longmao.model.CtoBInfo;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public interface CoinToBeanView {
    void onCoinToBeanSucc(CointoBeanInfo result);

    void onCtoBError(String msg);
}
