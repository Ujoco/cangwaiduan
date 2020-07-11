package com.shuangtu.prison.common.notice;

public class NoticeLogout {

    private boolean isSuccess;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public NoticeLogout(boolean isSuccess, int type) {
        this.isSuccess = isSuccess;
        this.type = type;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return "NoticeLogout{" +
                "isSuccess=" + isSuccess +
                ", type=" + type +
                '}';
    }
}
