package com.live.longmao.presenters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.live.longmao.BaseApp;
import com.live.longmao.model.ReadLiveInfo;
import com.live.longmao.presenters.viewinface.ReadLiveView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class ReadLiveHelper {
    private ReadLiveView mReadLiveView;

    public ReadLiveHelper(ReadLiveView readLiveView) {
        mReadLiveView = readLiveView;
    }

    public void getStartLive(String userId,String liveTitle,String lng,String lat,String city,String roomId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ADDLIVE_URL)
                .addParams("userId", userId)
                .addParams("liveTitle", liveTitle)
                .addParams("lng", lng)
                .addParams("lat", lat)
                .addParams("city", city)
                .addParams("roomId", roomId)
                .build()
                .execute(new GenericsCallback<ReadLiveInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mReadLiveView.onReadLiveError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ReadLiveInfo response, int id) {
                        if (response.isSuccess()) {
                            BaseApp.getInstance().setLId(response.getBody());
                            Log.e("BaseApp.getInstance()",BaseApp.getInstance().getLId());
                            mReadLiveView.onReadLiveSucc();
                        } else {
                            mReadLiveView.onReadLiveError(response.getErrMsg());
                        }
                    }
                });
    }
}
