package com.shuangtu.prison.inside;

import android.Manifest;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import com.shuangtu.prison.common.constant.Arouter;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.q.QActivity;
import com.shuangtu.prison.common.utils.SharedPreUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends QActivity {

    private EditText etUser;
    private EditText etPwd;


    /// 分控端
    private static final int STAppTypeHome = 1;
    /// 管理端
    private static final int STAppTypeAdmin = 2;

    public void jumpHome(View view) {

        String router = null;

        router = Arouter.ROUTER_HOME_MAIN;

        LogUtils.aTag(TAG, "jumpHome:" + router);

        if (TextUtils.isEmpty(router)) {
            ToastUtils.showLong("Router等于空！!");
            return;
        }

        if (TextUtils.isEmpty(etUser.getText().toString().trim())){
            ToastUtils.showLong("终端用户名不能等于空！!");
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString().trim())){
            ToastUtils.showLong("终端密码不能等于空！!");
            return;
        }


        SharedPreUtil.saveString(this, "intputUser",etUser.getText().toString().trim() );
        SharedPreUtil.saveString(this, "inputPwd",etPwd.getText().toString().trim() );
        ARouter.getInstance().build(router)
                .withString("user", etUser.getText().toString().trim())
                .withString("pwd", etPwd.getText().toString().trim())
                .navigation();
        finish();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        etUser = findViewById(R.id.etUser);
        etPwd = findViewById(R.id.etPwd);
    }

    final RxPermissions rxPermissions = new RxPermissions(this);

    @Override
    public void initData() {

        String user = SharedPreUtil.fishVuale("intputUser");
        String pwd = SharedPreUtil.fishVuale("inputPwd");
        etUser.setText(user);
        etPwd.setText(pwd);

        requestCamera();

    }

    private void requestCamera() {
        Disposable disposable = rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            requestStorage();
                        } else {
                            ToastUtils.showLong("获取人脸识别权限失败!");
                        }
                    }
                });
    }

    private void requestStorage() {
        Disposable disposable = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
//                            jumpHome(root);
//                        } else {
                        //    ToastUtils.showLong("获取存储权限失败");
                        }
                    }
                });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }
}
