package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelHospitalRecord {


    /**
     * current : 1
     * size : 10
     * curPageSize : 3
     * pages : 1
     * total : 3
     * records : [{"id":"1224028805468483585","appointmentId":"333","userId":"1237628346298961921","dockerId":null,"illDetail":"感冒","diagnosiDetail":"出现咽干、咽痒、打喷嚏、鼻塞咳嗽、流眼泪、头痛等","drug":"解热镇痛药","illStatus":2,"createTime":"2020-02-02T17:56:23.000+0000","updateTime":"2020-06-10T07:09:54.000+0000","illUserName":"吴敏","dockerName":null},{"id":"1270231145012473857","appointmentId":"1270230486821318657","userId":"1268458667382611969","dockerId":null,"illDetail":"消化不良","diagnosiDetail":"李医生","drug":"消化药品","illStatus":2,"createTime":"2020-06-09T05:47:59.000+0000","updateTime":"2020-06-10T07:09:43.000+0000","illUserName":"李立刚","dockerName":null},{"id":"1223884064412102657","appointmentId":"111","userId":"1237623903016882177","dockerId":null,"illDetail":"最常见的有上腹部不适或疼痛、恶心、呕吐、腹泻、食欲不振。胃炎及十二指肠溃疡的症状则为上腹部烧灼痛，特别是在两顿饭之间，早餐前或在饮用橙汁、咖啡之后发生。严重者可有柏油便、黑便或血便。","diagnosiDetail":"胃病","drug":"药物治疗","illStatus":1,"createTime":"2020-02-02T08:21:14.000+0000","updateTime":"2020-03-30T09:28:11.000+0000","illUserName":"赵婷","dockerName":null}]
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
         * id : 1224028805468483585
         * appointmentId : 333
         * userId : 1237628346298961921
         * dockerId : null
         * illDetail : 感冒
         * diagnosiDetail : 出现咽干、咽痒、打喷嚏、鼻塞咳嗽、流眼泪、头痛等
         * drug : 解热镇痛药
         * illStatus : 2
         * createTime : 2020-02-02T17:56:23.000+0000
         * updateTime : 2020-06-10T07:09:54.000+0000
         * illUserName : 吴敏
         * dockerName : null
         */

        private String id;
        private String appointmentId;
        private String userId;
        private Object dockerId;
        private String illDetail;
        private String diagnosiDetail;
        private String drug;
        private int illStatus;
        private String createTime;
        private String updateTime;
        private String illUserName;
        private String dockerName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(String appointmentId) {
            this.appointmentId = appointmentId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getDockerId() {
            return dockerId;
        }

        public void setDockerId(Object dockerId) {
            this.dockerId = dockerId;
        }

        public String getIllDetail() {
            return illDetail;
        }

        public void setIllDetail(String illDetail) {
            this.illDetail = illDetail;
        }

        public String getDiagnosiDetail() {
            return diagnosiDetail;
        }

        public void setDiagnosiDetail(String diagnosiDetail) {
            this.diagnosiDetail = diagnosiDetail;
        }

        public String getDrug() {
            return drug;
        }

        public void setDrug(String drug) {
            this.drug = drug;
        }

        public int getIllStatus() {
            return illStatus;
        }

        public void setIllStatus(int illStatus) {
            this.illStatus = illStatus;
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

        public String getIllUserName() {
            return illUserName;
        }

        public void setIllUserName(String illUserName) {
            this.illUserName = illUserName;
        }

        public String getDockerName() {
            return dockerName;
        }

        public void setDockerName(String dockerName) {
            this.dockerName = dockerName;
        }
    }
}
