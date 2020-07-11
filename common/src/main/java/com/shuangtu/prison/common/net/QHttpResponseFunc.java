package com.shuangtu.prison.common.net;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shuangtu.prison.common.constant.QApplication;
import com.shuangtu.prison.common.q.QActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class QHttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {

        return Observable.error(new Throwable("访问错误！"));
    }
}
