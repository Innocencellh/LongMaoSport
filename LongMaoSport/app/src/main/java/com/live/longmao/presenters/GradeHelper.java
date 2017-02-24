package com.live.longmao.presenters;

import com.live.longmao.bean.GiftBean;
import com.live.longmao.model.GiftGivingInfo;
import com.live.longmao.model.GradeInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.GradeView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/1.
 */
public class GradeHelper {
    private GradeView gradeView;

    public GradeHelper(GradeView gradeView) {
        this.gradeView = gradeView;
    }

    public void givingGift() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.PersonGrade_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .build()
                .execute(new GenericsCallback<GradeInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        gradeView.onGradeError("服务器未响应");
                    }

                    @Override
                    public void onResponse(GradeInfo response, int id) {
                        if (response.isSuccess()) {
                            gradeView.onGradeSucc(response);
                        } else {
                            gradeView.onGradeError(response.getErrMsg());
                        }
                    }
                });
    }

}
