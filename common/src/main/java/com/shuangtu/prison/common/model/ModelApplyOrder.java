package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelApplyOrder {

    /**
     * current : 1
     * size : 10
     * curPageSize : 2
     * pages : 1
     * total : 2
     * records : [{"id":"1210791392218263554","account":"jiaoguan11","orgId":null,"userName":"教官111","phone":null,"email":null,"sex":1,"avatar":"http://114.67.97.167:7480/mybucket1/7897e991-f6bd-4162-ba97-f5fb251e2f65.jpg","userType":2,"createTime":"2019-12-28T05:15:38.000+0000","updateTime":"2019-12-28T05:15:38.000+0000"},{"id":"1210932326528520194","account":"ww2","orgId":null,"userName":"ww2","phone":null,"email":null,"sex":1,"avatar":"http://114.67.97.167:7480/mybucket1/7897e991-f6bd-4162-ba97-f5fb251e2f65.jpg","userType":2,"createTime":"2019-12-28T14:35:39.000+0000","updateTime":"2019-12-28T14:35:39.000+0000"}]
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
         * id : 1210791392218263554
         * account : jiaoguan11
         * orgId : null
         * userName : 教官111
         * phone : null
         * email : null
         * sex : 1
         * avatar : http://114.67.97.167:7480/mybucket1/7897e991-f6bd-4162-ba97-f5fb251e2f65.jpg
         * userType : 2
         * createTime : 2019-12-28T05:15:38.000+0000
         * updateTime : 2019-12-28T05:15:38.000+0000
         */

        private String id;
        private String account;
        private Object orgId;
        private String userName;
        private Object phone;
        private Object email;
        private int sex;
        private String avatar;
        private int userType;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
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
