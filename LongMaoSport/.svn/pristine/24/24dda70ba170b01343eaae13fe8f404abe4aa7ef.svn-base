package com.live.longmao.presenters;

import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonInfo;
import com.live.longmao.model.UkPkInfo;
import com.live.longmao.presenters.viewinface.UKPKView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/14.
 */
public class UKPKHelper {

    private UKPKView ukpkView;

    public UKPKHelper(UKPKView ukpkView) {
        this.ukpkView = ukpkView;
    }

    //主播开启设置竞猜
    public void getUKPK(String userId,String tittle,String bean,String answa,String answb,String stopBetTime) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.UKPK_Url)
                .addParams("userId", userId)
                .addParams("roomId", String.valueOf(CurLiveInfo.getRoomNum()))
                .addParams("guessTitle", tittle)
                .addParams("beanNum", bean)
                .addParams("answa", answa)
                .addParams("answb", answb)
                .addParams("stopBetTime", stopBetTime)
                .build()
                .execute(new GenericsCallback<UkPkInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ukpkView.onUKPKError("服务器未响应");
                    }

                    @Override
                    public void onResponse(UkPkInfo response, int id) {
                        if (response.isSuccess()) {
                            ukpkView.onUKPKSucc(response);
                        } else {
                            ukpkView.onUKPKError(response.getErrMsg());
                        }
                    }
                });
    }

}
