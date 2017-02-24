package com.live.longmao.presenters;

import com.live.longmao.BaseApp;
import com.live.longmao.model.HotLiveInfo;
import com.live.longmao.model.NearListInfo;
import com.live.longmao.presenters.viewinface.HotListView;
import com.live.longmao.presenters.viewinface.NearListView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/10/8.
 */
public class NearListHelper {
    private NearListView nearListView;

    public NearListHelper(NearListView nearListView) {
        this.nearListView = nearListView;
    }

    public void getHotListHelper() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.NEARBY_URL)
                .addParams("currentPage", "1")
                .addParams("pageSize", "10")
                .addParams("qlng", String.valueOf(BaseApp.getInstance().getLatitude()))
                .addParams("qlat", String.valueOf(BaseApp.getInstance().getLatitude()))
                .build()
                .execute(new GenericsCallback<NearListInfo>(new JsonGenericsSerializator()) {

                    @Override 
                    public void onError(Call call, Exception e, int id) {
                        nearListView.NearListViewError("服务器未响应");
                    }

                    @Override
                    public void onResponse(NearListInfo response, int id) {
                        if (response.isSuccess()) {
                            nearListView.NearListViewSucc(response);
                        } else {
                            nearListView.NearListViewError(response.getErrMsg());
                        }
                    }
                });
    }

}
