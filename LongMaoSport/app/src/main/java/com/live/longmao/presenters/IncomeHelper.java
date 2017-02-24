package com.live.longmao.presenters;

import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.HotLiveInfo;
import com.live.longmao.model.IncomeInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.HotListView;
import com.live.longmao.presenters.viewinface.IncomeView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/25.
 */
public class IncomeHelper {
    private IncomeView incomeView;

    public IncomeHelper(IncomeView incomeView) {
        this.incomeView = incomeView;
    }

    public void getIncomeHelper() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ExchangerKLL_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .build()
                .execute(new GenericsCallback<IncomeInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        incomeView.IncomeViewError("服务器未响应");
                    }

                    @Override
                    public void onResponse(IncomeInfo response, int id) {
                        if (response.isSuccess()) {
                            incomeView.IncomeViewSucc(response);
                        } else {
                            incomeView.IncomeViewError(response.getErrMsg());
                        }
                    }
                });
    }

    public void getLiveIncomeHelper() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ExchangerKLL_URL)
                .addParams("userId", CurLiveInfo.getRoomNum()+"")
                .build()
                .execute(new GenericsCallback<IncomeInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        incomeView.IncomeViewError("服务器未响应");
                    }

                    @Override
                    public void onResponse(IncomeInfo response, int id) {
                        if (response.isSuccess()) {
                            incomeView.IncomeViewSucc(response);
                        } else {
                            incomeView.IncomeViewError(response.getErrMsg());
                        }
                    }
                });
    }

}
