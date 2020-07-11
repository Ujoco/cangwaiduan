package com.shuangtu.prison.home.table;


import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "收药确认")
public class TableHospConfirm {

    @SmartColumn(id = 1, name = "姓名")
    private String name;
    @SmartColumn(id = 2, name = "时间")
    private String time;
    @SmartColumn(id = 3, name = "药品")
    private String product;
    @SmartColumn(id = 4, name = "病情")
    private String details;
    @SmartColumn(id = 5,name = "收货确认")
    private int status;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
