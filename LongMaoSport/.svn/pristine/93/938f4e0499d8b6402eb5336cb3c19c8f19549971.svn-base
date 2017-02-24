package com.live.longmao.presenters;

import com.live.longmao.model.BeanInfo;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class BeanHelper {
    private BeanView mBeanView;

    public BeanHelper(BeanView beanView) {
        mBeanView = beanView;
    }

    public void getBean(String userId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.BEAN_URL)
                .addParams("userId", userId+"")
                .build()
                .execute(new GenericsCallback<BeanInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mBeanView.onBeanError("服务器未响应");
                    }

                    @Override
                    public void onResponse(BeanInfo response, int id) {
                        if (response.isSuccess()) {
                            mBeanView.onBeanSucc(response);
                        } else {
                            mBeanView.onBeanError(response.getErrMsg());
                        }
                    }
                });
    }
}
