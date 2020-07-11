package com.shuangtu.prison.home.table;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;


@SmartTable(name = "食谱查询")
public class TableRecipesSearch {

    @SmartColumn(id = 1, name = "时间")
    private String time;
    @SmartColumn(id = 2, name = "早餐")
    private String breakfast;
    @SmartColumn(id = 3, name = "中餐")
    private String noon;
    @SmartColumn(id = 4, name = "晚餐")
    private String dinner;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
