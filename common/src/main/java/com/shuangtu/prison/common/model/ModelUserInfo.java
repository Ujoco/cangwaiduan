package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelUserInfo {


    /**
     * pris : ["user_m","org_m","role_m","*all_pri","userRole_m","pri_m"]
     * roles : null
     * id : 1
     * account : weng
     * userName : wengzhonghui
     * phone : 18898555445
     * email : 1@163.com
     * sex : 1
     * avatar : 1
     * userType : 3
     * createTime : null
     * updateTime : 2019-10-19T09:00:58.000+0000
     */

    private Object roles;
    private String id;
    private String account;
    private String userName;
    private String phone;
    private String email;
    private int sex;
    private String avatar;
    private int userType;
    private Object createTime;
    private String updateTime;
    private List<String> pris;

    public Object getRoles() {
        return roles;
    }

    public void setRoles(Object roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getPris() {
        return pris;
    }

    public void setPris(List<String> pris) {
        this.pris = pris;
    }
}
