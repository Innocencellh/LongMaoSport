package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.model.HotLiveInfo;
import com.live.longmao.presenters.viewinface.HotListView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/10/8.
 */
public class HotListHelper {
    private HotListView hotListView;

    public HotListHelper(HotListView attentionView) {
        this.hotListView = attentionView;
    }

    public void getHotListHelper() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.HOT_URL)
                .addParams("currentPage", "1")
                .addParams("pageSize", "10")
                .build()
                .execute(new GenericsCallback<HotLiveInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        hotListView.hotListViewError("服务器未响应");
                    }

                    @Override
                    public void onResponse(HotLiveInfo response, int id) {
                        if (response.isSuccess()) {
                            hotListView.hotListViewSucc(response);
                        } else {
                            hotListView.hotListViewError(response.getErrMsg());
                        }
                    }
                });
    }

}
