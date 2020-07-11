package com.shuangtu.prison.common.model;

import java.util.List;
import java.util.Objects;

public class ModelHospitalOrderList {

    /**
     * current : 1
     * size : 10
     * curPageSize : 4
     * pages : 1
     * total : 4
     * records : [{"id":"1222124196071755777","name":"胃胀","code":null,"categoryId":"1222099136451497985","remark":"胃胀胃胀胃胀胃胀","createTime":"2020-01-28T11:48:09.000+0000","updateTime":"2020-01-28T11:48:09.000+0000","categoryName":"消化"},{"id":"1222124128145002498","name":"伤口感染","code":null,"categoryId":"1222099073469829122","remark":null,"createTime":"2020-01-28T11:47:53.000+0000","updateTime":"2020-01-28T11:47:53.000+0000","categoryName":"外伤"},{"id":"1222124074348859394","name":"感冒","code":null,"categoryId":"1222099035591069697","remark":null,"createTime":"2020-01-28T11:47:40.000+0000","updateTime":"2020-01-28T11:47:40.000+0000","categoryName":"常见病状"},{"id":"1222123868823769090","name":"头疼","code":null,"categoryId":"1222099035591069697","remark":null,"createTime":"2020-01-28T11:46:51.000+0000","updateTime":"2020-01-28T11:46:51.000+0000","categoryName":"常见病状"}]
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
         * id : 1222124196071755777
         * name : 胃胀
         * code : null
         * categoryId : 1222099136451497985
         * remark : 胃胀胃胀胃胀胃胀
         * createTime : 2020-01-28T11:48:09.000+0000
         * updateTime : 2020-01-28T11:48:09.000+0000
         * categoryName : 消化
         */

        private String id;
        private String name;
        private Object code;
        private String categoryId;
        private String remark;
        private String createTime;
        private String updateTime;
        private String categoryName;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

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

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RecordsBean bean = (RecordsBean) o;
            return
                    Objects.equals(id, bean.id) &&
                    Objects.equals(name, bean.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, code, categoryId, remark, createTime, updateTime, categoryName, isSelect);
        }


    }
}
