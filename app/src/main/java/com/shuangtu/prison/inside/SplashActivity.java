package com.shuangtu.prison.inside;

import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.q.QActivity;

/*
 * 闪屏页
 */

public class SplashActivity extends QActivity {


    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Global.startActivity(NetworkActivity.class);
        finish();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }
}
