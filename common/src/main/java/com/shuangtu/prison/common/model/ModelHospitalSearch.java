package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelHospitalSearch {


    /**
     * current : 1
     * size : 10
     * curPageSize : 1
     * pages : 1
     * total : 1
     * records : [{"id":"1222357440566210562","appointmentId":"预约号预约号预约号预约号预约号","userId":"1213297637296406530","dockerId":null,"illDetail":"病状描述病状描述病状描述病状描述","diagnosiDetail":"医生诊断医生诊断医生诊断医生诊断","drug":"药品药品药品","illStatus":1,"createTime":"2020-01-29T03:14:59.000+0000","updateTime":"2020-02-01T15:00:55.000+0000","illUserName":"犯人22"}]
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
         * id : 1222357440566210562
         * appointmentId : 预约号预约号预约号预约号预约号
         * userId : 1213297637296406530
         * dockerId : null
         * illDetail : 病状描述病状描述病状描述病状描述
         * diagnosiDetail : 医生诊断医生诊断医生诊断医生诊断
         * drug : 药品药品药品
         * illStatus : 1
         * createTime : 2020-01-29T03:14:59.000+0000
         * updateTime : 2020-02-01T15:00:55.000+0000
         * illUserName : 犯人22
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
    }
}
