package com.shuangtu.prison.common.model;

public class ModelMealGenerate {

    /**
     * id : 1213784465558798338
     * remark : null
     * userId : 1
     * userName : 翁
     * realPrice : 6.0
     * totalPrice : 6.0
     * couponAmount : 0.0
     * status : 0
     * isMeal : 1
     * payType : 0
     * createTime : 2020-01-05T11:29:02.278+0000
     * updateTime : 2020-01-05T11:29:02.278+0000
     */

    private String id;
    private Object remark;
    private String userId;
    private String userName;
    private double realPrice;
    private double totalPrice;
    private double couponAmount;
    private int status;
    private int isMeal;
    private int payType;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsMeal() {
        return isMeal;
    }

    public void setIsMeal(int isMeal) {
        this.isMeal = isMeal;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
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
