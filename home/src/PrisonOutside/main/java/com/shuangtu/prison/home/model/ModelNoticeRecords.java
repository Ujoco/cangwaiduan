package com.shuangtu.prison.home.model;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.shuangtu.prison.common.model.ModelNoticeMessage;

public class ModelNoticeRecords extends SectionEntity<ModelNoticeMessage.RecordsBean> {

    private String headerTitle;
    private String num;
    private String dateStart;
    private String dateEnd;
    private boolean isSelect;

    public ModelNoticeRecords(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ModelNoticeRecords(ModelNoticeMessage.RecordsBean recordsBean) {
        super(recordsBean);
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
