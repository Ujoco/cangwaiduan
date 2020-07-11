package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelMealOrderList {

    /**
     * current : 1
     * size : 10
     * curPageSize : 2
     * pages : 1
     * total : 2
     * records : [{"id":"1213784465802067969","orderId":"1213784465558798338","productId":"1213484368224600066","productPrice":2,"productNum":3,"status":0,"isMeal":1,"mealSupplyDate":"2020-01-05T00:00:00.000+0000","createTime":"2020-01-05T11:29:02.000+0000","updateTime":"2020-01-05T11:29:02.000+0000","productName":"月饼233","productPic":"http://114.67.97.167:7480/mybucket1/83dfdfdd-abf7-488a-b6ce-9efaef93bcce.jpg"},{"id":"1213785513493069826","orderId":"1213785513233022977","productId":"1213484368224600066","productPrice":2,"productNum":3,"status":0,"isMeal":1,"mealSupplyDate":"2020-01-05T00:00:00.000+0000","createTime":"2020-01-05T11:33:12.000+0000","updateTime":"2020-01-05T11:33:12.000+0000","productName":"月饼233","productPic":"http://114.67.97.167:7480/mybucket1/83dfdfdd-abf7-488a-b6ce-9efaef93bcce.jpg"}]
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
         * id : 1213784465802067969
         * orderId : 1213784465558798338
         * productId : 1213484368224600066
         * productPrice : 2.0
         * productNum : 3
         * status : 0
         * isMeal : 1
         * mealSupplyDate : 2020-01-05T00:00:00.000+0000
         * createTime : 2020-01-05T11:29:02.000+0000
         * updateTime : 2020-01-05T11:29:02.000+0000
         * productName : 月饼233
         * productPic : http://114.67.97.167:7480/mybucket1/83dfdfdd-abf7-488a-b6ce-9efaef93bcce.jpg
         */

        private String id;
        private String orderId;
        private String productId;
        private double productPrice;
        private int productNum;
        private int status;
        private int isMeal;
        private String mealSupplyDate;
        private String createTime;
        private String updateTime;
        private String productName;
        private String productPic;

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

        public String getMealSupplyDate() {
            return mealSupplyDate;
        }

        public void setMealSupplyDate(String mealSupplyDate) {
            this.mealSupplyDate = mealSupplyDate;
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

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductPic() {
            return productPic;
        }

        public void setProductPic(String productPic) {
            this.productPic = productPic;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id='" + id + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", productId='" + productId + '\'' +
                    ", productPrice=" + productPrice +
                    ", productNum=" + productNum +
                    ", status=" + status +
                    ", isMeal=" + isMeal +
                    ", mealSupplyDate='" + mealSupplyDate + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", productName='" + productName + '\'' +
                    ", productPic='" + productPic + '\'' +
                    '}';
        }
    }
}
