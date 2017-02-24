package com.live.longmao.presenters;

import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.ZBRankInfo;
import com.live.longmao.presenters.viewinface.ZBRankView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/12/26.
 */
public class ZBRankHelper {

    private ZBRankView zbRankView;

    public ZBRankHelper(ZBRankView zbRankView) {
        this.zbRankView = zbRankView;
    }

    public void getZBRank(String anchorUserId) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ZBRank_URL)
                .addParams("anchorUserId", anchorUserId)
                .build()
                .execute(new GenericsCallback<ZBRankInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        zbRankView.onZBRankError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ZBRankInfo response, int id) {
                        if (response.isSuccess()) {
                            zbRankView.onZBRankSucc(response);
                        } else {
                            zbRankView.onZBRankError(response.getErrMsg());
                        }
                    }
                });
    }


}
