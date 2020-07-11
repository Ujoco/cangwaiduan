package com.shuangtu.prison.home.qfragment.Inspection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.MonitorscreenAdapter;

import java.util.ArrayList;

//监控信息二级界面
public class QFragmentMonitoringA extends QFragment {


    private RecyclerView mRv_monitor;
    private MonitorscreenAdapter mMonitorscreenAdapter;

    private ArrayList<String> mlist = new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_qfragment_monitoring;
    }

    @Override
    public void initView() {

        mRv_monitor = root.findViewById(R.id.rv_monitor);
    }

    @Override
    public void initData() {
    for(int i = 0;i<30;i++){
        mlist.add("AA");
    }

        LinearLayoutManager SetLayoutManagersupervision = (new LinearLayoutManager(root.getContext()));
        SetLayoutManagersupervision.setOrientation(LinearLayoutManager.VERTICAL);
        mRv_monitor.setLayoutManager(SetLayoutManagersupervision);
        mMonitorscreenAdapter = new MonitorscreenAdapter();
        mMonitorscreenAdapter.setData(mlist);
        mRv_monitor.setAdapter(mMonitorscreenAdapter);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }
}
