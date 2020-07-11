package com.shuangtu.prison.common.model;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class ModelShop extends LitePalSupport {

    private String shopId;

    private String name;

    private Double price;

    private Integer num;

    private Double countPrice;

    private String imageUrl;

    public ModelShop() {
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        countPrice = price * num;
        return num;
    }

    public void setNum(Integer num) {
        countPrice = price * num;
        this.num = num;
    }

    public Double getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(Double countPrice) {
        this.countPrice = countPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ModelShop{" +
                "shopId='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", countPrice=" + countPrice +
                '}';
    }
}
