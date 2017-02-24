package com.live.longmao.presenters;

import com.live.longmao.model.NewsListInfo;
import com.live.longmao.presenters.viewinface.NewsListView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/11/28.
 */
public class NewsListHelper {

    private NewsListView newsListView;

    public NewsListHelper(NewsListView newsListView) {
        this.newsListView = newsListView;
    }

    public void getNewsList() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.NewsList_URL)
                .addParams("currentPage", "1")
                .addParams("pageSize", "10")
                .build()
                .execute(new GenericsCallback<NewsListInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        newsListView.onNewsListError("服务器未响应");
                    }

                    @Override
                    public void onResponse(NewsListInfo response, int id) {
                        if (response.isSuccess()) {
                            newsListView.onNewsListSucc(response);
                        } else {
                            newsListView.onNewsListError(response.getErrMsg());
                        }
                    }
                });
    }
}
