package com.shuangtu.prison.common.model;

public class ModelMeetingDetails {


    /**
     * id : 1210584210457542658
     * appointmentNum : 预约号33
     * name : 通知名称33
     * noticeType : 2
     * detail :
     * userName : 服刑人员33
     * prisonAreaId : 1208756132706062337
     * prisonAreaName : null
     * toMeetUsername : 会见人员3
     * meetTime : 2019-12-27T15:32:05.000+0000
     * meetArea : 会见区33
     * windowName : 会见窗口会见窗口33
     * createTime : 2019-12-27T15:32:22.000+0000
     * updateTime : 2019-12-27T15:32:22.000+0000
     */

    private String id;
    private String appointmentNum;
    private String name;
    private int noticeType;
    private String detail;
    private String userName;
    private String prisonAreaId;
    private Object prisonAreaName;
    private String toMeetUsername;
    private String meetTime;
    private String meetArea;
    private String windowName;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentNum() {
        return appointmentNum;
    }

    public void setAppointmentNum(String appointmentNum) {
        this.appointmentNum = appointmentNum;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrisonAreaId() {
        return prisonAreaId;
    }

    public void setPrisonAreaId(String prisonAreaId) {
        this.prisonAreaId = prisonAreaId;
    }

    public Object getPrisonAreaName() {
        return prisonAreaName;
    }

    public void setPrisonAreaName(Object prisonAreaName) {
        this.prisonAreaName = prisonAreaName;
    }

    public String getToMeetUsername() {
        return toMeetUsername;
    }

    public void setToMeetUsername(String toMeetUsername) {
        this.toMeetUsername = toMeetUsername;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public String getMeetArea() {
        return meetArea;
    }

    public void setMeetArea(String meetArea) {
        this.meetArea = meetArea;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
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
