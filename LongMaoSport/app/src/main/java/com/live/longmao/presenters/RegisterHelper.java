package com.live.longmao.presenters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.live.longmao.model.ForgetInfo;
import com.live.longmao.model.ResListUserInfo;
import com.live.longmao.presenters.viewinface.RegisterView;
import com.live.longmao.model.ListUserInfo;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/9.
 */
public class RegisterHelper {

    private RegisterView mRegisterView;

    public RegisterHelper(RegisterView registerView) {
        mRegisterView = registerView;
    }

    public void getReg(String phone, String pwd, String code) {
        OkHttpUtils
                .post()//
                .url(OkHttpUtils.REGISTER_URL)//
                .addParams("userName", phone)//
                .addParams("loginPwd", pwd)
                .addParams("verificationCode", code)
                .build()//
                .execute(new GenericsCallback<ResListUserInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mRegisterView.onRegisterError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ResListUserInfo response, int id) {
                        if (response.isSuccess()) {
                            mRegisterView.onRegisterSucc(response);
                        } else {
                            mRegisterView.onRegisterError(response.getErrMsg());
                            Log.i("Register", "" + response.getErrMsg());
                        }
                    }

                });
    }


    public void getForgetPassword(String userId, String pwd, String code) {
        OkHttpUtils
                .post()//
                .url(OkHttpUtils.ForgetPwd_URL)//
                .addParams("userId", userId)
                .addParams("loginPwd", pwd)
                .addParams("verificationCode", code)
                .build()//
                .execute(new GenericsCallback<ForgetInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mRegisterView.onRegisterError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ForgetInfo response, int id) {

                        if (response.isSuccess()) {
                            mRegisterView.onFprgetPassword(response);
                        } else {
                            mRegisterView.onRegisterError(response.getErrMsg());
                            Log.i("Register", "" + response.getErrMsg());
                        }
                    }

                });
    }


}
