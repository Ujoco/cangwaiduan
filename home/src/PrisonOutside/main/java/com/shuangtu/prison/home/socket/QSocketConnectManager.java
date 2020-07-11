package com.shuangtu.prison.home.socket;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.shuangtu.prison.common.constant.Constant;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.model.ModelDeviceInfo;
import com.shuangtu.prison.home.socket.model.ModelConnect;
import com.shuangtu.prison.home.socket.model.ModelHeartbeat;
import com.shuangtu.prison.home.socket.model.ModelInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class QSocketConnectManager extends WebSocketClient {

    private static final String TAG = "QSocketConnectManager";

    public static String getUrl() {
        String url = Constant.getServiceAddress().replace("http", "ws") + "/ws/websocket/" + Constant.getRoomId() + "?token=" + STUserManager.getInstance().getToekn_device();
        LogUtils.aTag(TAG, url);
        return url;
    }

    private static QSocketConnectManager manager;

    public static QSocketConnectManager getManager() {
        if (manager == null) {
            try {
                manager = new QSocketConnectManager(new URI(getUrl()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return manager;
    }

    public static void colse() {
        if (manager != null) {
            manager.closeConnect();
            manager = null;
        }
    }

    public QSocketConnectManager(URI serverUri) {
        super(serverUri);
        lock = new ReentrantLock();
        Time_Keep_Overtime = 5000;
        resetStart();
        setConnect(false);
    }

    private Lock lock;

    private Disposable disposableConnect, disposableOvertime, disposableKeep, disposablReconnect;

    // 当前心跳时间 用于判断是否心跳超时
    private long timeCurrentKeep;

    /// 是否超时
    private boolean isOvertime;

    /// 心跳当前超时次数
    private int overtimeCount;

    /// 是否关闭
    private boolean isClose;

    private boolean isConnects;

    public void setConnect(boolean connects) {
        if (connects != isConnects) {
            EventBus.getDefault().post(new ModelConnect(connects));
        }
        isConnects = connects;
    }

    public boolean isConnect() {
        return isConnects;
    }

    private int Time_Keep_Overtime;

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        LogUtils.d(TAG, "onOpen 连接成功");
        login();
    }

    @Override
    public void onMessage(String message) {
        Disposable disposable = Flowable.just(message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        hanlderMessage(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d(TAG, throwable.toString());
                    }
                });
    }

    private void hanlderMessage(String message) {
        LogUtils.d(TAG, "onMessage:" + message);
        HashMap params = null;
        try {
            params = QSocketCmd.getGson().fromJson(message, HashMap.class);
        } catch (Exception ex) {
            LogUtils.d(TAG, ex.toString());
            return;
        }
        if (params == null) {
            return;
        }
        String type = (String) params.get("type");
        if (TextUtils.equals(type, "heartbeat")) {
            ModelHeartbeat heartbeat = QSocketCmd.getGson().fromJson(message, ModelHeartbeat.class);
            hanlderKeep();
            EventBus.getDefault().post(heartbeat);
        } else if (TextUtils.equals(type, "overtimeConfig")) {
            ModelHeartbeat overtime = QSocketCmd.getGson().fromJson(message, ModelHeartbeat.class);
            com.shuangtu.prison.home.Constant.overtimeConfig = overtime.getMessage();
            EventBus.getDefault().post(overtime);
        } else if (TextUtils.equals(type, "prisonInfo")) {
            ModelInfo info = QSocketCmd.getGson().fromJson(message, ModelInfo.class);
            ModelDeviceInfo deviceInfo = STUserManager.getInstance().getDeviceInfo();
            if (deviceInfo == null) {
                deviceInfo = new ModelDeviceInfo();
            }
            deviceInfo.setAreaName(info.getMessage().getAreaName());
            deviceInfo.setName(info.getMessage().getRoomName());
            EventBus.getDefault().post(deviceInfo);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        LogUtils.d(TAG, "onClose " + "code:" + code + " reasion:" + reason + " remote:" + remote);
    }

    @Override
    public void onError(Exception ex) {
        LogUtils.d(TAG, "onError:" + ex.toString());

    }

    public void resetStart() {
        disposablReconnect = Flowable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isClose) {
                            return;
                        }
                        //   LogUtil.d(TAG, "resetStart");
                        if (!isConnects) {
                            stopConnect();
                            isOvertime = true;
                            if (!isClose) {
                                connectService(1);
                            }
                        }
                        resetStart();
                    }
                });
    }

    public void closeConnect() {
        stopConnect();
        isClose = true;
        stopDisposable(disposablReconnect);
        stopDisposable(disposableConnect);
        close();
    }

    private void stopConnect() {
        stopDisposable(disposableOvertime);
        stopDisposable(disposableKeep);
        if (!isClosed()) {
            close();
        }
    }

    /**
     * 连接服务器
     *
     * @param delay
     */
    public void connectService(int delay) {


        if (isOpen() && isOvertime) {
            /// 如果是连接的重新连接
            close();
        }
        isClose = false;
        disposableConnect = Flowable.timer(delay, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isConnects) {
                            return;
                        }
                        lock.tryLock();
                        LogUtils.d(TAG, "connectService");
                        try {
                            if (isOvertime) {
                                reconnectBlocking();
                            } else {
                                connectBlocking(10, TimeUnit.SECONDS);
                            }
                        } catch (InterruptedException e) {
                            LogUtils.d(TAG, "链接错误2" + e.toString());
                        } finally {
                            lock.unlock();
                        }

                    }
                });
    }

    @Override
    public void send(String text) {
        super.send(text);
        LogUtils.d(TAG, "send:" + text);
    }

    /**
     * 登陆
     */
    private void login() {
        isOvertime = false;
        setConnect(true);
        keep();
//        send(QSocketCmd.getOvertimeConfig());
    }

    /**
     * 开启心跳
     */
    private void keep() {

        if (isClose) {
            return;
        }
        timeCurrentKeep = System.currentTimeMillis();
        send(QSocketCmd.getHeartbeat());

        disposableKeep = Flowable.timer(Time_Keep_Overtime, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        keep();
                    }
                });

        disposableOvertime = Flowable.timer(Time_Keep_Overtime, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        hanlderOvertime();
                    }
                });
    }

    /**
     * 判断是否超时
     */
    private boolean isOvertime() {
        return (timeCurrentKeep == 0 || ((timeCurrentKeep + Time_Keep_Overtime) < System.currentTimeMillis()));
    }

    /**
     * 心跳超时处理
     */
    private void hanlderOvertime() {

        setConnect(false);
        isOvertime = true;

    }

    /**
     * 处理心跳回调
     */
    private void hanlderKeep() {
        if (isOvertime() || isOvertime) {
            /// 超时不处理
            LogUtils.d(TAG, "心跳超时不处理");
            isOvertime = true;
            setConnect(false);
            return;
        }
        setConnect(true);
        isOvertime = false;
        stopDisposable(disposableOvertime);
        timeCurrentKeep = System.currentTimeMillis();
    //    LogUtils.d(TAG, "心跳回调成功:" + timeCurrentKeep);

    }

    /**
     * 关闭定时器
     *
     * @param disposable 定时器
     */
    private void stopDisposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


}
