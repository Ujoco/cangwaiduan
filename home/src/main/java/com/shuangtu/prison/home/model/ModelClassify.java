package com.shuangtu.prison.home.model;

public class ModelClassify {

    private String title;

    private int res;



    public ModelClassify(String title, int res) {
        this.title = title;
        this.res = res;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
