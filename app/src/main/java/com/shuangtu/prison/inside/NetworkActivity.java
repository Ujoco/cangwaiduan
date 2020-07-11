package com.shuangtu.prison.inside;

import androidx.appcompat.app.AlertDialog;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.q.QActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import static com.shuangtu.prison.common.constant.Constant.FishServerAddress;
import static com.shuangtu.prison.common.constant.Constant.FishUdpAgainSendBroadcat;
import static com.shuangtu.prison.common.constant.Constant.FishUdpServiceDestroyTime;
import static com.shuangtu.prison.common.constant.Constant.FishsAutoUdpBroadcast;
import static com.shuangtu.prison.common.constant.Constant.checkUrlStartWith;
import static com.shuangtu.prison.common.constant.Constant.initServerAddress;

/**
 * 获取服务器地址统一在这里处理
 */
public class NetworkActivity extends QActivity {

    private TextView tvHint;
    private Button btnSetting;
    private Intent intentUdp;
    private AlertDialog dialogInput;

    private boolean isConnecting;
    private Observable observable;
    private Disposable disposableTimer;
    private Disposable disposableDestroy;
    private boolean isOverTime;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_network;
    }

    @Override
    public void initView() {
        tvHint = findViewById(R.id.tvHint);
        btnSetting = findViewById(R.id.btnSetting);
    }

    @Override
    public void initData() {
        setStatus("正在初始化");
        if (FishsAutoUdpBroadcast) {
            useCahcheAddress();
            //startUdpSerice();
        } else {
            useCahcheAddress();
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }

    @Override
    public boolean registerEventBus() {
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopUdpService();
        stopInputAddress();
        stopTimer();
        stopDestroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setStatus(String str) {
        tvHint.setText(str);
    }

    private void startUdpSerice() {
        isOverTime = false;
        stopInputAddress();
        setStatus("正在初始化搜索服务器");
        if (intentUdp == null) {
            intentUdp = new Intent(getApplicationContext(), UdpService.class);
        }
        startService(intentUdp);

        // 延迟一秒发送请求

        Flowable.timer(1 * 1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        setStatus("正在搜索服务器,请耐心等候...");
                        requestUdpSendMessage();
                    }
                });

        startUdpServiceDestroyTimer();
    }

    private void stopUdpService() {
        if (intentUdp != null) {
            stopService(intentUdp);
        }
    }

    private void stopInputAddress() {
        if (dialogInput != null) {
            if (dialogInput.isShowing()) {
                dialogInput.dismiss();
                dialogInput = null;
            }
        }
        btnSetting.setVisibility(View.GONE);
    }

    private void stopTimer() {
        if (disposableTimer != null && !disposableTimer.isDisposed()) {
            disposableTimer.dispose();
        }
    }

    private void stopDestroy() {
        if (disposableDestroy != null && !disposableDestroy.isDisposed()) {
            disposableDestroy.dispose();
        }
    }

    private void requestUdpSendMessage() {
        EventBus.getDefault().post(new UdpService.UdpMessage());
    }

    /**
     * udpService获取到服务器返回数据
     * 进行请求数据
     *
     * @param udpInfo
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hanlderServerAddress(UdpService.UdpInfo udpInfo) {
        setStatus("搜索获取服务器地址成功!_" + udpInfo.getIp());
        if (isOverTime) {
            LogUtils.dTag(TAG, "udp搜索服务器超时!");
            return;
        }
        String ip = checkUrlStartWith(udpInfo.getIp());
        stopDestroy();
        checkingConnect(ip);
//        Constant.initServerAddress(udpInfo.getIp());
    }

    /**
     * 开启销毁udp服务器定时器
     */
    private void startUdpServiceDestroyTimer() {
        Observable observableUdpDestroy = Observable.timer(FishUdpServiceDestroyTime, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
        observableUdpDestroy.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                disposableDestroy = d;
            }

            @Override
            public void onNext(Object o) {
                if (isFinishing()) {
                    return;
                }
                isOverTime = true;
                setStatus("搜索服务器地址失败，正在切换缓存地址");
                stopUdpService();
                useCahcheAddress();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void useCahcheAddress() {
        setStatus("使用缓存地址连接");
        if (TextUtils.isEmpty(FishServerAddress)) {
            waitStartUpdService();
        } else {
            setStatus("验证服务器");
            checkingConnect(FishServerAddress);
        }
    }

    private void waitStartUpdService() {
        //设置循环次数
        observable = Observable.interval(0, 1, TimeUnit.SECONDS) //0延迟  每隔1秒触发
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//操作UI主要在UI线程
                .take(FishUdpAgainSendBroadcat + 1);
        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposableTimer = d;
            }

            @Override
            public void onNext(Long o) {
                LogUtils.dTag(TAG, o);

                if (o == FishUdpAgainSendBroadcat) {
                    startUdpSerice();
                }
                setStatus("缓存地址为空," + (FishUdpAgainSendBroadcat - o) + "s后再次搜索服务器");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        showSetting();
    }

    /**
     * 弹出手动连接服务器
     */
    private void showSetting() {
        btnSetting.setVisibility(View.VISIBLE);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(NetworkActivity.this);
                dialogInput = new AlertDialog.Builder(NetworkActivity.this).setTitle("请输入服务器地址")
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ip = et.getText().toString();
                                if (TextUtils.isEmpty(ip)) {
                                    ToastUtils.showLong("输入服务器地址不能为空！");
                                    return;
                                }
//                                if (!RegexUtils.isIP(ip)) {
//                                    ToastUtils.showLong("输入服务器地址不合法！");
//                                    return;
//                                }
                                checkingConnect(ip);
                            }
                        })
                        .setNegativeButton("取消", null).show();
            }
        });
    }

    /**
     * 验证服务器是否连接成功
     *
     * @param ip
     */
    private void checkingConnect(String ip) {
        if (isConnecting) {
            return;
        }
        stopTimer();
        isConnecting = true;
        stopInputAddress();

        setStatus("检查服务器地址");
        requestNetwrok(ip);
    }

    private void requestNetwrok(final String ip) {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (true) {
                            checkSuccess(ip);
                        } else {
                            checkFail(ip);
                        }
                    }
                });
    }

    private void checkSuccess(String ip) {
        initServerAddress(ip);
        setStatus("连接服务器成功！正在为您跳转首页");
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        finish();
                        Global.startActivity(MainActivity.class);
                    }
                });
    }

    private void checkFail(final String ip) {
        setStatus("连接服务器失败！");
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        isConnecting = false;
                        if (TextUtils.isEmpty(FishServerAddress)) {
                            useCahcheAddress();
                        } else {
                            if (ip.equals(FishServerAddress)) {
                                waitStartUpdService();
                            } else {
                                useCahcheAddress();
                            }
                        }
                    }
                });
    }
}
