package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelGoodsShop {


    /**
     * current : 1
     * size : 10
     * curPageSize : 1
     * pages : 1
     * total : 1
     * records : [{"id":"1205696535359512577","productCategoryId":"1203685330373636097","categoryName":"日用品","name":"广式月饼","subTitle":"广式月饼广式月饼广式月饼广式月饼","description":"","price":50,"promotionPrice":30,"pic":"http://114.67.97.167:7480/mybucket1/5faf9775-b325-41ce-8dde-550a7722971b.jpg","stock":20,"unit":"个","newStatus":0,"recommandStatus":0,"createTime":"2019-12-14T03:50:29.000+0000","updateTime":"2019-12-14T09:37:28.000+0000"}]
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
         * id : 1205696535359512577
         * productCategoryId : 1203685330373636097
         * categoryName : 日用品
         * name : 广式月饼
         * subTitle : 广式月饼广式月饼广式月饼广式月饼
         * description :
         * price : 50.0
         * promotionPrice : 30.0
         * pic : http://114.67.97.167:7480/mybucket1/5faf9775-b325-41ce-8dde-550a7722971b.jpg
         * stock : 20
         * unit : 个
         * newStatus : 0
         * recommandStatus : 0
         * createTime : 2019-12-14T03:50:29.000+0000
         * updateTime : 2019-12-14T09:37:28.000+0000
         */

        private String id;
        private String productCategoryId;
        private String categoryName;
        private String name;
        private String subTitle;
        private String description;
        private double price;
        private double promotionPrice;
        private String pic;
        private int stock;
        private String unit;
        private int newStatus;
        private int recommandStatus;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(String productCategoryId) {
            this.productCategoryId = productCategoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPromotionPrice() {
            return promotionPrice;
        }

        public void setPromotionPrice(double promotionPrice) {
            this.promotionPrice = promotionPrice;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getNewStatus() {
            return newStatus;
        }

        public void setNewStatus(int newStatus) {
            this.newStatus = newStatus;
        }

        public int getRecommandStatus() {
            return recommandStatus;
        }

        public void setRecommandStatus(int recommandStatus) {
            this.recommandStatus = recommandStatus;
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
