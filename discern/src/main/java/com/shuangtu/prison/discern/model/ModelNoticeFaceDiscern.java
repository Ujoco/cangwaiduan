package com.shuangtu.prison.discern.model;

public class ModelNoticeFaceDiscern {


    /**
     * success : 1
     * code : 0000
     * message : 操作成功
     * data : {"user_name":null,"uid":"1237624059531530241","rollTime":"2020-05-31 23:00:40"}
     */

    private int success;
    private String code;
    private String message;
    private DataBean data;


    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_name : null
         * uid : 1237624059531530241
         * rollTime : 2020-05-31 23:00:40
         */

        private Object user_name;
        private String uid;
        private String rollTime;
        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getUser_name() {
            return user_name;
        }

        public void setUser_name(Object user_name) {
            this.user_name = user_name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getRollTime() {
            return rollTime;
        }

        public void setRollTime(String rollTime) {
            this.rollTime = rollTime;
        }
    }
}
