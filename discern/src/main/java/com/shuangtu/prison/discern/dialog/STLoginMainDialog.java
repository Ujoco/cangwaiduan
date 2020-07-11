package com.shuangtu.prison.discern.dialog;

import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.LogUtils;
import com.shuangtu.prison.discern.QDialogFragment;
import com.shuangtu.prison.discern.R;

public class STLoginMainDialog extends QDialogFragment {

    private ConstraintLayout btnFace;
    private ConstraintLayout btnFingerprint;
    private Button btnClose;

    @Override
    public int getLayoutRes() {
        return R.layout.login_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        btnFace = viewRoot.findViewById(R.id.btnFace);
        btnFingerprint = viewRoot.findViewById(R.id.btnFingerprint);
        btnClose = viewRoot.findViewById(R.id.btnClose);
    }

    @Override
    public void initListener() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.aTag(TAG, "点击了人脸登陆");
                dismiss();
                STLoginFaceDialog dialogFingerprint = new STLoginFaceDialog();
                dialogFingerprint.show(getActivity().getSupportFragmentManager(), "loginFace");
            }
        });

        btnFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.aTag(TAG, "点击了指纹登陆");
                dismiss();
                STLoginFingerprintDialog dialogFingerprint = new STLoginFingerprintDialog();
                dialogFingerprint.show(getActivity().getSupportFragmentManager(), "loginFingerprint");
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
