package com.live.longmao.model;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class CtoBInfo {
    private long gmtCreate;
    private long gmtModified;
    private String id;
    private int lmCoinNum;
    private String userId;

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLmCoinNum() {
        return lmCoinNum;
    }

    public void setLmCoinNum(int lmCoinNum) {
        this.lmCoinNum = lmCoinNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
