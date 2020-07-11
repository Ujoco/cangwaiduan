package com.shuangtu.prison.home.qfragment.Inspection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.IBackgroundFormat;
import com.bin.david.form.data.format.draw.ImageResDrawFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.style.LineStyle;
import com.bin.david.form.data.table.TableData;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.model.ModelHospitalSearch;
import com.shuangtu.prison.common.model.ModelNoticeMessage;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterNoticeMessage;
import com.shuangtu.prison.home.fragment.QFragmentWeb;
import com.shuangtu.prison.home.model.ModelMainTitle;
import com.shuangtu.prison.home.model.ModelNoticeRecords;
import com.shuangtu.prison.home.qfragment.notice.QFragmentNoticeList;
import com.shuangtu.prison.home.table.TableHospConfirm;
import com.shuangtu.prison.home.table.TablePolicenotice;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//警情通知
public class QFragmentPolicenotice extends QFragment {

    private SmartTable<TableHospConfirm> viewTable;
    private RefreshLayout refreshView;
    private int pageNo = 1;
    private final static int pageSize = 10;
    private TableData<TableHospConfirm> tableData;
    private List<TableHospConfirm> oreders;
    private List<Column> columnList;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_hospital_confirm;
    }

    @Override
    public void initView() {
        viewTable = root.findViewById(R.id.table);
        refreshView = root.findViewById(R.id.refreshView);
    }

    @Override
    public void initData() {
        oreders = new ArrayList<TableHospConfirm>();
        Column<String> columnTime = new Column<String>("姓名", "name");
        columnTime.setMinWidth((int) (Global.getScreenWidth() * 0.05));
        columnTime.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x69));

        Column<String> columnProduct = new Column<String>("时间", "time");
        columnProduct.setMinWidth((int) (Global.getScreenWidth() * 0.08));
        columnProduct.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnProduct.setTextAlign(Paint.Align.CENTER);

        Column<String> columnBed = new Column<String>("床号", "bed");
        columnProduct.setMinWidth((int) (Global.getScreenWidth() * 0.08));
        columnProduct.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnProduct.setTextAlign(Paint.Align.CENTER);

        columnProduct.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));
        Column<String> columnPrice = new Column<String>("警情", "product");
        columnPrice.setMinWidth((int) (Global.getScreenWidth() * 0.30));
        columnPrice.setTextAlign(Paint.Align.LEFT);
        columnPrice.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnPrice.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));



        int width = Global.mContext.getResources().getDimensionPixelSize(R.dimen.x117);
        int height = Global.mContext.getResources().getDimensionPixelSize(R.dimen.x49);


        columnList = new ArrayList<>();

        columnList.add(columnTime);
        columnList.add(columnProduct);
        columnList.add(columnBed);
        columnList.add(columnPrice);


        tableData = new TableData<TableHospConfirm>("警情通知", oreders, columnList);

        viewTable.setTableData(tableData);
        viewTable.getConfig().setColumnTitleBackground(new IBackgroundFormat() {
            @Override
            public void drawBackground(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(Global.toColorFromString("#1F417C"));
                paint.setStyle(Paint.Style.FILL);
                canvas.drawRect(rect, paint);
            }
        });
        viewTable.getConfig().setColumnTitleStyle(new FontStyle(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x30), Color.WHITE));
        viewTable.getConfig().setMinTableWidth((int) Global.mScreenWidth);
        viewTable.getConfig().setContentGridStyle(new LineStyle(3, R.color.common_blue));
        viewTable.getConfig().setColumnTitleGridStyle((new LineStyle(3, R.color.common_blue)));
        viewTable.getConfig().setShowTableTitle(false);
        viewTable.getConfig().setTextLeftOffset(20);
        viewTable.getConfig().setShowXSequence(false);
        viewTable.getConfig().setShowYSequence(false);
        viewTable.getConfig().setColumnTitleVerticalPadding(18);

    }

    @Override
    public void initListener() {
        refreshView.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
               // messageSerach();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
               //messageSerach();
            }
        });
    }

    @Override
    public void networkMessage() {
        refreshView.autoRefresh();
    }

    private void messageSerach() {
        Api.getInstance().getHospitalSearch(pageNo, pageSize, "1")
                .compose(this.<ModelHospitalSearch>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelHospitalSearch>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelHospitalSearch model) {
                        if (refreshView.getState().isHeader) {
                            refreshView.finishRefresh();
                            oreders.clear();
                        }

                        for (ModelHospitalSearch.RecordsBean bean : model.getRecords()) {
                            TableHospConfirm oreder = new TableHospConfirm();
                            if (!TextUtils.isEmpty(bean.getCreateTime())) {
                                oreder.setTime(Global.simpleTime3(bean.getCreateTime()));
                            }
                            oreder.setName(bean.getIllUserName());
                            oreder.setDetails(bean.getDiagnosiDetail());
                            oreder.setProduct(bean.getDrug());
                            oreder.setStatus(bean.getIllStatus());
                            oreder.setId(bean.getId());
                            oreders.add(oreder);
                            LogUtils.dTag(TAG, bean.toString());
                        }


                        tableData = new TableData<TableHospConfirm>("收药确认", oreders, columnList);
                        viewTable.setTableData(tableData);

                        if (model.getRecords().size() < pageSize) {
                            refreshView.finishLoadMoreWithNoMoreData();
                        } else {
                            refreshView.finishLoadMore();
                        }
                        pageNo += 1;
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                        if (refreshView.getState().isHeader) {
                            refreshView.finishRefresh(false);
                        } else {
                            refreshView.finishLoadMore(false);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
