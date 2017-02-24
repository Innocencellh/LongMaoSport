package com.live.longmao.presenters;

import com.live.longmao.model.ListGuessingInfo;
import com.live.longmao.model.ObjGuessingInfo;
import com.live.longmao.model.ReadLiveInfo;
import com.live.longmao.model.UKGuessingInfo;
import com.live.longmao.presenters.viewinface.GuessingView;
import com.live.longmao.presenters.viewinface.ReadLiveView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/10.
 */
public class GuessingHelper {
    private GuessingView mGuessingView;

    public GuessingHelper(GuessingView guessingView) {
        mGuessingView = guessingView;
    }

    //主播开启设置竞猜
    public void addGuessing(String userId, String guesstitle, String beannum, String answa, String answb, String stopBetTime) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ADDANCHOR_URL)
                .addParams("userId", userId)
                .addParams("guessTitle", guesstitle)
                .addParams("beanNum", beannum)
                .addParams("answa", answa)
                .addParams("answb", answb)
                .addParams("stopBetTime", stopBetTime)
                .build()
                .execute(new GenericsCallback<ListGuessingInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mGuessingView.onGuessingError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ListGuessingInfo response, int id) {
                        if (response.isSuccess()) {
                            mGuessingView.onGuessingSucc();
                        } else {
                            mGuessingView.onGuessingError(response.getErrMsg());
                        }
                    }
                });
    }

    //读盘
    public void readGuessing(String userId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.READGUESSNEW_URL)
                .addParams("userId", userId)
                .build()
                .execute(new GenericsCallback<ListGuessingInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mGuessingView.onGuessingError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ListGuessingInfo response, int id) {
                        mGuessingView.onGuessingRead(response.getBody(), response.isSuccess());
                    }
                });
    }

    //主播封盘
    public void stopGuessing(String guessId, String status) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.STOPBET_URL)
                .addParams("guessId", guessId)
                .addParams("status", status)
                .build()
                .execute(new GenericsCallback<ObjGuessingInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mGuessingView.onGuessingError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ObjGuessingInfo response, int id) {
                        if (response.isSuccess()) {
                            mGuessingView.onGuessingEntertained(response.getBody());
                        } else {
                            mGuessingView.onGuessingError(response.getErrMsg());
                        }
                    }
                });
    }

    //用户参与竞猜
    public void userBetGuessing(String userId, String guessId, String betResult, String betBeanNum) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ADDUSERRECORD_URL)
                .addParams("userId", userId)
                .addParams("guessId", guessId)
                .addParams("betResult", betResult)
                .addParams("betBeanNum", betBeanNum)
                .build()
                .execute(new GenericsCallback<UKGuessingInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mGuessingView.onGuessingError("服务器未响应");
                    }

                    @Override
                    public void onResponse(UKGuessingInfo response, int id) {
                        if (response.isSuccess()) {
                            mGuessingView.onGuessingBet(response.getBody());
                        } else {
                            mGuessingView.onGuessingError(response.getErrMsg());
                        }
                    }
                });
    }

    //主播结算竞猜
    public void statementGuessing(String userId, String guessId, String finalResult) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.STATEMENT_URL)
                .addParams("userId", userId)
                .addParams("guessId", guessId)
                .addParams("finalResult", finalResult)
                .build()
                .execute(new GenericsCallback<ListGuessingInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mGuessingView.onGuessingError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ListGuessingInfo response, int id) {
                        if (response.isSuccess()) {
                            mGuessingView.onGuessingJieSuanSucc();
                        } else {
                            mGuessingView.onGuessingError(response.getErrMsg());
                        }
                    }
                });
    }
}
