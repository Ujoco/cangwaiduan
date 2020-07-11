package com.shuangtu.prison.common.model;

public class ModelGoodsClassify {


    /**
     * value : 1203685330373636097
     * text : 日用品
     * selected : false
     */

    private String value;
    private String text;
    private boolean selected;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "ModelGoodsClassify{" +
                "value='" + value + '\'' +
                ", text='" + text + '\'' +
                ", selected=" + selected +
                '}';
    }
}
