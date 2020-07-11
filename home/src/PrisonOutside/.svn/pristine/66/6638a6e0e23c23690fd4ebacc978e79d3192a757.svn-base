package com.shuangtu.prison.home.qfragment.notice;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.common.model.ModelNoticeMessage;
import com.shuangtu.prison.home.fragment.OnListNoticeListener;
import com.shuangtu.prison.home.fragment.OnWebListNoticeListerner;
import com.shuangtu.prison.home.fragment.OnWebNoticeListener;
import com.shuangtu.prison.home.fragment.QFragmentWeb;
import com.shuangtu.prison.home.model.ModelNoticeRecords;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterNoticeMessage;
import com.shuangtu.prison.home.model.ModelMainTitle;
import com.shuangtu.prison.home.qfragment.Inspection.QFragmentPatrolappointment;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QFragmentNoticeMessage extends QFragment {

    private RecyclerView rlData;

    private int pageNo = 1;
    private ModelMainTitle model;
    private LinearLayoutManager manager;
    private AdapterNoticeMessage adapter;
    private QFragmentWeb fragmentWeb;
    private boolean isClickWeb;
    private RefreshLayout refreshView;
    private boolean isClickList;
    private QFragmentNoticeList fragmentNoticeList;

    public static QFragmentNoticeMessage newInstance(ModelMainTitle model) {
        //实例化对象
        QFragmentNoticeMessage myFragment = new QFragmentNoticeMessage();
        //Bundle类型
        Bundle args = new Bundle();
        //将下标放入以‘key’‘value’Bundle
        args.putParcelable("model", model);
        //利用setArguments传参数
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_notice_message;
    }

    @Override
    public void initView() {
        model = getArguments().getParcelable("model");
        LogUtils.dTag(TAG, model.toString());
        rlData = root.findViewById(R.id.rlData);
        refreshView = root.findViewById(R.id.refreshView);
    }

    @Override
    public void initData() {
        manager = new LinearLayoutManager(getContext().getApplicationContext());
        List<ModelNoticeRecords> list = new ArrayList<>();

        adapter = new AdapterNoticeMessage(R.layout.fragment_notice_message_item, R.layout.fragment_notice_message_header, list);
        rlData.setLayoutManager(manager);
        rlData.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {
                ModelNoticeRecords model = (ModelNoticeRecords) adapter.getData().get(position);
                if (model.isHeader) {
                    isClickList = true;
                    Bundle args = new Bundle();
                    args.putString("title", model.getHeaderTitle());
                    args.putString("num", model.getNum());
                    args.putString("dateStart", model.getDateStart());
                    args.putString("dateEnd", model.getDateEnd());
                    if(QFragmentNoticeMessage.this.model!=null){
                        args.putString("id", QFragmentNoticeMessage.this.model.getId());
                    }

                    fragmentNoticeList = QFragmentNoticeList.newInstance(args);
                    fragmentNoticeList.setOnWebListNoticeListerner(new OnWebListNoticeListerner() {
                        @Override
                        public void messageData(QFragmentWeb fragment, String id) {
                            QFragmentNoticeMessage.this.messageWeb(fragment, id);
                        }
                    });
                    fragmentNoticeList.setOnListNoticeListener(new OnListNoticeListener() {
                        @Override
                        public void messageData(QFragmentNoticeList fragment, int pageNo, int pageSize, String dateStart, String dateEnd, String id, String title) {
                            messageList(fragment, pageNo, pageSize, dateStart, dateEnd, id, title);
                        }

                    });
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    if (!fragmentNoticeList.isAdded()) {
                        transaction.add(R.id.fragmentLayoutWeb, fragmentNoticeList);
                    }
                    transaction.show(fragmentNoticeList).commitNow();
                } else {
                    isClickWeb = true;
                    if (fragmentWeb == null) {
                        fragmentWeb = new QFragmentWeb();
                    }
                    fragmentWeb.setDataNoticeListener(new OnWebNoticeListener() {
                        @Override
                        public void messageData(QFragmentWeb fragment) {
                            messageWeb(fragment, ((ModelNoticeRecords) adapter.getData().get(position)).t.getId());
                        }
                    });
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    if (!fragmentWeb.isAdded()) {
                        transaction.add(R.id.fragmentLayoutWeb, fragmentWeb);
                    }
                    transaction.show(fragmentWeb).commitNow();
                }
            }
        });

        refreshView.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                message("今天", 3);
            }
        });
        refreshView.autoRefresh();
    }

    @Override
    public void networkMessage() {

    }

    private void messageList(final QFragmentNoticeList fragment, final int pageNo, final int pageSize, final String dateStart, final String dateEnd, final String id, final String title) {

        Api.getInstance().newsList(pageNo, pageSize, id, dateStart, dateEnd)
                .compose(this.<ModelNoticeMessage>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelNoticeMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelNoticeMessage model) {



                        ModelNoticeRecords record = new ModelNoticeRecords(true, title);
                        record.setHeaderTitle(title);
                        record.setNum(String.valueOf(model.getTotal()));
                        List<ModelNoticeRecords> listRecords = new ArrayList<>();
                        listRecords.add(record);
                        for(int i = 0;i<listRecords.size();i++){
                            LogUtils.d("点击显示更多"+listRecords.get(i).getNum());
                            LogUtils.d("点击显示更多"+listRecords.get(i).getHeaderTitle());
                            LogUtils.d("点击显示更多"+listRecords.get(i).getDateStart());
                            LogUtils.d("点击显示更多"+listRecords.get(i).getDateStart());
                            LogUtils.d("点击显示更多"+model.getTotal());
                      //      LogUtils.d("点击显示更多------"+  listRecords.get(i).t.getTitle());

                        }

                        for (ModelNoticeMessage.RecordsBean bean : model.getRecords()) {
                            ModelNoticeRecords records = new ModelNoticeRecords(bean);
                            if (title.equals("今天")) {
                                records.setSelect(true);
                            }
                            listRecords.add(records);
                        }
                        fragment.adapter.setNewData(listRecords);
                        fragment.adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showLong(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void messageWeb(final QFragmentWeb fragment, String id) {
        Api.getInstance().webData(id)
                .compose(fragment.<com.shuangtu.prison.common.model.ModelWeb>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<com.shuangtu.prison.common.model.ModelWeb>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(com.shuangtu.prison.common.model.ModelWeb model) {
                        fragment.viewWeb.loadData(model.getContent(), "text/html;charset=utf-8", "utf-8");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
        } else if (isClickList) {
            if (fragmentNoticeList.isBack()) {
                return true;
            }
            isClickList = false;
            getChildFragmentManager().beginTransaction().remove(fragmentNoticeList).commitNow();
            return true;
        }
        return false;
    }

    public void message(final String msg, final int pageSize) {

        String dateStart = null;
        String dateEnd = null;
        if (msg.equals("今天")) {
            dateStart = Global.getPastDate(0);
            dateEnd = Global.getFetureDate(1);
        } else if (msg.equals("一周内")) {
            dateStart = Global.getPastDate(7);
            dateEnd = Global.getPastDate(0);
        } else {
            dateStart = Global.getStartDate();
            dateEnd = Global.getPastDate(7);
        }

        final String finalDateStart = dateStart;
        final String finalDateEnd = dateEnd;

        Api.getInstance().newsList(pageNo, pageSize, model.getId(), dateStart, dateEnd)
                .compose(this.<ModelNoticeMessage>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelNoticeMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelNoticeMessage model) {

                        ModelNoticeRecords record = new ModelNoticeRecords(true, msg);
                        record.setHeaderTitle(msg);
                        record.setDateStart(finalDateStart);
                        record.setDateEnd(finalDateEnd);
                        record.setNum(String.valueOf(model.getTotal()));

                        //model.getTotal()返回的是今天 一周 还有更多里的数据条目
                       // model.getTotal()
                        List<ModelNoticeRecords> listRecords = new ArrayList<>();
                        listRecords.add(record);

                        for(int i = 0; i<listRecords.size();i++){
                            LogUtils.d("第一个的A"+listRecords.get(i).getHeaderTitle());
                            LogUtils.d("第一个的B"+listRecords.get(i).getDateStart());
                            LogUtils.d("第一个的C"+listRecords.get(i).getDateStart());
                            LogUtils.d("第一个的C"+listRecords.get(i).getNum());
                            LogUtils.d("第一个的D"+model.getTotal());
                 //          LogUtils.d("点击显示更多--------------------------------"+  listRecords.get(i).t.getTitle());

                        }


                        for (ModelNoticeMessage.RecordsBean bean : model.getRecords()) {
                            ModelNoticeRecords records = new ModelNoticeRecords(bean);
                            if (msg.equals("今天")) {
                                records.setSelect(true);
                            }

                            LogUtils.d("第一个的E"+listRecords.get(0).getHeaderTitle());
                            listRecords.add(records);
                        }
                        if (refreshView.getState().isHeader && msg.equals("今天")) {
                            adapter.setNewData(listRecords);

                        } else {
                            adapter.addData(listRecords);
                        }
                        adapter.notifyDataSetChanged();
                        if (msg.equals("今天")) {
                            message("一周内", 3);
                        } else if (msg.equals("一周内")) {
                            message("更多", 3);
                            refreshView.finishRefresh();
                        }

               //         LogUtils.d("点击显示更多--------------------------------"+  listRecords.get(0).t.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                        refreshView.finishRefresh();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
