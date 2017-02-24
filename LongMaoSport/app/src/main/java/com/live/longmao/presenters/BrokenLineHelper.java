package com.live.longmao.presenters;

import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.BrokenLineInfo;
import com.live.longmao.model.ListBrokenLineInfo;
import com.live.longmao.presenters.viewinface.BrokenLineView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/27.
 */
public class BrokenLineHelper {
    private BrokenLineView mBrokenLineView;
    public BrokenLineHelper(BrokenLineView brokenLineView)
    {
        mBrokenLineView = brokenLineView;
    }

    public void isBrokenLine(String userId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.BrokenLine_URL)
                .addParams("userId", userId)
                .build()
                .execute(new GenericsCallback<ListBrokenLineInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mBrokenLineView.onBrokenLineError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ListBrokenLineInfo response, int id) {
                        if(response.isSuccess()) {
                            mBrokenLineView.onBrokenLineSucc(response.getBody());
                        }else
                        {
                            mBrokenLineView.onBrokenLineError("服务器未响应");
                        }
                    }
                });
    }
}
