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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public abstract class QFragmentNoticeCommon extends QFragment {

    private AdapterMainTitle adapterMainTitle;
    private LinearLayoutManager managerTitle;
    private List<ModelMainTitle> listTitle;
    private RecyclerView rlTitle;
    private TextView tvBack;
    private ImageView ivBack;
    protected int index;
    protected QFragment[] fragments;




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
        managerTitle = new LinearLayoutManager(getActivity().getApplicationContext());
        managerTitle.setOrientation(RecyclerView.HORIZONTAL);
        adapterMainTitle = new AdapterMainTitle(R.layout.main_title_adapter_item, null);
        rlTitle.setLayoutManager(managerTitle);
        rlTitle.setAdapter(adapterMainTitle);
        Observable.timer(0, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(FragmentEvent.DESTROY.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        listTitle = new ArrayList();

                        for (int i = 0; i < getTitles().length; i++) {
                            ModelMainTitle modelMain = new ModelMainTitle();
                            modelMain.setId(getIds()[i]);
                            modelMain.setTitle(getTitles()[i]);
                            listTitle.add(modelMain);
                        }
                        adapterMainTitle.replaceData(listTitle);
                        adapterMainTitle.notifyDataSetChanged();
                        fragments = new QFragment[listTitle.size()];
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

    private void switchFragment(int switchIndex) {
        LogUtils.dTag(TAG, switchIndex);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        QFragment fragment;
        if (fragments[switchIndex] == null) {
            fragments[switchIndex] = fragmentCreate(listTitle.get(switchIndex));
        }
        fragment = fragments[switchIndex];
        if (!fragment.isAdded()) {
            transaction.add(R.id.layoutFrameChild, fragment, fragment.getClass().getSimpleName());
        }
        transaction.show(fragment);
        if (index != switchIndex) {
            transaction.hide(fragments[index]);
        }
        transaction.commitAllowingStateLoss();
        index = switchIndex;

    }

    private void switchStatus(boolean isClick) {
        for (int i = 0; i < rlTitle.getChildCount(); i++) {
            View view = rlTitle.getChildAt(i);
            view.setClickable(isClick);
        }
    }

    public abstract String[] getTitles();

    public abstract String[] getIds();

    public abstract QFragment fragmentCreate(ModelMainTitle model);
}
