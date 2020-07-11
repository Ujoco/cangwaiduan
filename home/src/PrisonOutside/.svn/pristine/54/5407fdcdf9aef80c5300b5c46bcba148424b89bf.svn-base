package com.shuangtu.prison.home.table;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import java.util.List;

@SmartTable(name = "确认收货")
public class TableGoodsConfirm {

    /**
     * name :
     * array : [{"time":"","shop":"","price":""}]
     */
    @SmartColumn(id =1,name = "姓名")
    private String name;
    @SmartColumn(type = ColumnType.ArrayChild, autoCount = true)
    private List<TableGoodsConfirm.ArrayBean> array;
    private String count;
    private String orderID;
    private int orderStauts;
    @SmartColumn(id =6,name = "收货确认")
    private int confirm;

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getOrderStauts() {
        return orderStauts;
    }

    public void setOrderStauts(int orderStauts) {
        this.orderStauts = orderStauts;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TableGoodsConfirm.ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<TableGoodsConfirm.ArrayBean> array) {
        this.array = array;
    }

    public static class ArrayBean {
        /**
         * time :
         * shop :
         * price :
         */
        @SmartColumn(id =2,name = "时间")
        private String time;
        @SmartColumn(id =3,name = "物品")
        private String shop;
        @SmartColumn(id = 4, name = "价格/元", autoCount = true)
        private double price;
        @SmartColumn(id =5,name = "商品状态")
        private String status;


        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }



        public String getShop() {
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        @Override
        public String toString() {
            return "ArrayBean{" +
                    "time='" + time + '\'' +
                    ", shop='" + shop + '\'' +
                    ", price=" + price +
                    ", status='" + status+
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TableGoodsConfirm{" +
                "name='" + name + '\'' +
                ", array=" + array +
                ", count='" + count + '\'' +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}
