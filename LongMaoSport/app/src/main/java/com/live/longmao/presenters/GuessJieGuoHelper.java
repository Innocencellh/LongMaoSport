package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.BaseApp;
import com.live.longmao.model.GuessJieGuoInfo;
import com.live.longmao.model.ListGuessingInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.GuessJieGuoView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/18.
 */
public class GuessJieGuoHelper {

    private GuessJieGuoView guessJieGuoView;

    public GuessJieGuoHelper(GuessJieGuoView guessJieGuoView) {
        this.guessJieGuoView = guessJieGuoView;
    }

    public void GuessJieGuo(String userId,String guessId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.GuessJieGuo_URL)
                .addParams("userId",userId)
                .addParams("guessId", guessId)
                .build()
                .execute(new GenericsCallback<GuessJieGuoInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        guessJieGuoView.GuessJieGuoError("服务器未响应");
                    }

                    @Override
                    public void onResponse(GuessJieGuoInfo response, int id) {
                        if (response.isSuccess()){
                            guessJieGuoView.GuessJieGuoSucc(response);
                        }else {
                            guessJieGuoView.GuessJieGuoError(response.getErrMsg());
                        }
                    }
                });
    }

}
