package com.shuangtu.prison.common.model;

import java.util.List;

public class ModelNoticeMessage {


    /**
     * current : 1
     * size : 14
     * curPageSize : 1
     * pages : 1
     * total : 1
     * records : [{"id":"1203198594776002562","title":"信息播报内容","summary":null,"fileId":null,"fileUrl":null,"columnId":"1200624598388109314","sortNo":null,"visitCount":null,"orgId":null,"recommend":"0","status":null,"isDeleted":"0","publishUserId":null,"publishUserName":null,"publishDt":null,"createTime":null,"updateTime":null,"content":null,"columnName":"信息播报","columnCode":"broadcasting"}]
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
         * id : 1203198594776002562
         * title : 信息播报内容
         * summary : null
         * fileId : null
         * fileUrl : null
         * columnId : 1200624598388109314
         * sortNo : null
         * visitCount : null
         * orgId : null
         * recommend : 0
         * status : null
         * isDeleted : 0
         * publishUserId : null
         * publishUserName : null
         * publishDt : null
         * createTime : null
         * updateTime : null
         * content : null
         * columnName : 信息播报
         * columnCode : broadcasting
         */

        private String id;
        private String title;
        private Object summary;
        private Object fileId;

        private String columnId;
        private Object sortNo;
        private Object visitCount;
        private Object orgId;
        private String recommend;
        private Object status;
        private String isDeleted;
        private Object publishUserId;
        private Object publishUserName;
        private Object publishDt;
        private Object createTime;
        private Object updateTime;
        private Object content;
        private String columnName;
        private String columnCode;
        private String isPicNew;
        private String fileUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public Object getFileId() {
            return fileId;
        }

        public void setFileId(Object fileId) {
            this.fileId = fileId;
        }

        public String getFileUrl() {
            return fileUrl;
        }


        public String getColumnId() {
            return columnId;
        }

        public void setColumnId(String columnId) {
            this.columnId = columnId;
        }

        public Object getSortNo() {
            return sortNo;
        }

        public void setSortNo(Object sortNo) {
            this.sortNo = sortNo;
        }

        public Object getVisitCount() {
            return visitCount;
        }

        public void setVisitCount(Object visitCount) {
            this.visitCount = visitCount;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public Object getPublishUserId() {
            return publishUserId;
        }

        public void setPublishUserId(Object publishUserId) {
            this.publishUserId = publishUserId;
        }

        public Object getPublishUserName() {
            return publishUserName;
        }

        public void setPublishUserName(Object publishUserName) {
            this.publishUserName = publishUserName;
        }

        public Object getPublishDt() {
            return publishDt;
        }

        public void setPublishDt(Object publishDt) {
            this.publishDt = publishDt;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnCode() {
            return columnCode;
        }

        public void setColumnCode(String columnCode) {
            this.columnCode = columnCode;
        }

        public String getIsPicNew() {
            return isPicNew;
        }

        public void setIsPicNew(String isPicNew) {
            this.isPicNew = isPicNew;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", summary=" + summary +
                    ", fileId=" + fileId +
                    ", columnId='" + columnId + '\'' +
                    ", sortNo=" + sortNo +
                    ", visitCount=" + visitCount +
                    ", orgId=" + orgId +
                    ", recommend='" + recommend + '\'' +
                    ", status=" + status +
                    ", isDeleted='" + isDeleted + '\'' +
                    ", publishUserId=" + publishUserId +
                    ", publishUserName=" + publishUserName +
                    ", publishDt=" + publishDt +
                    ", createTime=" + createTime +
                    ", updateTime=" + updateTime +
                    ", content=" + content +
                    ", columnName='" + columnName + '\'' +
                    ", columnCode='" + columnCode + '\'' +
                    ", isPicNew='" + isPicNew + '\'' +
                    ", fileUrl='" + fileUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ModelNoticeMessage{" +
                "current=" + current +
                ", size=" + size +
                ", curPageSize=" + curPageSize +
                ", pages=" + pages +
                ", total=" + total +
                ", records=" + records +
                '}';
    }
}
