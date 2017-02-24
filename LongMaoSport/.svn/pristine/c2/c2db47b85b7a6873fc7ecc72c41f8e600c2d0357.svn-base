package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.model.AttentionInfo;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonAttentionInfo;
import com.live.longmao.model.PersonAttentionInfoOne;
import com.live.longmao.presenters.viewinface.AttentionView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/10/8.
 */
public class AttentionHelper {
    private AttentionView attentionView;

    public AttentionHelper(AttentionView attentionView) {
        this.attentionView = attentionView;
    }

    public void getAttention() {
        Log.i("关注", "===" + MySelfInfo.getInstance().getId());
        OkHttpUtils
                .post()
                .url(OkHttpUtils.AttentionsUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId() + "")
                .build()
                .execute(new GenericsCallback<PersonAttentionInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        attentionView.onAttentionError("服务器未响应");
                    }

                    @Override
                    public void onResponse(PersonAttentionInfo response, int id) {
                        if (response.isSuccess()) {
                            attentionView.onAttentionSucc(response);
                        } else {
                            attentionView.onAttentionError(response.getErrMsg());
                        }
                    }
                });
    }

}
