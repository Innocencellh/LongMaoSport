package com.live.longmao.model;

/**
 * Created by HPDN on 2016/10/8.
 */
public class AttentionBean {
    /**
     * experience : 0
     * grade : 1
     * id : 11001
     * sex : 女
     */
    private int experience;
    private int grade;
    private String id;
    private String sex;
    private int is_follow;

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
