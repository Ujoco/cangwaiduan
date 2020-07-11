package com.shuangtu.prison.common.notice;

public class NoticeLogin {

    private int type;

    private boolean isLogin;

    private String name;

    private String cover;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public NoticeLogin(int type, boolean isLogin) {
        this.type = type;
        this.isLogin = isLogin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    @Override
    public String toString() {
        return "NoticeLogin{" +
                "type=" + type +
                ", isLogin=" + isLogin +
                '}';
    }
}
