package com.shuangtu.prison.home.table;



import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "预约查询")
public class TableApplySarch {


    @SmartColumn(id = 1, name = "姓名")
    private String name;
    @SmartColumn(id = 2, name = "时间")
    private String time;
    @SmartColumn(id = 3, name = "预约者姓名")
    private String appointUserName;
    @SmartColumn(id = 4,name = "电话")
    private String appointPhone;
    @SmartColumn(id = 5, name = "关系")
    private String relationship;
    @SmartColumn(id = 6,name = "事由")
    private String cause;
    @SmartColumn(id = 7,name = "状态")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAppointUserName() {
        return appointUserName;
    }

    public void setAppointUserName(String appointUserName) {
        this.appointUserName = appointUserName;
    }

    public String getAppointPhone() {
        return appointPhone;
    }

    public void setAppointPhone(String appointPhone) {
        this.appointPhone = appointPhone;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
