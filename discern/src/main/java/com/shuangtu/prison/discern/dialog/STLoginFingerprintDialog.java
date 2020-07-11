package com.shuangtu.prison.discern.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuangtu.prison.common.net.FishLoadManager;
import com.shuangtu.prison.common.net.GlideApp;
import com.shuangtu.prison.discern.QDialogFragment;
import com.shuangtu.prison.discern.R;

public class STLoginFingerprintDialog extends QDialogFragment {

    private TextView tvTitle;
    private ImageView ivPingerprint;
    private ImageView ivStatus;
    private TextView tvStatus;
    private Button btnClose;

    @Override
    public int getLayoutRes() {
        return R.layout.login_fingerprint;
    }

    @Override
    public void initView() {
        tvTitle = viewRoot.findViewById(R.id.tvTitle);
        tvTitle.setText("请放置指纹");
        ivPingerprint = viewRoot.findViewById(R.id.ivPingerprint);
        GlideApp.with(getActivity()).asGif().load(R.mipmap.discern_fingerprint).into(ivPingerprint);
        ivStatus = viewRoot.findViewById(R.id.ivStatus);
        tvStatus = viewRoot.findViewById(R.id.tvStatus);
        btnClose = viewRoot.findViewById(R.id.btnClose);
    }

    @Override
    public void initData() {
        statusChange(1);
    }

    @Override
    public void initListener() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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

    private void statusChange(int status){
        if (status == 0) {
            tvStatus.setText("认证中......");
            ivStatus.setVisibility(View.GONE);
        }else if (status == 1) {
            tvStatus.setText("指纹认证通过");
            ivStatus.setVisibility(View.VISIBLE);
        }else {
            tvStatus.setText("指纹认证失败");
            ivStatus.setVisibility(View.GONE);
        }
    }
}
