package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelGoodsOrder {


    /**
     * current : 1
     * size : 10
     * curPageSize : 4
     * pages : 1
     * total : 4
     * records : [{"id":"1206758895546953729","remark":null,"userId":"1","userName":"翁","realPrice":60,"totalPrice":60,"couponAmount":0,"status":0,"createTime":"2019-12-17T02:11:56.000+0000","updateTime":"2019-12-17T02:11:56.000+0000"},{"id":"1206763159937855489","remark":null,"userId":"1","userName":"翁","realPrice":60,"totalPrice":60,"couponAmount":0,"status":0,"createTime":"2019-12-17T02:28:53.000+0000","updateTime":"2019-12-17T02:28:53.000+0000"},{"id":"1206763183350460418","remark":null,"userId":"1","userName":"翁","realPrice":30,"totalPrice":30,"couponAmount":0,"status":1,"createTime":"2019-12-17T02:28:58.000+0000","updateTime":"2019-12-17T02:30:51.000+0000"}]
     */

    private int current;
    private int size;
    private int curPageSize;
    private int pages;
    private int total;
    private List<RecordsBean> records;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurPageSize() {
        return curPageSize;
    }

    public void setCurPageSize(int curPageSize) {
        this.curPageSize = curPageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 1206758895546953729
         * remark : null
         * userId : 1
         * userName : 翁
         * realPrice : 60.0
         * totalPrice : 60.0
         * couponAmount : 0.0
         * status : 0
         * createTime : 2019-12-17T02:11:56.000+0000
         * updateTime : 2019-12-17T02:11:56.000+0000
         */

        private String id;
        private String remark;
        private String userId;
        private String userName;
        private double realPrice;
        private double totalPrice;
        private double couponAmount;
        private int status;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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
}
