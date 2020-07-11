package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelMeeting {


    /**
     * current : 1
     * size : 10
     * curPageSize : 6
     * pages : 1
     * total : 6
     * records : [{"id":"1210585377178697730","appointmentNum":"预约号11","name":"监察院会见通知","noticeType":4,"detail":"<p>234324<\/p>","userName":"服刑人员","prisonAreaId":"1208756054872363010","prisonAreaName":"一监区","toMeetUsername":"会见人员1","meetTime":"2019-12-27T15:36:42.000+0000","meetArea":"会见区","windowName":"会见窗口1","createTime":"2019-12-27T15:37:00.000+0000","updateTime":"2019-12-27T15:37:00.000+0000"},{"id":"1210039736673296385","appointmentNum":"11111111","name":"通知名称11","detail":"<p>safdasfdaf<\/p>","userName":"服刑人员324324","prisonAreaId":"1208756132706062337","prisonAreaName":"三监区","toMeetUsername":"会见人员22","meetTime":"2019-12-11T16:04:00.000+0000","meetArea":"会见区11111","windowName":"会见窗口会见窗口234","createTime":"2019-12-26T03:28:49.000+0000","updateTime":"2019-12-27T15:19:09.000+0000"},{"id":"1210036997406908418","appointmentNum":null,"name":"王三 提审通知","detail":"<p>王三 提审通知王三 提审通知王三 提审通知123213<\/p>","userName":"王三","prisonAreaId":null,"prisonAreaName":null,"toMeetUsername":null,"meetTime":null,"meetArea":null,"windowName":null,"createTime":"2019-12-26T03:17:56.000+0000","updateTime":"2019-12-26T03:22:53.000+0000"}]
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
         * id : 1210585377178697730
         * appointmentNum : 预约号11
         * name : 监察院会见通知
         * noticeType : 4
         * detail : <p>234324</p>
         * userName : 服刑人员
         * prisonAreaId : 1208756054872363010
         * prisonAreaName : 一监区
         * toMeetUsername : 会见人员1
         * meetTime : 2019-12-27T15:36:42.000+0000
         * meetArea : 会见区
         * windowName : 会见窗口1
         * createTime : 2019-12-27T15:37:00.000+0000
         * updateTime : 2019-12-27T15:37:00.000+0000
         */

        private String id;
        private String appointmentNum;
        private String name;
        private int noticeType;
        private String detail;
        private String userName;
        private String prisonAreaId;
        private String prisonAreaName;
        private String toMeetUsername;
        private String meetTime;
        private String meetArea;
        private String windowName;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppointmentNum() {
            return appointmentNum;
        }

        public void setAppointmentNum(String appointmentNum) {
            this.appointmentNum = appointmentNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNoticeType() {
            return noticeType;
        }

        public void setNoticeType(int noticeType) {
            this.noticeType = noticeType;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPrisonAreaId() {
            return prisonAreaId;
        }

        public void setPrisonAreaId(String prisonAreaId) {
            this.prisonAreaId = prisonAreaId;
        }

        public String getPrisonAreaName() {
            return prisonAreaName;
        }

        public void setPrisonAreaName(String prisonAreaName) {
            this.prisonAreaName = prisonAreaName;
        }

        public String getToMeetUsername() {
            return toMeetUsername;
        }

        public void setToMeetUsername(String toMeetUsername) {
            this.toMeetUsername = toMeetUsername;
        }

        public String getMeetTime() {
            return meetTime;
        }

        public void setMeetTime(String meetTime) {
            this.meetTime = meetTime;
        }

        public String getMeetArea() {
            return meetArea;
        }

        public void setMeetArea(String meetArea) {
            this.meetArea = meetArea;
        }

        public String getWindowName() {
            return windowName;
        }

        public void setWindowName(String windowName) {
            this.windowName = windowName;
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
