package com.shuangtu.prison.home.model;

import com.shuangtu.prison.common.q.QFragment;

public class ModelMain {
    private int id;
    private String title;
    private String url;
    private Integer res;
    private QFragment fragment;

    public ModelMain() {
    }

    public ModelMain(int id, String title, String url, QFragment fragment) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.fragment = fragment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public QFragment getFragment() {
        return fragment;
    }

    public void setFragment(QFragment fragment) {
        this.fragment = fragment;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "ModelMain{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


}
