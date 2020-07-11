package com.shuangtu.prison.common.model;

public class ModelMakeOrder {


    /**
     * id : 1206758895546953729
     * remark : null
     * userId : 1
     * userName : 翁
     * realPrice : 60.0
     * totalPrice : 60.0
     * couponAmount : 0.0
     * createTime : 2019-12-17T02:11:55.851+0000
     * updateTime : 2019-12-17T02:11:55.851+0000
     */

    private String id;
    private Object remark;
    private String userId;
    private String userName;
    private double realPrice;
    private double totalPrice;
    private double couponAmount;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(double couponAmount) {
        this.couponAmount = couponAmount;
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
