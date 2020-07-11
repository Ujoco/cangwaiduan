package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelAreaDuty {

    /**
     * current : 1
     * size : 10
     * curPageSize : 2
     * pages : 1
     * total : 2
     * records : [{"id":"1213307920190410753","roomId":"1213111235512705025","beginTime":"06:30","endTime":"07:00","dutyUser":"张三","remark":null,"createTime":"2020-01-04T03:55:25.000+0000","updateTime":"2020-01-04T03:55:25.000+0000"},{"id":"1213307979774693378","roomId":"1213111235512705025","beginTime":"07:00","endTime":"07:30","dutyUser":"李四，王五","remark":null,"createTime":"2020-01-04T03:55:39.000+0000","updateTime":"2020-01-04T03:55:39.000+0000"}]
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
         * id : 1213307920190410753
         * roomId : 1213111235512705025
         * beginTime : 06:30
         * endTime : 07:00
         * dutyUser : 张三
         * remark : null
         * createTime : 2020-01-04T03:55:25.000+0000
         * updateTime : 2020-01-04T03:55:25.000+0000
         */

        private String id;
        private String roomId;
        private String beginTime;
        private String endTime;
        private String dutyUser;
        private Object remark;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getDutyUser() {
            return dutyUser;
        }

        public void setDutyUser(String dutyUser) {
            this.dutyUser = dutyUser;
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
