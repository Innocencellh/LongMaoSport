package com.live.longmao.presenters;


import com.live.longmao.bean.GiftBean;
import com.live.longmao.model.GiftGivingInfo;
import com.live.longmao.model.ListGuessingInfo;
import com.live.longmao.presenters.viewinface.GiftGivingView;
import com.live.longmao.presenters.viewinface.GuessingView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/11.
 * 发送礼物的接口
 */
public class GiftGivingHelper {
    private GiftGivingView mGiftGivingView;

    public GiftGivingHelper(GiftGivingView giftGivingView) {
        mGiftGivingView = giftGivingView;
    }

    public void givingGift(String userId, String anchorUserId,String giftId,String liveId, final GiftBean giftBean) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.QUERYBYIDS_URL)
                .addParams("userId", userId)
                //.addParams("anchorUserId", anchorUserId)
                .addParams("destUserId", anchorUserId)
                .addParams("giftId", giftId)
                .addParams("liveId", liveId)
                .build()
                .execute(new GenericsCallback<GiftGivingInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mGiftGivingView.onError("服务器未响应");
                    }

                    @Override
                    public void onResponse(GiftGivingInfo response, int id) {
                        if (response.isSuccess()) {
                            mGiftGivingView.onGiftGivingSucc(giftBean);
                        } else {
                            mGiftGivingView.onError(response.getErrMsg());
                        }
                    }
                });
    }

}
