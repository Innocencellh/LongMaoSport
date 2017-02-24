package com.live.longmao.presenters.viewinface;

import com.live.longmao.bean.GiftBean;
import com.live.longmao.model.GradeInfo;

/**
 * Created by HPDN on 2016/12/1.
 */
public interface GradeView {
    void onGradeSucc(GradeInfo gradeInfo);//送礼物成功
    void onGradeError(String msg);
}
