package com.shuangtu.prison.home.table;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "营养餐订单记录")
public class TableMealOrder {

    @SmartColumn(id = 1, name = "时间")
    private String time;
    @SmartColumn(id = 2, name = "食品")
    private String product;
    @SmartColumn(id = 3, name = "单价")
    private String pirce;
    @SmartColumn(id = 4, name = "数量")
    private String num;
    @SmartColumn(id = 5, name = "总价")
    private String totalPrice;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPirce() {
        return pirce;
    }

    public void setPirce(String pirce) {
        this.pirce = pirce;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
