package com.shuangtu.prison.home.qfragment.hospital;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuangtu.prison.common.model.ModelGoodsClassify;
import com.shuangtu.prison.common.model.ModelHospitalOrder;
import com.shuangtu.prison.common.model.ModelHospitalOrderClassify;
import com.shuangtu.prison.common.model.ModelHospitalOrderList;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterGoodsListClassify;
import com.shuangtu.prison.home.adapter.AdapterHospitalOrderList;
import com.shuangtu.prison.home.adapter.OnCheckValueChangeListener;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QFragmentHospitalOrder extends QFragment {

    private RecyclerView rlClassify;
    private RecyclerView rlList;
    private AdapterGoodsListClassify adapterClassify;
    private LinearLayoutManager managerClassify;
    private RefreshLayout refreshView;
    private int pageNo = 1;
    private final static int pageSize = 10;
    private AdapterHospitalOrderList adapterList;
    private LinearLayoutManager managerList;
    private HashMap<String, List<ModelHospitalOrderList.RecordsBean>> map;
    private TextView tvSelectList;
    private ImageView ivOrder;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_hospital_order;
    }

    @Override
    public void initView() {
        rlClassify = root.findViewById(R.id.rlClassify);
        rlList = root.findViewById(R.id.rlList);
        refreshView = root.findViewById(R.id.refreshView);
        tvSelectList = root.findViewById(R.id.tvSelectList);
        ivOrder = root.findViewById(R.id.ivOrder);
    }

    @Override
    public void initData() {
        map = new HashMap<>();

        adapterClassify = new AdapterGoodsListClassify(R.layout.fragment_goods_classify_item, null);
        managerClassify = new LinearLayoutManager(getContext().getApplicationContext());
        rlClassify.setLayoutManager(managerClassify);
        rlClassify.setAdapter(adapterClassify);

        adapterList = new AdapterHospitalOrderList(R.layout.fragment_hospital_order_list, null);
        managerList = new LinearLayoutManager(getContext().getApplicationContext());
        rlList.setLayoutManager(managerList);
        rlList.setAdapter(adapterList);
    }

    @Override
    public void initListener() {
        adapterClassify.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapterClassify.setSelectIndex(position);
                adapter.notifyDataSetChanged();
                adapterList.setNewData(new ArrayList<ModelHospitalOrderList.RecordsBean>());
                adapterList.notifyDataSetChanged();
                pageNo = 1;
                refreshView.autoRefresh();
            }
        });
        refreshView.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                messageList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                messageList();
            }
        });
        adapterList.setOnCheckValueChangeListener(new OnCheckValueChangeListener() {
            @Override
            public void changeValue(AdapterHospitalOrderList adapter) {
                StringBuilder stringBuilder = new StringBuilder();
                String value = adapterClassify.getData().get(adapterClassify.getSelectIndex()).getValue();
                List<ModelHospitalOrderList.RecordsBean> list = new ArrayList<>();
                for (ModelHospitalOrderList.RecordsBean bean : adapter.getData()) {
                    if (bean.isSelect()) {
                        ModelHospitalOrderList.RecordsBean recordsBean = new ModelHospitalOrderList.RecordsBean();
                        recordsBean.setSelect(true);
                        recordsBean.setId(bean.getId());
                        recordsBean.setName(bean.getName());
                        list.add(recordsBean);
                    }
                }
                map.put(value, list);

                stringBuilder.append("已选择：");

                int count = 0;
                for (ModelGoodsClassify classify : adapterClassify.getData()) {
                    List<ModelHospitalOrderList.RecordsBean> array = map.get(classify.getValue());
                    if (array == null || array.size() == 0) {
                        continue;
                    }
                    for (ModelHospitalOrderList.RecordsBean bean : array) {
                        if (bean.isSelect()) {
                            stringBuilder.append(bean.getName() + ",");
                            count += 1;
                        }
                    }
                }
                if (count > 0) {
                    String text = stringBuilder.toString();
                    tvSelectList.setText(text.substring(0, text.length() - 1));
                } else {
                    tvSelectList.setText(stringBuilder.toString());
                }

            }
        });
        ivOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = tvSelectList.getText().toString().replace("已选择：", "");
                if (TextUtils.isEmpty(value)) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("提示")
                            .setContentText("预约医生病情不能为空!")
                            .show();
                    return;
                }
                messageSubmit(value);

            }
        });
    }

    @Override
    public void networkMessage() {
        refreshView.autoRefresh();
    }

    private void messageClassify() {
        Api.getInstance().getHospitalOrderClassify(1, pageSize)
                .compose(this.<ModelHospitalOrderClassify>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelHospitalOrderClassify>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelHospitalOrderClassify model) {
                        List<ModelGoodsClassify> data = new ArrayList<>();
                        for (ModelHospitalOrderClassify.RecordsBean bean : model.getRecords()) {
                            ModelGoodsClassify classify = new ModelGoodsClassify();
                            classify.setText(bean.getName());
                            classify.setValue(bean.getId());
                            data.add(classify);
                        }
                        LogUtils.dTag("classify data:" + TAG, data.size());
                        adapterClassify.setNewData(data);
                        if (refreshView.getState().isHeader) {
                            messageList();
                        } else {
                            refreshView.autoRefresh();
                        }

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

    private void messageList() {
        if (adapterClassify.getData().size() == 0) {
            messageClassify();
            return;
        }
        final String value = adapterClassify.getData().get(adapterClassify.getSelectIndex()).getValue();
        Api.getInstance().getHospitalOrderList(pageNo, pageSize, value)
                .compose(this.<ModelHospitalOrderList>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelHospitalOrderList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelHospitalOrderList model) {
                        List<ModelHospitalOrderList.RecordsBean> array = map.get(value);
                        if (array != null && array.size() > 0) {
                            for (ModelHospitalOrderList.RecordsBean bean : model.getRecords()) {
                                if (array.contains(bean)) {
                                    changeSelect(bean, array);
                                }
                            }
                        }

                        if (refreshView.getState().isHeader) {
                            adapterList.setNewData(model.getRecords());
                            refreshView.finishRefresh();
                        } else {
                            adapterList.addData(model.getRecords());
                        }
                        if (model.getRecords().size() < pageSize) {
                            refreshView.finishLoadMoreWithNoMoreData();
                        } else {
                            refreshView.finishLoadMore();
                        }

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

    private void messageSubmit(String value) {
        Api.getInstance().getHospitalOrder(value)
                .compose(this.<ModelHospitalOrder>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelHospitalOrder>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelHospitalOrder model) {
                        map.clear();
                        for (ModelHospitalOrderList.RecordsBean bean : adapterList.getData()) {
                            bean.setSelect(false);
                        }
                        adapterList.notifyDataSetChanged();
                        tvSelectList.setText("已选择：");
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("提示")
                                .setContentText("预约医生成功!")
                                .show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("提示")
                                .setContentText("预约医生失败!")
                                .show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void changeSelect(ModelHospitalOrderList.RecordsBean bean, List<ModelHospitalOrderList.RecordsBean> list) {

        for (ModelHospitalOrderList.RecordsBean each : list) {

            if (bean.getId().equals(each.getId())) {

                bean.setSelect(each.isSelect());
                return;
            }
        }
        /// 如果id发生变化...

    }
}
