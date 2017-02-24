package com.live.longmao.model;

/**
 * Created by HPDN on 2016/12/7.
 */
public class GetGuessingInfoBean {
    /**
     * anchorUserId : 10178070
     * answa : 范
     * answb : 发送
     * beanNum : 1000
     * countBeanNum : 0
     * gmtCreate : 1481036751000
     * guessId : 230013
     * guessTitle : 反反复复
     * status : 2
     * surplusTime : 0
     */

    private String anchorUserId;
    private String answa;
    private String answb;
    private int beanNum;
    private int countBeanNum;
    private long gmtCreate;
    private String guessId;
    private String guessTitle;
    private int status;
    private int surplusTime;

    public String getAnchorUserId() {
        return anchorUserId;
    }

    public void setAnchorUserId(String anchorUserId) {
        this.anchorUserId = anchorUserId;
    }

    public String getAnswa() {
        return answa;
    }

    public void setAnswa(String answa) {
        this.answa = answa;
    }

    public String getAnswb() {
        return answb;
    }

    public void setAnswb(String answb) {
        this.answb = answb;
    }

    public int getBeanNum() {
        return beanNum;
    }

    public void setBeanNum(int beanNum) {
        this.beanNum = beanNum;
    }

    public int getCountBeanNum() {
        return countBeanNum;
    }

    public void setCountBeanNum(int countBeanNum) {
        this.countBeanNum = countBeanNum;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGuessId() {
        return guessId;
    }

    public void setGuessId(String guessId) {
        this.guessId = guessId;
    }

    public String getGuessTitle() {
        return guessTitle;
    }

    public void setGuessTitle(String guessTitle) {
        this.guessTitle = guessTitle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(int surplusTime) {
        this.surplusTime = surplusTime;
    }

}
