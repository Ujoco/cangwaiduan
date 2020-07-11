package com.shuangtu.prison.common.constant;

import android.text.TextUtils;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.shuangtu.prison.common.utils.NetworkUtil;
import com.shuangtu.prison.common.utils.SharedPreUtil;

public class Constant {

    private static String HOST = "http://47.92.222.140";

    public static final String TokenError = "8002";

    public static final boolean FISH_DEBUG = true;

    public static final int FishUdpServiceDestroyTime = 6;

    public static final int FishUdpAgainSendBroadcat = 30;

    private static final String FishConfigAutoUdpBroadcast = "ConfigAutoUdpBroadcast";

    private static final String FishConfigCacheServiceAddress = "ConfigCacheSericeAddress";

    public static boolean FishsAutoUdpBroadcast = SharedPreUtil.fishVualeBoolean(FishConfigAutoUdpBroadcast, true);

    public static String FishServerAddress = SharedPreUtil.fishVuale(FishConfigCacheServiceAddress);

    //1208756132706062337
    public static String getRoomId() {
        if (STUserManager.getInstance().getDeviceInfo() != null) {
            return STUserManager.getInstance().getDeviceInfo().getId();
        }
        return "";
    }

    public static void initServerAddress(String address) {
        if (TextUtils.isEmpty(address)) {
            address = "http://192.168.1.1";
        }
        address = checkUrlStartWith(address);
        SharedPreUtil.fishSaveString(FishConfigCacheServiceAddress, address);
        LogUtils.a(address);
        HOST = address;
    }

    public static String getServiceAddress() {
        if (TextUtils.isEmpty(HOST)) {
            return SharedPreUtil.fishVuale(FishConfigCacheServiceAddress, "http://192.168.1.1");
        }
        return HOST;
    }

    public static String checkUrlStartWith(String address) {
        if (!address.startsWith("http://")) {
            address = "http://" + address;
        }
        return address;
    }

    public static String getLogin() {
        return Constant.getServiceAddress() + "/api/v1/auth/login";
    }

    public static String getMatch() {
        return Constant.getServiceAddress() + "/api/v1/face/matchUser";
    }

    public static String getFaceDiscern() {
        return Constant.getServiceAddress() + "/api/v1/face/faceRecord";
    }

    public static String getMacAddress() {
        String address = NetworkUtil.getNetwrokInfo(NetworkUtil.MACADDRESS);
        if (TextUtils.isEmpty(address)) {
            DeviceUtils.getMacAddress();
        }
        return address;
    }


}
