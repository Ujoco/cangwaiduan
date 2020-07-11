package com.shuangtu.prison.common.q;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.trello.rxlifecycle3.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class QFragment extends RxFragment implements QUIOperation {

    protected View root;
    protected final String TAG = getClass().getSimpleName();
    public boolean isFirstCreate = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutRes(), null, false);
        if (registerEventBus()) {
            EventBus.getDefault().register(this);
        }
        initView();
        initData();
        initListener();
        networkMessage();
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.dTag(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.dTag(TAG, "onStart");
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtils.dTag(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.dTag(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.dTag(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.dTag(TAG, "onDestroy");
        if (registerEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    public boolean isBack() {
        return false;
    }
}
