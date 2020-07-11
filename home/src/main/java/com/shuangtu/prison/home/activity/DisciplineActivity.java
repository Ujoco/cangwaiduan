package com.shuangtu.prison.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.qfragment.Discipline.QFragmentMultimedia;
import com.shuangtu.prison.home.socket.QSocketConnectManager;
import com.shuangtu.prison.home.socket.model.ModelConnect;

public class DisciplineActivity extends HomeEventBusActivity {

    private TextView mTvBack;
    private ImageView mIvBack;
    private ImageView mImg_violationsentry;
    private ImageView mImg_callmanagement;
    private ImageView mImg_multimediacontrol;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_discipline;
    }

    @Override
    public void initView() {
        tvErrorConnect = findViewById(R.id.tvErrorConnect);
        tvRootName = findViewById(R.id.tvRootName);
        tvCurrentTime = findViewById(R.id.tvTime);
        mTvBack = findViewById(R.id.tvBack);
        mIvBack = findViewById(R.id.ivBack);


        //违规录入
        mImg_violationsentry = findViewById(R.id.img_violationsentry);
        //点名管理
        mImg_callmanagement = findViewById(R.id.img_callmanagement);
        //多媒体控制
        mImg_multimediacontrol = findViewById(R.id.img_multimediacontrol);

    }

    @Override
    public void initData() {

        onMessageConnect(new ModelConnect(QSocketConnectManager.getManager().isConnect()));
        onMessagePrisonInfo(STUserManager.getInstance().getDeviceInfo());

        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBack();
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBack();
            }
        });


        mImg_violationsentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisciplineActivity.this, DisciplineCallManagementActivity.class);

                intent.putExtra("fragment", 0);

                startActivity(intent);
                //  LogUtils.d("跳转过去");
            }
        });


        mImg_callmanagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisciplineActivity.this, DisciplineCallManagementActivity.class);
                intent.putExtra("fragment", 1);

                startActivity(intent);
            }
        });


        mImg_multimediacontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisciplineActivity.this, QFragmentMultimedia.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }

    public void clickBack() {
        finish();

    }

//    getTitles


}

