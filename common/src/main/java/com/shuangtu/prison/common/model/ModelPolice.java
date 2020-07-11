package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelPolice {


    /**
     * current : 1
     * size : 10
     * curPageSize : 2
     * pages : 1
     * total : 2
     * records : [{"id":"1272116613222817794","roomId":"1221298252775747586","userId":"1210791392218263554","dutyId":"1","dutyName":"巡视","remark":null,"createTime":"2020-06-14T10:40:10.000+0000","updateTime":"2020-06-14T11:04:17.000+0000","userName":"教官111"},{"id":"1272122806301417473","roomId":"1221298252775747586","userId":"1210791392218263554","dutyId":"2","dutyName":"管教","remark":null,"createTime":"2020-06-14T11:04:47.000+0000","updateTime":"2020-06-14T11:04:54.000+0000","userName":"教官111"}]
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
         * id : 1272116613222817794
         * roomId : 1221298252775747586
         * userId : 1210791392218263554
         * dutyId : 1
         * dutyName : 巡视
         * remark : null
         * createTime : 2020-06-14T10:40:10.000+0000
         * updateTime : 2020-06-14T11:04:17.000+0000
         * userName : 教官111
         */

        private String id;
        private String roomId;
        private String userId;
        private String dutyId;
        private String dutyName;
        private Object remark;
        private String createTime;
        private String updateTime;
        private String userName;
        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDutyId() {
            return dutyId;
        }

        public void setDutyId(String dutyId) {
            this.dutyId = dutyId;
        }

        public String getDutyName() {
            return dutyName;
        }

        public void setDutyName(String dutyName) {
            this.dutyName = dutyName;
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

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id='" + id + '\'' +
                    ", roomId='" + roomId + '\'' +
                    ", userId='" + userId + '\'' +
                    ", dutyId='" + dutyId + '\'' +
                    ", dutyName='" + dutyName + '\'' +
                    ", remark=" + remark +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }
}
