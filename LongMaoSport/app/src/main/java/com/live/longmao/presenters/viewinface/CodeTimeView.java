package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.CodeTimeInfo;
import com.live.longmao.model.ForgetPasswordInfo;

/**
 * Created by HPDN on 2016/9/13.
 */
public interface CodeTimeView {

    void onCodeTimeViewSucc(CodeTimeInfo result);

    void onForgetTimeViewSucc(ForgetPasswordInfo result);

    void onError(String msg);

}
