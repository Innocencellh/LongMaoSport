package com.live.longmao.presenters;

import com.live.longmao.model.CoinInfo;
import com.live.longmao.presenters.viewinface.CoinView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by jz on 2016/9/11 0011.
 */
public class CoinHelper {
    private CoinView mCoinView;

    public CoinHelper(CoinView coinView) {
        mCoinView = coinView;
    }

    public void getCoin(String userId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.COIN_URL)
                .addParams("userId", userId + "")
                .build()
                .execute(new GenericsCallback<CoinInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mCoinView.onError("服务器未响应");
                    }

                    @Override
                    public void onResponse(CoinInfo response, int id) {
                        if (response.isSuccess()) {
                            mCoinView.onCoinSucc(response);
                        } else {
                            mCoinView.onError(response.getErrMsg());
                        }
                    }
                });
    }
}
