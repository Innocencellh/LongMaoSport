package com.live.longmao.bean;

/**
 * Created by Administrator on 2016/8/24.
 */
public class GiftData {
    private int ID;
    private int level;
    private int num;
    private int type;//1代表普通消息，2代表小礼物，3代表大礼物，4代表弹幕，5代表豆, 6代表开盘，7代表/封盘，8代表结算
    private int giftID;
    private int beanNum;
    private String giftName;
    private String giftImg;
    private String sendID;
    private String sendName;
    private String sendImg;
    private String message;
    private String guessId;
    private String userName;
    private String handImg;

    public String getHandImg() {
        return handImg;
    }

    public void setHandImg(String handImg) {
        this.handImg = handImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGuessId() {
        return guessId;
    }

    public void setGuessId(String guessId) {
        this.guessId = guessId;
    }

    public int getBeanNum() {
        return beanNum;
    }

    public void setBeanNum(int beanNum) {
        this.beanNum = beanNum;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGiftID() {
        return giftID;
    }

    public void setGiftID(int giftID) {
        this.giftID = giftID;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftImg() {
        return giftImg;
    }

    public void setGiftImg(String giftImg) {
        this.giftImg = giftImg;
    }

    public String getSendID() {
        return sendID;
    }

    public void setSendID(String sendID) {
        this.sendID = sendID;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendImg() {
        return sendImg;
    }

    public void setSendImg(String sendImg) {
        this.sendImg = sendImg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
