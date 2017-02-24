package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PutPhotoInfo;
import com.live.longmao.presenters.viewinface.UpPhotoView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import java.io.File;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/21.
 */
public class UpPhotoHelper {

    private UpPhotoView upPhotoView;

    public UpPhotoHelper(UpPhotoView upPhotoView) {
        this.upPhotoView = upPhotoView;
    }

    public void uploadPhoto(String photo) {
        Log.e("相册照片", "相册照片上传=========>>" + new File(photo));
        OkHttpUtils
                .post()
                .url(OkHttpUtils.IMAGEUPLOAD_URL)
                .addParams("photoType", "1")
                .addParams("userId", MySelfInfo.getInstance().getId()+"")
                .addFile("userFace", "userFace.png", new File(photo))
                .build()
                .execute(new GenericsCallback<PutPhotoInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("onError", e.getMessage());
                        upPhotoView.onUpPhotoError("服务器未响应");
                    }

                    @Override
                    public void onResponse(PutPhotoInfo response, int id) {
                     //   Log.e("onResponse", response);
                        if (response.isSuccess()){
                            upPhotoView.onUpPhotoSucc(response);
                        }else {
                            upPhotoView.onUpPhotoError(response.getErrMsg());
                        }
                    }
                });
    }

}
