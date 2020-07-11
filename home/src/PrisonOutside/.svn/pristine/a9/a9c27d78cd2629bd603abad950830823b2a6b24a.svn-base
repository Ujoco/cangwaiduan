package com.shuangtu.prison.home.qfragment.hospital;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.IBackgroundFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.style.LineStyle;
import com.bin.david.form.data.table.TableData;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.model.ModelHospitalSearch;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.table.TableHospConfirm;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QFragmentHospitalSearch extends QFragment {

    private SmartTable<TableHospConfirm> viewTable;
    private RefreshLayout refreshView;
    private int pageNo = 1;
    private final static int pageSize = 10;
    private TableData<TableHospConfirm> tableData;
    private List<TableHospConfirm> oreders;
    private List<Column> columnList;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_hospital_search;
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
        columnTime.setMinWidth((int) (Global.getScreenWidth() * 0.10));
        Column<String> columnProduct = new Column<String>("时间", "time");
        columnProduct.setMinWidth((int) (Global.getScreenWidth() * 0.10));
        columnProduct.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnProduct.setTextAlign(Paint.Align.LEFT);
        columnProduct.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));
        Column<String> columnPrice = new Column<String>("药品", "product");
        columnPrice.setMinWidth((int) (Global.getScreenWidth() * 0.35));
        columnPrice.setTextAlign(Paint.Align.LEFT);
        columnPrice.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnPrice.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));
        Column<String> columnNum = new Column<String>("病情", "details");
        columnNum.setMinWidth((int) (Global.getScreenWidth() * 0.10));
        columnNum.setTextAlign(Paint.Align.LEFT);
        columnNum.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnNum.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));

        columnTime.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x69));
        columnList = new ArrayList<>();

        columnList.add(columnTime);
        columnList.add(columnProduct);
        columnList.add(columnPrice);
        columnList.add(columnNum);

        tableData = new TableData<TableHospConfirm>("用药查询", oreders, columnList);

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
                messageSerach();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                messageSerach();
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
                            oreders.add(oreder);
                            LogUtils.dTag(TAG, bean.toString());
                        }


                        tableData = new TableData<TableHospConfirm>("用药查询", oreders, columnList);
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
