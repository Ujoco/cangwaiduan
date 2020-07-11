package com.shuangtu.prison.home.qfragment.notice;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.model.ModelCaseNotice;
import com.shuangtu.prison.common.model.ModelCaseNoticeDetails;
import com.shuangtu.prison.common.model.ModelMeeting;
import com.shuangtu.prison.common.model.ModelMeetingDetails;
import com.shuangtu.prison.common.model.ModelNoticeMessage;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterNoticeMessage;
import com.shuangtu.prison.home.fragment.OnListNoticeListener;
import com.shuangtu.prison.home.fragment.OnWebListNoticeListerner;
import com.shuangtu.prison.home.fragment.OnWebNoticeListener;
import com.shuangtu.prison.home.fragment.QFragmentWeb;
import com.shuangtu.prison.home.model.ModelMainTitle;
import com.shuangtu.prison.home.model.ModelNoticeRecords;
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

public class QFragmentNoticeMeeting extends QFragment {


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

    private String noticeType;

    public static QFragmentNoticeMeeting newInstance(String type) {
        //实例化对象
        QFragmentNoticeMeeting myFragment = new QFragmentNoticeMeeting();
        //Bundle类型
        Bundle args = new Bundle();
        //将下标放入以‘key’‘value’Bundle
        args.putString("type", type);
        //利用setArguments传参数
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_meeting;
    }

    @Override
    public void initView() {
        rlData = root.findViewById(R.id.rlData);
        refreshView = root.findViewById(R.id.refreshView);
    }

