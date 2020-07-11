package com.shuangtu.prison.common.model;

public class LoginModel {


    /**
     * id : 1
     * account : weng
     * trueName : 翁
     * expireTimeLong : 7200
     * freshTime : 1575689981591
     * token : c6b4c4f39ffa406296b3bb9c4555eedc
     * pris : null
     */

    private String id;
    private String account;
    private String trueName;
    private int expireTimeLong;
    private long freshTime;
    private String token;
    private Object pris;

    public LoginModel() {
    }

    public LoginModel(String id, String account, String trueName, int expireTimeLong, long freshTime, String token, Object pris) {
        this.id = id;
        this.account = account;
        this.trueName = trueName;
        this.expireTimeLong = expireTimeLong;
        this.freshTime = freshTime;
        this.token = token;
        this.pris = pris;
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

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public int getExpireTimeLong() {
        return expireTimeLong;
    }

    public void setExpireTimeLong(int expireTimeLong) {
        this.expireTimeLong = expireTimeLong;
    }

    public long getFreshTime() {
        return freshTime;
    }

    public void setFreshTime(long freshTime) {
        this.freshTime = freshTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getPris() {
        return pris;
    }

    public void setPris(Object pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", trueName='" + trueName + '\'' +
                ", expireTimeLong=" + expireTimeLong +
                ", freshTime=" + freshTime +
                ", token='" + token + '\'' +
                ", pris=" + pris +
                '}';
    }
}
