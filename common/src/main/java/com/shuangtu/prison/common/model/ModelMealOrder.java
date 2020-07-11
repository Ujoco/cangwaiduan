package com.shuangtu.prison.common.model;

import java.util.List;
import java.util.Objects;

public class ModelMealOrder {


    /**
     * current : 1
     * size : 10
     * curPageSize : 1
     * pages : 1
     * total : 1
     * records : [{"id":"1213492004483600386","weekNum":1,"weekName":"星期一","timeSlot":1,"timeSlotName":"上午","remark":null,"createTime":"2020-01-04T16:06:54.000+0000","updateTime":"2020-01-04T16:06:54.000+0000","productName":"月饼233","price":2,"promotionPrice":null,"productPic":"http://114.67.97.167:7480/mybucket1/83dfdfdd-abf7-488a-b6ce-9efaef93bcce.jpg"}]
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
         * id : 1213492004483600386
         * weekNum : 1
         * weekName : 星期一
         * timeSlot : 1
         * timeSlotName : 上午
         * remark : null
         * createTime : 2020-01-04T16:06:54.000+0000
         * updateTime : 2020-01-04T16:06:54.000+0000
         * productName : 月饼233
         * price : 2.0
         * promotionPrice : null
         * productPic : http://114.67.97.167:7480/mybucket1/83dfdfdd-abf7-488a-b6ce-9efaef93bcce.jpg
         */

        private String id;
        private int weekNum;
        private String weekName;
        private int timeSlot;
        private String timeSlotName;
        private Object remark;
        private String createTime;
        private String updateTime;
        private String productName;
        private double price;
        private Object promotionPrice;
        private String productPic;
        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getWeekNum() {
            return weekNum;
        }

        public void setWeekNum(int weekNum) {
            this.weekNum = weekNum;
        }

        public String getWeekName() {
            return weekName;
        }

        public void setWeekName(String weekName) {
            this.weekName = weekName;
        }

        public int getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(int timeSlot) {
            this.timeSlot = timeSlot;
        }

        public String getTimeSlotName() {
            return timeSlotName;
        }

        public void setTimeSlotName(String timeSlotName) {
            this.timeSlotName = timeSlotName;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Object getPromotionPrice() {
            return promotionPrice;
        }

        public void setPromotionPrice(Object promotionPrice) {
            this.promotionPrice = promotionPrice;
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
                    ", weekNum=" + weekNum +
                    ", weekName='" + weekName + '\'' +
                    ", timeSlot=" + timeSlot +
                    ", timeSlotName='" + timeSlotName + '\'' +
                    ", remark=" + remark +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", productName='" + productName + '\'' +
                    ", price=" + price +
                    ", promotionPrice=" + promotionPrice +
                    ", productPic='" + productPic + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RecordsBean bean = (RecordsBean) o;
            return weekNum == bean.weekNum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weekNum);
        }
    }

    @Override
    public String toString() {
        return "ModelMealOrder{" +
                "current=" + current +
                ", size=" + size +
                ", curPageSize=" + curPageSize +
                ", pages=" + pages +
                ", total=" + total +
                ", records=" + records +
                '}';
    }
}
