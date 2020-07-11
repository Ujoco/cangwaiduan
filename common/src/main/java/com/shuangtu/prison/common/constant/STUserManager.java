package com.shuangtu.prison.common.constant;

import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.shuangtu.prison.common.model.LoginModel;
import com.shuangtu.prison.common.model.ModelDeviceInfo;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.common.utils.SharedPreUtil;

import org.greenrobot.eventbus.EventBus;

public class STUserManager {

    private static final String TAG = "STUserManager";

    private static STUserManager instance;

    private static final String STDeviceToken = "STDeviceToken";

    private static final String STUserToken = "STUserToken";

    private static final String STUserModel = "STUserModel";

    private static final String STDeviceModel = "STDeviceModel";

    public static synchronized STUserManager getInstance() {
        if (instance == null) {
            instance = new STUserManager();
        }
        return instance;
    }

    /// 设备token
    private String toekn_device;

    /// 用户token
    private String token_user;

    /// 设备登陆信息
    private LoginModel modelDevice;

    /// 用户登陆信息
    private LoginModel modelUser;

    /// 监室信息
    private ModelDeviceInfo deviceInfo;

    public ModelDeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(ModelDeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
        EventBus.getDefault().post(deviceInfo);
    }

    public LoginModel getModelUser() {
        if (modelUser == null){
            modelUser = GsonUtils.getGson().fromJson(SharedPreUtil.getString(Global.mContext, STUserModel, ""), LoginModel.class);
        }
        return modelUser;
    }

    public  String setModelUser(LoginModel modelUser) {
        SharedPreUtil.saveString(Global.mContext, STUserModel, GsonUtils.getGson().toJson(modelUser));
        return STUserModel;
    }

    public LoginModel getModelDevice() {
        if (modelDevice == null){
            modelDevice = GsonUtils.getGson().fromJson(SharedPreUtil.getString(Global.mContext, STDeviceModel, ""), LoginModel.class);
        }
        return modelDevice;
    }

    public void setModelDevice(LoginModel modelDevice) {
        SharedPreUtil.saveString(Global.mContext, STDeviceModel, GsonUtils.getGson().toJson(modelDevice));

        this.modelDevice = modelDevice;
    }

    public String getToekn_device() {
        if (TextUtils.isEmpty(toekn_device)) {
            toekn_device = SharedPreUtil.getString(Global.mContext, STDeviceToken, "");
        }
        return toekn_device;
    }

    public void setToekn_device(String toekn_device) {
        SharedPreUtil.saveString(Global.mContext, STDeviceToken, toekn_device);
        this.toekn_device = toekn_device;
    }

    public String getToken_user() {
        if (TextUtils.isEmpty(token_user)) {
            token_user = SharedPreUtil.getString(Global.mContext, STUserToken, "");
        }
        return token_user;
    }

    public void setToken_user(String token_user) {
        SharedPreUtil.saveString(Global.mContext, STUserToken, token_user);
        this.token_user = token_user;
    }

    /// 重置登陆
    public void reset() {
        toekn_device = null;
        token_user = null;
        modelDevice = null;
        modelUser = null;
        SharedPreUtil.saveString(Global.mContext, STUserToken, "");
        SharedPreUtil.saveString(Global.mContext, STDeviceToken, "");
        SharedPreUtil.saveString(Global.mContext, STDeviceModel, "");
        SharedPreUtil.saveString(Global.mContext, STUserModel, "");
    }

    /// 退出登陆
    public void logout() {
        token_user = null;
        modelUser = null;
        SharedPreUtil.saveString(Global.mContext, STUserToken, "");
        SharedPreUtil.saveString(Global.mContext, STUserModel, "");
    }

    public void logoutDev() {
        toekn_device = null;
        modelDevice = null;
        SharedPreUtil.saveString(Global.mContext, STDeviceToken, "");
        SharedPreUtil.saveString(Global.mContext, STDeviceModel, "");
        setDeviceInfo(new ModelDeviceInfo());

    }

    /// 用户是否登陆
    public boolean isLogin() {
        return !TextUtils.isEmpty(getToken_user());
    }

    /// 终端是否登陆
    public boolean isLoginDevice() {
        return !TextUtils.isEmpty(getToekn_device());
    }

}
