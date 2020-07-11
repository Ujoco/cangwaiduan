package com.shuangtu.prison.home.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterMainTitle;
import com.shuangtu.prison.home.model.ModelMainTitle;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class QFragmentCommon extends QFragment {

    private AdapterMainTitle adapterMainTitle;
    private LinearLayoutManager managerTitle;
    private List<ModelMainTitle> listTitle;
    private RecyclerView rlTitle;
    private TextView tvBack;
    private ImageView ivBack;
    protected int index;
    public QFragment[] fragments;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_common;
    }

    @Override
    public void initView() {
        rlTitle = root.findViewById(R.id.rlTitle);
        tvBack = root.findViewById(R.id.tvBack);
        ivBack = root.findViewById(R.id.ivBack);
    }

    @Override
    public void initData() {
        fragments = new QFragment[fragmentsTitle().length];
        managerTitle = new LinearLayoutManager(getActivity().getApplicationContext());
        managerTitle.setOrientation(RecyclerView.HORIZONTAL);
        adapterMainTitle = new AdapterMainTitle(R.layout.main_title_adapter_item, null);
        rlTitle.setLayoutManager(managerTitle);
        rlTitle.setAdapter(adapterMainTitle);
        Disposable disposable = Observable.timer(0, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(FragmentEvent.DESTROY.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        listTitle = new ArrayList();

                        for (int i = 0; i < getTitles().length; i++) {
                            ModelMainTitle modelMain = new ModelMainTitle();

                            modelMain.setTitle(getTitles()[i]);
                            listTitle.add(modelMain);
                        }
                        adapterMainTitle.replaceData(listTitle);
                        adapterMainTitle.notifyDataSetChanged();

                        switchFragment(0);
                    }
                });
    }

    @Override
    public void initListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBack();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBack();
            }
        });
        adapterMainTitle.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                adapterMainTitle.clickView(position, view);
                switchFragment(position);
            }
        });
    }

    @Override
    public void networkMessage() {

    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    public void clickBack() {
        getActivity().finish();
    }

    protected void switchFragment(int switchIndex) {
        LogUtils.dTag(TAG, switchIndex);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (fragments[switchIndex] == null) {
            Class cls = null;
            QFragment fragment = null;
            try {
                cls = Class.forName(fragmentsTitle()[switchIndex]);
                fragment = (QFragment) cls.newInstance();
            } catch (Exception e) {
                LogUtils.dTag(TAG, e.toString());
                fragment = new QFragmentWeb();
            }
            fragments[switchIndex] = fragment;
        }
        QFragment fragment = fragments[switchIndex];
        if (!fragment.isAdded()) {
            getChildFragmentManager().executePendingTransactions();
            if (fragment.isFirstCreate) {
                transaction.add(R.id.layoutFrameChild, fragment, fragment.getClass().getSimpleName());
                fragment.isFirstCreate = false;
            } else {
                transaction.hide(fragment);
            }

        }
        transaction.show(fragment);
        if (index != switchIndex && fragments[index] != null) {
            if (tags().size() != 0 || tags().contains(fragmentsTitle()[switchIndex])) {
                transaction.remove(fragments[index]);
            } else {
                transaction.hide(fragments[index]);
            }
        }
        transaction.commitAllowingStateLoss();
        index = switchIndex;

    }

    public abstract String[] getTitles();

    public abstract String[] fragmentsTitle();

    public List<String> tags() {
        return new ArrayList<>();
    }





}
