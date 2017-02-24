package com.live.longmao.model;

/**
 * Created by HPDN on 2016/12/1.
 */
public class GradeInfoBean {
    private int currentExp;
    private String userId;
    private GradeCInfo userLevelDTO;
    private String userName;

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GradeCInfo getUserLevelDTO() {
        return userLevelDTO;
    }

    public void setUserLevelDTO(GradeCInfo userLevelDTO) {
        this.userLevelDTO = userLevelDTO;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
