package com.shuangtu.prison.home.socket.model;

import java.util.List;

public class ModelInfo {


    /**
     * type : prisonInfo
     * message : {"areaName":"区11","roomName":"室11","prisonDutyTables":[{"id":"1213307920190410753","roomId":"1213111235512705025","beginTime":"06:30","endTime":"07:00","dutyUser":"张三","remark":null,"createTime":"2020-01-04T03:55:25.000+0000","updateTime":"2020-01-04T03:55:25.000+0000"},{"id":"1213307979774693378","roomId":"1213111235512705025","beginTime":"07:00","endTime":"07:30","dutyUser":"李四，王五","remark":null,"createTime":"2020-01-04T03:55:39.000+0000","updateTime":"2020-01-04T03:55:39.000+0000"}],"prisonDutyRosters":[{"weekNum":"1","duty":{"扫地":"犯人4","打水":"犯人22"},"weekName":"星期一"},{"weekNum":"2","duty":{},"weekName":"星期二"},{"weekNum":"3","duty":{},"weekName":"星期三"},{"weekNum":"4","duty":{"打水":"犯人4"},"weekName":"星期四"},{"weekNum":"5","duty":{},"weekName":"星期五"},{"weekNum":"6","duty":{},"weekName":"星期六"},{"weekNum":"7","duty":{},"weekName":"星期日"}]}
     */

    private String type;
    private MessageBean message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * areaName : 区11
         * roomName : 室11
         * prisonDutyTables : [{"id":"1213307920190410753","roomId":"1213111235512705025","beginTime":"06:30","endTime":"07:00","dutyUser":"张三","remark":null,"createTime":"2020-01-04T03:55:25.000+0000","updateTime":"2020-01-04T03:55:25.000+0000"},{"id":"1213307979774693378","roomId":"1213111235512705025","beginTime":"07:00","endTime":"07:30","dutyUser":"李四，王五","remark":null,"createTime":"2020-01-04T03:55:39.000+0000","updateTime":"2020-01-04T03:55:39.000+0000"}]
         * prisonDutyRosters : [{"weekNum":"1","duty":{"扫地":"犯人4","打水":"犯人22"},"weekName":"星期一"},{"weekNum":"2","duty":{},"weekName":"星期二"},{"weekNum":"3","duty":{},"weekName":"星期三"},{"weekNum":"4","duty":{"打水":"犯人4"},"weekName":"星期四"},{"weekNum":"5","duty":{},"weekName":"星期五"},{"weekNum":"6","duty":{},"weekName":"星期六"},{"weekNum":"7","duty":{},"weekName":"星期日"}]
         */

        private String areaName;
        private String roomName;
        private List<PrisonDutyTablesBean> prisonDutyTables;
        private List<PrisonDutyRostersBean> prisonDutyRosters;

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public List<PrisonDutyTablesBean> getPrisonDutyTables() {
            return prisonDutyTables;
        }

        public void setPrisonDutyTables(List<PrisonDutyTablesBean> prisonDutyTables) {
            this.prisonDutyTables = prisonDutyTables;
        }

        public List<PrisonDutyRostersBean> getPrisonDutyRosters() {
            return prisonDutyRosters;
        }

        public void setPrisonDutyRosters(List<PrisonDutyRostersBean> prisonDutyRosters) {
            this.prisonDutyRosters = prisonDutyRosters;
        }

        public static class PrisonDutyTablesBean {
            /**
             * id : 1213307920190410753
             * roomId : 1213111235512705025
             * beginTime : 06:30
             * endTime : 07:00
             * dutyUser : 张三
             * remark : null
             * createTime : 2020-01-04T03:55:25.000+0000
             * updateTime : 2020-01-04T03:55:25.000+0000
             */

            private String id;
            private String roomId;
            private String beginTime;
            private String endTime;
            private String dutyUser;
            private Object remark;
            private String createTime;
            private String updateTime;

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

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getDutyUser() {
                return dutyUser;
            }

            public void setDutyUser(String dutyUser) {
                this.dutyUser = dutyUser;
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

        public static class PrisonDutyRostersBean {
            /**
             * weekNum : 1
             * duty : {"扫地":"犯人4","打水":"犯人22"}
             * weekName : 星期一
             */

            private String weekNum;
            private DutyBean duty;
            private String weekName;

            public String getWeekNum() {
                return weekNum;
            }

            public void setWeekNum(String weekNum) {
                this.weekNum = weekNum;
            }

            public DutyBean getDuty() {
                return duty;
            }

            public void setDuty(DutyBean duty) {
                this.duty = duty;
            }

            public String getWeekName() {
                return weekName;
            }

            public void setWeekName(String weekName) {
                this.weekName = weekName;
            }

            public static class DutyBean {
                /**
                 * 扫地 : 犯人4
                 * 打水 : 犯人22
                 */

                private String 扫地;
                private String 打水;

                public String get扫地() {
                    return 扫地;
                }

                public void set扫地(String 扫地) {
                    this.扫地 = 扫地;
                }

                public String get打水() {
                    return 打水;
                }

                public void set打水(String 打水) {
                    this.打水 = 打水;
                }
            }
        }
    }
}
