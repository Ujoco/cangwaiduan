package com.shuangtu.prison.home.table;


import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "值班表")
public class TableAreaDuty {

    @SmartColumn(id = 1, name = "时间")
    private String time;
    @SmartColumn(id = 2, name = "值班人员")
    private String personnel;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }
}
