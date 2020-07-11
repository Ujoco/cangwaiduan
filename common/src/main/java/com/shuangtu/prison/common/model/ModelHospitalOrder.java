package com.shuangtu.prison.common.model;

public class ModelHospitalOrder {

    /**
     * id : 1223609962962743297
     * userId : 1
     * dockerId : null
     * illDetail : 胃痛，腹泻，描述病状描述
     * createTime : 2020-02-01T14:12:03.409+0000
     * updateTime : 2020-02-01T14:12:03.409+0000
     */

    private String id;
    private String userId;
    private Object dockerId;
    private String illDetail;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getDockerId() {
        return dockerId;
    }

    public void setDockerId(Object dockerId) {
        this.dockerId = dockerId;
    }

    public String getIllDetail() {
        return illDetail;
    }

    public void setIllDetail(String illDetail) {
        this.illDetail = illDetail;
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
