package com.shuangtu.prison.common.net;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.RequestBuilder;
import com.shuangtu.prison.common.constant.Constant;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.utils.SharedPreUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonInterceptor implements Interceptor {

    private static final String TAG = "CommonInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String token = STUserManager.getInstance().getToekn_device();
        if (STUserManager.getInstance().isLogin()) {
            token = token + "," + STUserManager.getInstance().getToken_user();
        }

        Request.Builder build = request.newBuilder()
                .addHeader("client", "android_inside");
        if (!TextUtils.isEmpty(token)) {
            build.addHeader("x-access-token", token);
        }

        LogUtils.aTag(TAG, "token：" + token);

        request = build.build();

        LogUtils.d(TAG, request.toString());

        return chain.proceed(request);
    }


}
