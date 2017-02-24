package com.live.longmao.presenters;

import com.live.longmao.model.ListGuessingInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.ObjGuessingInfo;
import com.live.longmao.model.PersonInfo;
import com.live.longmao.model.UKGuessingInfo;
import com.live.longmao.presenters.viewinface.GuessingView;
import com.live.longmao.presenters.viewinface.PersonView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/10.
 */
public class PersonHelper {
    private PersonView personView;

    public PersonHelper(PersonView personView) {
        this.personView = personView;
    }

    //用户信息
    public void getPerson() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.Person_Url)
                .addParams("userId", MySelfInfo.getInstance().getId()+"")
                .build()
                .execute(new GenericsCallback<PersonInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        personView.onPersonInfoError("服务器未响应");
                    }

                    @Override
                    public void onResponse(PersonInfo response, int id) {
                        if (response.isSuccess()) {
                            personView.onPersonInfoSucc(response);
                        } else {
                            personView.onPersonInfoError(response.getErrMsg());
                        }
                    }
                });
    }
}
