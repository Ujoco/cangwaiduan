package com.shuangtu.prison.common.q;


public interface QUIOperation {

    /**
     * 返回activity的布局文件
     */
    int getLayoutRes();

    /**
     * 查找子控件
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 设置监听器
     */
    void initListener();

    void networkMessage();

    boolean registerEventBus();

}

