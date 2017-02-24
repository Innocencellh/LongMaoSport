package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.ForgetInfo;
import com.live.longmao.model.ListUserInfo;
import com.live.longmao.model.ResListUserInfo;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface RegisterView {
    void onRegisterSucc(ResListUserInfo result);
    void onRegisterError(String msg);
    void onFprgetPassword(ForgetInfo result);
}
