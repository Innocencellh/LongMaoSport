package com.live.okhttp.builder;

import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.request.RequestCall;
import com.live.okhttp.request.OtherRequest;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
