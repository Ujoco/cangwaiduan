package com.shuangtu.prison.home.qfragment.hospital;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.IBackgroundFormat;
import com.bin.david.form.data.format.draw.ImageResDrawFormat;
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

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QFragmentHospitalConfirm extends QFragment {

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

        int width = Global.mContext.getResources().getDimensionPixelSize(R.dimen.x117);
        int height = Global.mContext.getResources().getDimensionPixelSize(R.dimen.x49);
        final Column<Integer> columnConfirm = new Column<Integer>("状态", "status", new ImageResDrawFormat<Integer>(width, height) {
            @Override
            protected Context getContext() {
                return getActivity();
            }

            @Override
            protected int getResourceID(Integer integer, String value, int position) {
                switch (integer) {
                    case 1:
                        // 已发货 处于确认收货状态
                        return R.mipmap.icon_goods_confirm_check;
                    case 2:
                        // 已完成
                        return R.mipmap.icon_goods_confirm_normal;
                    default:
                        /// 其他状态的时候显示为null
                        return 0;
                }
            }
        });

        columnList = new ArrayList<>();

        columnList.add(columnTime);
        columnList.add(columnProduct);
        columnList.add(columnPrice);
        columnList.add(columnNum);
        columnList.add(columnConfirm);

        tableData = new TableData<TableHospConfirm>("收货确认", oreders, columnList);

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

        tableData.setOnItemClickListener(new TableData.OnItemClickListener() {
            @Override
            public void onClick(Column column, String value, Object o, int col, int row) {
                if (column == columnConfirm) {
                    int position = 0;
                    TableHospConfirm model = null;
                    for (int i = 0; i < oreders.size(); i++) {
                        TableHospConfirm confirm = tableData.getT().get(i);
                        position += 1;
                        if (position >= row) {
                            model = confirm;
                            break;
                        }
                    }
                    LogUtils.dTag(TAG, "当前选中的单元格" + model.toString());
                    messageConfirm(model);
                }
            }
        });
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

    private void messageConfirm(TableHospConfirm model) {
        if (model.getStatus() == 2){
            return;
        }
        final SweetAlertDialog dialog = new SweetAlertDialog(getActivity())
                .setTitleText("提示")
                .setContentText("正在确认收药中");
        dialog.show();
        Api.getInstance().getHospitalConfirm(model.getId())
                .compose(this.<String>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String model) {
                        dialog.dismissWithAnimation();
                        if (!TextUtils.isEmpty(model)) {
                            refreshView.autoRefresh();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("提示")
                                    .setContentText("确认收药成功！")
                                    .show();
                        } else {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("提示")
                                    .setContentText("确认收药失败! 错误代码0")
                                    .show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismissWithAnimation();
                        ToastUtils.showLong(e.getMessage());
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("提示")
                                .setContentText("确认收药失败!" + e.getMessage())
                                .show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