    @Override
    public void initData() {
        noticeType = getArguments().getString("type");
        LogUtils.d(TAG, "noticeType:" + noticeType);
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
                    if (fragmentNoticeList == null) {
                        Bundle args = new Bundle();
                        args.putString("title", model.getHeaderTitle());
                        args.putString("num", model.getNum());
                        args.putString("dateStart", model.getDateStart());
                        args.putString("dateEnd", model.getDateEnd());
                        args.putString("id", noticeType);
                        fragmentNoticeList = QFragmentNoticeList.newInstance(args);
                    }
                    fragmentNoticeList.setOnWebListNoticeListerner(new OnWebListNoticeListerner() {
                        @Override
                        public void messageData(QFragmentWeb fragment, String id) {

                            messageWeb(fragment, id);
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
                            if (noticeType.equals("1")) {
                                messageWeb(fragmentWeb, (((ModelNoticeRecords) adapter.getData().get(position)).t.getId()));
                            } else {
                                messageWeb(fragmentWeb, (((ModelNoticeRecords) adapter.getData().get(position)).t.getId()));
                            }
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

    private void messageList(final QFragmentNoticeList fragment, int pageNo, int pageSize, String dateStart, String dateEnd, final String id, final String title) {
        Api.getInstance().getCaseNotice(pageNo, pageSize, dateStart, dateEnd)
                .compose(fragment.<ModelCaseNotice>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelCaseNotice>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelCaseNotice model) {
                        ModelNoticeRecords record = new ModelNoticeRecords(true, title);
                        record.setHeaderTitle(title);
                        record.setNum(String.valueOf(model.getTotal()));
                        List<ModelNoticeRecords> listRecords = new ArrayList<>();
                        listRecords.add(record);
                        for (ModelCaseNotice.RecordsBean bean : model.getRecords()) {
                            ModelNoticeMessage.RecordsBean data = new ModelNoticeMessage.RecordsBean();
                            data.setTitle(bean.getName());
                            data.setId(bean.getId());
                            ModelNoticeRecords records = new ModelNoticeRecords(data);
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

    private void messageWeb(final QFragmentWeb fragmentWeb, final String id) {
        LogUtils.dTag(TAG, "id:" + id + " noticeType:" + noticeType);
        if (noticeType.equals("1")) {
            Api.getInstance().getCaseNoticeDetails(id)
                    .compose(fragmentWeb.<ModelCaseNoticeDetails>bindUntilEvent(FragmentEvent.DESTROY))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<ModelCaseNoticeDetails>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ModelCaseNoticeDetails model) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("<p>提审人：");
                            sb.append(model.getUserName());
                            sb.append("<br/><br/>");
                            sb.append("提审时间：");
                            sb.append(Global.simpleTime2(model.getTrialTime()));
                            sb.append("<br/><br/>");
                            sb.append("详情：");
                            sb.append(model.getDetail());
                            sb.append("</p>");
                            fragmentWeb.viewWeb.loadData(sb.toString(), "text/html;charset=utf-8", "utf-8");
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtils.showLong(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            Api.getInstance().getMeetingNoticeDetails(id)
                    .compose(fragmentWeb.<ModelMeetingDetails>bindUntilEvent(FragmentEvent.DESTROY))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<ModelMeetingDetails>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ModelMeetingDetails model) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("<p>预约号：");
                            sb.append(model.getAppointmentNum());
                            sb.append("<br/><br/>");
                            sb.append("服刑人员：");
                            sb.append(model.getUserName());
                            sb.append("<br/><br/>");
                            sb.append("监区：");
                            sb.append(model.getPrisonAreaName());
                            sb.append("<br/><br/>");
                            sb.append("会见区：");
                            sb.append(model.getMeetArea());
                            sb.append("<br/><br/>");
                            sb.append("窗口：");
                            sb.append(model.getWindowName());
                            sb.append("<br/><br/>");
                            sb.append("会见人员：");
                            sb.append(model.getToMeetUsername());
                            sb.append("<br/><br/>");
                            sb.append("会见时间：");
                            sb.append(Global.simpleTime2(model.getMeetTime()));
                            sb.append("</p>");
                            fragmentWeb.viewWeb.loadData(sb.toString(), "text/html;charset=utf-8", "utf-8");
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
    }

    @Override
    public void networkMessage() {

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

    public void message(final String msg, int pageSize) {

        if (noticeType.equals("1")) {
            messageCaseNotice(msg, pageSize);
        } else {
            messageMeeting(msg, pageSize);
        }

    }

    private void messageMeeting(final String msg, int pageSize) {
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
        Api.getInstance().getMeeting(pageNo, pageSize, dateStart, dateEnd, noticeType)
                .compose(this.<ModelMeeting>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelMeeting>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelMeeting model) {
                        ModelNoticeRecords record = new ModelNoticeRecords(true, msg);
                        record.setHeaderTitle(msg);
                        record.setDateStart(finalDateStart);
                        record.setDateEnd(finalDateEnd);
                        record.setNum(String.valueOf(model.getTotal()));

                        List<ModelNoticeRecords> listRecords = new ArrayList<>();
                        listRecords.add(record);
                        for (ModelMeeting.RecordsBean bean : model.getRecords()) {
                            ModelNoticeMessage.RecordsBean data = new ModelNoticeMessage.RecordsBean();
                            data.setId(bean.getId());
                            data.setTitle(bean.getName());
                            data.setContent(bean.getDetail());
                            ModelNoticeRecords records = new ModelNoticeRecords(data);
                            if (msg.equals("今天")) {
                                records.setSelect(true);
                            }
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

    private void messageCaseNotice(final String msg, int pageSize) {
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
        Api.getInstance().getCaseNotice(pageNo, pageSize, dateStart, dateEnd)
                .compose(this.<ModelCaseNotice>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelCaseNotice>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelCaseNotice model) {
                        ModelNoticeRecords record = new ModelNoticeRecords(true, msg);
                        record.setHeaderTitle(msg);
                        record.setDateStart(finalDateStart);
                        record.setDateEnd(finalDateEnd);
                        record.setNum(String.valueOf(model.getTotal()));

                        List<ModelNoticeRecords> listRecords = new ArrayList<>();
                        listRecords.add(record);
                        for (ModelCaseNotice.RecordsBean bean : model.getRecords()) {
                            ModelNoticeMessage.RecordsBean data = new ModelNoticeMessage.RecordsBean();
                            data.setId(bean.getId());
                            data.setTitle(bean.getName());
                            data.setContent(bean.getDetail());
                            ModelNoticeRecords records = new ModelNoticeRecords(data);
                            if (msg.equals("今天")) {
                                records.setSelect(true);
                            }
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
