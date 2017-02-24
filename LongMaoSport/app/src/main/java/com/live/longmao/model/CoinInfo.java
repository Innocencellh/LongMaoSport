package com.live.longmao.model;

import java.util.List;

/**
 * Created by jz on 2016/9/11 0011.
 */
public class CoinInfo extends Code {
    private List<CInfo> body;
    public List<CInfo> getBody() {
        return body;
    }

    public void setBody(List<CInfo> body) {
        this.body = body;
    }

}
