package com.shuangtu.prison.home.table;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;
@SmartTable(name = "警情通知")
public class TablePolicenotice {


    @SmartColumn(id = 1, name = "姓名")
    private String name;
    @SmartColumn(id = 2, name = "时间")
    private String time;
    @SmartColumn(id = 3, name = "床位")
    private String bed;
    @SmartColumn(id = 4, name = "警情")
    private String Police;




    public TablePolicenotice() {

    }

    public TablePolicenotice(String name, String time,String bed, String police) {
        this.name = name;
        this.time = time;
        this.Police = police;
        this.bed = bed;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPolice(String police) {
        Police = police;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getPolice() {
        return Police;
    }
}
