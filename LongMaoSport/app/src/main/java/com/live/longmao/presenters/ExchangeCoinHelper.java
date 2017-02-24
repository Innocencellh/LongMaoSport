package com.live.longmao.presenters;

import com.live.longmao.model.ExchangeCoinInfo;
import com.live.longmao.model.HotLiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.ExchangeCoinView;
import com.live.longmao.presenters.viewinface.IncomeView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/25.
 */
public class ExchangeCoinHelper {
    private ExchangeCoinView exchangeCoinView;

    public ExchangeCoinHelper(ExchangeCoinView exchangeCoinView) {
        this.exchangeCoinView = exchangeCoinView;
    }

    public void getExchangeCoinHelper(String kllNum) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.KLL_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("coinNum",kllNum)
                .build()
                .execute(new GenericsCallback<ExchangeCoinInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        exchangeCoinView.ExchangeCoinError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ExchangeCoinInfo response, int id) {
                        if (response.isSuccess()) {
                            exchangeCoinView.ExchangeCoinSucc(response);
                        } else {
                            exchangeCoinView.ExchangeCoinError(response.getErrMsg());
                        }
                    }
                });
    }
}
