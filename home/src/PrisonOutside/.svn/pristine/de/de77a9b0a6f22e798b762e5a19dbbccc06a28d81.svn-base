package com.shuangtu.prison.home.table;

import android.graphics.Paint;
import android.text.TextUtils;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import java.util.List;

@SmartTable(name = "订单记录")
public class TableGoodsOreder {


    /**
     * name :
     * array : [{"time":"","shop":"","price":""}]
     */
    @SmartColumn(id = 1, name = "姓名")
    private String name;
    @SmartColumn(type = ColumnType.ArrayChild, autoCount = true)
    private List<ArrayBean> array;
    @SmartColumn(id = 5, name = "总价")
    private String count;
    private String orderID;

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    public static class ArrayBean {
        /**
         * time :
         * shop :
         * price :
         */
        @SmartColumn(id = 2, name = "时间")
        private String time;
        @SmartColumn(id = 3, name = "物品")
        private String shop = "";
        @SmartColumn(id = 4, name = "价格/元", autoCount = true)
        private double price;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getShop() {
            if (TextUtils.isEmpty(shop)) {
                return "";
            }
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "ArrayBean{" +
                    "time='" + time + '\'' +
                    ", shop='" + shop + '\'' +
                    ", price=" + price +
                    '}';
        }


    }

    @Override
    public String toString() {
        return "TableGoodsOreder{" +
                "name='" + name + '\'' +
                ", array=" + array +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}
