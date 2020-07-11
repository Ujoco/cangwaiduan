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
import com.shuangtu.prison.common.model.ModelHospitalRecord;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.table.TableHostitalRecord;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QFragmentHospitalRecord extends QFragment {

    private SmartTable<TableHostitalRecord> viewTable;
    private RefreshLayout refreshView;
    private int pageNo = 1;
    private final static int pageSize = 10;
    private TableData<TableHostitalRecord> tableData;
    private List<TableHostitalRecord> oreders;
    private List<Column> columnList;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_hospital_record;
    }

    @Override
    public void initView() {
        viewTable = root.findViewById(R.id.table);
        refreshView = root.findViewById(R.id.refreshView);
    }

    @Override
    public void initData() {


        oreders = new ArrayList<TableHostitalRecord>();

        Column<String> columnId = new Column<String>("预约号", "id");
        columnId.setMinWidth((int) (Global.getScreenWidth() * 0.10));
        columnId.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnId.setTextAlign(Paint.Align.LEFT);
        columnId.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));

        Column<String> columnName = new Column<String>("姓名", "name");
        columnName.setMinWidth((int) (Global.getScreenWidth() * 0.10));
        columnName.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnName.setTextAlign(Paint.Align.LEFT);
        columnName.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));


        Column<String> columnIllDetail= new Column<String>("科室", "illDetail");
        columnIllDetail.setMinWidth((int) (Global.getScreenWidth() * 0.10));
        columnIllDetail.setTextAlign(Paint.Align.LEFT);
        columnIllDetail.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnIllDetail.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));

        Column<String> columnTime = new Column<String>("预约时间", "time");
        columnTime.setMinWidth((int) (Global.getScreenWidth() * 0.10));



        Column<String> columnDiagnosiDetail = new Column<String>("病状描述", "diagnosiDetail");
        columnDiagnosiDetail.setMinWidth((int) (Global.getScreenWidth() * 0.10));
        columnDiagnosiDetail.setTextAlign(Paint.Align.LEFT);
        columnDiagnosiDetail.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnDiagnosiDetail.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));

        Column<String> columnDrug = new Column<String>("状态", "drug");
        columnDrug.setMinWidth((int) (Global.getScreenWidth() * 0.11));
        columnDrug.setTextAlign(Paint.Align.LEFT);
        columnDrug.setMinHeight((int) (Global.getScreenWidth() * 0.11));
        columnDrug.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x91));


        columnTime.setMinHeight(Global.mContext.getResources().getDimensionPixelSize(R.dimen.x69));
        columnList = new ArrayList<>();

        columnList.add(columnId);
        columnList.add(columnName);
        columnList.add(columnIllDetail);
        columnList.add(columnDiagnosiDetail);
        columnList.add(columnTime);
        columnList.add(columnDrug);

        tableData = new TableData<TableHostitalRecord>("预约查询", oreders, columnList);

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
        Api.getInstance().getHospitalRecord(pageNo, pageSize)
                .compose(this.<ModelHospitalRecord>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelHospitalRecord>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelHospitalRecord model) {
                        if (refreshView.getState().isHeader) {
                            refreshView.finishRefresh();
                            oreders.clear();
                        }

                        for (ModelHospitalRecord.RecordsBean bean : model.getRecords()) {
                            TableHostitalRecord oreder = new TableHostitalRecord();
                            if (!TextUtils.isEmpty(bean.getCreateTime())) {
                                oreder.setTime(Global.simpleTime4(bean.getCreateTime()));
                            }
                            oreder.setId(bean.getAppointmentId());
                            oreder.setName(bean.getIllUserName());
                            oreder.setIllDetail(bean.getDockerName());
                            oreder.setDiagnosiDetail(bean.getDiagnosiDetail());
                            if (bean.getIllStatus() == 1){
                                oreder.setDrug("待确认");
                            }else if (bean.getIllStatus() == 2){
                                oreder.setDrug("收药确认");
                            }else {
                                oreder.setDrug("诊单完成");
                            }
                            oreders.add(oreder);
                            LogUtils.dTag(TAG, bean.toString());
                        }


                        tableData = new TableData<TableHostitalRecord>("用药查询", oreders, columnList);
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