package com.live.longmao.presenters;

import android.view.View;

import com.live.longmao.model.GetGuessingInfo;
import com.live.longmao.presenters.viewinface.GetGuessingView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/7.
 */
public class GetGuessHelper {

    GetGuessingView getGuessingView;

    public GetGuessHelper(GetGuessingView getGuessingView) {
        this.getGuessingView = getGuessingView;
    }

    public void getGuess(String guessId) {
       // String url = "http://121.40.65.153:8080/gwcommon/guess/readByGuessId.do?";
        OkHttpUtils
                .get()
                .url(OkHttpUtils.Guessing_URL)
                .addParams("guessId", guessId)
                .build()
                .execute(new GenericsCallback<GetGuessingInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getGuessingView.onGetGuessingError("服务器未响应");
                    }

                    @Override
                    public void onResponse(GetGuessingInfo response, int id) {
                        if (response.isSuccess()) {
                            getGuessingView.onGetGuessingSucc(response.getBody());
                        } else {
                            getGuessingView.onGetGuessingError(response.getErrMsg());
                        }
                    }
                });
    }

}
