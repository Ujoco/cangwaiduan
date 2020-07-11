package com.shuangtu.prison.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.Constant;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterMain;
import com.shuangtu.prison.home.fragment.QFragmentCommon;
import com.shuangtu.prison.home.fragment.QFragmentCommons;
import com.shuangtu.prison.home.fragment.QFragmentWeb;
import com.shuangtu.prison.home.model.ModelMain;
import com.shuangtu.prison.home.qfragment.Discipline.QFragmentViolations;
import com.shuangtu.prison.home.socket.QSocketConnectManager;
import com.shuangtu.prison.home.socket.model.ModelConnect;
import com.trello.rxlifecycle3.android.ActivityEvent;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DisciplineCallManagementActivity extends HomeEventBusActivity {

    private RecyclerView rlMenu;
    private AdapterMain adapterMain;
    private int index;
    private List<ModelMain> listData;
    private LinearLayoutManager managerMain;
    public QFragmentCommon[] fragments = new QFragmentCommon[fragmentsName.length];
    //com.shuangtu.prison.home.fragment.QFragmentArea
    public static final String[] fragmentsName = {
//            "com.shuangtu.prison.home.fragment.QFragmentInspection",
//
//            "com.shuangtu.prison.home.fragment.QFragmentDiscipline",
//            "com.shuangtu.prison.home.fragment.QFragmentFix",
//            "com.shuangtu.prison.home.fragment.QFragmentInfoNotice",

            "com.shuangtu.prison.home.fragment.QFragmentInspection",
            "com.shuangtu.prison.home.fragment.QFragmentDiscipline",
            "com.shuangtu.prison.home.fragment.QFragmentFix",
            "com.shuangtu.prison.home.fragment.QFragmentInfoNotice",


    };
    private Integer fragmentm;
    private int clickIndex;
    private int clickIndex2;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_home_main;
    }

    @Override
    public void initView() {
        LogUtils.d("执行了----------");
        Intent intent = getIntent();//获取意图对象
        //获取意图对象数据
//        fragmentm = intent.getIntExtra("fragment", 0);
//        switchFragment(1);


        rlMenu = findViewById(R.id.rlMenu2);
        tvErrorConnect = findViewById(R.id.tvErrorConnect);
        tvRootName = findViewById(R.id.tvRootName);
        tvCurrentTime = findViewById(R.id.tvTime);


    }

    @Override
    public void initData() {

        clickIndex = getIntent().getIntExtra("index", 0);
        clickIndex2 = getIntent().getIntExtra("click", 0);

        LogUtils.d("碎片成功运行");
        index = -1;

        onMessageConnect(new ModelConnect(QSocketConnectManager.getManager().isConnect()));
        onMessagePrisonInfo(STUserManager.getInstance().getDeviceInfo());

        adapterMain = new AdapterMain(R.layout.main_adapter_item, null);
        managerMain = new LinearLayoutManager(getApplicationContext());
        managerMain.setOrientation(RecyclerView.HORIZONTAL);
        rlMenu.setAdapter(adapterMain);
        rlMenu.setLayoutManager(managerMain);
        Disposable disposable = Observable.timer(0, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        listData = new ArrayList();
                        for (int i = 0; i < Constant.modules.size(); i++) {
                            ModelMain modelMain = new ModelMain();
                            //modelMain.setRes(R.mipmap.header);
                            modelMain.setTitle(Constant.modules.get(i));
                            listData.add(modelMain);

                            if (i == 0) {
                                modelMain.setRes(R.mipmap.inspection);
                            } else {
                                if (i == 1) {
                                    modelMain.setRes(R.mipmap.discipline);
                                } else if (i == 2) {
                                    modelMain.setRes(R.mipmap.arraignment);
                                } else if (i == 3) {
                                    modelMain.setRes(R.mipmap.medical);
                                }
                            }
                        }

                        int index = getIntent().getIntExtra("index", 1);
                        adapterMain.setClickSelect(index);
                        adapterMain.replaceData(listData);
                        adapterMain.notifyDataSetChanged();
                        if (index > 8) {
                            rlMenu.scrollToPosition(index);
                        }
                        switchFragment(index);

                    }
                });


    }

    @Override
    public void initListener() {
        adapterMain.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                adapterMain.setClickSelect(position);
                adapterMain.notifyDataSetChanged();
                rlMenu.scrollToPosition(position);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        switchFragment(position);
                    }
                });
            }
        });

    }

    @Override
    public void networkMessage() {

    }

    private void switchFragment(int switchIndex) {

        LogUtils.d("Index是多少" + switchIndex);
        if (switchIndex == index) {
            return;
        }


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        QFragmentCommon fragment = fragments[switchIndex];

        if (fragment == null) {
            Class cls = null;
            try {
                cls = Class.forName(fragmentsName[switchIndex]);
                Constructor c = cls.getConstructor(int.class);
                if (switchIndex == clickIndex) {
                    /// clickIndex是tabbar中的第几个 底部bottom
                    /// clickIndex2是子fragment中的第几个
                    fragment = (QFragmentCommon) c.newInstance(clickIndex2);
                } else {
                    fragment = (QFragmentCommon) c.newInstance(0);
                }
            } catch (Exception e) {
                LogUtils.dTag(TAG, "构造器创建失败！", e.toString());
            }
            fragments[switchIndex] = fragment;
        }

        if (fragment == null) {
            LogUtils.e(TAG, fragmentsName[switchIndex], "创建为空!!!");
            return;
        }

        if (!fragment.isAdded()) {
            getSupportFragmentManager().executePendingTransactions();
            transaction.add(R.id.layoutFrame, fragment, fragment.getClass().getSimpleName());
        }
        transaction.show(fragment);
        if (index != -1 && index != switchIndex && fragments[index] != null) {
            transaction.hide(fragments[index]);
        }

        transaction.commitAllowingStateLoss();
        index = switchIndex;


    }

    @Override
    public void finish() {
        if (ergodicIsBack()) {
            LogUtils.dTag(TAG, "已被子fragment消耗");
        } else {
            super.finish();
        }
    }

    public boolean ergodicIsBack() {
        if (index == -1) {
            return false;
        }
        return fragments[index].isBack();
    }

    @Override
    public boolean registerEventBus() {
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragments = null;
        LogUtils.d(TAG, "onDestroy");
    }
}