package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonAttentionInfo;
import com.live.longmao.presenters.viewinface.AttentionSellLiveView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/26.
 */
public class AttentionSellLiveHelper {

    private AttentionSellLiveView attentionSellLiveView;

    public AttentionSellLiveHelper(AttentionSellLiveView attentionSellLiveView) {
        this.attentionSellLiveView = attentionSellLiveView;
    }

    public void getAttention() {
        Log.i("关注", "===" + MySelfInfo.getInstance().getId());
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ZBAttentions_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .build()
                .execute(new GenericsCallback<PersonAttentionInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        attentionSellLiveView.onAttentionError("服务器未响应");
                    }

                    @Override
                    public void onResponse(PersonAttentionInfo response, int id) {
                        if (response.isSuccess()) {
                            attentionSellLiveView.onAttentionSucc(response);
                        } else {
                            attentionSellLiveView.onAttentionError(response.getErrMsg());
                        }
                    }
                });
    }

}
