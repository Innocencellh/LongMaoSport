package com.live.longmao.model;

import java.util.List;

/**
 * Created by HPDN on 2016/11/28.
 */
public class NewsListInfo extends Code{

    private NewsListBean body;
    /**
     * body : {"currentPage":1,"itemCount":378,"list":[{"city":"杭州市","distance":0,"gmtCreate":1475112302000,"id":"127020","lat":30.276915,"liveTitle":"","lng":119.982161,"roomId":"10160006","seeNums":0,"userId":"10160006"},{"city":"杭州市","distance":0,"gmtCreate":1480317687000,"id":"203016","lat":30.277107,"liveTitle":"","lng":119.98221,"roomId":"10178070","seeNums":0,"stats":0,"userId":"10178070"},{"city":"杭州市","distance":0,"gmtCreate":1480312788000,"id":"203015","lat":30.276965,"liveTitle":"","lng":119.982198,"roomId":"10210028","seeNums":0,"stats":1,"userId":"10210028"},{"city":"杭州市","distance":0,"gmtCreate":1480312239000,"gmtModified":1480312652000,"id":"203014","lat":0,"liveTitle":"","lng":0,"roomId":"10210028","seeNums":0,"stats":1,"userId":"10210028"},{"city":"杭州市","distance":0,"gmtCreate":1480305266000,"gmtModified":1480305272000,"id":"203013","lat":0,"liveTitle":"","lng":0,"roomId":"10178070","seeNums":0,"stats":1,"userId":"10178070"},{"city":"杭州市","distance":0,"gmtCreate":1480303379000,"id":"203012","lat":30.276965,"liveTitle":"","lng":119.982198,"roomId":"10210028","seeNums":0,"stats":1,"userId":"10210028"},{"city":"杭州市","distance":0,"gmtCreate":1480302123000,"gmtModified":1480303107000,"id":"203011","lat":0,"liveTitle":"","lng":0,"roomId":"10210028","seeNums":0,"stats":1,"userId":"10210028"},{"city":"杭州市","distance":0,"gmtCreate":1480301395000,"gmtModified":1480301550000,"id":"203010","lat":0,"liveTitle":"","lng":0,"roomId":"10210028","seeNums":0,"stats":1,"userId":"10210028"},{"city":"杭州市","distance":0,"gmtCreate":1480301147000,"gmtModified":1480301338000,"id":"203009","lat":0,"liveTitle":"","lng":0,"roomId":"10210028","seeNums":0,"stats":1,"userId":"10210028"},{"city":"杭州市","distance":0,"gmtCreate":1480300915000,"gmtModified":1480301009000,"id":"203008","lat":0,"liveTitle":"还会","lng":0,"roomId":"10210028","seeNums":0,"stats":1,"userId":"10210028"}],"pageSize":10,"pageTotal":38}
     * errSerious : false
     * newSessionId : 20161128aUw7pjh9AcYzcas
     * success : true
     */


    public NewsListBean getBody() {
        return body;
    }

    public void setBody(NewsListBean body) {
        this.body = body;
    }

}
