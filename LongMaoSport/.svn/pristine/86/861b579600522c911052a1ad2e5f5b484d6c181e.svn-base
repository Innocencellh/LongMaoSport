package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.model.ListGuessingInfo;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/30.
 */
public class HeartBeatHelper {
    private static HeartBeatHelper mHeartBeatHelper;
    public static HeartBeatHelper getInstance()
    {
        if(null==mHeartBeatHelper)
        {
            mHeartBeatHelper = new HeartBeatHelper();
        }
        return mHeartBeatHelper;
    }
    public void sendHeartBeat(String id,String userId)
    {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.LIVEHEART)
                .addParams("id", id)
                .addParams("userId", userId)
                .build()
                .execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response",response);
                    }
                });
    }
}
