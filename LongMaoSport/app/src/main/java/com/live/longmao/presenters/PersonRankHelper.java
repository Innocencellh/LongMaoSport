package com.live.longmao.presenters;

import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonRankInfo;
import com.live.longmao.presenters.viewinface.PersonRankView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/23.
 */
public class PersonRankHelper {
    private PersonRankView personRankView;

    public PersonRankHelper(PersonRankView personRankView) {
        this.personRankView = personRankView;
    }

    //用户信息
    public void getPersonRank() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.PersonRank_URL)
                .addParams("userId", MySelfInfo.getInstance().getId()+"")
                .build()
                .execute(new GenericsCallback<PersonRankInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        personRankView.onPersonRankError("服务器未响应");
                    }

                    @Override
                    public void onResponse(PersonRankInfo response, int id) {
                        if (response.isSuccess()) {
                            personRankView.onPersonRankSucc(response);
                        } else {
                            personRankView.onPersonRankError(response.getErrMsg());
                        }
                    }
                });
    }
}
