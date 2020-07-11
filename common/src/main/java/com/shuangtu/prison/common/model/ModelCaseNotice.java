package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelCaseNotice {


    /**
     * current : 1
     * size : 10
     * curPageSize : 1
     * pages : 1
     * total : 1
     * records : [{"id":"1210036997406908418","name":"王三 提审通知","userName":"王三","trialTime":"2019-12-26T18:02:02.000+0000","detail":"<p>王三 提审通知王三 提审通知王三 提审通知123213<\/p>","createTime":"2019-12-26T03:17:56.000+0000","updateTime":"2019-12-26T03:22:53.000+0000"}]
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
         * id : 1210036997406908418
         * name : 王三 提审通知
         * userName : 王三
         * trialTime : 2019-12-26T18:02:02.000+0000
         * detail : <p>王三 提审通知王三 提审通知王三 提审通知123213</p>
         * createTime : 2019-12-26T03:17:56.000+0000
         * updateTime : 2019-12-26T03:22:53.000+0000
         */

        private String id;
        private String name;
        private String userName;
        private String trialTime;
        private String detail;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTrialTime() {
            return trialTime;
        }

        public void setTrialTime(String trialTime) {
            this.trialTime = trialTime;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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
