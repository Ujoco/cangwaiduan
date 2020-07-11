package com.shuangtu.prison.common.model;

public class ModelCaseNoticeDetails {

    /**
     * id : 1210036997406908418
     * name : 王三 提审通知
     * noticeType : 1
     * userId : null
     * userName : 王三
     * trialTime : 2019-12-26T18:02:02.000+0000
     * detail : <p>王三 提审通知王三 提审通知王三 提审通知123213</p>
     * createTime : 2019-12-26T03:17:56.000+0000
     * updateTime : 2019-12-26T03:22:53.000+0000
     */

    private String id;
    private String name;
    private int noticeType;
    private Object userId;
    private String userName;
    private String trialTime;
    private String detail;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrialTime() {
        return trialTime;
    }

    public void setTrialTime(String trialTime) {
        this.trialTime = trialTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
