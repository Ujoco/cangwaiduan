package com.shuangtu.prison.home.activity;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.model.ModelDeviceInfo;
import com.shuangtu.prison.common.notice.NoticeLogout;
import com.shuangtu.prison.common.q.QActivity;
import com.shuangtu.prison.home.Constant;
import com.shuangtu.prison.home.manager.QOvertTimeExitManager;
import com.shuangtu.prison.home.model.ModelNoticeOverExit;
import com.shuangtu.prison.home.socket.QSocketConnectManager;
import com.shuangtu.prison.home.socket.model.ModelConnect;
import com.shuangtu.prison.home.socket.model.ModelHeartbeat;
import com.shuangtu.prison.home.socket.model.ModelOvertime;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class HomeEventBusActivity extends QActivity {

    private long timeIndex = 0;
    protected TextView tvCurrentTime;
    protected TextView tvRootName;
    protected TextView tvErrorConnect;
    private Disposable disposableCurrentTime, disposableExit;
    private long timeCurrent = System.currentTimeMillis();

    @Override
    protected void onRestart() {
        super.onRestart();
//        startTimer(System.currentTimeMillis());
//        startOvertExit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer(timeCurrent + timeIndex * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopDisposable(disposableCurrentTime);
        stopDisposable(disposableExit);
    }

    protected void startTimer(final long time) {
        timeCurrent = time;
        timeIndex = 0;
        stopDisposable(disposableCurrentTime);
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
                if (isFinishing()) {
                    return;
                }
                if (tvCurrentTime != null) {
                    tvCurrentTime.setText(TimeUtils.millis2String(time + timeIndex * 1000));
                }
                timeIndex += 1;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessagePrisonInfo(ModelDeviceInfo model) {
        if (tvRootName != null) {
            if (model == null) {
                tvRootName.setText("");
            } else {
                tvRootName.setText(model.getAreaName() + "\t\t" + model.getName());
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageTime(ModelHeartbeat heartbeat) {
        stopDisposable(disposableCurrentTime);
        startTimer(heartbeat.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageConnect(ModelConnect connect) {
        if (tvErrorConnect == null) {
            return;
        }
        if (connect.isConnect()) {
            tvErrorConnect.setVisibility(View.GONE);
        } else {
            tvErrorConnect.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageStopOverExit(ModelNoticeOverExit notice) {
        QOvertTimeExitManager.getInstance().time = System.currentTimeMillis();
    }





    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d(TAG, "屏幕点击了");
        QOvertTimeExitManager.getInstance().time = System.currentTimeMillis();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        QOvertTimeExitManager.getInstance().time = System.currentTimeMillis();
        return super.dispatchKeyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageConnect(ModelOvertime overtime) {
        QOvertTimeExitManager.getInstance().time = System.currentTimeMillis();
    }
}
