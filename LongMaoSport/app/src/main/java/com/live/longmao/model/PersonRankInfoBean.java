package com.live.longmao.model;

/**
 * Created by HPDN on 2016/12/23.
 */
public class PersonRankInfoBean {

    /**
     * cntNum : 45
     * sex : 0
     * sumClr : 1843
     * userId : 10224007
     * userName : 13354265289
     */

    private int cntNum;
    private int sex;
    private int sumClr;
    private String userId;
    private String userName;
    /**
     * photoUrl : 20161224/umjpprDtDBKDa5zmjVei.png
     */

    private String photoUrl;

    public int getCntNum() {
        return cntNum;
    }

    public void setCntNum(int cntNum) {
        this.cntNum = cntNum;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSumClr() {
        return sumClr;
    }

    public void setSumClr(int sumClr) {
        this.sumClr = sumClr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
