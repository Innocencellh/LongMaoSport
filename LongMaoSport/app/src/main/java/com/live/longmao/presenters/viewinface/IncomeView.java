package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.IncomeInfo;

/**
 * Created by HPDN on 2016/12/25.
 */
public interface IncomeView {
    void IncomeViewSucc(IncomeInfo result);
    void IncomeViewError(String msg);
}
