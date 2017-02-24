package com.live.longmao.presenters.viewinface;

import com.live.longmao.bean.GiftBean;
import com.live.longmao.model.GuessingInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public interface GiftGivingView {
    void onGiftGivingSucc(GiftBean giftBean);//送礼物成功
    void onError(String msg);
}
