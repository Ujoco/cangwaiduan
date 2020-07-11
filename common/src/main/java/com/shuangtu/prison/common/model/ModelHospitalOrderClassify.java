package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelHospitalOrderClassify {


    /**
     * current : 1
     * size : 10
     * curPageSize : 3
     * pages : 1
     * total : 3
     * records : [{"id":"1222099035591069697","name":"常见病状","code":null,"remark":null,"createTime":"2020-01-28T10:08:10.000+0000","updateTime":"2020-01-28T10:08:10.000+0000"},{"id":"1222099073469829122","name":"外伤","code":null,"remark":null,"createTime":"2020-01-28T10:08:19.000+0000","updateTime":"2020-01-28T10:08:19.000+0000"},{"id":"1222099136451497985","name":"消化","code":null,"remark":null,"createTime":"2020-01-28T10:08:34.000+0000","updateTime":"2020-01-28T10:08:34.000+0000"}]
     */

    private int current;
    private int size;
    private int curPageSize;
    private int pages;
    private int total;
    private List<RecordsBean> records;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurPageSize() {
        return curPageSize;
    }

    public void setCurPageSize(int curPageSize) {
        this.curPageSize = curPageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 1222099035591069697
         * name : 常见病状
         * code : null
         * remark : null
         * createTime : 2020-01-28T10:08:10.000+0000
         * updateTime : 2020-01-28T10:08:10.000+0000
         */

        private String id;
        private String name;
        private Object code;
        private Object remark;
        private String createTime;
        private String updateTime;

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

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
