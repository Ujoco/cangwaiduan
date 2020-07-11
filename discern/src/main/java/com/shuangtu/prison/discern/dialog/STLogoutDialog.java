package com.shuangtu.prison.discern.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.shuangtu.prison.common.notice.NoticeLogout;
import com.shuangtu.prison.discern.QDialogFragment;
import com.shuangtu.prison.discern.R;

import org.greenrobot.eventbus.EventBus;

public class STLogoutDialog extends QDialogFragment {

    private Button btnClose;
    private Button btnCancel;
    private Button btnLogout;

    @Override
    public int getLayoutRes() {
        return R.layout.login_logout;
    }

    @Override
    public void initView() {
        TextView tvTitle = viewRoot.findViewById(R.id.tvTitle);
        tvTitle.setText("退出登陆");
        btnClose = viewRoot.findViewById(R.id.btnClose);
        btnCancel = viewRoot.findViewById(R.id.btnCancel);
        btnLogout = viewRoot.findViewById(R.id.btnLogout);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                EventBus.getDefault().post(new NoticeLogout(false, 1));
               // ToastUtils.showLong("您已成功退出系统！");
            }
        });
    }

    @Override
    public void networkMessage() {

    }

    @Override
    public boolean registerEventBus() {
        return false;
    }
}
