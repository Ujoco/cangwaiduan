package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelApplySearch {


    /**
     * current : 2
     * size : 10
     * curPageSize : 5
     * pages : 2
     * total : 15
     * records : [{"id":"1273988407732375554","userId":"1268195219558047745","userName":"201","appointType":"5","status":0,"appointUserId":"1237622378878111746","appointUserName":"何琦","appointPhone":"Ghhh","relationship":"律师","cause":"Jjjj","remark":null,"createTime":"2020-06-19T14:38:00.000+0000","updateTime":"2020-06-19T14:38:00.000+0000"},{"id":"1273988427063922689","userId":"1268195219558047745","userName":"201","appointType":"5","status":0,"appointUserId":"1237622378878111746","appointUserName":"何琦","appointPhone":"Ghhh","relationship":"律师","cause":"Jjjj","remark":null,"createTime":"2020-06-19T14:38:05.000+0000","updateTime":"2020-06-19T14:38:05.000+0000"},{"id":"1273988452657565697","userId":"1268195219558047745","userName":"201","appointType":"5","status":0,"appointUserId":"1237622378878111746","appointUserName":"何琦","appointPhone":"Ghhh","relationship":"律师","cause":"Jjjj","remark":null,"createTime":"2020-06-19T14:38:11.000+0000","updateTime":"2020-06-19T14:38:11.000+0000"},{"id":"1273989181933785089","userId":"1268195219558047745","userName":"201","appointType":"2","status":0,"appointUserId":"1237621166900416514","appointUserName":"孙辉","appointPhone":"Gggg","relationship":"教官","cause":"Ujj","remark":null,"createTime":"2020-06-19T14:41:05.000+0000","updateTime":"2020-06-19T14:41:05.000+0000"},{"id":"1273990530381549570","userId":"1268195219558047745","userName":"201","appointType":"4","status":0,"appointUserId":"1237623227406782465","appointUserName":"罗慧","appointPhone":"Uhh","relationship":"Uuj","cause":"Jji","remark":null,"createTime":"2020-06-19T14:46:26.000+0000","updateTime":"2020-06-19T14:46:26.000+0000"}]
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
         * id : 1273988407732375554
         * userId : 1268195219558047745
         * userName : 201
         * appointType : 5
         * status : 0
         * appointUserId : 1237622378878111746
         * appointUserName : 何琦
         * appointPhone : Ghhh
         * relationship : 律师
         * cause : Jjjj
         * remark : null
         * createTime : 2020-06-19T14:38:00.000+0000
         * updateTime : 2020-06-19T14:38:00.000+0000
         */

        private String id;
        private String userId;
        private String userName;
        private String appointType;
        private int status;
        private String appointUserId;
        private String appointUserName;
        private String appointPhone;
        private String relationship;
        private String cause;
        private Object remark;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAppointType() {
            return appointType;
        }

        public void setAppointType(String appointType) {
            this.appointType = appointType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAppointUserId() {
            return appointUserId;
        }

        public void setAppointUserId(String appointUserId) {
            this.appointUserId = appointUserId;
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
