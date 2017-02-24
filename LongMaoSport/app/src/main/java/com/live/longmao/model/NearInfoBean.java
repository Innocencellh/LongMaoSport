package com.live.longmao.model;

import java.util.List;

/**
 * Created by HPDN on 2016/11/30.
 */
public class NearInfoBean {
    /**
     * currentPage : 1
     * itemCount : 1
     * list : [{"liveDTO":{"city":"杭州市","distance":0,"gmtCreate":1480515069000,"id":"213021","lat":30.274555,"liveTitle":"","lng":119.952062,"roomId":"10210028","seeNums":0,"stats":0,"userId":"10210028"},"picUrl":"","sex":0,"userName":"15858100243"}]
     * pageSize : 10
     * pageTotal : 1
     */

    private NearInfo liveDTO;


    public NearInfo getLiveDTO() {
        return liveDTO;
    }

    public void setLiveDTO(NearInfo liveDTO) {
        this.liveDTO = liveDTO;
    }

    }
