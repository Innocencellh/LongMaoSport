package com.live.longmao.presenters;

import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.DelPhotoView;
import com.live.longmao.presenters.viewinface.ExaminePhotoView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/10/8.
 */
public class ExaminePhotoHelper {

    private ExaminePhotoView examinePhotoView;

    public ExaminePhotoHelper(ExaminePhotoView examinePhotoView) {
        this.examinePhotoView = examinePhotoView;
    }

    public void getAttention() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.UpPhoto_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())

                .build()
                .execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        examinePhotoView.onExaminePhotoSucc("服务器未响应");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }


}
