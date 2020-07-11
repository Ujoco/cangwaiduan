package com.shuangtu.prison.common.net;


import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.shuangtu.prison.common.constant.QApplication;
import com.shuangtu.prison.common.model.QModel;
import com.shuangtu.prison.common.notice.NoticeLogout;
import com.shuangtu.prison.common.q.QActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import static com.shuangtu.prison.common.constant.Constant.TokenError;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final String TAG = "GsonResponseBodyConverter";
    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();

        LogUtils.aTag(TAG, "返回的数据:" + response);

        //先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
        String code = null;
        String message = null;
        String data = null;
        try {
            JSONObject object = new JSONObject(response);
            message = object.getString("message");
            code = (String) object.get("code");
            data = object.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        if (code.equals(TokenError)) {

            if (TextUtils.equals(data, "1")) {
                QActivity activity = QApplication.activities.get(QApplication.activities.size() - 1);
                activity.show_logonMain();
                EventBus.getDefault().post(new NoticeLogout(true, 1));
            } else if (TextUtils.equals(data, "2")) {
                EventBus.getDefault().post(new NoticeLogout(true, 2));
                ToastUtils.showLong("终端登陆失效");
            }

            throw new IllegalArgumentException(message);
        }
        if (!code.equals("0000")) {

            throw new IllegalArgumentException("错误代码:" + code + "-" + message);
        }


        T model = gson.fromJson(response, type);


        return model;
    }
}


