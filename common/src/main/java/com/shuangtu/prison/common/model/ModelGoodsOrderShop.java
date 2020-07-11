package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelGoodsOrderShop {


    /**
     * current : 1
     * size : 10
     * curPageSize : 1
     * pages : 1
     * total : 1
     * records : [{"id":"1206758895807000578","orderId":"1206758895546953729","productId":"1205696535359512577","productName":"广式月饼","productPrice":30,"productNum":2,"status":"1","createTime":"2019-12-17T02:11:56.000+0000","updateTime":"2019-12-17T02:11:56.000+0000"}]
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
         * id : 1206758895807000578
         * orderId : 1206758895546953729
         * productId : 1205696535359512577
         * productName : 广式月饼
         * productPrice : 30.0
         * productNum : 2
         * status : 1
         * createTime : 2019-12-17T02:11:56.000+0000
         * updateTime : 2019-12-17T02:11:56.000+0000
         */

        private String id;
        private String orderId;
        private String productId;
        private String productName;
        private double productPrice;
        private int productNum;
        private String status;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public int getProductNum() {
            return productNum;
        }

        public void setProductNum(int productNum) {
            this.productNum = productNum;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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
