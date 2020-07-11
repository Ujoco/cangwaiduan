package com.shuangtu.prison.common.model;

import java.util.HashMap;

public class ModelAreaRoster {

    /**
     * weekNum : 1
     * duty : {"扫地":"犯人4","打水":"犯人22","打饭":"犯人22","整理被褥":"犯人22","清洗卫生间":"犯人22"}
     * weekName : 星期一
     */

    private String weekNum;
    private HashMap<String, String> duty;
    private String weekName;

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public HashMap<String, String>  getDuty() {
        return duty;
    }

    public void setDuty(HashMap<String, String>  duty) {
        this.duty = duty;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }


}
