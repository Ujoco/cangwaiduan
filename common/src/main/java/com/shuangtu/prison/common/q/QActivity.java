package com.shuangtu.prison.common.q;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
;
import com.noober.background.BackgroundLibrary;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.constant.QApplication;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import io.reactivex.disposables.Disposable;
import me.jessyan.autosize.AutoSizeCompat;

public abstract class QActivity extends RxAppCompatActivity implements QUIOperation {

    protected View root;
    protected final String TAG = getClass().getSimpleName();

    DialogFragment dialogFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        LogUtils.dTag(TAG, "onCreate");
        Global.setNoTitle(this);
        Global.setFullScreen(getWindow());
        setContentView(getLayoutRes());
        root = findViewById(android.R.id.content);
        QApplication.activities.add(this);
        if (registerEventBus()) {
            EventBus.getDefault().register(this);
        }
        initView();
        initData();
        initListener();
        networkMessage();
    }

    @Override
    protected void onStart() {
        LogUtils.dTag(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        LogUtils.dTag(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        LogUtils.dTag(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtils.dTag(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtils.dTag(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtils.dTag(TAG, "onDestroy");

        super.onDestroy();

        if (registerEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        QApplication.activities.remove(this);

    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));
        return super.getResources();
    }


    /// 登陆选择弹窗
    public void show_logonMain() {
        show_dialogFragment("com.shuangtu.prison.discern.dialog.STLoginMainDialog", "loginMain");
    }

    private long time;

    /// 人脸弹窗
    public void show_loginFace() {
        show_dialogFragment("com.shuangtu.prison.discern.dialog.STLoginFaceDialog", "loginFace");
    }

    /// 指纹弹窗
    public void show_loginFingerprint() {

        show_dialogFragment("com.shuangtu.prison.discern.dialog.STLoginFingerprintDialog", "loginnFingerprint");
    }

    /// 退出弹窗
    public void show_logout() {
        show_dialogFragment("com.shuangtu.prison.discern.dialog.STLogoutDialog", "loginLogout");
    }

    private void show_dialogFragment(final String path,final String tag) {
        if (time + 800 > System.currentTimeMillis()) {
            return;
        }
        time = System.currentTimeMillis();
        if (dialogFragment != null && dialogFragment.isVisible()) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Class cls = null;
                try {
                    cls = Class.forName(path);
                    DialogFragment dialog = (DialogFragment) cls.newInstance();
                    dialog.show(getSupportFragmentManager(), tag);
                    dialogFragment = dialog;
                } catch (Exception e) {
                    LogUtils.aTag(TAG, "弹窗错误：" + e.toString());
                }
            }
        });
    }

    protected void dimssDialog() {
        if (dialogFragment != null && dialogFragment.isVisible()) {
            dialogFragment.dismiss();
        }
        dialogFragment = null;
    }

    public void stopDisposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
