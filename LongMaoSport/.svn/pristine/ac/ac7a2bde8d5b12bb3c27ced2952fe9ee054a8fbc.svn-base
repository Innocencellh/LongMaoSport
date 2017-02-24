package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.model.SeeLiveInfo;
import com.live.longmao.presenters.viewinface.SeeLiveView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/30.
 */
public class SeeLiveHelper {
//    private static SeeLiveHelper mSeeLiveHelper;
//
//    public static SeeLiveHelper getInstance() {
//        if (null == mSeeLiveHelper) {
//            mSeeLiveHelper = new SeeLiveHelper();
//        }
//        return mSeeLiveHelper;
//    }

    private SeeLiveView seeLiveView;

    public SeeLiveHelper(SeeLiveView seeLiveView) {
        this.seeLiveView = seeLiveView;
    }

    public void seeLive(String userId, String roomId, String countType) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.SEELIVE)
                .addParams("userId", userId)
                .addParams("roomId", roomId)
                .addParams("countType", countType)
                .build()
                .execute(new GenericsCallback<SeeLiveInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        seeLiveView.onSeeLiveError("服务器未响应");
                    }

                    @Override
                    public void onResponse(SeeLiveInfo response, int id) {
                        if (response.isSuccess()){
                            seeLiveView.onSeeLiveSucc(response);
                        }else {
                            seeLiveView.onSeeLiveError(response.getErrMsg());
                        }
                      //  Log.d("response", response);
                    }
                });
    }

    public void outLive(String userId, String roomId, String countType) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.OUTLIVE)
                .addParams("userId", userId)
                .addParams("roomId", roomId)
                .addParams("countType", countType)
                .build()
                .execute(new GenericsCallback<SeeLiveInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        seeLiveView.onSeeLiveError("服务器未响应");
                    }

                    @Override
                    public void onResponse(SeeLiveInfo response, int id) {
                        if (response.isSuccess()){
                            seeLiveView.onSeeLiveSucc(response);
                        }else {
                            seeLiveView.onSeeLiveError(response.getErrMsg());
                        }
                    }
                });
    }
}
