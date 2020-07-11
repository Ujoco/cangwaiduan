package com.shuangtu.prison.discern;

import android.view.View;

import com.blankj.utilcode.util.ScreenUtils;
import com.shuangtu.prison.common.q.QActivity;
import com.shuangtu.prison.discern.dialog.STLoginMainDialog;

public class DiscernMainActivity extends QActivity {



    public void OnClickLoginMain(View view){
//        STLogoutDialog dialogLogout = new STLogoutDialog();
//        dialogLogout.show(getSupportFragmentManager(),"loginLogout");

//        STLoginFaceDialog dialogFingerprint = new STLoginFaceDialog();
//        dialogFingerprint.show(getSupportFragmentManager(),"loginFace");


//        STLoginFingerprintDialog dialogFingerprint = new STLoginFingerprintDialog();
//        dialogFingerprint.show(getSupportFragmentManager(),"loginFingerprint");

        STLoginMainDialog dialog = new STLoginMainDialog();
        dialog.show(getSupportFragmentManager(), "loginMain");
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_discern_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }
}
