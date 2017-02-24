package com.live.longmao.model;

/**
 * Created by HPDN on 2016/12/25.
 */
public class PersonAttentionInfoTwoList {
    /**
     * attentionsState : 1
     * facePhoto : 20161224/umjpprDtDBKDa5zmjVei.png
     * liveInfo : {"city":"杭州市","distance":0,"gmtCreate":1482644603000,"gmtCreateUser":"","gmtModified":1482644615000,"gmtModifiedUser":"","id":"243004","lat":0,"liveTitle":"","liveTopic":"","liveVideoUrl":"","lng":0,"particNum":51,"robot":50,"roomId":"10224007","seeNums":0,"stats":1,"tag":"","userId":"10224007"}
     * nickName : 帅哥帅
     * userId : 10224007
     */
    private int attentionsState;
    private String facePhoto;


    private PersonAttentionInfoTwoListBean personAttentionInfoTwoListBean;
    private String nickName;
    private String userId;

    public int getAttentionsState() {
        return attentionsState;
    }

    public void setAttentionsState(int attentionsState) {
        this.attentionsState = attentionsState;
    }

    public String getFacePhoto() {
        return facePhoto;
    }

    public void setFacePhoto(String facePhoto) {
        this.facePhoto = facePhoto;
    }

    public PersonAttentionInfoTwoListBean getPersonAttentionInfoTwoListBean() {
        return personAttentionInfoTwoListBean;
    }

    public void setPersonAttentionInfoTwoListBean(PersonAttentionInfoTwoListBean personAttentionInfoTwoListBean) {
        this.personAttentionInfoTwoListBean = personAttentionInfoTwoListBean;
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

}
