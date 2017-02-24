package com.live.longmao.presenters;

import com.live.longmao.model.CointoBeanInfo;
import com.live.longmao.model.CtoBInfo;
import com.live.longmao.presenters.viewinface.CoinToBeanView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class CoinToBeanHelper {
    private CoinToBeanView mCoinToBeanView;

    public CoinToBeanHelper(CoinToBeanView coinToBeanView) {
        mCoinToBeanView = coinToBeanView;
    }

    public void getCoinToBean(String userId,String lmCoinNum) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.COINTBEAN_URL)
                .addParams("lmCoinNum", lmCoinNum)
                .addParams("userId", userId)
                .build()
                .execute(new GenericsCallback<CointoBeanInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mCoinToBeanView.onCtoBError("服务器未响应");
                    }

                    @Override
                    public void onResponse(CointoBeanInfo response, int id) {
                        if (response.isSuccess()) {
                            mCoinToBeanView.onCoinToBeanSucc(response);
                        } else {
                            mCoinToBeanView.onCtoBError(response.getErrMsg());
                        }
                    }
                });

    }
}
