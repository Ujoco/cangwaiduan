package com.shuangtu.prison.inside;

import android.app.IntentService;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import com.shuangtu.prison.common.constant.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UdpService extends IntentService {

    private static final int SEND_BROADCAST_PORT = 3580;
    private static final int RECV_BROADCAST_PORT = 3581;
    private static final String BROADCAST_ADDR = "255.255.255.255";
    private static final String TAG = "UdpService";
    public Boolean IsThreadDisable = false;//指示监听线程是否终止


    private WifiManager.MulticastLock lock;
    private boolean destroty = false;
    private DatagramSocket datagramSocket;

    public UdpService() {
        super("UdpService");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        this.lock = manager.createMulticastLock("UDPwifi");
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        receiveInit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sendUdpMessage(UdpMessage message) {
        Observable.just("one", "two", "three")
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (!destroty) {
                            sendMessage();
                        }
                        Thread.sleep(1 * 1000);
                    }
                });
    }

    private void receiveInit() {
        LogUtils.dTag(TAG, "receiveInit");
        byte[] message = new byte[128];

        try {
            datagramSocket = null;

            if (datagramSocket == null) {
                datagramSocket = new DatagramSocket(null);
                datagramSocket.setReuseAddress(true);
                datagramSocket.bind(new InetSocketAddress(RECV_BROADCAST_PORT));
            }

            final DatagramPacket datagramPacket = new DatagramPacket(message, message.length);
            startListener(datagramSocket, datagramPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void startListener(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        while (!IsThreadDisable) {
            LogUtils.dTag(TAG, "开始监听");
            try {
                lock.acquire();
                datagramSocket.receive(datagramPacket);
                String ip = datagramPacket.getAddress()
                        .getHostAddress().toString();
                LogUtils.dTag(TAG, "服务器ip地址：" + ip);
                EventBus.getDefault().post(new UdpInfo(ip));
                this.lock.release();
                this.lock = null;
                IsThreadDisable = true;
                stopSelf();
            } catch (Exception e) {
                LogUtils.dTag(TAG, e.toString());
            } finally {
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
            }
        }
    }

    private void sendMessage() {
        String message = "get-tvapp-server-ip, macid=" + Constant.getMacAddress();
        DatagramSocket datagramSocket = null;
        try {
            LogUtils.dTag(TAG, "准备发送：" + message);
            datagramSocket = new DatagramSocket();
            datagramSocket.setBroadcast(true);
            InetAddress address = InetAddress.getByName(BROADCAST_ADDR);

            DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), address, SEND_BROADCAST_PORT);

            datagramSocket.send(datagramPacket);

        } catch (Exception e) {
            LogUtils.dTag(TAG, e.toString());
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stop(StopService stopService) {
        LogUtils.dTag(TAG, "stop");
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroty = true;
        LogUtils.dTag(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
    }

    public static class UdpMessage {
    }

    public static class StopService {
    }

    public static class UdpInfo {
        private String ip;

        public UdpInfo() {
        }

        public UdpInfo(String ip) {
            this.ip = ip;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        @Override
        public String toString() {
            return "UdpInfo{" +
                    "ip='" + ip + '\'' +
                    '}';
        }
    }
}
