package com.live.longmao.presenters;

import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.DelPhotoView;
import com.live.longmao.presenters.viewinface.ModificationMessageView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/10/8.
 */
public class DelPhotoHelper {

    private DelPhotoView delPhotoView;

    public DelPhotoHelper(DelPhotoView delPhotoView) {
        this.delPhotoView = delPhotoView;
    }

    public void getAttention(String photoUrl) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.DELPHOTO_URL)
                .addParams("photoUrl","20160928/mzCQwWSIxwGdaEv7fsiY.jpg")

                .build()
                .execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        delPhotoView.onDelPhotoSucc("服务器未响应");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }


}
