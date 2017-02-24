package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.model.BeanInfo;
import com.live.longmao.presenters.viewinface.FollowView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/8.
 */
public class FollowHelper {
    private FollowView mFollowView;

    public FollowHelper(FollowView followView) {
        mFollowView = followView;
    }

    public void getFollow(String userId, String anchorUserId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.Attentions_URL)
                .addParams("userId", userId)
                .addParams("anchorUserId", anchorUserId)
                .build()
                .execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mFollowView.onFollowError("服务器未响应");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("response", response);
                    }
                });
    }

    public void cancelFollow(String userId, String anchorUserId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.CancelAttentions_URL)
                .addParams("userId", userId)
                .addParams("anchorUserId", anchorUserId)
                .build()
                .execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mFollowView.onFollowError("服务器未响应");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("response", response);
                    }
                });
    }
}
