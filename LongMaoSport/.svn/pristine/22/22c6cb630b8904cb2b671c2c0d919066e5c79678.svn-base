package com.live.longmao.presenters;

import com.live.longmao.model.AttentionInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonAttentionInfo;
import com.live.longmao.presenters.viewinface.AttentionView;
import com.live.longmao.presenters.viewinface.FansView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/10/8.
 */
public class FansHelper {
    private FansView fansView;

    public FansHelper(FansView fansView) {
        this.fansView = fansView;
    }

    public void getFans() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.FunsUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId() + "")
                .build()
                .execute(new GenericsCallback<PersonAttentionInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        fansView.onFansError("服务器未响应");
                    }

                    @Override
                    public void onResponse(PersonAttentionInfo response, int id) {
                        if (response.isSuccess()) {
                            fansView.onFansSucc(response);
                        } else {
                            fansView.onFansError(response.getErrMsg());
                        }
                    }
                });
    }

}
