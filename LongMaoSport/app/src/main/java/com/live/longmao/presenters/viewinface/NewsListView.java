package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.NewsListInfo;

/**
 * Created by HPDN on 2016/11/28.
 */
public interface NewsListView {
    void onNewsListSucc(NewsListInfo result);

    void onNewsListError(String msg);
}
