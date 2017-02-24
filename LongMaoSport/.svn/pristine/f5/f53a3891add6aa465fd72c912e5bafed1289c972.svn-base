package com.live.longmao.presenters;

import com.live.longmao.BaseApp;
import com.live.longmao.model.ExitLiveInfo;
import com.live.longmao.model.ReadLiveInfo;
import com.live.longmao.presenters.viewinface.ExitLiveView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/10.
 */
public class ExitLiveHelper {

    private ExitLiveView mExitLiveView;

    public ExitLiveHelper(ExitLiveView exitLiveView)
    {
        mExitLiveView = exitLiveView;
    }
    public void getExitLive(String id,String userId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.DELETELIVE_URL)
                .addParams("id", id)
                .addParams("userId", userId)
                .build()
                .execute(new GenericsCallback<ExitLiveInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mExitLiveView.onError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ExitLiveInfo response, int id) {
                        if (response.isSuccess()) {
                            mExitLiveView.onExitLiveSucc();
                        } else {
                            mExitLiveView.onError(response.getErrMsg());
                        }
                    }
                });
    }
}
