package com.shuangtu.prison.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterClassify;
import com.shuangtu.prison.home.model.ModelClassify;
import com.shuangtu.prison.home.socket.QSocketConnectManager;
import com.shuangtu.prison.home.socket.model.ModelConnect;

import java.util.ArrayList;
import java.util.List;

/// 分类
public class HomeClassifyActivity extends HomeEventBusActivity {

    private TextView tvBack;
    private ImageView ivBack;
    private int index;
    private List<ModelClassify> data;
    private AdapterClassify adapter;
    private LinearLayoutManager manager;
    private RecyclerView rlList;

    @Override

    public int getLayoutRes() {
        return R.layout.activity_home_classify;
    }

    @Override
    public void initView() {
        tvErrorConnect = findViewById(R.id.tvErrorConnect);
        tvRootName = findViewById(R.id.tvRootName);
        tvCurrentTime = findViewById(R.id.tvTime);
        tvBack = root.findViewById(R.id.tvBack);
        ivBack = root.findViewById(R.id.ivBack);
        rlList = root.findViewById(R.id.rlList);
    }

    @Override
    public void initData() {
        onMessageConnect(new ModelConnect(QSocketConnectManager.getManager().isConnect()));
        onMessagePrisonInfo(STUserManager.getInstance().getDeviceInfo());

        data = new ArrayList<>();
        index = getIntent().getIntExtra("index", 0);
        if (index == 1) {
            data.add(new ModelClassify("违规录入", R.mipmap.admin_0));
            data.add(new ModelClassify("点名管理", R.mipmap.admin_1));
            data.add(new ModelClassify("监室评分", R.mipmap.admin_2));
            data.add(new ModelClassify("违规监视", R.mipmap.admin_3));
            data.add(new ModelClassify("电源控制", R.mipmap.admin_4));
            data.add(new ModelClassify("多媒体控制", R.mipmap.admin_5));
        } else if (index == 0) {
            data.add(new ModelClassify("安防动态", R.mipmap.tour_0));
            data.add(new ModelClassify("警情通告", R.mipmap.tour_1));
            data.add(new ModelClassify("监室信息", R.mipmap.tour_2));
            data.add(new ModelClassify("监控信息", R.mipmap.tour_3));
            data.add(new ModelClassify("巡更签到", R.mipmap.tour_4));
        }

        adapter = new AdapterClassify(R.layout.activity_home_classify_item, data);
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
        rlList.setAdapter(adapter);
        rlList.setLayoutManager(manager);
    }

    @Override
    public void initListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DisciplineCallManagementActivity.class);
                intent.putExtra("index", index);
                intent.putExtra("click", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void networkMessage() {

    }
}
