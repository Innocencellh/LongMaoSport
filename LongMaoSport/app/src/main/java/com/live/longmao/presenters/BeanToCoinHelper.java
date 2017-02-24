package com.live.longmao.presenters;

import com.live.longmao.model.BeanInfo;
import com.live.longmao.presenters.viewinface.BeanToCoinView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class BeanToCoinHelper {
    private BeanToCoinView mBeanToCoinView;

    public BeanToCoinHelper(BeanToCoinView beanToCoinView) {
        mBeanToCoinView = beanToCoinView;
    }

    public void getData(String userId, String lmBeanNum) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.BeanExchangeMoney_URL)
                .addParams("userId", userId)
                .addParams("lmBeanNum", lmBeanNum)
                .build()
                .execute(new GenericsCallback<BeanInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mBeanToCoinView.onBeanToCoinError("服务器未响应");
                    }

                    @Override
                    public void onResponse(BeanInfo response, int id) {
                        if (response.isSuccess()){
                            mBeanToCoinView.onBeanToCoinSucc(response);
                        }else {
                            mBeanToCoinView.onBeanToCoinError(response.getErrMsg());
                        }
                    }
                });
    }
}
