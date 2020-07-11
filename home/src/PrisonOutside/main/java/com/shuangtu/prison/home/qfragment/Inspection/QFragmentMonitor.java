package com.shuangtu.prison.home.qfragment.Inspection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.activity.HomeActivity;
import com.shuangtu.prison.home.activity.HomeEventBusActivity;
import com.shuangtu.prison.home.adapter.CallmanagementAdapter;
import com.shuangtu.prison.home.adapter.MonitorAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//监控信息
public class QFragmentMonitor extends QFragment {


    private RecyclerView mRv_monitor;
    private MonitorAdapter mAdapter;
    //模拟数据
    ArrayList<String> mlist   =new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_qfragment_monitor;
    }

    @Override
    public void initView() {
      mRv_monitor = root.findViewById(R.id.rv_monitor);
    }

    @Override
    public void initData() {
        for(int i =0;i<30;i++){
            mlist.add("a");
        }
        GridLayoutManager mLayoutManager = new GridLayoutManager(root.getContext(),5,GridLayoutManager.VERTICAL,false);
        mRv_monitor.setLayoutManager(mLayoutManager);
        //适配器
        mAdapter = new MonitorAdapter();
        //虚拟数据 服务器接口还没做好
        mAdapter.setdata(mlist);
        mRv_monitor.setAdapter(mAdapter);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }
}
