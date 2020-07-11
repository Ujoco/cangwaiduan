package com.shuangtu.prison.home.qfragment.notice;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.common.model.ModelNoticeMessage;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.common.view.OnPageNumberListener;
import com.shuangtu.prison.common.view.PageNumberView;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterNoticeMessage;
import com.shuangtu.prison.home.fragment.OnListNoticeListener;
import com.shuangtu.prison.home.fragment.OnWebListNoticeListerner;
import com.shuangtu.prison.home.fragment.OnWebNoticeListener;
import com.shuangtu.prison.home.fragment.QFragmentWeb;
import com.shuangtu.prison.home.model.ModelNoticeRecords;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QFragmentNoticeList extends QFragment {

    private RecyclerView rlData;

    private int pageNo = 1;
    private static final int pageSize = 10;

    private LinearLayoutManager manager;
    public AdapterNoticeMessage adapter;
    private QFragmentWeb fragmentWeb;
    private boolean isClickWeb;

    private String id;
    private String dateStart;
    private String dateEnd;
    private String title;
    private String num;
    private PageNumberView viewPageNumber;
    private OnWebListNoticeListerner onWebListNoticeListerner;
    private OnListNoticeListener onListNoticeListener;

    public void setOnWebListNoticeListerner(OnWebListNoticeListerner onWebListNoticeListerner) {
        this.onWebListNoticeListerner = onWebListNoticeListerner;
    }

    public void setOnListNoticeListener(OnListNoticeListener onListNoticeListener) {
        this.onListNoticeListener = onListNoticeListener;
    }

    public static QFragmentNoticeList newInstance(Bundle model) {
        //实例化对象
        QFragmentNoticeList myFragment = new QFragmentNoticeList();
        //利用setArguments传参数
        myFragment.setArguments(model);
        return myFragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_notice_list;
    }

    @Override
    public void initView() {
        id = getArguments().getString("id");
        dateStart = getArguments().getString("dateStart");
        dateEnd = getArguments().getString("dateEnd");
        title = getArguments().getString("title");
        num = getArguments().getString("num");
        rlData = root.findViewById(R.id.rlData);
        viewPageNumber = root.findViewById(R.id.viewPageNumber);
    }

    @Override
    public void initData() {
        manager = new LinearLayoutManager(getContext().getApplicationContext());
        List<ModelNoticeRecords> list = new ArrayList<>();
        adapter = new AdapterNoticeMessage(R.layout.fragment_notice_message_item, R.layout.fragment_notice_message_header, list);
        rlData.setLayoutManager(manager);
        rlData.setAdapter(adapter);

        int num = (int) Math.ceil(1.0 * Integer.valueOf(this.num) / pageSize);
        if (Integer.valueOf(this.num) <= pageSize) {
            num = 0;
        }
        viewPageNumber.setPageNumber(num);
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {
                ModelNoticeRecords model = (ModelNoticeRecords) adapter.getData().get(position);
                if (model.isHeader) {
                    LogUtils.dTag(TAG, model.header);
                } else {
                    isClickWeb = true;
                    if (fragmentWeb == null) {
                        fragmentWeb = new QFragmentWeb();
                    }
                    fragmentWeb.setDataNoticeListener(new OnWebNoticeListener() {
                        @Override
                        public void messageData(QFragmentWeb fragment) {
                            if (onWebListNoticeListerner != null) {
                                onWebListNoticeListerner.messageData(fragment, ((ModelNoticeRecords) adapter.getData().get(position)).t.getId());
                            }
                        }
                    });

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    if (!fragmentWeb.isAdded()) {
                        transaction.add(R.id.fragmentLayoutList, fragmentWeb);
                    }
                    transaction.show(fragmentWeb).commitNow();
                }
            }
        });

        viewPageNumber.setOnPageNumberListener(new OnPageNumberListener() {
            @Override
            public void loadPage(int index) {
                pageNo = index;
                message();
            }
        });
        message();
    }

    @Override
    public void networkMessage() {

    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    @Override
    public boolean isBack() {
        if (isClickWeb) {
            isClickWeb = false;
            getChildFragmentManager().beginTransaction().remove(fragmentWeb).commitNow();
            return true;
        }
        return false;
    }

    public void message() {
        if (onListNoticeListener != null) {
            onListNoticeListener.messageData(this, pageNo, pageSize, dateStart, dateEnd, id, title);
        }
    }

}
