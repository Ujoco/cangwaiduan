package com.shuangtu.prison.common.model;

public class ModelDeviceInfo {

    /**
     * id : 1221298252775747586
     * name : 监室333
     * areaId : 1208756104079937538
     * address : 123213
     * createTime : 2020-01-26T05:06:09.000+0000
     * updateTime : 2020-01-26T05:06:09.000+0000
     * areaName : 二监区
     */

    private String id;
    private String name = "";
    private String areaId;
    private String address;
    private String createTime;
    private String updateTime;
    private String areaName = "";

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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
