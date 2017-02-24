package com.live.longmao.presenters;

import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.CodeTimeInfo;
import com.live.longmao.model.ForgetPasswordInfo;
import com.live.longmao.presenters.viewinface.CodeTimeView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/9/13.
 */
public class CodeTimeHelper {

    CodeTimeView codeTimeView;

    public CodeTimeHelper(CodeTimeView codeTimeView) {
        this.codeTimeView = codeTimeView;
    }

    public void getCodeTime(String userName) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.CODETIME_URL)
                .addParams("userName", userName)
                .build()
                .execute(new GenericsCallback<CodeTimeInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        codeTimeView.onError("服务器未响应");
                    }

                    @Override
                    public void onResponse(CodeTimeInfo response, int id) {
                        if (response.isSuccess()) {
                            codeTimeView.onCodeTimeViewSucc(response);
                        } else {
                            codeTimeView.onError(response.getErrMsg());
                        }
                    }
                });
    }


    public void getForgetCodeTime(String userName) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.Forget_URL)
                .addParams("userName", userName)
                .build()
                .execute(new GenericsCallback<ForgetPasswordInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        codeTimeView.onError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ForgetPasswordInfo response, int id) {
                        if (response.isSuccess()) {
                            codeTimeView.onForgetTimeViewSucc(response);
                        } else {
                            codeTimeView.onError(response.getErrMsg());
                        }
                    }
                });
    }


}
