package com.shuangtu.prison.home.manager;

import android.app.Activity;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.example.tts.TTSHandler;
import com.shuangtu.prison.common.constant.QApplication;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.notice.NoticeLogout;
import com.shuangtu.prison.home.Constant;
import com.shuangtu.prison.home.activity.HomeActivity;
import com.shuangtu.prison.home.socket.QSocketConnectManager;

import org.greenrobot.eventbus.EventBus;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QOvertTimeExitManager {

    private static final String TAG = "QOvertTimeExitManager";

    private static volatile QOvertTimeExitManager instance = null;

    public static QOvertTimeExitManager getInstance() {
        if (instance == null) {
            synchronized (QOvertTimeExitManager.class) {
                if (instance == null) {
                    instance = new QOvertTimeExitManager();
                }
            }
        }
        return instance;
    }

    public long time = System.currentTimeMillis();

    private Disposable disposableCurrentTime;

    public QOvertTimeExitManager() {
        Observable observable = Observable.interval(0, 1, TimeUnit.SECONDS) //0延迟  每隔1秒触发
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())//操作UI主要在UI线程
                .take(Integer.MAX_VALUE);

        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposableCurrentTime = d;
            }

            @Override
            public void onNext(Long o) {
                if (System.currentTimeMillis() > time + Constant.overtimeConfig) {
                    if (STUserManager.getInstance().isLogin()) {
                        LogUtils.d(TAG, "退出登陆！~" + Constant.overtimeConfig);
                        for (Activity activity : QApplication.activities) {
                            if (!(activity instanceof HomeActivity)) {
                                activity.finish();
                            }
                        }
                        EventBus.getDefault().post(new NoticeLogout(false, 1));
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
