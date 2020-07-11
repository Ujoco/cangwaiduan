package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelAreaBed {


    /**
     * current : 1
     * size : 10
     * curPageSize : 2
     * pages : 1
     * total : 2
     * records : [{"id":"1213292108805181441","bedNum":"A01","userId":"1210932326528520194","roomId":"1213111235512705025","remark":null,"createTime":"2020-01-04T02:52:35.000+0000","updateTime":"2020-01-04T02:53:30.000+0000","userName":"ww2"},{"id":"1213292149221494785","bedNum":"A02","userId":"1213298035449102337","roomId":"1213111235512705025","remark":null,"createTime":"2020-01-04T02:52:45.000+0000","updateTime":"2020-01-04T03:56:57.000+0000","userName":"犯人5"}]
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
         * id : 1213292108805181441
         * bedNum : A01
         * userId : 1210932326528520194
         * roomId : 1213111235512705025
         * remark : null
         * createTime : 2020-01-04T02:52:35.000+0000
         * updateTime : 2020-01-04T02:53:30.000+0000
         * userName : ww2
         */

        private String id;
        private String bedNum;
        private String userId;
        private String roomId;
        private Object remark;
        private String createTime;
        private String updateTime;
        private String userName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBedNum() {
            return bedNum;
        }

        public void setBedNum(String bedNum) {
            this.bedNum = bedNum;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
