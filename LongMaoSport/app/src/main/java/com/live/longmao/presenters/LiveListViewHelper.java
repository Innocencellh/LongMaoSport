package com.live.longmao.presenters;


import com.live.longmao.BaseApp;
import com.live.longmao.model.ListLiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.LiveListView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 直播列表页Presenter
 */
public class LiveListViewHelper {
    private LiveListView mLiveListView;

    public LiveListViewHelper(LiveListView view) {
        mLiveListView = view;
    }

    /**
     * 获取后台数据接口
     */
    public void getPageData(int type) {
        String url="";
        Map<String,String> params = new HashMap<>();
        if(type == 1)
        {
            params.put("userId", MySelfInfo.getInstance().getId());
            url = OkHttpUtils.HOT_URL;
        }
        else if(type == 2)
        {
            url = OkHttpUtils.NEW_URL;
        }
        else if(type == 3)
        {
            url = OkHttpUtils.NEARBY_URL;
            params.put("userId", MySelfInfo.getInstance().getId());
            params.put("userLng", BaseApp.getInstance().getLongitude()+"");
            params.put("userLat", BaseApp.getInstance().getLatitude()+"");
        }
        OkHttpUtils
                .post()
                .url(url)
                .params(params)
                .build()
                .execute(new GenericsCallback<ListLiveInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mLiveListView.onError("服务器未响应");
                    }
                    @Override
                    public void onResponse(ListLiveInfo response, int id) {
                        if (response.isSuccess()){
                            mLiveListView.showFirstPage(response);
                        }else
                        {
                            mLiveListView.onError(response.getErrMsg());
                        }
                    }
                });
    }
    public void getMoreData() {

    }
}
