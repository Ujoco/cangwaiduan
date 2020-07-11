package com.shuangtu.prison.home.table;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "预约查询")
public class TableHostitalRecord {

    @SmartColumn(id = 1, name = "预约号")
    private String id;
    @SmartColumn(id = 2, name = "姓名")
    private String name;
    @SmartColumn(id = 3, name = "时间")
    private String time;
    @SmartColumn(id = 4,name = "病因")
    private String illDetail;
    @SmartColumn(id = 5, name = "病情")
    private String diagnosiDetail;
    @SmartColumn(id = 6, name = "药品")
    private String drug;


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

    public String getIllDetail() {
        return illDetail;
    }

    public void setIllDetail(String illDetail) {
        this.illDetail = illDetail;
    }

    public String getDiagnosiDetail() {
        return diagnosiDetail;
    }

    public void setDiagnosiDetail(String diagnosiDetail) {
        this.diagnosiDetail = diagnosiDetail;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
