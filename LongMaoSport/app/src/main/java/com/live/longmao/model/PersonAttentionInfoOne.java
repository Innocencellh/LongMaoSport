package com.live.longmao.model;

import java.util.List;

/**
 * Created by HPDN on 2016/12/25.
 */
public class PersonAttentionInfoOne {

    /**
     * facePhoto : 20161224/fYdUeasAqLnZnrtstAix.png
     * liveInfo : {"city":"杭州市","distance":0,"gmtCreate":1482644395000,"gmtCreateUser":"","gmtModified":1482644586000,"gmtModifiedUser":"","id":"243003","lat":0,"liveTitle":"","liveTopic":"","liveVideoUrl":"","lng":0,"particNum":35,"robot":35,"roomId":"10224009","seeNums":0,"stats":1,"tag":"","userId":"10224009"}
     * nickName : 出来咯gh
     * objList : [{"attentionsState":1,"facePhoto":"20161224/umjpprDtDBKDa5zmjVei.png","liveInfo":{"city":"杭州市","distance":0,"gmtCreate":1482644603000,"gmtCreateUser":"","gmtModified":1482644615000,"gmtModifiedUser":"","id":"243004","lat":0,"liveTitle":"","liveTopic":"","liveVideoUrl":"","lng":0,"particNum":51,"robot":50,"roomId":"10224007","seeNums":0,"stats":1,"tag":"","userId":"10224007"},"nickName":"帅哥帅","userId":"10224007"}]
     * userId : 10224009
     */

    private String facePhoto;
    private PersonAttentionInfoTwo personAttentionInfoTwo;
    private String nickName;
    private String userId;


    private List<PersonAttentionInfoTwoList> objList;

    public String getFacePhoto() {
        return facePhoto;
    }

    public void setFacePhoto(String facePhoto) {
        this.facePhoto = facePhoto;
    }

    public PersonAttentionInfoTwo getPersonAttentionInfoTwo() {
        return personAttentionInfoTwo;
    }

    public void setPersonAttentionInfoTwo(PersonAttentionInfoTwo personAttentionInfoTwo) {
        this.personAttentionInfoTwo = personAttentionInfoTwo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<PersonAttentionInfoTwoList> getObjList() {
        return objList;
    }

    public void setObjList(List<PersonAttentionInfoTwoList> objList) {
        this.objList = objList;
    }



}
