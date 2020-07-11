package com.shuangtu.prison.common.model;

import com.shuangtu.prison.common.constant.QApplication;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.q.QActivity;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Function;

import static com.shuangtu.prison.common.constant.Constant.TokenError;

public class QModel<T> implements Function<QModel<T>, T> {
    private String code;
    private String message;
    private T data;

    public QModel() {
    }

    public QModel(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public T apply(QModel<T> model) throws Exception {

        return model.getData();
    }
}
