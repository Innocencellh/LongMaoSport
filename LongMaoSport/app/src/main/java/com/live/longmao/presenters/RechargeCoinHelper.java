package com.live.longmao.presenters;

import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.RechargeCoinView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2017/1/10.
 */
public class RechargeCoinHelper {
    RechargeCoinView rechargeCoinView;

    public RechargeCoinHelper(RechargeCoinView rechargeCoinView) {
        this.rechargeCoinView = rechargeCoinView;
    }

    public void getRechargeCoin(String rmb){

        OkHttpUtils
                .post()
                .url(OkHttpUtils.LongMao_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("rmb",rmb)
                .build()
                .execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        rechargeCoinView.onRechargeCoinErr("服务器未响应");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });

    }

    public void getPay(String rmb){

        OkHttpUtils
                .get()
                .url(OkHttpUtils.LongMao_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("rmb",rmb)
                .build()
                .execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        rechargeCoinView.onRechargeCoinErr("服务器未响应");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });

    }


}
